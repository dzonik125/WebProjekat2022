<!-- eslint-disable vue/require-v-for-key -->
<template>
  <div id="mid">
    <ul style="list-style: none;">
      <li v-for="sObject in sportObjects">
        <div class="card mb-3" style="max-width: 540px;">
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
                  <small class="text-muted">Tip objekta: {{sObject.objectType}}</small>
                </p>
              </div>
            </div>
          </div>
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
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#mid {
  margin-left: 20px;
  width: 100%;
  height: 38rem;
}

img {
  max-width: 100%;
  max-height: 100%;
}
</style>
