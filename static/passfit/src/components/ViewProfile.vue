<template>
    <div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">{{this.username}}</span><span> </span></div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Profil</h4>
                </div>
                <div class="row mt-2">
                    <div class="col-md-6"><label class="labels">Ime</label><input type="text" class="form-control" placeholder="ime" v-model="name"></div>
                    <div class="col-md-6"><label class="labels">Prezime</label><input type="text" class="form-control" v-model="surname" placeholder="prezime"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-12"><label class="labels">Username</label><input type="text" class="form-control" placeholder="username" v-model="username"></div>
                    <div class="col-md-12"><label class="labels">Password</label><input type="text" class="form-control" placeholder="password" v-model="password"></div>
                    <div class="col-md-12"><label class="labels">Datum rodjenja</label><input type="date" class="form-control" placeholder="datum rodjenja" v-model="dateToSet"></div>
                    <div class="col-md-12"><label class="labels">Pol</label><input type="text" class="form-control" placeholder="pol" v-model="gender"></div>
                    <div class="col-md-12"><label class="labels">Tip korisnika</label><input type="text" class="form-control" placeholder="tip korisnika" v-model="userType" readonly></div>
                </div>
                <div class="mt-5 text-center"><button v-on:click="editUser()" class="btn btn-primary profile-button" type="button">Sacuvaj</button><button class="btn btn-primary profile-button" type="button" style="margin-left: 10px" v-on:click="goBack()">Nazad</button></div>
            </div>
        </div>
    </div>
    </div>
</template>

<script>
export default {
  props: ['user'],
  name: 'ViewProfile',
  data () {
    return {
      name: '',
      surname: '',
      username: '',
      password: '',
      birthday: '',
      gender: '',
      userType: '',
      dateToSet: {},
      oldUsername: ''
    }
  },
  methods: {
    goBack: function () {
      window.location.href = 'http://localhost:8081/#/'
    },
    editUser: function () {
      const axios = require('axios')
      axios.post('http://localhost:8082/rest/editUser/', {
        oldUsername: this.oldUsername,
        name: this.name,
        surname: this.surname,
        username: this.username,
        password: this.password,
        birthday: this.birthday,
        gender: this.gender,
        userType: this.userType
      }).then(response => {
        if (response.data === 400) {
          window.alert('Vec postoji korisnik sa tim usernameom!')
        } else {
          window.location.reload()
        }
      })
    }
  },
  mounted () {
    const userValue = this.user
    console.log(userValue)
    const axios = require('axios')
    axios.post('http://localhost:8082/rest/getUser/', {username: userValue}).then(response => {
      this.name = response.data.name
      this.surname = response.data.surname
      this.username = response.data.userName
      this.password = response.data.password
      this.birthday = response.data.birthday
      this.gender = response.data.gender
      this.userType = response.data.userType
      var d = new Date(this.birthday)
      const date = [
        d.getFullYear(),
        ('0' + (d.getMonth() + 1)).slice(-2),
        ('0' + d.getDate()).slice(-2)
      ].join('-')
      this.dateToSet = date
      console.log(date)
      this.oldUsername = response.data.userName
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body {
    background: rgb(99, 39, 120)
}

.form-control:focus {
    box-shadow: none;
    border-color: #BA68C8
}

.profile-button {
    background: rgb(99, 39, 120);
    box-shadow: none;
    border: none
}

.profile-button:hover {
    background: #682773
}

.profile-button:focus {
    background: #682773;
    box-shadow: none
}

.profile-button:active {
    background: #682773;
    box-shadow: none
}

.back:hover {
    color: #682773;
    cursor: pointer
}

.labels {
    font-size: 11px
}

.add-experience:hover {
    background: #BA68C8;
    color: #fff;
    cursor: pointer;
    border: solid 1px #BA68C8
}
</style>
