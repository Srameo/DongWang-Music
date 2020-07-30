<!--
 * @Author: your name
 * @Date: 2020-07-29 14:45:50
 * @LastEditTime: 2020-07-30 01:11:23
 * @LastEditors: your name
 * @Description: In User Settings Edit
 * @FilePath: \music_player\src\components\album\top.vue
--> 
<template>
    <section class="breadcumb-area bg-img bg-overlay">
        <div class="bradcumbContent">
            <p>没人比我更懂</p>
             <div>
                <el-avatar src="http://p3.music.126.net/xm1X-fSyn05AjQZcZV05tQ==/109951165162829329.jpg?param=130y130"></el-avatar>
            </div>
            <h2>你的收藏</h2>
        </div>
    </section>
</template>

<script>
import bgImg from '../../assets/bg-img/breadcumb.jpg'

export default {
    name: 'top',
    data() {
        return {
            img: bgImg
        }
    }
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