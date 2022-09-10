<!-- eslint-disable vue/require-v-for-key -->
<template>
    <div id="objectBuyers">
      <input type="search" placeholder="Pretrazi kupce" v-model="search" style="margin-bottom: 1rem">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Ime</th>
                    <th scope="col">Prezime</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="buyer in SearchBuyers" v-if="!buyer.deleted">
                    <td>{{buyer.name}}</td>
                    <td>{{buyer.surname}}</td>
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
      buyers: [],
      search: ''
    }
  },
  mounted () {
    console.log(this.user)
    const axios = require('axios')
    axios.post('http://localhost:8082/rest/getObjectBuyers/', {object: this.object}).then(response => {
      this.buyers = response.data
    })
  },
  methods: {
    goBack: function () {
      this.$router.push({name: 'manageObject', params: {user: this.user}})
    }
  },
  computed: {
    SearchBuyers: function () {
      let val = this.search.toLowerCase()
      return this.buyers.filter((buyer) => {
        if ((buyer.name.toLowerCase() + ' ' + buyer.surname.toLowerCase()).includes(val)) {
          return true
        }
      })
    }
  }
}
</script>

  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #objectBuyers{
    margin: 20px;
  }
</style>
