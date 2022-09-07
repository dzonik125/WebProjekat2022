<template>
    <div id="manageObject">
        <section style="background-color: #eee;">
  <div class="container py-5">

    <div class="row">
      <div class="col-lg-4">
        <div class="card mb-4" :style="'background-image: url(' + sportObject.imageRelLocation + '); background-size: cover; background-position: center;'">
          <div class="card-body text-center">
            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
              class="rounded-circle img-fluid" style="width: 150px; visibility: hidden;">
            <h5 class="my-3" style="visibility: hidden;">John Smith</h5>
            <p class="text-muted mb-1" style="visibility: hidden;">Full Stack Developer</p>
            <p class="text-muted mb-4" style="visibility: hidden;">Bay Area, San Francisco, CA</p>
            <div class="d-flex justify-content-center mb-2" style="visibility: hidden;">
              <button type="button" class="btn btn-primary" style="visibility: hidden;">Follow</button>
              <button type="button" class="btn btn-outline-primary ms-1" style="visibility: hidden;">Message</button>
            </div>
          </div>
        </div>
        <div class="card mb-4 mb-lg-0">
          <div class="card-body p-0">
            <ul class="list-group list-group-flush rounded-3">
              <li class="list-group-item justify-content-between align-items-center p-3 baton">
                <router-link :to="{name: 'addTraining', params: {object: sportObject.name, user: user}}" class="buttonText" v-if="sportObject.name">
                <p class="mb-0 text-center" >Dodaj trening</p>
                </router-link>
              </li>
              <li class="list-group-item justify-content-between align-items-center p-3 baton">
                <router-link :to="{ name: 'ObjectCoaches', params: { object: sportObject.name, user: user}}" class="buttonText" v-if="sportObject.name">
                <p class="mb-0 text-center">Treneri</p>
                </router-link>
              </li>
              <li class="list-group-item justify-content-between align-items-center p-3 baton">
                <router-link :to="'/'" class="buttonText">
                <p class="mb-0 text-center">Nazad</p>
                </router-link>
            </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="col-lg-8">
        <div class="card mb-4">
          <div class="card-body">
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0">Naziv objekta</p>
              </div>
              <div class="col-sm-9">
                <p class="text-muted mb-0">{{sportObject.name}}</p>
              </div>
            </div>
            <hr>
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0">Tip objekta</p>
              </div>
              <div class="col-sm-9">
                <p class="text-muted mb-0">{{convertStrings(sportObject.objectType)}}</p>
              </div>
            </div>
            <hr>
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0">Usluge</p>
              </div>
              <div class="col-sm-9">
                <p class="text-muted mb-0">{{sportObject.services}}</p>
              </div>
            </div>
            <hr>
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0">Radno vreme</p>
              </div>
              <div class="col-sm-9">
                <p class="text-muted mb-0">{{sportObject.workingHours}}</p>
              </div>
            </div>
            <hr>
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0">Adresa</p>
              </div>
              <div class="col-sm-9">
                <p class="text-muted mb-0" v-if="fetched">{{sportObject.location.adress.street + ' ' + sportObject.location.adress.sNum + ',' + ' ' + sportObject.location.adress.postCode + ' ' + sportObject.location.adress.city}}</p>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 whiteSpace" v-for="(training, index) in trainings" :key=index>
            <div class="card mb-4 mb-md-0 whiteSpace" style="min-width: 300px; min-height: 695px;">
              <div class="card-body">
                <p class="mb-4"><span class="text-primary font-italic me-1">Trening</span> {{training.name}}
                </p>
                <span style="display: none;" :name="'trgID' + index">{{training.id}}</span>
                <button class="btn position-absolute top-0 end-0 mt-2" style="display: none;" v-on:click="editTraining($event, index)">Potvrdi</button>
                <button class="btn position-absolute top-0 start-50 mt-2" style="display: none;" v-on:click="cancelEdit($event, index)">Otkazi</button>
                <button class="btn position-absolute top-0 end-0 mt-2" v-on:click="editCall($event, index)">Izmeni</button>
                <img :src="training.imageLocation" alt="" style="height: 15rem; width: 100%; object-fit: cover; margin-bottom: 10px;">
                <p class="mb-1" style="font-size: .77rem;">Tip treninga</p>
                <div class="input-group input-group-sm mb-3" :name="'input' + index" style="display: none;">
                    <select class="form-control" :name="'inp' + index" aria-label="Small" aria-describedby="inputGroup-sizing-sm" >
                        <option value="" selected disabled hidden>Izaberite jedan</option>
                        <option value="GROUP">Grupni</option>
                        <option value="PERSONAL">Personalni</option>
                        <option value="GYM">Teretana</option>
                    </select>
                </div>
                <p class="text-muted mb-0" :name="'trgType' + index">{{convertStrings2(training.trainingType)}}</p>
                <p class="mt-4 mb-1" style="font-size: .77rem;">Trajanje</p>
                <div class="input-group input-group-sm mb-3" :name="'inputt' + index" style="display: none;">
                    <input type="text" class="form-control" :name="'inpp' + index" aria-label="Small" aria-describedby="inputGroup-sizing-sm">
                </div>
                <p class="text-muted mb-0" :name="'duration' + index">{{training.duration}} minuta</p>
                <p class="mt-4 mb-1" style="font-size: .77rem;">Opis treninga</p>
                <div class="input-group input-group-sm mb-3" :name="'inputtt' + index" style="display: none;">
                    <input type="text" class="form-control" :name="'inppp' + index" aria-label="Small" aria-describedby="inputGroup-sizing-sm">
                </div>
                <p class="text-muted mb-0" :name="'description' + index">{{training.description}}</p>
                <p class="mt-4 mb-1" style="font-size: .77rem;">Trener</p>
                <p class="text-muted mb-0">{{training.coach}}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
    </div>
  </template>

