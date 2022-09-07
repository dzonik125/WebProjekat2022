<template>
  <div id="header">
    <button class="btn" v-if="!loggedIn"><router-link class="regText" to="/register">Register</router-link></button>
    <button class="btn" v-if="!loggedIn"><router-link class="loginText" to="/login">Login</router-link></button>
    <button class="btn" v-if="loggedIn" v-on:click.prevent="logOut()">Logout</button>
    <button class="btn" v-if="loggedIn"><router-link class="profileText" :to="'/profileView/' + this.user">Profile</router-link></button>
    <button class="btn" v-if="userType === 'ADMINISTRATOR'"><router-link class="profileText" :to="'/adminPanel/'">Admin Panel</router-link></button>
    <button class="btn" v-if="userType === 'MANAGER'"><router-link class="profileText" :to="'/manageObject/' + this.user">Upravljaj objektom</router-link></button>
    <button class="btn" v-if="userType === 'BUYER'"><router-link class="profileText" :to="{name: 'BuyMembership', params: {user: user}}">Kupi clanarinu</router-link></button>
  </div>
</template>

<script>
export default {
  props: ['loggedIn', 'user', 'userType'],
  data () {
    return {
    }
  },
  methods: {
    logOut: function () {
      const axios = require('axios')
      axios.post('http://localhost:8082/rest/logOut/', {
        username: this.user
      }).then(response => {
        if (response.data === 400) {
          window.alert('Doslo je do greske')
        } else {
          window.alert('Uspesno izlogovan')
          window.localStorage.removeItem('jwt')
          location.reload()
        }
      })
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#header{
    width: 100%;
    height: 5rem;
    background-color: white;
    display: flex;
    flex-direction: row-reverse;
}
.btn{
    margin-right: 2rem;
    /* margin-top: 1rem; */
    /* height: 2rem; */
}

.loginText{
  cursor: pointer;
  color:black;
  text-decoration: none;
}

.regText{
    cursor: pointer;
    color:black;
    text-decoration: none;
}
.regText:visited{
    color: black;
}

.profileText{
  cursor: pointer;
  color: black;
  text-decoration: none;
}

</style>
