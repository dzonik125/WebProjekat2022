<template>
    <div id="userTable">
      <input type="search" placeholder="Pretrazi korisnike" v-model="search" style="margin-bottom: 1rem">
      <select name="" id="" v-model="sort">
            <option value="" selected disabled hidden>Sortiraj po</option>
            <option value="nOp">Imenu ↓</option>
            <option value="nRa">Imenu ↑</option>
            <option value="dOp">Prezimenu ↓</option>
            <option value="dRa">Prezimenu ↑</option>
            <option value="kOp">Korisnickom imenu ↓</option>
            <option value="kRa">Korisnickom imenu ↑</option>
        </select>
        <select name="" id="" v-model="filter1">
            <option value="" selected disabled hidden>Filtriraj po ulozi</option>
            <option value="trt">Administrator</option>
            <option value="spc">Menadzer</option>
            <option value="ps">Kupac</option>
            <option value="b">Trener</option>
        </select>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Username</th>
                    <th scope="col">Password</th>
                    <th scope="col">Ime</th>
                    <th scope="col">Prezime</th>
                    <th scope="col">Obrisan</th>
                    <th scope="col">Brisanje</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(user, index) in Filtered1" :key="index" v-if="loggedUser != user.userName">
                    <td>{{user.userName}}</td>
                    <td>{{user.password}}</td>
                    <td>{{user.name}}</td>
                    <td>{{user.surname}}</td>
                    <td>{{user.deleted}}</td>
                    <td v-if="!user.deleted"><button class="btn" v-on:click="deleteUser($event)">Obrisi</button></td>
                </tr>
            </tbody>
        </table>
        <div class="d-flex justify-content-center"><button class="btn"><router-link  class="Text" :to="'/adminPanel/'">Nazad</router-link></button></div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      allUsers: [],
      loggedUser: '',
      search: '',
      sort: '',
      filter1: ''
    }
  },
  methods: {
    deleteUser: function (event) {
      console.log((event.target.parentElement).parentElement.firstChild.innerHTML)
      let username = (event.target.parentElement).parentElement.firstChild.innerHTML
      const axios = require('axios')
      axios.post('http://localhost:8082/rest/deleteUser/', {username: username}).then(response => {
        if (response.data === 400) {
          console.log('There was an error!')
        } else {
          window.alert('Uspesno ste obrisali korisnika!')
          window.location.reload()
        }
      })
    }
  },
  mounted () {
    const axios = require('axios')
    axios.get('http://localhost:8082/rest/getAllUsers/').then(response => {
      this.allUsers = response.data
      console.log(this.allUsers)
    })

    axios.get('http://localhost:8082/rest/checkIfLogged/', {
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    }).then(response => {
      this.loggedUser = response.data.split('.')[0]
    })
  },
  computed: {
    SearchUsers: function () {
      let val = this.search.toLowerCase()
      return this.allUsers.filter((user) => {
        if ((user.name.toLowerCase() + ' ' + user.surname.toLowerCase()).includes(val)) {
          return true
        }
        if (user.userName.toLowerCase().includes(val)) {
          return true
        }
      })
    },
    Sorted: function () {
      if (this.sort === '') {
        return this.SearchUsers
      }
      if (this.sort === 'nOp') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.SearchUsers.sort(function (a, b) {
          if (a.name > b.name) {
            return 1
          }
          if (a.name < b.name) {
            return -1
          }
          return 0
        })
      }
      if (this.sort === 'nRa') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.SearchUsers.sort(function (a, b) {
          if (a.name > b.name) {
            return -1
          }
          if (a.name < b.name) {
            return 1
          }
          return 0
        })
      }
      if (this.sort === 'dOp') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.SearchUsers.sort(function (a, b) {
          if (a.surname > b.surname) {
            return 1
          }
          if (a.surname < b.surname) {
            return -1
          }
          return 0
        })
      }
      if (this.sort === 'dRa') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.SearchUsers.sort(function (a, b) {
          if (a.surname > b.surname) {
            return -1
          }
          if (a.surname < b.surname) {
            return 1
          }
          return 0
        })
      }
      if (this.sort === 'kOp') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.SearchUsers.sort(function (a, b) {
          if (a.userName > b.userName) {
            return 1
          }
          if (a.userName < b.userName) {
            return -1
          }
          return 0
        })
      }
      if (this.sort === 'kRa') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.SearchUsers.sort(function (a, b) {
          if (a.userName > b.userName) {
            return -1
          }
          if (a.userName < b.userName) {
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
          if (element.userType === 'ADMINISTRATOR') {
            return true
          }
          return false
        })
      }
      if (this.filter1 === 'spc') {
        return this.Sorted.filter((element) => {
          if (element.userType === 'MANAGER') {
            return true
          }
          return false
        })
      }
      if (this.filter1 === 'b') {
        return this.Sorted.filter((element) => {
          if (element.userType === 'COACH') {
            return true
          }
          return false
        })
      }
      if (this.filter1 === 'ps') {
        return this.Sorted.filter((element) => {
          if (element.userType === 'BUYER') {
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
    #userTable{
        margin: 4rem;
    }

    .Text{
        cursor: pointer;
        color: black;
        text-decoration: none;
    }
</style>
