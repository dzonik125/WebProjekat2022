<template>
  <div id="index">
    <Header v-bind:loggedIn="loggedIn" v-bind:user="user"></Header>
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
      user: '',
      userType: '',
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
        this.loggedIn = false
        this.user = ''
        this.userType = ''
      } else {
        const info = response.data.split('.')
        console.log(response.data.split('.'))
        this.loggedIn = true
        this.user = info[0]
        this.userType = info[1]
        console.log(this.user)
        console.log(this.userType)
      }
    })

    axios.get('http://localhost:8082/rest/findAllSportObjects/').then(response => {
      this.sportObjects = response.data
      console.log(response.data)
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
