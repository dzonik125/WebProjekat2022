<!-- eslint-disable vue/require-v-for-key -->
<template>
    <div id="schedTrg">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Naziv treninga</th>
                    <th scope="col">Datum</th>
                    <th>Otkazivanje</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="training in trainings">
                    <td>{{training.name}}</td>
                    <td>{{training.appointmentDate}}</td>
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
      trainings: []
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