<script>
export default {
  props: ['user'],
  data () {
    return {
      sportObject: {},
      fetched: false,
      trainings: [],
      coaches: []
    }
  },
  mounted () {
    const axios = require('axios')
    axios.post('http://localhost:8082/rest/getManagerObject/', {username: this.user}).then(response => {
      if (response.data == null) {
        this.sportObject = {}
        this.fetched = false
      } else {
        this.sportObject = response.data
        this.fetched = true
        axios.post('http://localhost:8082/rest/getTrainingsForObject/', {object: this.sportObject.name}).then(response => {
          if (response.data == null) {
            console.log('No trainings')
            this.trainings = []
          } else {
            this.trainings = response.data
            this.trainings.forEach(element => {
              axios.post('http://localhost:8082/rest/getCoachName/', {coach: element.coach}).then(response => {
                console.log(response.data)
                element.coach = response.data
              })
            })
          }
        })
      }
    })
  },
  updated () {
  },
  methods: {
    convertStrings: function (toConvert) {
      if (toConvert === 'POOL') {
        return 'Bazen'
      } else if (toConvert === 'GYM') {
        return 'Teretana'
      } else if (toConvert === 'SPORTCENTRE') {
        return 'Sportski centar'
      } else if (toConvert === 'DANCESTUDIO') {
        return 'Plesni studio'
      }
    },
    convertStrings2: function (toConvert) {
      if (toConvert === 'PERSONAL') {
        return 'Personalni'
      } else if (toConvert === 'GROUP') {
        return 'Grupni'
      } else if (toConvert === 'GYM') {
        return 'Teretana'
      }
    },
    editCall: function (e, i) {
      let btn1 = e.target.previousElementSibling
      let btn2 = btn1.previousElementSibling
      e.target.style.display = 'none'
      btn1.style.display = 'inline-block'
      btn2.style.display = 'inline-block'
      let element1 = document.getElementsByName('trgType' + i)[0]
      element1.style.display = 'none'
      let element2 = document.getElementsByName('duration' + i)[0]
      element2.style.display = 'none'
      let element3 = document.getElementsByName('description' + i)[0]
      element3.style.display = 'none'
      let input1 = document.getElementsByName('input' + i)[0]
      input1.style.display = 'block'
      let inp1 = document.getElementsByName('inp' + i)[0]
      inp1.style.width = '100%'
      let input2 = document.getElementsByName('inputt' + i)[0]
      input2.style.display = 'block'
      let inp2 = document.getElementsByName('inpp' + i)[0]
      inp2.style.width = '100%'
      let input3 = document.getElementsByName('inputtt' + i)[0]
      input3.style.display = 'block'
      let inp3 = document.getElementsByName('inppp' + i)[0]
      inp3.style.width = '100%'
    },
    cancelEdit: function (e, i) {
      let btn1 = e.target.nextElementSibling
      let btn2 = e.target.previousElementSibling
      e.target.style.display = 'none'
      btn1.style.display = 'inline-block'
      btn2.style.display = 'none'
      let element1 = document.getElementsByName('trgType' + i)[0]
      element1.style.display = 'block'
      let element2 = document.getElementsByName('duration' + i)[0]
      element2.style.display = 'block'
      let element3 = document.getElementsByName('description' + i)[0]
      element3.style.display = 'block'
      let input1 = document.getElementsByName('input' + i)[0]
      input1.style.display = 'none'
      let inp1 = document.getElementsByName('inp' + i)[0]
      inp1.style.width = '100%'
      let input2 = document.getElementsByName('inputt' + i)[0]
      input2.style.display = 'none'
      let inp2 = document.getElementsByName('inpp' + i)[0]
      inp2.style.width = '100%'
      let input3 = document.getElementsByName('inputtt' + i)[0]
      input3.style.display = 'none'
      let inp3 = document.getElementsByName('inppp' + i)[0]
      inp3.style.width = '100%'
    },
    editTraining: function (e, i) {
      let input1 = document.getElementsByName('inp' + i)[0]
      let input2 = document.getElementsByName('inpp' + i)[0]
      let input3 = document.getElementsByName('inppp' + i)[0]
      let index = document.getElementsByName('trgID' + i)[0]
      const axios = require('axios')
      axios.post('http://localhost:8082/rest/editTraining/', {type: input1.value, duration: input2.value, description: input3.value, id: index.innerText}).then(response => {
        if (response.data === 200) {
          window.location.reload()
        } else {
          console.log('Nesto nije u redu')
        }
      })
    }
  }
}
</script>

  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
    .baton{
        cursor: pointer;
    }
    .baton:hover{
        border: 1px solid black;
    }

    .buttonText{
        text-decoration: none;
        color: black;
    }

    .whiteSpace::after{
        content: "\a";
        white-space: pre;
    }
  </style>
