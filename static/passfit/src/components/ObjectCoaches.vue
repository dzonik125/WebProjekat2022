<!-- eslint-disable vue/require-v-for-key -->
<template>
    <div id="objectCoaches">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Ime</th>
                    <th scope="col">Prezime</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="coach in coaches">
                    <td>{{coach.name}}</td>
                    <td>{{coach.surname}}</td>
                </tr>
            </tbody>
        </table>
        <div class="d-flex justify-content-center"><button class="btn" v-on:click.prevent="goBack()">Nazad</button></div>
    </div>
  </template>

<script>
export default {
  props: ['object', 'user'],
  data () {
    return {
      coaches: []
    }
  },
  mounted () {
    const axios = require('axios')
    console.log(this.user)
    axios.post('http://localhost:8082/rest/getCoachByObject/', {object: this.object}).then(response => {
      this.coaches = response.data
    })
  },
  methods: {
    goBack: function () {
      this.$router.push({name: 'manageObject', params: {user: this.user}})
    }
  }
}
</script>

  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
    #objectCoaches{
        margin: 4rem;
    }

    .Text{
        cursor: pointer;
        color: black;
        text-decoration: none;
    }
  </style>
