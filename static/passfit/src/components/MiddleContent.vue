<!-- eslint-disable vue/require-v-for-key -->
<template>
  <div id="mid" style="background-image: url('static/bg01.png/'); background-repeat: no-repeat; background-size: cover">
    <ul style="list-style: none; padding-top: 4.2rem;">
      <li v-for="sObject in sportObjects">
        <div class="card mb-3 cardTransition animate pop" style="max-width: 540px;">
          <router-link class="text" :to="{name: 'ObjectView', params: {object: sObject.name}}">
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
  props: ['sportObjects'],
  data () {
    return {}
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
