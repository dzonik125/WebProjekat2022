<template>
    <div id="objectView">
        <section style="background-color: #eee; min-height: 100vh;">
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
          <div class="col-md-6 whiteSpace" v-if="!training.deleted" v-for="(training, index) in trainings" :key=index>
            <div class="card mb-4 mb-md-0 whiteSpace" style="min-width: 300px; min-height: 695px;">
              <div class="card-body">
                <p class="mb-4"><span class="text-primary font-italic me-1">Trening</span> {{training.name}}
                </p>
                <button class="btn position-absolute top-0 end-0 mt-2" v-if="userType === 'BUYER'" v-on:click="doTheTraining(training.name, training.trainingType, index)">Prijavi</button>
                <input type="datetime-local" style="margin-bottom: 5px; display: none;" :name="'dt' + index" v-model="date">
                <span><button class="btn" style="display: none;" :name="'zakazi' + index" v-on:click="makeAppointment(training.coach, training.name)">Zakazi</button></span>
                <span><button class="btn" style="display: none;" :name="'otkazi' + index" v-on:click="cancelMakeApp(index)">Otkazi</button></span>
                <img :src="training.imageLocation" alt="" style="height: 15rem; width: 100%; object-fit: cover; margin-bottom: 10px;">
                <p class="mb-1" style="font-size: .77rem;">Tip treninga</p>
                <p class="text-muted mb-0" :name="'trgType' + index">{{convertStrings2(training.trainingType)}}</p>
                <p class="mt-4 mb-1" style="font-size: .77rem;">Trajanje</p>
                <p class="text-muted mb-0" :name="'duration' + index">{{training.duration}} minuta</p>
                <p class="mt-4 mb-1" style="font-size: .77rem;">Opis treninga</p>
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
  props: ['object', 'userType', 'user'],
  data () {
    return {
      sportObject: {},
      fetched: false,
      trainings: [],
      date: {}
    }
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
    doTheTraining: function (name, type, index) {
      console.log(type)
      if (type !== 'PERSONAL') {
        const axios = require('axios')
        axios.post('http://localhost:8082/rest/train/', {object: this.object, training: name, user: this.user}).then(response => {
          if (response.data === 200) {
            window.alert('Uspesno ste se prijavili u objekat')
            this.$router.push({name: 'Index'})
          } else {
            window.alert('Nemate vise ulazaka za ovaj dan, molimo Vas dodjite sutra.')
          }
        })
      } else {
        let dt = document.getElementsByName('dt' + index)[0]
        dt.style.display = 'inline-block'
        const today = new Date()
        let tomorrow = new Date()
        tomorrow.setDate(today.getDate() + 1)
        dt.min = tomorrow.toISOString().slice(0, tomorrow.toISOString().lastIndexOf(':'))
        let zakazi = document.getElementsByName('zakazi' + index)[0]
        zakazi.style.display = 'inline-block'
        let otkazi = document.getElementsByName('otkazi' + index)[0]
        otkazi.style.display = 'inline-block'
      }
    },
    makeAppointment: function (coach, name) {
      console.log(this.date)
      console.log(coach)
      const axios = require('axios')
      axios.post('http://localhost:8082/rest/scheduleTraining/', {date: this.date, coach: coach, name: name, object: this.object, user: this.user}).then(response => {
        if (response.data === 200) {
          window.alert('Uspesno ste zakazali trening')
          this.$router.push({name: 'Index'})
        } else {
          window.alert('Nesto nije u redu')
        }
      })
    },
    cancelMakeApp: function (index) {
      let dt = document.getElementsByName('dt' + index)[0]
      dt.style.display = 'none'
      let zakazi = document.getElementsByName('zakazi' + index)[0]
      zakazi.style.display = 'none'
      let otkazi = document.getElementsByName('otkazi' + index)[0]
      otkazi.style.display = 'none'
    }
  },
  mounted () {
    console.log(this.userType)
    const axios = require('axios')
    axios.post('http://localhost:8082/rest/GetObjectByName/', {object: this.object}).then(response => {
      if (response.data == null) {
        this.sportObject = {}
        this.fetched = false
      } else {
        this.sportObject = response.data
        this.fetched = true
        axios.post('http://localhost:8082/rest/getTrainingsForObject/', {object: this.object}).then(response => {
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
