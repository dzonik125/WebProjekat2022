<template>
  <div id="header">
    <button id="reg"><router-link class="regText" to="/register">Register</router-link></button>
  </div>
</template>

<script>
export default {
  data () {
    return {

    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#header{
    width: 100%;
    height: 10rem;
    background-color: white;
    display: flex;
    flex-direction: row-reverse;
}
#reg{
    margin-right: 2rem;
    margin-top: 1.5rem;
    height: 2rem;
}
.regText{
    cursor: default;
    color:black;
    text-decoration: none;
}
.regText:visited{
    color: black;
}
</style>
