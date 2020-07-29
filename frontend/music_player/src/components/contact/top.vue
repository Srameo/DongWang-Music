<template>
    <section class="breadcumb-area bg-img bg-overlay">
        <div class="bradcumbContent">
            <p>中软小组</p>
            <el-avatar src="http://p2.music.126.net/P6onbFsTVrhKa4m1pu0eSg==/2938994581268613.jpg"></el-avatar>
            
            <h2>Contact</h2>
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