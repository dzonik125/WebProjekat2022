<template>
    <div>
      <img src="../assets/logo-project.png" alt="">
      <div class="space-between"></div>
      <button>Registruj se</button>
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

<style scoped>
div{
    width: 100%;
    background-color: white;
    height: 10rem;
    margin: 0;
    display: flex;
}
img{
  width: 25%;
  height: 8.9rem;
  object-fit: contain;
}
button{
  height: 2rem;
  margin-top: 4rem;
  width: 10rem;
  margin-right: 5rem;
}
.space-between{
  display: flex;
  justify-content: space-between;
}
</style>
