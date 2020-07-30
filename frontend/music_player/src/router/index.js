/*
 * @Author: your name
 * @Date: 2020-07-28 15:54:09
 * @LastEditTime: 2020-07-29 13:04:12
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \music_player\src\router\index.js
 */ 
import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from "../components/Login.vue";
import index from '../components/index.vue';
import main from '../components/main.vue';
import register from '../components/register.vue';
import contact from '../components/contact.vue';
import result from '../components/result.vue';
import rank from '../components/rank.vue';
import musicPlayer from '../components/MusicPlayer.vue';
import mvs from '../components/mvs.vue';
import album from '../components/album.vue'
import mv from '../components/mv.vue'

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
		path: "/contact",
		component: contact
	},
	{
		path:'/result',
		component:result
	},
	{
		path:'/rank',
		component:rank
	},
	{
		path: "/music",
		component: musicPlayer
	},
	{
		path:'/mvs',
		component: mvs
	},
	{
		path:'/album',
		component:album
	},
	{
		path:'/mv',
		component:mv
	}
]

const router = new VueRouter({
  routes
})

// 挂载路由导航
router.beforeEach((to, from, next)=>{
	if(to.path!='/album')return next();
	const user = window.sessionStorage.getItem("userToken");
	if(!user) return next("/login");
	return next();
})

export default router
