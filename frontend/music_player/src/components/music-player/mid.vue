<template>
  <section class="contact-area section-padding-100-0">
    <div class="container">
      <div class="row">
        <div class="song-info">
          <!-- 歌曲信息界面 -->
          <el-image class="song-img" :src="img" @click="toCloud"></el-image>
          <span>{{ nme }}</span>
          <div class="all-info">
            <div class="singer-info" v-for="singer in singers" :key="singer.id">
              <i class="el-icon-user icon" style="font-size:25px"></i>
              <span class="singer-name">{{ singer.name }}</span>
            </div>
            <ul class="info">
              <li class="info-content">
                <div class="song-tag">
                  <i class="el-icon-house icon"></i>标签:
                  <span v-for="(tag, index) in tags" :key="index">{{ tag }}</span>
                </div>
              </li>
              <li class="info-content">
                <i class="el-icon-s-data icon"></i>
                <span>播放量：{{ num }}</span>
              </li>
              <li class="info-content">
                <div v-if="stared && uid" @click="star">
                  <el-button size="mini" type="warning" icon="el-icon-star-off" circle></el-button>
                  <span class="star-text">取消收藏</span>
                </div>
                <div v-else-if="uid" @click="star">
                  <el-button
                    size="mini"
                    type="warning"
                    icon="el-icon-star-off"
                    :style="el_style"
                    circle
                  ></el-button>
                  <span class="star-text">收藏</span>
                </div>
              </li>
            </ul>
          </div>
        </div>
        <el-tabs v-model="activeName">
          <!-- 推荐歌曲 -->
          <el-tab-pane label="推荐歌曲" name="first">
            <el-table
              :data="recommendMusics"
              style="width: 100%"
              @row-click="toMusicPlayer"
            >
              <el-table-column prop="name" label="歌曲名称" style="width: 50%">
              </el-table-column>
              <el-table-column
                prop="singer_name"
                label="歌手"
                style="width: 50%"
              >
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="评论" name="second">
            <!-- 所有评论 -->
            <div class="comment-item" v-for="(c, index) in cmts" :key="index">
              <h4 class="comment-user">
                用户：
                <span>{{ c.name }}</span>
              </h4>
              <comment :txt="c.content"></comment>
              <div class="comment-time">
                <span>{{ c.commentTime | formatDate }}</span>
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
      <div class="contact-form-area row" v-if="uid">
        <!-- 进行评论 -->
        <div class="col-12">
          <div class="form-group">
            <h3>评论</h3>
            <textarea
              name="message"
              class="form-control"
              id="message"
              cols="30"
              rows="10"
              placeholder="我的评论"
              v-model="cmt"
            ></textarea>
          </div>
        </div>
        <div class="col-12 text-center">
          <button class="btn oneMusic-btn mt-15" type="submit" @click="comment">
            Send
            <i class="fa fa-angle-double-right"></i>
          </button>
        </div>
      </div>
    </div>

    <div class="player">
      <audio :src="musicUrl" controls autoplay></audio>
    </div>
  </section>
</template>

<script>
import RecommendMusic from "./recommend-music";
import Comment from "./comment";
import axios from "axios";

