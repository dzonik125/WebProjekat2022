<template>
  <div id="index">
    <Header v-bind:loggedIn="loggedIn" v-bind:user="this.username"></Header>
    <MiddleContent v-bind:sportObjects="sportObjects"></MiddleContent>
  </div>
</template>

<script>
import Header from './Header.vue'
import MiddleContent from './MiddleContent.vue'
export default {
  data () {
    return {
      loggedIn: false,
      username: '',
      sportObjects: []
    }
  },
  components: { Header, MiddleContent },
  mounted () {
    const axios = require('axios')
    axios.get('http://localhost:8082/rest/checkIfLogged/', {
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    }).then(response => {
      if (response.data === 'No user logged in') {
        this.username = ''
        this.loggedIn = false
      } else {
        this.loggedIn = true
        this.username = response.data.split('.')[0]
      }
    })

    axios.get('http://localhost:8082/rest/findAllSportObjects/').then(response => {
      this.sportObjects = response.data
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
