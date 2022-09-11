<!-- eslint-disable vue/require-v-for-key -->
<template>
  <div id="createSportObject">
    <form>
      <label for="name">Naziv objekta</label>
      <input type="text" placeholder="Mora da pocinje velikim slovom" v-model="name" id="name">
      <label for="objectType">Tip objekta</label>
      <select id="objectType" v-model="objectType" name="oType">
        <option value="GYM">Teretana</option>
        <option value="POOL">Bazen</option>
        <option value="SPORTCENTRE">Sportski centar</option>
        <option value="DANCESTUDIO">Plesni studio</option>
      </select>
      <label for="services">Usluge</label>
      <input type="text" placeholder="Mora da pocinje velikim slovom" v-model="services" id="services">
      <p>Odaberite lokaciju:</p>
      <GmapMap :center="center" :zoom="18" map-style-id="roadmap" :options="mapOptions"
        style="width: 100vmin; height: 50vmin" ref="mapRef" @click="handleMapClick">
        <GmapMarker :position="marker.position" :clickable="true" :draggable="true" @drag="handleMarkerDrag"
          @click="panToMarker" />
      </GmapMap>
      <button v-on:click.prevent="geolocate()" style="display: block;">Detect Location</button>
      <label for="street">Ulica</label>
      <input type="text" placeholder="Mora pocinjati velikim slovom" v-model="street" id="street">
      <label for="sNum">Broj ulice</label>
      <input type="text" placeholder="Moze biti i broj u formatu 12b" v-model="sNum" id="sNum">
      <label for="city">Mesto</label>
      <input type="text" placeholder="Mora da pocinje velikim slovom" v-model="city" id="city">
      <label for="postcode">Postanski broj</label>
      <input type="text" v-model="postcode" placeholder="Mora biti broj" id="postcode">
      <label for="image">Slika</label>
      <input type="file" v-on:change="filePreview($event)" id="image">
      <label for="workingHours">Radno vreme</label>
      <input type="text" placeholder="Mora biti u formatu HH:mm-HH:mm" v-model="workingHours" id="workingHours">
      <label for="selectManager">Izaberite menadzera</label>
      <select name="selMan" id="selectManager" v-model="selectedManager" v-on:change="showManagerInputs()">
        <option v-for="manager in freeManagers" :value="manager.userName">{{manager.name}}
        </option>
        <option value="CNM">Kreiraj novog menadzera</option>
      </select>
      <label for="username" id="l1" style="display: none;">Korisnicko ime</label>
      <input type="username" style="display: none;" v-model="username" id="username">
      <label for="password" style="display: none;" id="l2">Sifra</label>
      <input type="password" style="display: none;" v-model="password" id="password">
      <label for="name" style="display: none;" id="l3">Ime</label>
      <input type="text" style="display: none;" v-model="nam" id="nam">
      <label for="surname" style="display: none;" id="l4">Prezime</label>
      <input type="text" style="display: none;" id="surname" v-model="surnam">
      <label for="gender" style="display: none;" id="l5">Pol</label>
      <select id="gender" style="display: none;" v-model="gender">
        <option value="M">Musko</option>
        <option value="F">Zensko</option>
        <option value="N">Srednji rod</option>
      </select>
      <label for="birthday" style="display: none;" id="l6">Rodjendan</label>
      <input type="date" style="display: none;" id="birthday" v-model="birthday">
      <input type="submit" v-on:click.prevent="createObject()" value="Potvrdi">
    </form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      name: '',
      objectType: '',
      services: '',
      lat: '',
      long: '',
      street: '',
      sNum: '',
      city: '',
      postcode: '',
      image: '',
      workingHours: '',
      freeManagers: [],
      selectedManager: '',
      username: '',
      password: '',
      nam: '',
      surnam: '',
      gender: '',
      birthday: '',
      marker: { position: { lat: 10, lng: 10 } },
      center: { lat: 10, lng: 10 },

      mapOptions: {
        disableDefaultUI: true
      }
    }
  },
  methods: {
    createObject: function () {
      if (this.objectType === '') {
        window.alert('Odaberite tip objekta')
        return
      }
      if (this.image === '') {
        window.alert('Odaberite sliku')
        return
      }
      if (this.selectedManager === '') {
        window.alert('Odaberite jednu od opcija za menadzera!')
        return
      }
      // eslint-disable-next-line no-useless-escape
      const regex1 = new RegExp('^[a-zA-Z].*[\\s\.]*$')
      // eslint-disable-next-line no-useless-escape
      const regex3 = new RegExp('[0-9]+[A-z]*')
      const regex4 = new RegExp('[0-9]+')
      const regex5 = new RegExp('[0-9]{2}:[0-9]{2}-[0-9]{2}:[0-9]{2}')
      if (!regex1.test(this.name)) {
        window.alert('Niste uneli naziv kako treba')
        return
      }
      if (!regex1.test(this.services)) {
        window.alert('Niste uneli usluge kako treba')
        return
      }
      if (!regex1.test(this.street)) {
        window.alert('Niste uneli ulicu kako treba!')
        return
      }
      if (!regex3.test(this.sNum)) {
        window.alert('Niste uneli broj ulice kako treba')
        return
      }
      if (!regex1.test(this.city)) {
        window.alert('Niste uneli grad kako treba')
        return
      }
      if (!regex4.test(this.postcode)) {
        window.alert('Niste uneli postanski broj kako treba')
        return
      }

      if (!regex5.test(this.workingHours)) {
        window.alert('Unesite vreme u odgovarajucem formatu!')
        return
      }
      const axios = require('axios')
      if (this.selectedManager === 'CNM') {
        const regex6 = new RegExp('^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$')
        // eslint-disable-next-line no-useless-escape
        const regex7 = new RegExp('^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$')
        const regex8 = new RegExp('[A-Z][a-z]{1,}')
        const regex9 = new RegExp('[A-Z][a-z]{1,}')
        if (this.gender === '') {
          window.alert('Popunite pol!')
          return
        }
        if (this.birthday === '') {
          window.alert('Popunite rodjendan!')
          return
        }
        if (!regex6.test(this.username)) {
          window.alert('Niste uneli korisnicko ime kako treba!')
          return
        }
        if (!regex7.test(this.password)) {
          window.alert('Niste uneli sifru kako treba')
          return
        }
        if (!regex8.test(this.nam)) {
          window.alert('Niste uneli ime kako treba')
          return
        }
        if (!regex9.test(this.surnam)) {
          window.alert('Niste uneli prezime kako treba')
          return
        }
        axios.post('http://localhost:8082/rest/createSportObject/', {
          name: this.name,
          objectType: this.objectType,
          services: this.services,
          lat: this.marker.position.lat,
          long: this.marker.position.lng,
          street: this.street,
          sNum: this.sNum,
          city: this.city,
          postcode: this.postcode,
          image: this.image,
          workingHours: this.workingHours,
          manager: this.selectedManager,
          username: this.username,
          password: this.password,
          nam: this.nam,
          surnam: this.surnam,
          gender: this.gender,
          birthday: this.birthday
        }).then(response => {
          if (response.data === 400) {
            window.alert('Takav objekat vec postoji!')
          } else {
            window.alert('Uspesno ste kreirali objekat')
            window.location.href = 'http://localhost:8081/#/'
          }
        })
      } else {
        axios.post('http://localhost:8082/rest/createSportObject/', {
          name: this.name,
          objectType: this.objectType,
          services: this.services,
          lat: this.lat,
          long: this.long,
          street: this.street,
          sNum: this.sNum,
          city: this.city,
          postcode: this.postcode,
          image: this.image,
          workingHours: this.workingHours,
          manager: this.selectedManager
        }).then(response => {
          if (response.data === 400) {
            window.alert('Takav objekat vec postoji!')
          } else {
            window.alert('Uspesno ste kreirali objekat')
            window.location.href = 'http://localhost:8081/#/'
          }
        })
      }
    },
    filePreview: function (event) {
      const reader = new FileReader()
      const file = event.target.files[0]
      let rawImg
      let self = this
      reader.onloadend = (e) => {
        rawImg = e.target.result
        self.image = rawImg
        console.log(self.image)
      }
      reader.readAsDataURL(file)
    },
    showManagerInputs: function () {
      let username = document.getElementById('username')
      let password = document.getElementById('password')
      let name = document.getElementById('nam')
      let surname = document.getElementById('surname')
      let gender = document.getElementById('gender')
      let birthday = document.getElementById('birthday')
      let l1 = document.getElementById('l1')
      let l2 = document.getElementById('l2')
      let l3 = document.getElementById('l3')
      let l4 = document.getElementById('l4')
      let l5 = document.getElementById('l5')
      let l6 = document.getElementById('l6')
      if (this.selectedManager === 'CNM') {
        username.style.display = 'inline-block'
        password.style.display = 'inline-block'
        name.style.display = 'inline-block'
        surname.style.display = 'inline-block'
        gender.style.display = 'inline-block'
        birthday.style.display = 'inline-block'
        l1.style.display = 'inline-block'
        l2.style.display = 'inline-block'
        l3.style.display = 'inline-block'
        l4.style.display = 'inline-block'
        l5.style.display = 'inline-block'
        l6.style.display = 'inline-block'
      } else {
        username.style.display = 'none'
        password.style.display = 'none'
        name.style.display = 'none'
        surname.style.display = 'none'
        gender.style.display = 'none'
        birthday.style.display = 'none'
        l1.style.display = 'none'
        l2.style.display = 'none'
        l3.style.display = 'none'
        l4.style.display = 'none'
        l5.style.display = 'none'
        l6.style.display = 'none'
      }
    },
    geolocate () {
      navigator.geolocation.getCurrentPosition((position) => {
        this.marker.position = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        }

        this.panToMarker()
      })
      console.log(this.marker.position)
    },

    // sets the position of marker when dragged
    handleMarkerDrag (e) {
      this.marker.position = { lat: e.latLng.lat(), lng: e.latLng.lng() }
    },

    // Moves the map view port to marker
    panToMarker () {
      this.$refs.mapRef.panTo(this.marker.position)
      this.$refs.mapRef.setZoom(18)
    },

    // Moves the marker to click position on the map
    handleMapClick (e) {
      this.marker.position = { lat: e.latLng.lat(), lng: e.latLng.lng() }
      console.log(e)
    }
  },
  mounted () {
    this.geolocate()
    const axios = require('axios')
    axios.get('http://localhost:8082/rest/getFreeManagers/').then(response => {
      this.freeManagers = response.data
      console.log(this.freeManagers)
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
input,
select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}

body {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  padding: 20px;
  background: #ffffff;
}

.wrapper {
  max-width: 1090px;
  width: 100%;
  margin: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.wrapper .table {
  background: #fff;
  width: calc(33% - 20px);
  padding: 30px 30px;
  position: relative;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
  border: 4px solid black;
  border-radius: 10px;
}

.table .price-section {
  display: flex;
  justify-content: center;
}

.table .price-area {
  height: 120px;
  width: 120px;
  border-radius: 50%;
  padding: 2px;
}

.price-area .inner-area {
  height: 100%;
  width: 100%;
  border-radius: 50%;
  border: 3px solid #fff;
  line-height: 117px;
  text-align: center;
  color: #fff;
  position: relative;
}

.price-area .inner-area .text {
  font-size: 25px;
  font-weight: 400;
  position: absolute;
  top: -10px;
  left: 17px;
}

.price-area .inner-area .price {
  font-size: 45px;
  font-weight: 500;
  margin-left: 16px;
}

.table .package-name {
  width: 100%;
  height: 2px;
  margin: 35px 0;
  position: relative;
}

.table .package-name::before {
  position: absolute;
  top: 50%;
  left: 50%;
  font-size: 25px;
  font-weight: 500;
  background: #fff;
  padding: 0 15px;
  transform: translate(-50%, -50%);
}

.table .features li {
  margin-bottom: 15px;
  list-style: none;
  display: flex;
  justify-content: space-between;
}

.features li .list-name {
  font-size: 17px;
  font-weight: 400;
}

.features li .icon {
  font-size: 15px;
}

.features li .icon.check {
  color: #2db94d;
}

.features li .icon.cross {
  color: #cd3241;
}

.table .btn {
  width: 100%;
  display: flex;
  margin-top: 35px;
  justify-content: center;
}

.table .btn button {
  width: 80%;
  height: 50px;
  color: #fff;
  font-size: 20px;
  font-weight: 500;
  border: none;
  outline: none;
  border-radius: 25px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.table .btn button:hover {
  border-radius: 5px;
}

.basic .features li::selection {
  background: #ffd861;
}

.basic ::selection,
.basic .price-area,
.basic .inner-area {
  background: #ffd861;
}

.basic .btn button {
  border: 2px solid #ffd861;
  background: #fff;
  color: #ffd861;
}

.basic .btn button:hover {
  background: #ffd861;
  color: #fff;
}

.premium ::selection,
.premium .price-area,
.premium .inner-area,
.premium .btn button {
  background: #a26bfa;
}

.premium .btn button:hover {
  background: #833af8;
}

.ultimate ::selection,
.ultimate .price-area,
.ultimate .inner-area {
  background: #43ef8b;
}

.ultimate .btn button {
  border: 2px solid #43ef8b;
  color: #43ef8b;
  background: #fff;
}

.ultimate .btn button:hover {
  background: #43ef8b;
  color: #fff;
}

.basic .package-name {
  background: #ffecb3;
}

.premium .package-name {
  background: #d0b3ff;
}

.ultimate .package-name {
  background: #baf8d4;
}

.basic .package-name::before {
  content: "Bronze";
}

.premium .package-name::before {
  content: "Silver";
  font-size: 24px;
}

.ultimate .package-name::before {
  content: "Gold";
  font-size: 24px;
}

@media (max-width: 1020px) {
  .wrapper .table {
    width: calc(50% - 20px);
    margin-bottom: 40px;
  }
}

@media (max-width: 698px) {
  .wrapper .table {
    width: 100%;
  }
}

::selection {
  color: #fff;
}

.table .ribbon {
  width: 150px;
  height: 150px;
  position: absolute;
  top: -10px;
  left: -10px;
  overflow: hidden;
}

.table .ribbon::before,
.table .ribbon::after {
  position: absolute;
  content: "";
  z-index: -1;
  display: block;
  border: 7px solid #4606ac;
  border-top-color: transparent;
  border-left-color: transparent;
}

.table .ribbon::before {
  top: 0px;
  right: 15px;
}

.table .ribbon::after {
  bottom: 15px;
  left: 0px;
}

.table .ribbon span {
  position: absolute;
  top: 30px;
  right: 0;
  transform: rotate(-45deg);
  width: 200px;
  background: #a26bfa;
  padding: 10px 0;
  color: #fff;
  text-align: center;
  font-size: 17px;
  text-transform: uppercase;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.12);
}

input[type=submit] {
  margin-top: 1.5rem;
}
</style>
