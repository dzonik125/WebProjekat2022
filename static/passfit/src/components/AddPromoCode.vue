<template>
    <div id="promoCodes">
        <div class="container-fluid px-1 py-5 mx-auto">
            <div class="row d-flex justify-content-center">
                <div class="col-xl-7 col-lg-8 col-md-9 col-11 text-center">
                    <h3>Dodajte promo kod</h3>
                    <div class="card">
                        <form class="form-card">
                            <div class="row justify-content-between text-left">
                                <div class="form-group col-sm-6 flex-column d-flex"> <label
                                        class="form-control-label px-3">Istek vazenja:<span class="text-danger">
                                            </span></label> <input type="date" id="fname" name="fname" v-model="expire"> </div>
                            </div>
                            <div class="row justify-content-between text-left">
                                <div class="form-group col-sm-6 flex-column d-flex"> <label
                                        class="form-control-label px-3">Dozvoljen broj koriscenja<span class="text-danger">
                                            </span></label> <input type="number" id="email" name="email" v-model="max"> </div>
                            </div>
                            <div class="row justify-content-between text-left">
                                <div class="form-group col-sm-6 flex-column d-flex"> <label
                                        class="form-control-label px-3">Popust<span class="text-danger">
                                            </span></label> <input type="number" v-model="discount" id="job" name="job"> </div>
                            </div>
                            <div class="row justify-content-between text-left">
                                <div class="form-group col-12 flex-column d-flex"> <label
                                        class="form-control-label px-3">Kod<span
                                            class="text-danger"></span></label> <input type="text" v-model="code" id="ans" name="ans"> </div>
                            </div>
                            <div class="row justify-content-end">
                                <div class="form-group col-sm-6"> <button v-on:click.prevent="createPromoCode()"
                                        class="btn-block btn-primary">Kreiraj kod</button> </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      expire: '',
      max: '',
      discount: '',
      code: ''
    }
  },
  methods: {
    createPromoCode: function () {
      const axios = require('axios')
      axios.post('http://localhost:8082/rest/addPromoCode/', {expire: this.expire, max: this.max, discount: this.discount, code: this.code}).then(response => {
        console.log(response.data)
        this.$router.push({name: 'Index'})
      })
    }
  }
}
</script>

  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body {
    color: lightgrey;
    overflow-x: hidden;
    height: 100%;
}

.card {
    padding: 30px 40px;
    margin-top: 60px;
    margin-bottom: 60px;
    border: none !important;
    box-shadow: 0 6px 12px 0 rgba(0, 0, 0, 0.2)
}

.blue-text {
    color: #00BCD4
}

.form-control-label {
    margin-bottom: 0
}

input,
textarea,
button {
    padding: 8px 15px;
    border-radius: 5px !important;
    margin: 5px 0px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    font-size: 18px !important;
    font-weight: 300
}

input:focus,
textarea:focus {
    -moz-box-shadow: none !important;
    -webkit-box-shadow: none !important;
    box-shadow: none !important;
    border: 1px solid #00BCD4;
    outline-width: 0;
    font-weight: 400
}

.btn-block {
    text-transform: uppercase;
    font-size: 15px !important;
    font-weight: 400;
    height: 43px;
    cursor: pointer
}

.btn-block:hover {
    color: #fff !important
}

button:focus {
    -moz-box-shadow: none !important;
    -webkit-box-shadow: none !important;
    box-shadow: none !important;
    outline-width: 0
}
</style>
