import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../components/Login.vue"
import index from '../components/index.vue'
import main from '../components/main.vue';
// import main from '../components/main.vue'

Vue.use(VueRouter)

  const routes = [
	{
		path:'/',
		redirect: '/main',
	},{
		path:'/main',
		component:main
	},
	{
		path: "/login",
		component: Login
	},{
		path:"/index",
		component:index
	}
]

const router = new VueRouter({
  routes
})

export default router
