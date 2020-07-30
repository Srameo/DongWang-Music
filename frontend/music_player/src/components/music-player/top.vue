<!--
 * @Author: your name
 * @Date: 2020-07-29 13:46:36
 * @LastEditTime: 2020-07-30 15:15:48
 * @LastEditors: your name
 * @Description: In User Settings Edit
 * @FilePath: \music_player\src\components\music-player\top.vue
--> 
<template>
    <section class="breadcumb-area bg-img bg-overlay">
        <div class="bradcumbContent">
        <span class="sub-title">没人比我更懂</span>
        <h2>
          <div>
            <el-avatar src="http://p2.music.126.net/Y0It48uESD-eycOa9Pdfog==/109951164245036271.jpg"></el-avatar>
          </div>好的音乐
        </h2>
        </div>
    </section>
</template>

<script>
export default {
    props: ['musicname', 'id']
    
}
</script>

<style scoped>
.bg-overlay {
  position: relative;
  z-index: 2;
  background-position: center center;
  background-image: url(../../assets/bg-img/breadcumb.jpg);
  background-size: cover; }
  .bg-overlay::after {
    background-color: rgba(0, 0, 0, 0.65);
    position: absolute;
    z-index: -1;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    content: ""; }
</style>