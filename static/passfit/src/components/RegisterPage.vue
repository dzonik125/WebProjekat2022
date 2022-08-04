<template>
  <div id="register">
    <form>
      <label for="username">Username</label>
      <input type="text" id="username">
      <label for="password">Password</label>
      <input type="text" id="password">
      <label for="name">Ime</label>
      <input type="text" id="name">
      <label for="surname">Prezime</label>
      <input type="text" id="surname">
      <label for="gender">Pol</label>
      <select id="gender">
        <option value="M">Musko</option>
        <option value="F">Zensko</option>
        <option value="N">Srednji rod</option>
      </select>
      <label for="birthday"></label>
      <input type="date" id="birthday">
      <input type="submit" value="Potvrdi">
    </form>
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
input, select{
   width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}
</style>
