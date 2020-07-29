<!--
 * @Author: your name
 * @Date: 2020-07-29 14:45:50
 * @LastEditTime: 2020-07-30 00:09:33
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
          >
            <div class="single-album">
              <img src="" alt="" />
              <div class="album-info">
                <h5><!-- 歌曲名 --></h5>
                <p><!-- 歌手名 --></p>
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
      uid : null,
      // 获取图片
      img : null,
      // 收藏歌曲id
      id : [],
    };
  },
  created() {
    // 获取图片
    this.getPic();
    // 获取收藏的歌曲
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
          console.log(this.uid)
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
    getPic() {
      axios({
        url: "http://musicapi.leanapp.cn/song/detail",
        method: "get",
        params: {
          ids: this.id,
        },
      }).then((res) => {
        console.log(res.data);
        this.img = res.data.songs[0].al.picUrl;
      });
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
