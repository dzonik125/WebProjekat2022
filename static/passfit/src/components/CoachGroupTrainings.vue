<!-- eslint-disable vue/require-v-for-key -->
<template>
    <div id="coachGrpTrg">
      <input type="search" placeholder="Pretrazi treninge" v-model="search">
        <select name="" id="" v-model="sort">
            <option value="" selected disabled hidden>Sortiraj po</option>
            <option value="nOp">Naziv sportskog objekta ↓</option>
            <option value="nRa">Naziv sportskog objekta ↑</option>
        </select>
        <select name="" id="" v-model="filter1">
            <option value="" selected disabled hidden>Filtriraj po tipu sportskog centra</option>
            <option value="trt">Teretane</option>
            <option value="spc">Sportske centre</option>
            <option value="ps">Plesne studije</option>
            <option value="b">Bazene</option>
        </select>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Naziv treninga</th>
                    <th scope="col">Objekat</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="training in Filtered1" v-if="!training.sportObject.deleted">
                    <td>{{training.name}}</td>
                    <td>{{training.sportObject.name}}</td>
                </tr>
            </tbody>
        </table>
        <div class="d-flex justify-content-center"><button class="btn" v-on:click.prevent="goBack()">Nazad</button></div>
    </div>
  </template>

<script>
export default {
  props: ['user'],
  data () {
    return {
      trainings: [],
      search: '',
      sort: '',
      filter1: ''
    }
  },
  methods: {
    goBack: function () {
      this.$router.push({name: 'Index'})
    }
  },
  mounted () {
    const axios = require('axios')
    axios.post('http://localhost:8082/rest/getGroupTrainingsForCoach/', {coach: this.user}).then(response => {
      this.trainings = response.data
      console.log(this.trainings)
    })
  },
  computed: {
    SearchTrainings: function () {
      let val = this.search.toLowerCase()
      return this.trainings.filter((training) => {
        if (training.sportObject.name.toLowerCase().includes(val)) {
          return true
        }
      })
    },
    Sorted: function () {
      if (this.sort === '') {
        return this.SearchTrainings
      }
      if (this.sort === 'nOp') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.SearchTrainings.sort(function (a, b) {
          if (a.sportObject.name > b.sportObject.name) {
            return 1
          }
          if (a.sportObject.name < b.sportObject.name) {
            return -1
          }
          return 0
        })
      }
      if (this.sort === 'nRa') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.SearchTrainings.sort(function (a, b) {
          if (a.sportObject.name > b.sportObject.name) {
            return -1
          }
          if (a.sportObject.name < b.sportObject.name) {
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
          if (element.sportObject.objectType === 'GYM') {
            return true
          }
          return false
        })
      }
      if (this.filter1 === 'spc') {
        return this.Sorted.filter((element) => {
          if (element.sportObject.objectType === 'SPORTCENTRE') {
            return true
          }
          return false
        })
      }
      if (this.filter1 === 'b') {
        return this.Sorted.filter((element) => {
          if (element.sportObject.objectType === 'POOL') {
            return true
          }
          return false
        })
      }
      if (this.filter1 === 'ps') {
        return this.Sorted.filter((element) => {
          if (element.sportObject.objectType === 'DANCESTUDIO') {
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
    #coachGrpTrg{
        margin: 4rem;
    }
</style>
