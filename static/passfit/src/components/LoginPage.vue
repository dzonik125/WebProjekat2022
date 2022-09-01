<template>
  <div id="login">
    <form>
        <label for="userInput">Username</label>
        <input type="username" v-model="username" name="userIn" id="userInput">
        <label for="passwordInput">Sifra</label>
        <input type="password" v-model="password" id="passwordInput">
        <input type="submit" value="Potvrdi" v-on:click.prevent="LoginUser()">
    </form>
  </div>
</template>

<script>
export default {
  name: 'LoginPage',
  data () {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    LoginUser: function () {
      const axios = require('axios')
      axios.post('http://localhost:8082/rest/loginUser/', {
        username: this.username,
        password: this.password
      }).then(response => {
        if (response.data === 400) {
          window.alert('Uneti podaci nisu registrovani!')
        } else if (response.data === 401) {
          window.alert('Neispravna sifra!')
        } else {
          console.log(response.data)
          console.log(response.data.JWTToken)
          localStorage.setItem('jwt', response.data.JWTToken)
          window.alert('Uspesno logovanje!')
          window.location.href = 'http://localhost:8081/#/'
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
input, select{
   width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}
</style>
