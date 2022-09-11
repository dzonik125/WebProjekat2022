<!-- eslint-disable vue/require-v-for-key -->
<template>
  <div id="objectView">
    <section style="background-color: #eee; min-height: 100vh;">
      <div class="container py-5">

        <div class="row">
          <div class="col-lg-4">
            <div class="card mb-4"
              :style="'background-image: url(' + sportObject.imageRelLocation + '); background-size: cover; background-position: center;'">
              <div class="card-body text-center">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                  class="rounded-circle img-fluid" style="width: 150px; visibility: hidden;">
                <h5 class="my-3" style="visibility: hidden;">John Smith</h5>
                <p class="text-muted mb-1" style="visibility: hidden;">Full Stack Developer</p>
                <p class="text-muted mb-4" style="visibility: hidden;">Bay Area, San Francisco, CA</p>
                <div class="d-flex justify-content-center mb-2" style="visibility: hidden;">
                  <button type="button" class="btn btn-primary" style="visibility: hidden;">Follow</button>
                  <button type="button" class="btn btn-outline-primary ms-1"
                    style="visibility: hidden;">Message</button>
                </div>
              </div>
            </div>
            <div class="card mb-4 mb-lg-0">
              <div class="card-body p-0">
                <ul class="list-group list-group-flush rounded-3">
                  <li class="list-group-item justify-content-between align-items-center p-3 baton">
                    <router-link :to="'/'" class="buttonText">
                      <p class="mb-0 text-center">Nazad</p>
                    </router-link>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <div class="col-lg-8">
            <div class="card mb-4">
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Naziv objekta</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0">{{sportObject.name}}</p>
                  </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Tip objekta</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0">{{convertStrings(sportObject.objectType)}}</p>
                  </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Usluge</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0">{{sportObject.services}}</p>
                  </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Radno vreme</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0">{{sportObject.workingHours}}</p>
                  </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Adresa</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0" v-if="fetched">{{sportObject.location.adress.street + ' ' +
                    sportObject.location.adress.sNum + ',' + ' ' + sportObject.location.adress.postCode + ' ' +
                    sportObject.location.adress.city}}</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6 whiteSpace" v-if="!training.deleted" v-for="(training, index) in trainings"
                :key=index>
                <div class="card mb-4 mb-md-0 whiteSpace" style="min-width: 300px; min-height: 695px;">
                  <div class="card-body">
                    <p class="mb-4"><span class="text-primary font-italic me-1">Trening</span> {{training.name}}
                    </p>
                    <span style="display: none;" :name="'trgID' + index">{{training.id}}</span>
                    <button class="btn position-absolute top-0 end-0 mt-2" v-if="userType === 'BUYER'"
                      v-on:click="doTheTraining(training.name, training.trainingType, index)">Prijavi</button>
                    <button class="btn position-absolute top-0 end-0 mt-2" v-if="userType === 'ADMINISTRATOR'"
                      v-on:click="deleteTraining(index)">Obrisi</button>
                    <input type="datetime-local" style="margin-bottom: 5px; display: none;" :name="'dt' + index"
                      v-model="date">
                    <span><button class="btn" style="display: none;" :name="'zakazi' + index"
                        v-on:click="makeAppointment(training.coach, training.name)">Zakazi</button></span>
                    <span><button class="btn" style="display: none;" :name="'otkazi' + index"
                        v-on:click="cancelMakeApp(index)">Otkazi</button></span>
                    <img :src="training.imageLocation" alt=""
                      style="height: 15rem; width: 100%; object-fit: cover; margin-bottom: 10px;">
                    <p class="mb-1" style="font-size: .77rem;">Tip treninga</p>
                    <p class="text-muted mb-0" :name="'trgType' + index">{{convertStrings2(training.trainingType)}}</p>
                    <p class="mt-4 mb-1" style="font-size: .77rem;">Trajanje</p>
                    <p class="text-muted mb-0" :name="'duration' + index">{{training.duration}} minuta</p>
                    <p class="mt-4 mb-1" style="font-size: .77rem;">Opis treninga</p>
                    <p class="text-muted mb-0" :name="'description' + index">{{training.description}}</p>
                    <p class="mt-4 mb-1" style="font-size: .77rem;">Trener</p>
                    <p class="text-muted mb-0">{{training.coach}}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <div class="container">
      <div class="be-comment-block">
        <h1 class="comments-title">Comments</h1>
        <div class="be-comment" v-for="comment in comments">
          <div class="be-comment-content">
            <span class="be-comment-name">
              <a href="blog-detail-2.html">{{comment.buyer}}</a>
            </span>
            <span class="be-comment-time">
              <i class="fa fa-clock-o"></i>
              {{comment.created}}
            </span>
            <p class="be-comment-text">
              {{comment.text}}
            </p>
          </div>
        </div>
        <form class="form-block" v-if="userType === 'BUYER' && canLeaveComment">
          <div class="row">
            <div class="col-xs-12">
              <div class="form-group">
                <textarea class="form-input" required="" placeholder="Your text" v-model="commentText"></textarea>
              </div>
            </div>
            <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
              crossorigin="anonymous">
            <div class="container">
              <div class="starrating risingstar d-flex justify-content-center flex-row-reverse">
                <input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="5 star">5</label>
                <input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="4 star">4</label>
                <input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="3 star">3</label>
                <input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="2 star">2</label>
                <input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="1 star">1</label>
              </div>
            </div>
            <a class="btn btn-primary pull-right" v-on:click="makeComment()">submit</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['object', 'userType', 'user'],
  data () {
    return {
      sportObject: {},
      fetched: false,
      trainings: [],
      date: {},
      commentText: '',
      canLeaveComment: false,
      grade: 0,
      comments: []
    }
  },
  methods: {
    convertStrings: function (toConvert) {
      if (toConvert === 'POOL') {
        return 'Bazen'
      } else if (toConvert === 'GYM') {
        return 'Teretana'
      } else if (toConvert === 'SPORTCENTRE') {
        return 'Sportski centar'
      } else if (toConvert === 'DANCESTUDIO') {
        return 'Plesni studio'
      }
    },
    convertStrings2: function (toConvert) {
      if (toConvert === 'PERSONAL') {
        return 'Personalni'
      } else if (toConvert === 'GROUP') {
        return 'Grupni'
      } else if (toConvert === 'GYM') {
        return 'Teretana'
      }
    },
    doTheTraining: function (name, type, index) {
      console.log(type)
      if (type !== 'PERSONAL') {
        const axios = require('axios')
        axios.post('http://localhost:8082/rest/train/', { object: this.object, training: name, user: this.user }).then(response => {
          if (response.data === 200) {
            window.alert('Uspesno ste se prijavili u objekat')
            this.$router.push({ name: 'Index' })
          } else {
            window.alert('Nemate vise ulazaka za ovaj dan, molimo Vas dodjite sutra.')
          }
        })
      } else {
        let dt = document.getElementsByName('dt' + index)[0]
        dt.style.display = 'inline-block'
        const today = new Date()
        let tomorrow = new Date()
        tomorrow.setDate(today.getDate() + 1)
        dt.min = tomorrow.toISOString().slice(0, tomorrow.toISOString().lastIndexOf(':'))
        let zakazi = document.getElementsByName('zakazi' + index)[0]
        zakazi.style.display = 'inline-block'
        let otkazi = document.getElementsByName('otkazi' + index)[0]
        otkazi.style.display = 'inline-block'
      }
    },
    makeAppointment: function (coach, name) {
      console.log(this.date)
      console.log(coach)
      const axios = require('axios')
      axios.post('http://localhost:8082/rest/scheduleTraining/', { date: this.date, coach: coach, name: name, object: this.object, user: this.user }).then(response => {
        if (response.data === 200) {
          window.alert('Uspesno ste zakazali trening')
          this.$router.push({ name: 'Index' })
        } else {
          window.alert('Nesto nije u redu')
        }
      })
    },
    cancelMakeApp: function (index) {
      let dt = document.getElementsByName('dt' + index)[0]
      dt.style.display = 'none'
      let zakazi = document.getElementsByName('zakazi' + index)[0]
      zakazi.style.display = 'none'
      let otkazi = document.getElementsByName('otkazi' + index)[0]
      otkazi.style.display = 'none'
    },
    deleteTraining: function (index) {
      let ind = document.getElementsByName('trgID' + index)[0]
      const axios = require('axios')
      axios.post('http://localhost:8082/rest/DeleteTraining/', { id: ind.innerText }).then(response => {
        this.$router.push({ name: 'Index' })
      })
    },
    makeComment: function () {
      if (this.commentText === '') {
        window.alert('Morate nesto napisati u komentaru!')
        return
      }
      let i5 = document.getElementById('star5')
      let i4 = document.getElementById('star4')
      let i3 = document.getElementById('star3')
      let i2 = document.getElementById('star2')
      let i1 = document.getElementById('star1')
      if (i5.checked) {
        this.grade = 5
      }
      if (i4.checked) {
        this.grade = 4
      }
      if (i3.checked) {
        this.grade = 3
      }
      if (i2.checked) {
        this.grade = 2
      }
      if (i1.checked) {
        this.grade = 1
      }
      const axios = require('axios')
      axios.post('http://localhost:8082/rest/makeComment/', { text: this.commentText, sportObject: this.object, buyer: this.user, grade: this.grade }).then(response => {
        window.alert(response.data)
      })
    }
  },
  mounted () {
    console.log(this.userType)
    const axios = require('axios')
    axios.post('http://localhost:8082/rest/GetObjectByName/', { object: this.object }).then(response => {
      if (response.data == null) {
        this.sportObject = {}
        this.fetched = false
      } else {
        this.sportObject = response.data
        this.fetched = true
        axios.post('http://localhost:8082/rest/getTrainingsForObject/', { object: this.object }).then(response => {
          if (response.data == null) {
            console.log('No trainings')
            this.trainings = []
          } else {
            this.trainings = response.data
            this.trainings.forEach(element => {
              axios.post('http://localhost:8082/rest/getCoachName/', { coach: element.coach }).then(response => {
                console.log(response.data)
                element.coach = response.data
              })
            })
          }
        })
      }
    })
    if (this.userType === 'BUYER') {
      axios.post('http://localhost:8082/rest/CanBuyerComment/', { buyer: this.user, sportObject: this.object }).then(response => {
        if (response.data === 200) {
          this.canLeaveComment = true
        } else {
          this.canLeaveComment = false
        }
      })
    }
    axios.post('http://localhost:8082/rest/getObjectComments/', { sportObject: this.object }).then(response => {
      this.comments = response.data
    })
  }
}
</script>

  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.baton {
  cursor: pointer;
}

