<template>
  <div id="index">
    <Header v-bind:loggedIn="loggedIn" v-bind:user="this.username" v-bind:userType="this.userType"></Header>
    <MiddleContent v-bind:sportObjects="sportObjects" v-bind:user="this.username" v-bind:userType="this.userType" :key="componentKey"></MiddleContent>
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
      sportObjects: [],
      userType: '',
      componentKey: 0
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
        this.userType = ''
      } else {
        this.loggedIn = true
        this.username = response.data.split('.')[0]
        this.userType = response.data.split('.')[1]
      }
    })

    axios.get('http://localhost:8082/rest/sortObjects/').then(response => {
      this.sportObjects = response.data
    })

    var self = this
    window.setInterval(function () {
      console.log('gas')
      const axios = require('axios')
      axios.get('http://localhost:8082/rest/sortObjects/').then(response => {
        self.sportObjects = response.data
        self.$forceUpdate()
      })
    }, 30000)
  },
  created () {
    window.setInterval(function () {
      const axios = require('axios')
      // axios.get('http://localhost:8082/rest/checkDailyLogs/').then(response => {
      // console.log(response.data)
      // })
      axios.get('http://localhost:8082/rest/checkDailyLogs/').then(response => {
        console.log(response.data)
      })
    }, 5000)
    window.setInterval(function () {
      const axios = require('axios')
      axios.get('http://localhost:8082/rest/checkMembershipActivity/').then(response => {
        console.log(response.data)
      }
      )
    }, 10000)
    window.setInterval(function () {
      const axios = require('axios')
      axios.get('http://localhost:8082/rest/checkIfPersonalTrainingIsCompleted/').then(response => {
        console.log(response.data)
      })
    }, 60000)
  },
  methods: {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #index{
    height: 100vh;
  }
</style>
