<template>
  <div id="app">
    <!-- <index></index> -->
    <!-- <main></main> -->
    <router-view></router-view>
  </div>
</template>

<script>
import index from './components/index.vue';
// import main from './components/main.vue'
export default {
  name:"app",
  components:{
    index,
    // main
  }
}
</script>

<style>

</style>
