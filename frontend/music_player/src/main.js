import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import axios from 'axios'
// import './assets/styles/global.stylus'
import "./assets/css/style.css"
// import "./assets/js/active"

Vue.config.productionTip = false

Vue.prototype.$http = axios
axios.defaults.baseURL = "http://localhost:8882"

new Vue({ 
  router,
  render: h => h(App)
}).$mount('#app')

// 全局时间过滤器
Vue.filter('timeFormat', (times) => {
  let minutes = parseInt(times / 1000 / 60) + ''
  minutes = minutes.padStart(2, '0')
  let seconds = parseInt(times / 1000 % 60) + ''
  seconds = seconds.padStart(2, '0')
  return `${minutes} : ${seconds}`
})
Vue.filter('dateFormat', function (originVal) {
	const dt = new Date(originVal)
	const year = dt.getFullYear()
	const month = (dt.getMonth() + 1 + '').padStart(2, '0')
	const date = (dt.getDate() + '').padStart(2, '0')
	const hour = (dt.getHours() + '').padStart(2, '0')
	const minute = (dt.getMinutes() + '').padStart(2, '0')
	const seconds = (dt.getSeconds() + '').padStart(2, '0')

	return `${year}-${month}-${date} ${hour}:${minute}:${seconds}`
})