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
