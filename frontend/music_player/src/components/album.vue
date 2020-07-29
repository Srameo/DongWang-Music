<!--
 * @Author: your name
 * @Date: 2020-07-29 14:45:50
 * @LastEditTime: 2020-07-30 01:22:54
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \music_player\src\components\album.vue
-->
<template>
  <div>
    <top></top>
    <section class="album-catagory section-padding-100-0">
      <div class="container">
        <div class="row oneMusic-albums">
          <!-- Single Album -->
          <div
            class="col-12 col-sm-4 col-md-3 col-lg-2 single-album-item"
            v-for="(item, index) in songs"
            :key="index"
          >
            <div class="single-album" @click="toMusicPlayer(item.id)">
              <img :src="img[index]" alt="" />
              <div class="album-info">
                <h5>{{ item.name }}</h5>
                <p>{{ item.singers[0].name }}</p>
              </div>
            </div>
          </div>
        </div>
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
      // 收藏的歌曲信息
      songs: [],
      // 用户id
      uid: null,
      // 获取图片
      img: [],
      // 收藏歌曲id
      id: [],
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
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
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
    toMusicPlayer(id) {
      this.$router.push("/music?id=" + id);
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
