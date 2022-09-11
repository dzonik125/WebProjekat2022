<!-- eslint-disable vue/require-v-for-key -->
<template>
    <div id="addTraining">
      <form>
  <!-- 2 column grid layout with text inputs for the first and last names -->
  <div class="row mb-4">
    <div class="col">
      <div class="form-outline">
        <input type="text" id="form6Example1" class="form-control" v-model="name" placeholder="Mora pocinjati velikim slovom"/>
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
    <input type="file" id="form6Example3" class="form-control" v-on:change="filePreview($event)"/>
    <label class="form-label" for="form6Example3">Slika</label>
  </div>

  <!-- Number input -->
  <div class="form-outline mb-4">
    <input type="number" id="form6Example6" class="form-control" v-model="duration" placeholder="Mora biti broj" />
    <label class="form-label" for="form6Example6">Trajanje</label>
  </div>

  <!-- Message input -->
  <div class="form-outline mb-4">
    <textarea class="form-control" id="form6Example7" rows="4" v-model="description" placeholder="Mora pocinjati velikim slovom"></textarea>
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
      if (this.trainingType === '') {
        window.alert('Odaberite tip')
        return
      }
      if (this.image === '') {
        window.alert('Odaberite sliku')
        return
      }
      if (this.selectedCoach === '') {
        window.alert('Odaberite trenera')
        return
      }
      // eslint-disable-next-line no-useless-escape
      const regex1 = new RegExp('^[a-zA-Z].*[\\s\.]*$')
      const regex2 = new RegExp('[0-9]+')
      if (!regex1.test(this.name)) {
        window.alert('Niste uneli naziv kako treba')
        return
      }
      if (!regex2.test(this.duration)) {
        window.alert('Niste uneli trajanje kako treba')
        return
      }
      if (!regex1.test(this.description)) {
        window.alert('Niste uneli opis kako treba')
        return
      }
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
          window.alert('Postoji trening sa tim imenom')
        } else {
          window.alert('Uspesno ste dodali trening')
          // window.location.href = 'http://localhost:8081/#/'
          this.$router.push({name: 'manageObject', params: {user: this.user}})
        }
      })
    },
    filePreview: function (event) {
      const reader = new FileReader()
      const file = event.target.files[0]
      let rawImg
      let self = this
      reader.onloadend = (e) => {
        rawImg = e.target.result
        self.image = rawImg
        console.log(self.image)
      }
      reader.readAsDataURL(file)
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
