// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css'
import * as VueGoogleMaps from 'vue2-google-maps'

export const bus = new Vue()

Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyDB5FB7OVJMToswfMjgGkrJ-qUSLAF4g0g'
  },
  installComponents: true
})
Vue.prototype.$http = Axios
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
