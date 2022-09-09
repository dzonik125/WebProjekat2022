<!-- eslint-disable vue/require-v-for-key -->
<template>
  <div id="mid" style="background-image: url('static/bg01.png/'); background-repeat: no-repeat; background-size: 100% 100%; min-height: 100vh; overflow-y: auto;">
    <ul style="list-style: none; padding-top: 4.2rem;">
      <div class="input-group input-group-sm mb-3 animate pop" style="max-width: 540px;">
                <input v-model="search" type="search" placeholder="Naziv, tip, grad, prosecna ocena" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" style="margin-right:10px">
                <select name="text-right" id="" style="width: 100px;" v-model="sortSelection">
                  <option value="" selected disabled hidden>Sortiraj po</option>
                  <option value="nOp">Naziv ↓ (A-Z)</option>
                  <option value="nRa">Naziv ↑ (Z-A)</option>
                  <option value="gOp">Grad ↓ (A-Z)</option>
                  <option value="gRa">Grad ↑ (Z-A)</option>
                </select>
                <select name="" id="" style="margin-left: 10px; width: 100px;" v-model="filterSelection">
                  <option value="" selected disabled hidden>Filtriraj</option>
                  <option value="trt">Teretane</option>
                  <option value="baz">Bazene</option>
                  <option value="spc">Sportske centre</option>
                  <option value="ps">Plesne studije</option>
                </select>
      </div>
      <li v-for="sObject in filteredObjects2">
        <div class="card mb-3 cardTransition animate pop" style="max-width: 540px;">
          <router-link class="text" :to="{name: 'ObjectView', params: {object: sObject.name, userType: userType, user: user}}">
          <div class="row g-0">
            <div class="col-md-4">
              <img :src="sObject.imageRelLocation" class="img-fluid rounded-start"/>
            </div>
            <div class="col-md-8">
              <div class="card-body">
                <h5 class="card-title">{{sObject.name}}</h5>
                <p class="card-text">
                  {{sObject.services}}
                </p>
                <p class="card-text">
                  <small class="text-muted" v-if="sObject.objectType">Tip objekta: {{convertStrings(sObject.objectType)}}</small>
                </p>
              </div>
            </div>
          </div>
        </router-link>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: ['sportObjects', 'userType', 'user'],
  data () {
    return {
      search: '',
      sortSelection: '',
      filterSelection: ''
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
    }
  },
  computed: {
    filteredObjects: function () {
      let value = this.search.toLowerCase()
      if (!(value.includes(','))) {
        return this.sportObjects.filter((sportObject) => {
          let oType = ''
          if (sportObject.objectType === 'POOL') {
            oType = 'Bazen'
          } else if (sportObject.objectType === 'GYM') {
            oType = 'Teretana'
          } else if (sportObject.objectType === 'SPORTCENTRE') {
            oType = 'Sportski centar'
          } else if (sportObject.objectType === 'DANCESTUDIO') {
            oType = 'Plesni studio'
          }
          if (sportObject.name.toLowerCase().includes(value) || oType.toLowerCase().includes(value) || sportObject.location.adress.city.toLowerCase().includes(value)) {
            return true
          }
          return false
        }
        )
      } else {
        const value1 = value.split(', ')
        return this.sportObjects.filter((sportObject) => {
          let oType = ''
          if (sportObject.objectType === 'POOL') {
            oType = 'Bazen'
          } else if (sportObject.objectType === 'GYM') {
            oType = 'Teretana'
          } else if (sportObject.objectType === 'SPORTCENTRE') {
            oType = 'Sportski centar'
          } else if (sportObject.objectType === 'DANCESTUDIO') {
            oType = 'Plesni studio'
          }
          if ((sportObject.name.toLowerCase().includes(value1[0]) && oType.toLowerCase().includes(value1[1])) || (sportObject.name.toLowerCase().includes(value1[0]) && oType.toLowerCase().includes(value1[1]) && sportObject.location.adress.city.toLowerCase().includes(value1[2]))) {
            return true
          }
          return false
        }
        )
      }
    },
    sortedObjects: function () {
      if (this.sortSelection === 'nOp') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.filteredObjects.sort(function (a, b) {
          if (a.name > b.name) {
            return 1
          }
          if (b.name > a.name) {
            return -1
          }
          return 0
        })
      } else if (this.sortSelection === '') {
        return this.filteredObjects
      } else if (this.sortSelection === 'nRa') {
        console.log(this.sortSelection)
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.filteredObjects.sort(function (a, b) {
          if (a.name > b.name) {
            return -1
          }
          if (b.name > a.name) {
            return 1
          }
          return 0
        })
      } else if (this.sortSelection === 'gOp') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.filteredObjects.sort(function (a, b) {
          if (a.location.adress.city > b.location.adress.city) {
            return 1
          }
          if (b.location.adress.city > a.location.adress.city) {
            return -1
          }
          return 0
        })
      } else if (this.sortSelection === 'gRa') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.filteredObjects.sort(function (a, b) {
          if (a.location.adress.city > b.location.adress.city) {
            return -1
          }
          if (b.location.adress.city > a.location.adress.city) {
            return 1
          }
          return 0
        })
      }
    },
    filteredObjects2: function () {
      if (this.filterSelection === 'trt') {
        return this.sortedObjects.filter((sortedObject) => {
          if (sortedObject.objectType === 'GYM') {
            return true
          }
          return false
        })
      } else if (this.filterSelection === '') {
        return this.sortedObjects
      } else if (this.filterSelection === 'baz') {
        return this.sortedObjects.filter((sortedObject) => {
          if (sortedObject.objectType === 'POOL') {
            return true
          }
          return false
        })
      } else if (this.filterSelection === 'spc') {
        return this.sortedObjects.filter((sortedObject) => {
          if (sortedObject.objectType === 'SPORTCENTRE') {
            return true
          }
          return false
        })
      } else if (this.filterSelection === 'ds') {
        return this.sortedObjects.filter((sortedObject) => {
          if (sortedObject.objectType === 'DANCESTUDIO') {
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
#mid {
  margin-left: 0px;
  width: 100%;
  height: 100%;
}

img {
  max-width: 100%;
  max-height: 100%;
}

.cardTransition{
  transition: transform .2s;
  cursor: pointer;
}

.cardTransition:hover{
  transform: scale(1.1)
}

.animate.pop {
  animation-duration: 0.5s;
  animation-name: animate-pop;
  animation-timing-function: cubic-bezier(.26, .53, .74, 1.48);
}

@keyframes animate-pop {
  0% {
    opacity: 0;
    transform: scale(0.5, 0.5);
  }

  100% {
    opacity: 1;
    transform: scale(1, 1);
  }
}
.text{
        text-decoration: none;
        color: black;
  }
</style>