.baton:hover {
  border: 1px solid black;
}

.buttonText {
  text-decoration: none;
  color: black;
}

.whiteSpace::after {
  content: "\a";
  white-space: pre;
}

body {
  margin-top: 20px;
  background-color: #e9ebee;
}

.be-comment-block {
  margin-bottom: 50px !important;
  border: 1px solid #edeff2;
  border-radius: 2px;
  padding: 50px 70px;
  border: 1px solid #ffffff;
}

.comments-title {
  font-size: 16px;
  color: #262626;
  margin-bottom: 15px;
  font-family: 'Conv_helveticaneuecyr-bold';
}

.be-img-comment {
  width: 60px;
  height: 60px;
  float: left;
  margin-bottom: 15px;
}

.be-ava-comment {
  width: 60px;
  height: 60px;
  border-radius: 50%;
}

.be-comment-content {
  margin-left: 80px;
}

.be-comment-content span {
  display: inline-block;
  width: 49%;
  margin-bottom: 15px;
}

.be-comment-name {
  font-size: 13px;
  font-family: 'Conv_helveticaneuecyr-bold';
}

.be-comment-content a {
  color: #383b43;
}

.be-comment-content span {
  display: inline-block;
  width: 49%;
  margin-bottom: 15px;
}

.be-comment-time {
  text-align: right;
}

