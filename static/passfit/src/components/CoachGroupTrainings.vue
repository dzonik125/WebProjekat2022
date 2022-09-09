<!-- eslint-disable vue/require-v-for-key -->
<template>
    <div id="coachGrpTrg">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Naziv treninga</th>
                    <th scope="col">Objekat</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="training in trainings">
                    <td>{{training.name}}</td>
                    <td>{{training.sportObject.name}}</td>
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
      trainings: {}
    }
  },
  methods: {
    goBack: function () {
      this.$router.push({name: 'Index'})
    }
  },
  mounted () {
    const axios = require('axios')
    axios.post('http://localhost:8082/rest/getGroupTrainingsForCoach/', {coach: this.user}).then(response => {
      this.trainings = response.data
      console.log(this.trainings)
    })
  }
}
</script>

  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #coachGrpTrg{
        margin: 4rem;
    }
</style>
