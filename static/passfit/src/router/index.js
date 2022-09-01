import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/IndexPage'
import Register from '@/components/RegisterPage'
import Login from '@/components/LoginPage'
import ViewProfile from '@/components/ViewProfile'

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
    },
    {
      path: '/profileView/:user',
      name: 'viewProfile',
      component: ViewProfile,
      props: true
    }
  ]
})
