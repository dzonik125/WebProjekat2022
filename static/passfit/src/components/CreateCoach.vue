<template>
    <div id="createCoach">
    <form>
      <label for="username">Korisnicko ime</label>
      <input type="username" v-model="username" id="username" placeholder="Najmanje 5 karaktera, moze bilo koji karakter">
      <label for="password">Sifra</label>
      <input type="password" v-model="password" id="password" placeholder="Najmanje 4, a najvise 8 karaktera, najmanje 1 broj i 1 slovo">
      <label for="name">Ime</label>
      <input type="text" v-model="nam" id="name" placeholder="Mora da pocinje velikim slovom">
      <label for="surname">Prezime</label>
      <input type="text" id="surname" v-model="surnam" placeholder="Mora da pocinje velikim slovom">
      <label for="gender">Pol</label>
      <select id="gender" v-model="gender">
        <option value="M">Musko</option>
        <option value="F">Zensko</option>
        <option value="N">Srednji rod</option>
      </select>
      <label for="birthday"></label>
      <input type="date" id="birthday" v-model="birthday">
    <input type="submit" v-on:click.prevent="sendInfo()" value="Potvrdi">
    </form>
    </div>
  </template>

<script>
export default {
  data () {
    return {
      username: '',
      password: '',
      nam: '',
      surnam: '',
      gender: '',
      birthday: ''
    }
  },
  methods: {
    sendInfo: function () {
      const regex1 = new RegExp('^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$')
      // eslint-disable-next-line no-useless-escape
      const regex2 = new RegExp('^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$')
      const regex3 = new RegExp('[A-Z][a-z]{1,}')
      const regex4 = new RegExp('[A-Z][a-z]{1,}')
      if (this.gender === '') {
        window.alert('Popunite pol!')
        return
      }
      if (this.birthday === '') {
        window.alert('Popunite rodjendan!')
        return
      }
      console.log(regex2.test(this.password))
      if (!regex1.test(this.username)) {
        window.alert('Niste uneli korisnicko ime kako treba!')
        return
      }
      if (!regex2.test(this.password)) {
        window.alert('Niste uneli sifru kako treba')
        return
      }
      if (!regex3.test(this.nam)) {
        window.alert('Niste uneli ime kako treba')
        return
      }
      if (!regex4.test(this.surnam)) {
        window.alert('Niste uneli prezime kako treba')
        return
      }
      const axios = require('axios')
      axios.post('http://localhost:8082/rest/createCoach/', {
        username: this.username,
        password: this.password,
        nam: this.nam,
        surnam: this.surnam,
        gender: this.gender,
        birthday: this.birthday}).then(response => {
        if (response.data === 400) {
          window.alert('Korisnik sa tim korisnickim imenom vec postoji!')
        } else {
          window.alert('Uspesno ste kreirali trenera!')
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
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap");
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}
body {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  padding: 20px;
  background: #ffffff;
}
.wrapper {
  max-width: 1090px;
  width: 100%;
  margin: auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
.wrapper .table {
  background: #fff;
  width: calc(33% - 20px);
  padding: 30px 30px;
  position: relative;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
  border: 4px solid black;
  border-radius: 10px;
}
.table .price-section {
  display: flex;
  justify-content: center;
}
.table .price-area {
  height: 120px;
  width: 120px;
  border-radius: 50%;
  padding: 2px;
}
.price-area .inner-area {
  height: 100%;
  width: 100%;
  border-radius: 50%;
  border: 3px solid #fff;
  line-height: 117px;
  text-align: center;
  color: #fff;
  position: relative;
}
.price-area .inner-area .text {
  font-size: 25px;
  font-weight: 400;
  position: absolute;
  top: -10px;
  left: 17px;
}
.price-area .inner-area .price {
  font-size: 45px;
  font-weight: 500;
  margin-left: 16px;
}
.table .package-name {
  width: 100%;
  height: 2px;
  margin: 35px 0;
  position: relative;
}
.table .package-name::before {
  position: absolute;
  top: 50%;
  left: 50%;
  font-size: 25px;
  font-weight: 500;
  background: #fff;
  padding: 0 15px;
  transform: translate(-50%, -50%);
}
.table .features li {
  margin-bottom: 15px;
  list-style: none;
  display: flex;
  justify-content: space-between;
}
.features li .list-name {
  font-size: 17px;
  font-weight: 400;
}
.features li .icon {
  font-size: 15px;
}
.features li .icon.check {
  color: #2db94d;
}
.features li .icon.cross {
  color: #cd3241;
}
.table .btn {
  width: 100%;
  display: flex;
  margin-top: 35px;
  justify-content: center;
}
.table .btn button {
  width: 80%;
  height: 50px;
  color: #fff;
  font-size: 20px;
  font-weight: 500;
  border: none;
  outline: none;
  border-radius: 25px;
  cursor: pointer;
  transition: all 0.3s ease;
}
.table .btn button:hover {
  border-radius: 5px;
}
.basic .features li::selection {
  background: #ffd861;
}
.basic ::selection,
.basic .price-area,
.basic .inner-area {
  background: #ffd861;
}
.basic .btn button {
  border: 2px solid #ffd861;
  background: #fff;
  color: #ffd861;
}
.basic .btn button:hover {
  background: #ffd861;
  color: #fff;
}
.premium ::selection,
.premium .price-area,
.premium .inner-area,
.premium .btn button {
  background: #a26bfa;
}
.premium .btn button:hover {
  background: #833af8;
}
.ultimate ::selection,
.ultimate .price-area,
.ultimate .inner-area {
  background: #43ef8b;
}
.ultimate .btn button {
  border: 2px solid #43ef8b;
  color: #43ef8b;
  background: #fff;
}
.ultimate .btn button:hover {
  background: #43ef8b;
  color: #fff;
}
.basic .package-name {
  background: #ffecb3;
}
.premium .package-name {
  background: #d0b3ff;
}
.ultimate .package-name {
  background: #baf8d4;
}
.basic .package-name::before {
  content: "Bronze";
}
.premium .package-name::before {
  content: "Silver";
  font-size: 24px;
}
.ultimate .package-name::before {
  content: "Gold";
  font-size: 24px;
}
@media (max-width: 1020px) {
  .wrapper .table {
    width: calc(50% - 20px);
    margin-bottom: 40px;
  }
}
@media (max-width: 698px) {
  .wrapper .table {
    width: 100%;
  }
}
::selection {
  color: #fff;
}
.table .ribbon {
  width: 150px;
  height: 150px;
  position: absolute;
  top: -10px;
  left: -10px;
  overflow: hidden;
}
.table .ribbon::before,
.table .ribbon::after {
  position: absolute;
  content: "";
  z-index: -1;
  display: block;
  border: 7px solid #4606ac;
  border-top-color: transparent;
  border-left-color: transparent;
}
.table .ribbon::before {
  top: 0px;
  right: 15px;
}
.table .ribbon::after {
  bottom: 15px;
  left: 0px;
}
.table .ribbon span {
  position: absolute;
  top: 30px;
  right: 0;
  transform: rotate(-45deg);
  width: 200px;
  background: #a26bfa;
  padding: 10px 0;
  color: #fff;
  text-align: center;
  font-size: 17px;
  text-transform: uppercase;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.12);
}

input[type=submit]{
  margin-top: 1.5rem;
}
  </style>
