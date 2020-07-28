import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
// import './assets/styles/global.stylus'
import "./assets/css/style.css"
// import "./assets/js/active"

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
