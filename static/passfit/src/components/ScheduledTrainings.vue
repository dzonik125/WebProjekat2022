<!-- eslint-disable vue/require-v-for-key -->
<template>
    <div id="schedTrg">
        <input type="search" placeholder="Pretrazi treninge" v-model="search" style="margin-bottom: 1rem">
        <select name="" id="" v-model="sort">
            <option value="" selected disabled hidden>Sortiraj po</option>
            <option value="nOp">Naziv sportskog objekta ↓</option>
            <option value="nRa">Naziv sportskog objekta ↑</option>
            <option value="dOp">Datumu treniranja ↓</option>
            <option value="dRa">Datumu treniranja ↑</option>
        </select>
        <select name="" id="" v-model="filter1">
            <option value="" selected disabled hidden>Filtriraj po tipu sportskog centra</option>
            <option value="trt">Teretane</option>
            <option value="spc">Sportske centre</option>
            <option value="ps">Plesne studije</option>
            <option value="b">Bazene</option>
        </select>
        <p display="inline-block">Od: <input type="date" name="" id="" v-model="startDate"></p>
        <p>Do: <input type="date" name="" id="" v-model="endDate"></p>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Naziv treninga</th>
                    <th scope="col">Datum</th>
                    <th scope="col">Objekat</th>
                    <th>Otkazivanje</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="training in Filtered1" v-if="!training.sportObject.deleted">
                    <td>{{training.name}}</td>
                    <td>{{training.appointmentDate}}</td>
                    <td>{{training.sportObject.name}}</td>
                    <td v-if="checkIfEligible(training.appointmentDate)"><button v-on:click="cancelTraining(training.name, training.appointmentDate)">Otkazi</button></td>
                </tr>
            </tbody>
        </table>
        <div class="d-flex justify-content-center"><button class="btn" v-on:click.prevent="goBack()">Nazad</button></div>
    </div>
</template>

<script>
export default {
  props: ['user'],
  data () {
    return {
      trainings: [],
      search: '',
      startDate: '',
      endDate: '',
      sort: '',
      filter1: ''
    }
  },
  mounted () {
    const axios = require('axios')
    axios.post('http://localhost:8082/rest/getScheduledTrainingsForCoach/', {coach: this.user}).then(response => {
      this.trainings = response.data
      console.log(this.trainings)
    })
  },
  methods: {
    goBack: function () {
      this.$router.push({name: 'Index'})
    },
    checkIfEligible: function (date) {
      var today = new Date()
      var toCheck = new Date(date)
      var diff = toCheck.getTime() - today.getTime()
      var diffInDays = diff / (1000 * 3600 * 24)
      if (diffInDays < 2) {
        return false
      }
      return true
    },
    cancelTraining: function (name, date) {
      var tzoffset = (new Date()).getTimezoneOffset() * 60000
      var dt = new Date(date)
      dt = new Date(dt - tzoffset)
      dt = dt.toISOString().slice(0, dt.toISOString().lastIndexOf(':'))
      const axios = require('axios')
      axios.post('http://localhost:8082/rest/cancelTraining/', {username: this.user, name: name, date: dt}).then(response => {
        if (response.data === 200) {
          window.alert('Uspesno ste otkazali trening')
          window.location.reload()
        } else {
          window.alert('Doslo je do greske')
        }
      })
    }
  },
  computed: {
    SearchTrainings: function () {
      let val = this.search.toLowerCase()
      return this.trainings.filter((training) => {
        if (training.sportObject.name.toLowerCase().includes(val)) {
          return true
        }
      })
    },
    SearchByDate: function () {
      let start = this.startDate
      let end = this.endDate
      let s = new Date(start)
      let e = new Date(end)
      if (start === '' && end === '') {
        return this.SearchTrainings
      }
      if (start !== '' && end !== '') {
        return this.SearchTrainings.filter((training) => {
          let check = new Date(training.appointmentDate)
          if (check > s && check < e) {
            return true
          }
          return false
        })
      }
      if (start !== '' && end === '') {
        console.log('gas')
        return this.SearchTrainings.filter((training) => {
          let check = new Date(training.appointmentDate)
          if (check > s) {
            return true
          }
          return false
        })
      }
      return this.SearchTrainings.filter((training) => {
        let check = new Date(training.appointmentDate)
        if (check < e) {
          return true
        }
        return false
      })
    },
    Sorted: function () {
      if (this.sort === '') {
        return this.SearchByDate
      }
      if (this.sort === 'nOp') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.SearchByDate.sort(function (a, b) {
          if (a.sportObject.name > b.sportObject.name) {
            return 1
          }
          if (a.sportObject.name < b.sportObject.name) {
            return -1
          }
          return 0
        })
      }
      if (this.sort === 'nRa') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.SearchByDate.sort(function (a, b) {
          if (a.sportObject.name > b.sportObject.name) {
            return -1
          }
          if (a.sportObject.name < b.sportObject.name) {
            return 1
          }
          return 0
        })
      }
      if (this.sort === 'dRa') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.SearchByDate.sort(function (a, b) {
          var d1 = new Date(a.appointmentDate)
          var d2 = new Date(b.appointmentDate)
          if (d1 > d2) {
            return 1
          }
          if (d1 < d2) {
            return -1
          }
          return 0
        })
      }
      if (this.sort === 'dOp') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.SearchByDate.sort(function (a, b) {
          var d1 = new Date(a.appointmentDate)
          var d2 = new Date(b.appointmentDate)
          if (d1 > d2) {
            return -1
          }
          if (d1 < d2) {
            return 1
          }
          return 0
        })
      }
    },
    Filtered1: function () {
      if (this.filter1 === '') {
        return this.Sorted
      }
      if (this.filter1 === 'trt') {
        return this.Sorted.filter((element) => {
          if (element.sportObject.objectType === 'GYM') {
            return true
          }
          return false
        })
      }
      if (this.filter1 === 'spc') {
        return this.Sorted.filter((element) => {
          if (element.sportObject.objectType === 'SPORTCENTRE') {
            return true
          }
          return false
        })
      }
      if (this.filter1 === 'b') {
        return this.Sorted.filter((element) => {
          if (element.sportObject.objectType === 'POOL') {
            return true
          }
          return false
        })
      }
      if (this.filter1 === 'ps') {
        return this.Sorted.filter((element) => {
          if (element.sportObject.objectType === 'DANCESTUDIO') {
            return true
          }
          return false
        })
      }
    }
  }
}
</script>

  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #schedTrg{
        margin: 4rem;
    }

    .Text{
        cursor: pointer;
        color: black;
        text-decoration: none;
    }
</style>
