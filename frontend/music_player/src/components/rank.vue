<!--
 * @Author: your name
 * @Date: 2020-07-28 16:07:31
 * @LastEditTime: 2020-07-29 14:44:55
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \music_player\src\components\rank.vue
-->
<template>
  <div>
     <section class="breadcumb-area bg-img bg-overlay1">
      <div class="bradcumbContent">
        <span class="sub-title">没人比我更懂</span>
        <h2>   
              <div>
                <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
              </div>
            排行榜
          </h2>
      </div>
    </section>
    <div class="album-catagory section-padding-100-0">
      <div class="container">
        <div class="row">
          <el-tabs @tab-click="handleClick">
            <el-tab-pane label="全部">
              <div class="row oneMusic-albums">
                <!-- Single Album -->
                <el-row>
                  <el-col :span="24">
                    <div class="weeks-top-area mb-100">
                      <div
                        class="section-heading text-left mb-50 wow fadeInUp"
                        data-wow-delay="50ms"
                      >
                        <p>Nobody knows</p>
                        <h2>全部排行榜</h2>
                        <p>better than me</p>
                      </div>

                      <!-- Single Top Item -->
                      <div
                        class="single-top-item d-flex align-items-center justify-content-between wow fadeInUp "
                        data-wow-delay="100ms"
                        v-for="(item, index) in list"
                        :key="index"
                      >
                        <div class="first-part d-flex align-items-center" @click="toMusicPlayer(item.id)">
                          <div class="content-">
                            <h6>{{ item.name }}</h6>
                            <p>{{ item.singers[0].name }}</p>
                          </div>
                        </div>
                        <div class="listen-counts">
                          <p>当前播放量为：{{ item.num }}</p>
                        </div>
                      </div>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </el-tab-pane>
            <el-tab-pane
              v-for="(item, index) in tags"
              :key="index"
              :label="item"
              @click="tag = item"
            >
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
                        <h2>{{ item }}排行榜</h2>
                      </div>

                      <!-- Single Top Item -->
                      <div
                        class="single-top-item d-flex align-items-center justify-content-between wow fadeInUp "
                        data-wow-delay="100ms"
                        v-for="(item, index) in list"
                        :key="index"
                      >
                        <div class="first-part d-flex align-items-center" @click="toMusicPlayer(item.id)">
                          <div class="content-">
                            <h6>{{ item.name }}</h6>
                            <p>{{ item.singers[0].name }}</p>
                          </div>
                        </div>
                        <div class="listen-counts">
                          <p>当前播放量为：{{ item.num }}</p>
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
      tags: [],
      // 当前榜单类别
      tag: "全部",
    };
  },
  watch: {
    tag() {
      if (this.tag == "全部") {
        // 按照类别获取某个榜单
        this.$http
          .get("/rank/all", {
            params: {
              limit: 10,
            },
          })
          .then((res) => {
            this.list = res.data.data;
          });
      } else {
        // 按照类别获取某个榜单
        this.$http
          .get("/rank/tag", {
            params: {
              limit: 10,
              tag: this.tag,
            },
          })
          .then((res) => {
            this.list = res.data.data;
          });
      }
    },
  },
  created() {
    // 获取全部歌曲标签名
    this.$http.get("/music/tags").then((res) => {
      this.tags = res.data;
    });

    // 按照类别获取某个榜单
    this.$http
      .get("/rank/all", {
        params: {
          limit: 10,
        },
      })
      .then((res) => {
        this.list = res.data.data;
      });
  },
  methods: {
	  toMusicPlayer(id) {
		  this.$router.push("/music?id=" + id)
	  },
    // 点击导航栏时更改tag标签
    handleClick(tab, event) {
      console.log(tab, event);
      this.tag = tab.label;
    },
  },
};
</script>

<style>
.bg-overlay1 {
  position: relative;
  z-index: 2;
  background-position: center center;
  background-image: url("../assets/bg-img/breadcumb2.jpg");
  background-size: cover; }
.nav-background {
  background-image: url("../assets/bg-img/blog2.jpg");
}
.el-tabs {
  width: 100%;
}
.el-row {
  width: 100%;
}
</style>
