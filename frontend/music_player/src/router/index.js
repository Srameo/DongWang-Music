import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../components/Login.vue"
import index from '../components/index.vue'
import main from '../components/main.vue';
import register from '../components/register.vue'
import result from '../components/result.vue';
// import main from '../components/main.vue'

Vue.use(VueRouter)

  const routes = [
	{
		path:'/',
		redirect: '/main',
		component:index
	},
	{
		path:'/main',
		component:main
	},
	{
		path: "/login",
		component: Login
	},
	{
		path:"/index",
		component:main
	},
	{
		path: "/register",
		component: register
	},
	{
		path:'/result',
		component:result
	}
]

const router = new VueRouter({
  routes
})

export default router
