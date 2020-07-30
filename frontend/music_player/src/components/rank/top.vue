<template>
    <section class="breadcumb-area bg-img bg-overlay">
        <div class="bradcumbContent">
        <span class="sub-title">没人比我更懂</span>
        <h2>
          <div>
            <el-avatar src="http://p2.music.126.net/pVik5zMtELjKz-zUxIqW7w==/109951164827431706.jpg"></el-avatar>
          </div>排行榜
        </h2>
        </div>
    </section>
</template>

<script>
export default {
    
}
</script>

<style scoped>
.bg-overlay {
  position: relative;
  z-index: 2;
  background-position: center center;
  background-image: url(../../assets/bg-img/breadcumb2.jpg);
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