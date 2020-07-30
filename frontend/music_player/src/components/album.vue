<!--
 * @Author: your name
 * @Date: 2020-07-29 14:45:50
 * @LastEditTime: 2020-07-30 14:04:34
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \music_player\src\components\album.vue
-->
<template>
  <div>
    <top></top>
    <section class="album-catagory section-padding-100-0">
      <div class="container">
        <el-tabs v-model="activeName">
          <!-- 我的收藏 -->
          <el-tab-pane label="我的收藏" name="first">
            <div class="row oneMusic-albums">
              <!-- Single Album -->
              <div
                class="col-12 col-sm-4 col-md-3 col-lg-2 single-album-item"
                v-for="(item, index) in songs"
                :key="index"
              >
                <div class="single-album" @click="toMusicPlayer(item.id)">
                  <img :src="img[index]" alt />
                  <div class="album-info">
                    <h5>{{ item.name }}</h5>
                    <p>{{ item.singers[0].name }}</p>
                  </div>
                </div>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="推荐歌曲" name="second">
            <!-- 推荐歌曲 -->
            <div class="recommend-content">
              <el-table
                :data="recommendMusics"
                style="width: 100%"
                @row-click="toMusicPlayer"
              >
                <el-table-column
                  prop="name"
                  label="歌曲名称"
                  style="width: 50%"
                ></el-table-column>
                <el-table-column
                  prop="singer_name"
                  label="歌手"
                  style="width: 50%"
                ></el-table-column>
              </el-table>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </section>
  </div>
</template>

<script>
import Top from "./album/top";
import axios from "axios";
export default {
  components: {
    Top,
  },
  data() {
    return {
      activeName: "first",
      // 收藏的歌曲信息
      songs: [],
      // 用户id
      uid: null,
      // 获取图片
      img: [],
      // 收藏歌曲id
      id: [],
      // 推荐歌曲列表
      recommendMusics: [],
    };
  },
  created() {
    this.img = [];
    // 获取当前用户id
    let token = window.sessionStorage.getItem("userToken");
    if (token) {
      console.log(token);
      let params = new URLSearchParams();
      params.append("token", token);
      this.$http
        .post("/user/authentication", params)
        .then((res) => {
          console.log(res.data);
          this.uid = res.data.id;
          // 获取所有歌曲信息
          let params = new URLSearchParams();
          axios({
            url: "http://127.0.0.1:8882/recommend/user",
            method: "post",
            params: {
              id: this.uid,
            },
          })
            .then((res) => {
              console.log(res);
              this.recommendMusics = new Array();
              for (let i = 0; i < res.data.ids.length; i++) {
                this.getMusicInfo(res.data.ids[i]);
              }
            })
            .catch((err) => {
              console.log(err);
            });
          params.append("id", this.uid);
          this.$http.post("/music/getstars", params).then((res) => {
            this.songs = res.data.data;
            console.log(this.songs);
            // 获取歌曲封面
            for (let i = 0; i < this.songs.length; i++) {
              this.getPic(this.songs[i].id);
            }
            console.log(this.img);
          });
        })
        .catch((err) => {
          console.log(err);
        });
    } else {
      this.uid = null;
    }
  },
  mounted() {},
  methods: {
    async getMusicInfo(id) {
      let params = new URLSearchParams();
      params.append("id", id);
      this.$http
        .post("/music/get", params)
        .then((res) => {
          let data = res.data.songInfo;
          data.singer_name = data.singers[0].name;
          this.recommendMusics.push(data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 获取图片方法
    getPic(id) {
      axios({
        url: "http://musicapi.leanapp.cn/song/detail",
        method: "get",
        params: {
          ids: id,
        },
      }).then((res) => {
        console.log(res.data);
        this.img.push(res.data.songs[0].al.picUrl);
      });
    },
    toMusicPlayer(row,event,column) {
      this.$router.push("/music?id=" + row.id);
    },
  },
};
</script>

<style>
.album.container {
  margin-top: 30px;
  margin-bottom: 30px;
  color: antiquewhite;
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
