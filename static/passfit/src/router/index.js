import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/IndexPage'
import Register from '@/components/RegisterPage'
import Login from '@/components/LoginPage'
import ViewProfile from '@/components/ViewProfile'
import AdminPanel from '@/components/AdminPanel'
import UserTable from '@/components/UserTable'
import CreateSportObject from '@/components/CreateSportObject'
import CreateManager from '@/components/CreateManager'
import CreateCoach from '@/components/CreateCoach'
import ManageObject from '@/components/ManageObject'
import AddTraining from '@/components/AddTraining'
import ObjectCoaches from '@/components/ObjectCoaches'
import ObjectView from '@/components/objectView'

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
    },
    {
      path: '/createManager/',
      name: 'createManager',
      component: CreateManager
    },
    {
      path: '/createCoach/',
      name: 'createCoach',
      component: CreateCoach
    },
    {
      path: '/manageObject/:user',
      name: 'manageObject',
      component: ManageObject,
      props: true
    },
    {
      path: '/addTraining/:object',
      name: 'addTraining',
      component: AddTraining,
      props: true
    },
    {
      path: '/objectCoaches/:object',
      name: 'ObjectCoaches',
      component: ObjectCoaches,
      props: true
    },
    {
      path: '/objectView/:object',
      name: 'ObjectView',
      component: ObjectView,
      props: true
    }
  ]
})
