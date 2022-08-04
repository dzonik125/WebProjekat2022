import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/IndexPage'
import Register from '@/components/RegisterPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    }
  ]
})
