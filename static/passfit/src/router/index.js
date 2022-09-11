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
import ObjectView from '@/components/ObjectView'
import BuyMembership from '@/components/BuyMembership'
import ScheduledTrainings from '@/components/ScheduledTrainings'
import CGroupTrainings from '@/components/CoachGroupTrainings'
import ObjectBuyers from '@/components/ObjectBuyers'
import BuyerTrainings from '@/components/BuyerTrainings'
import BuyerScheduledTrainings from '@/components/BuyerScheduledTrainings'
import Comments from '@/components/Comments'
import PromoCodes from '@/components/AddPromoCode'

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
    },
    {
      path: '/buyMembership/:user',
      name: 'BuyMembership',
      component: BuyMembership,
      props: true
    },
    {
      path: '/scheduledTrainings/:user',
      name: 'ScheduledTrainings',
      component: ScheduledTrainings,
      props: true
    },
    {
      path: '/coachGroupTrainings/:user',
      name: 'CGroupTrainings',
      component: CGroupTrainings,
      props: true
    },
    {
      path: '/objectBuyers/:object',
      name: 'ObjectBuyers',
      component: ObjectBuyers,
      props: true
    },
    {
      path: '/buyerTrainings/:user',
      name: 'BuyerTrainings',
      component: BuyerTrainings,
      props: true
    },
    {
      path: '/buyerScheduledTrainings/:user',
      name: 'BuyerScheduledTrainings',
      component: BuyerScheduledTrainings,
      props: true
    },
    {
      path: '/comments',
      name: 'Comments',
      component: Comments
    },
    {
      path: '/promoCodes',
      name: 'PromoCodes',
      component: PromoCodes
    }
  ]
})
