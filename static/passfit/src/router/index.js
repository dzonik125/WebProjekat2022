import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/IndexPage'
import Register from '@/components/RegisterPage'
import Login from '@/components/LoginPage'
import ViewProfile from '@/components/ViewProfile'
import AdminPanel from '@/components/AdminPanel'
import UserTable from '@/components/UserTable'
import CreateSportObject from '@/components/CreateSportObject'

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
    },
    {
      path: '/adminPanel/',
      name: 'adminPanel',
      component: AdminPanel
    },
    {
      path: '/userTable/',
      name: 'userTable',
      component: UserTable
    },
    {
      path: '/createSO/',
      name: 'createSportObject',
      component: CreateSportObject
    }
  ]
})
