import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/IndexPage'
import Register from '@/components/RegisterPage'
import Login from '@/components/LoginPage'

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
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
