import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../components/Login.vue"
import index from '../components/index.vue'
import register from '../components/register.vue'
// import main from '../components/main.vue'

Vue.use(VueRouter)

  const routes = [
	{
		path:'/',
		redirect: '/main',
		component:index
	},
	{
		path: "/login",
		component: Login
	},
	{
		path:"/index",
		component:index
	},
	{
		path: "/register",
		component: register
	}
]

const router = new VueRouter({
  routes
})

export default router
