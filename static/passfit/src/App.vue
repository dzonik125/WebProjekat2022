<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
body, html{
  min-height: 100vh;
  margin: 0;
  padding: 0;
}
#app {
  height: 100vh;
  margin: 0;
}
</style>
