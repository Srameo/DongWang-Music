<!--
 * @Author: your name
 * @Date: 2020-07-28 16:07:31
 * @LastEditTime: 2020-07-29 13:02:46
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \music_player\src\components\rank.vue
-->
<template>
  <div>
    <div class="breadcumb-area bg-img bg-overlay nav-background"></div>
    <div class="album-catagory section-padding-100-0">
      <div class="container">
        <div class="row">
          <el-tabs @tab-click="handleClick">
            <el-tab-pane
              v-for="(item, index) in ranks"
              :key="index"
              :label="item.name"
            >
              {{ item.name }}
              <div class="row oneMusic-albums">
                <!-- Single Album -->
                <el-row>
                  <el-col :span="24">
                    <div class="weeks-top-area mb-100">
                      <div
                        class="section-heading text-left mb-50 wow fadeInUp"
                        data-wow-delay="50ms"
                      >
                        <p>See what’s new</p>
                        <h2>This week’s top</h2>
                      </div>

                      <!-- Single Top Item -->
                      <div
                        class="single-top-item d-flex align-items-center justify-content-between wow fadeInUp "
                        data-wow-delay="100ms" v-for="(item, index) in list" :key="index"
                      >
                        <div class="first-part d-flex align-items-center">
                          <div class="thumbnail">
                            <img :src="item.coverImgUrl" alt="" />
                          </div>
                          <div class="content-">
                            <h6>Sam Smith</h6>
                            <p>Underground</p>
                          </div>
                        </div>
                        <div class="listen-counts">
                          <p>
                            当前播放量为：{{item.playCount}}
                          </p>
                        </div>
                      </div>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 排行榜
      ranks: [],
      // 榜单数据
      list: [],
      // 歌曲类别
      tag:[],
    };
  },
  created() {
    // 获取全部歌曲标签名
    this.$http.get('/music/tags').then(res=>{
      console.log(res)
    })
    // 获取某个排行榜内容
    // axios({
    //   url: "https://autumnfish.cn/top/playlist/",
    //   method: "get",
    //   params: {
    //     limit: 10,
    //     offset: 0,
    //     cat: "全部",
    //   },
    // }).then((res) => {
    //   console.log(res);
    //   this.list = res.data.playlists;
    // });
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
  },
};
</script>

<style>
.nav-background {
  background-image: url("../assets/core-img/breadcumb3.jpg");
}
.el-tabs {
  width: 100%;
}
.el-row {
  width: 100%;
}
</style>
