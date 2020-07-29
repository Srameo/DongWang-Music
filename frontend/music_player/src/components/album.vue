<template>
  <div>
      <top></top>
      <div class="album.container">
          <div class="al-margin">
              <div class="al-first">
                  <div class="text-centor">
                      <h2>推荐歌单</h2>
                  </div>
                  <div class="al-float-left">
                      <img src="./assets/core-img/time.jpg">
                  </div>
                  <div class="al-float-right">
                      <h4>歌单名</h4>
                  </div>
              </div>

              <div class="al-second">

              </div>
          </div>
      </div>
 
  </div>
</template>

<script>
import Top from './album/top'

export default {
    name:'album',
    components:{
        Top,
    }
    
}
</script>

<style>
.album.container {
    margin-top: 30px;
    margin-bottom: 30px;
    color:antiquewhite;
    width: 100%;
}
.al-margin {
    margin-top: 15px;
    margin-bottom: 15px;
}
.al-float-left {
    float: left;
    width: 40%;
}
.al-float-right {
    margin-top: 150px;
    float: left;
    width: 40%;
}
.text-centor {
    text-align: center;
    width: 100%;
}
</style>