.be-comment-time {
  font-size: 11px;
  color: #b4b7c1;
}

.be-comment-text {
  font-size: 13px;
  line-height: 18px;
  color: #7a8192;
  display: block;
  background: #f6f6f7;
  border: 1px solid #edeff2;
  padding: 15px 20px 20px 20px;
}

.form-group.fl_icon .icon {
  position: absolute;
  top: 1px;
  left: 16px;
  width: 48px;
  height: 48px;
  background: #f6f6f7;
  color: #b5b8c2;
  text-align: center;
  line-height: 50px;
  -webkit-border-top-left-radius: 2px;
  -webkit-border-bottom-left-radius: 2px;
  -moz-border-radius-topleft: 2px;
  -moz-border-radius-bottomleft: 2px;
  border-top-left-radius: 2px;
  border-bottom-left-radius: 2px;
}

.form-group .form-input {
  font-size: 13px;
  line-height: 50px;
  font-weight: 400;
  color: #b4b7c1;
  width: 100%;
  height: 50px;
  padding-left: 20px;
  padding-right: 20px;
  border: 1px solid #edeff2;
  border-radius: 3px;
}

.form-group.fl_icon .form-input {
  padding-left: 70px;
}

.form-group textarea.form-input {
  height: 150px;
}

.starrating>input {
  display: none;
}

/* Remove radio buttons */

.starrating>label:before {
  content: "\f005";
  /* Star */
  margin: 2px;
  font-size: 8em;
  font-family: FontAwesome;
  display: inline-block;
}

.starrating>label {
  color: #222222;
  /* Start color when not clicked */
}

.starrating>input:checked~label {
  color: #ffca08;
}

/* Set yellow color when star checked */

.starrating>input:hover~label {
  color: #ffca08;
}

/* Set yellow color when star hover */
</style>