export default {
  props: ["id", "singers", "nme", "tags", "num"],
  components: {
    RecommendMusic,
    Comment,
  },
  data() {
    return {
      // name: "憨憨",
      // singers:[{id: 1, name: "憨憨"}, {id: 2, name: "智障"}],
      cmts: ["111", "222", "333", "444"],
      recommendMusics: [],
      img: require("../../assets/bg-img/breadcumb.jpg"),
      activeName: "first",
      // name: "",
      cmt: "",
      uid: null,
      musicUrl: "",
      user: null,
      stared: false,
      el_style: {
        color: `black`,
        "background-color": `white`,
        "border-color": `black`,
      },
    };
  },
  methods: {
    star() {
      console.log("star clicked");
      let u = "";
      if (!this.stared) {
        u = "http://127.0.0.1:8882/music/star";
      } else {
        u = "http://127.0.0.1:8882/music/cancelstar";
      }
      axios({
        url: u,
        method: "post",
        params: {
          mid: this.id,
          uid: this.uid,
        },
      }).then((res) => {
        console.log("success");
        this.getStarInfo();
      });
    },
    getStarInfo() {
      axios({
        url: "http://127.0.0.1:8882/user/stared",
        method: "post",
        params: {
          mid: this.id,
          uid: this.uid,
        },
      }).then((res) => {
        this.stared = res.data;
        console.log(this.stared);
      });
    },
    toCloud() {
      window.open("https://music.163.com/#/song?id=" + this.id);
      // 播放次数
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
    // 播放歌曲
    playMusic() {
      let params = new URLSearchParams();
      params.append("id", this.id);
      this.$http.post("/music/play", params);
      axios({
        url: "https://autumnfish.cn/song/url",
        method: "get",
        params: {
          id: this.id,
        },
      }).then((res) => {
        // console.log(res)
        let url = res.data.data[0].url;
        // console.log(this.$parent)
        // 直接获取父组件，可以修改任意的值
        this.musicUrl = url;
      });
    },
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
    comment() {
      console.log(this.cmt);
      if (this.cmt === "") {
        this.$message.warning("请输入内容");
        return;
      }
      let params = new URLSearchParams();
      params.append("uid", this.uid);
      params.append("mid", this.id);
      params.append("text", this.cmt);
      this.$http.post("/music/comment", params).then((res) => {
        axios({
          url: "http://127.0.0.1:8882/music/get/comments",
          method: "post",
          params: {
            id: this.id,
          },
        })
          .then((res) => {
            console.log(res.data);
            this.cmts = res.data.commentInfos;
          })
          .catch((err) => {
            console.log(err);
          });
      });
    },
    getCmtName(id, i) {
      let token = null;
      let params = new URLSearchParams();
      params.append("id", id);
      this.$http.post("/user/get", params).then((res) => {
        this.cmts[i].name = res.data.token;
      });
      return token;
    },
    toMusicPlayer(row, event, column) {
      this.$router.push("/music?id=" + row.id);
    },
  },
  created() {
    this.getPic();
    this.playMusic();
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
          this.getStarInfo();
          let p = new URLSearchParams();
          p.append("uid", this.uid);
          p.append("mid", this.id);
          this.$http.post("/music/history", p);
        })
        .catch((err) => {
          console.log(err);
        });
    } else {
      this.uid = null;
    }
  },
  mounted() {
    console.log(this.singers);
    axios({
      url: "http://127.0.0.1:8882/music/get/comments",
      method: "post",
      params: {
        id: this.id,
      },
    })
      .then((res) => {
        console.log(res.data);
        this.cmts = res.data.commentInfos;
        // 获取评论人姓名
        for (let i = 0; i < this.cmts.length; i++) {
          this.getCmtName(this.cmts[i].uid, i);
        }
      })
      .catch((err) => {
        console.log(err);
      }),
      axios({
        url: "http://127.0.0.1:8882/recommend/music",
        method: "post",
        params: {
          id: this.id,
        },
      })
        .then((res) => {
          console.log(res.data);
          this.recommendMusics = new Array();
          for (let i = 0; i < res.data.ids.length; i++) {
            this.getMusicInfo(res.data.ids[i]);
          }
        })
        .catch((err) => {
          console.log(err);
        });
  },
  filters: {
    formatDate: function (value) {
      // 时间戳转换日期格式方法
      if (value == null) {
        return "";
      } else {
        let date = new Date(value);
        let y = date.getFullYear(); // 年
        let MM = date.getMonth() + 1; // 月
        MM = MM < 10 ? "0" + MM : MM;
        let d = date.getDate(); // 日
        d = d < 10 ? "0" + d : d;
        let h = date.getHours(); // 时
        h = h < 10 ? "0" + h : h;
        let m = date.getMinutes(); // 分
        m = m < 10 ? "0" + m : m;
        let s = date.getSeconds(); // 秒
        s = s < 10 ? "0" + s : s;
        return y + "-" + MM + "-" + d + " " + h + ":" + m + ":" + s;
      }
    },
  },
};
</script>

<style scoped>
h4 {
  color: #999;
}
.player {
  background: #f1f3f4;
  height: 60px;
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
}
audio {
  width: 100%;
  border-radius: none;
  outline: none;
}
.song-info {
  position: relative;
  height: 250px;
  width: 100%;
  margin-top: 40px;
  margin-bottom: 35px;
}

.song-img {
  width: 250px;
  height: 250px;
  float: left;
}

.all-info {
  height: 250px;
  float: left;
  margin-left: 40px;
}

.info {
  font-size: 20px;
  margin-top: 20px;
}

.singer-name {
  font-size: 25px;
}

.comment-item {
  position: relative;
  padding: 15px 0 15px 56px;
  zoom: 1;
  border-bottom-width: 1px;
  border-bottom-style: solid;
  border-color: #ededed;
}

.comment-item comment {
  overflow: hidden;
  word-break: break-all;
  word-wrap: break-word;
  line-height: 24px;
  text-align: justify;
}

.comment-time {
  line-height: 28px;
  color: #999;
}

.btn {
  border: 1px solid #000;
}

.icon {
  float: left;
  width: 20px;
  height: 20px;
  margin: 5px 10px 0 3px;
}

.comment-user {
  font-weight: 400;
  margin-bottom: 6px;
  overflow: hidden;
  height: 20px;
  white-space: nowrap;
  text-overflow: ellipsis;
  font-size: 100%;
}
.comment-user span {
  color: #0c73c2;
}
.star-text {
  margin-left: 5px;
}
</style>
