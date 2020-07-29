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
                  <el-tabs @tab-click="handleClick">
            <el-tab-pane
              v-for="(item, index) in id"
              :key="index"
              :label="item.name"
            >
              {{ item.name }}
                    <div class="single-new-item d-flex align-items-center justify-content-between wow fadeInUp" 
                      data-wow-delay="300ms" >
                                <div class="first-part d-flex align-items-center">
                                    <div class="thumbnail">
                                        <img :src="item.coverImgUrl" alt=""/>
                                    </div>
                                    <div class="content-"  v-for="(item,index) in name" :key="index">
                                        <h6>歌曲名</h6>
                                    </div>
                                    <div class="content-"  v-for="(item,index) in  singers" :key="index">
                                        <p>歌手</p>
                                    </div>
                                </div>
                                
                    </div>
              </div>
          </div>
      </div>
 
  </div>
</template>

<script>
import Top from './album/top';
import axios from "axios";
export default {
    name:'album',
    components:{
        Top,
    },
    data() {
        return {
            id: "",
			name: "",
			singers: []
        }
    },
    created() {

    axios({
      url: "https://autumnfish.cn/top/playlist/",
      method: "get",
      params: {
        limit: 10,
      },
    }).then((res) => {
      console.log(res);
      this.list = res.data.playlists;
    });
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
  },
	
    
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