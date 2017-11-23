import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Create from '@/components/CreateObject'
import Edit from '@/components/EditObject'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/create',
      name: 'Create',
      component: Create
    },
    {
      path: '/Edit',
      name: 'Edit',
      component: Edit
    }
  ]
})
