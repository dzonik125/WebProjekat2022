<!-- eslint-disable vue/require-v-for-key -->
<template>
    <div id="addTraining">
      <form>
  <!-- 2 column grid layout with text inputs for the first and last names -->
  <div class="row mb-4">
    <div class="col">
      <div class="form-outline">
        <input type="text" id="form6Example1" class="form-control" v-model="name"/>
        <label class="form-label" for="form6Example1">Naziv treninga</label>
      </div>
    </div>
    <div class="col">
      <div class="form-outline">
        <select id="form6Example2" class="form-control" v-model="trainingType">
          <option value="GROUP">Grupni</option>
          <option value="PERSONAL">Personalni</option>
          <option value="GYM">Teretana</option>
        </select>
        <label class="form-label" for="form6Example2">Tip treninga</label>
      </div>
    </div>
  </div>

  <!-- Text input -->
  <div class="form-outline mb-4">
    <input type="text" id="form6Example3" class="form-control" v-model="image"/>
    <label class="form-label" for="form6Example3">Slika</label>
  </div>

  <!-- Number input -->
  <div class="form-outline mb-4">
    <input type="number" id="form6Example6" class="form-control" v-model="duration" />
    <label class="form-label" for="form6Example6">Trajanje</label>
  </div>

  <!-- Message input -->
  <div class="form-outline mb-4">
    <textarea class="form-control" id="form6Example7" rows="4" v-model="description"></textarea>
    <label class="form-label" for="form6Example7">Opis treninga</label>
  </div>
  <div class="form-outline mb-4">
        <label for="select" class="form-label">Izaberite trenera</label>
        <select id="select" class="form-control" v-model="selectedCoach">
          <option :value="coach.userName" v-for="coach in coaches">{{coach.name}} {{coach.surname}}</option>
        </select>
  </div>
  <!-- Submit button -->
  <button type="submit" class="btn btn-primary btn-block mb-4" v-on:click.prevent="createTraining()">Kreiraj</button>
</form>
    </div>
</template>

<script>
export default {
  props: ['object', 'user'],
  data () {
    return {
      name: '',
      trainingType: '',
      image: '',
      duration: '',
      description: '',
      coaches: [],
      selectedCoach: ''
    }
  },
  methods: {
    createTraining: function () {
      const axios = require('axios')
      axios.post('http://localhost:8082/rest/createTraining/', {
        name: this.name,
        trainingType: this.trainingType,
        image: this.image,
        duration: this.duration,
        description: this.description,
        sportObject: this.object,
        coach: this.selectedCoach
      }).then(response => {
        if (response.data === 400) {
          window.alert('Postoji problem')
        } else {
          window.alert('Uspesno ste dodali trening')
          // window.location.href = 'http://localhost:8081/#/'
          this.$router.push({name: 'manageObject', params: {user: this.user}})
        }
      })
    }
  },
  mounted () {
    const axios = require('axios')
    console.log(this.user)
    axios.get('http://localhost:8082/rest/getAllCoaches/').then(response => {
      this.coaches = response.data
    })
  }
}
</script>

  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
    form{
      margin: 20px;
    }
  </style>
