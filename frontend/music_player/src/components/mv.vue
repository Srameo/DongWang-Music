<template>
  <div>
    <section class="breadcumb-area bg-img bg-overlay">
      <div class="bradcumbContent">
        <span class="sub-title">没人比我更懂</span>
        <h2>播放
          <div> 
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          </div>MV
        </h2>
      </div>
    </section>
    <div class="outer-container">
      <div class="mv-container">
        <div class="mv-wrap">
          <h3 class="title">💫mv详细信息🦄</h3>
          <!-- mv -->
          <div class="video-wrap">
            <video controls :src="url" autoplay></video>
          </div>
          <!-- mv信息 -->
          <div class="info-wrap">
            <div class="singer-info">
              <div class="avatar-wrap">
                <!-- 头像 -->
                <img :src="icon" alt />
              </div>
              <!-- 歌手名 -->
              <span class="name">{{ mvInfo.artistName }}</span>
            </div>
            <div class="mv-info">
              <!-- 标题 -->
              <h6 class="title">{{ mvInfo.name }}</h6>
              <!-- 播放次数 -->
              <span class="number">播放：{{ mvInfo.playCount }}次</span>
              <!-- 描述 -->
              <p class="desc">{{ mvInfo.desc }}</p>
            </div>
          </div>
          <!-- 精彩评论 -->
          <div class="comment-wrap">
            <p class="title">
              精彩评论
              <span class="number">{{comments.length}}</span>
            </p>
            <div class="comments-wrap" v-for="(item,index) in comments" :key='index'>
              <div class="item">
                <div class="icon-wrap">
                  <img :src="item.user.avatarUrl" alt />
                </div>
                <div class="content-wrap">
                  <div class="content">
                    <span class="name">{{item.user.nickname}}</span>
                    <span class="comment">: {{item.content}}</span>
                  </div>
                  <div class="date">{{ item.time | dateFormat }}</div>
                </div>
                </div>
              </div>
            </div>
          </div>
          <!-- 最新评论  不要了-->
          <!-- 分页器 -->
          <el-pagination
            @current-change="handleCurrentChange"
            background
            layout="prev, pager, next"
            :total="total"
            :current-page="page"
            :page-size="5"
            :limit="limit"
          ></el-pagination>
        </div>
        <!-- 相关推荐 -->
        <div class="mv-recommend">
          <h3 class="title">相关推荐</h3>
          <div class="mvs">
            <div class="items">
              <div v-for="(item, index) in simiMvs" :key="index" class="item">
                <div class="img-wrap" @click='toMv(item.id)'>
                  <img :src="item.cover" alt />
                  <span class="iconfont icon-play" ><i class="el-icon-caret-right"></i></span>
                  <div class="num-wrap">
                    <div class="iconfont icon-play"></div>
                    <div class="num">{{ item.playCount | playCountFilter}}</div>
                  </div>
                  <span class="time">{{ item.duration | timeFormat}}</span>
                </div>
                <div class="info-wrap">
                  <div class="name">{{ item.name }}</div>
                  <div class="singer">{{ item.artistName }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div>
        </div>
      </div>
    </div>
</template>

<script>
// 导入 axios
import axios from "axios";
export default {
  name: "mv",
  data() {
    return {
      // 总条数
      total: 20,
      // 页码
      page: 1,
      // 页容量
      limit: 10,
      // mv地址
      url: "",
      // 相关mv
      simiMvs: [],
      // mv的信息
      mvInfo: {},
      // 头像
      icon: "",
      comments:[],
    };
  },
  created() {
    // this.InitializeData()
    // 根据 id 查询对应id详情信息
    /* this.getMvUrlByIdAsync({
			V: this,
			id: this.id
		}) */
    // 获取mv播放地址
    axios({
      url: "https://autumnfish.cn/mv/url",
      method: "get",
      params: {
        // 获取url中的 id

        id: this.$route.query.q,
      },
    }).then((res) => {
      // console.log(this.$route.query.q);
      // console.log("加载地址");
      this.url = res.data.data.url;
    });
    // 获取 相关的mv
    axios({
      url: "https://autumnfish.cn/simi/mv",
      method: "get",
      params: {
        mvid: this.$route.query.q,
      },
    }).then((res) => {
      // console.log(res)
      // 保存相关MV
      this.simiMvs = res.data.mvs;

    });

    // 获取 mv的信息
    axios({
      url: "https://autumnfish.cn/mv/detail",
      method: "get",
      params: {
        mvid: this.$route.query.q,
      },
    }).then((res) => {
      // console.log(res)
      // mv的信息
      this.mvInfo = res.data.data;
      // 获取 歌手信息
      axios({
        url: "https://autumnfish.cn/artists",
        method: "get",
        params: {
          id: this.mvInfo.artists[0].id,
        },
      }).then((res) => {
        // console.log(res);
        // 歌手的封面信息
        this.icon = res.data.artist.picUrl;
      });
    });

    // 获取评论数据
    axios({
      url: "https://autumnfish.cn/comment/mv",
      method: "get",
      params: {
        id: this.$route.query.q,
        limit: 10,
        offset: 0,
      },
    }).then((res) => {
      
      this.comments=res.data.hotComments;
      console.log(res.data.hotComments);
      						// 计算歌曲时间
						for (let i = 0; i < this.comments.length; i++) {
						  let min = parseInt(this.comments[i].time / 1000 / 60)
						  let sec = parseInt((this.comments[i].time / 1000) % 60)
						  if (min < 10) {
						    min = '0' + min
						  }
						  if (sec < 10) {
						    sec = '0' + sec
						  }
						  // console.log(min + '|' + sec)
						  this.comments[i].time = min + ':' + sec
						}
						// 保存总数
    });
  },
  methods: {
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    toMv(id){
			this.$router.push(`/mv?q=${id}`)
		  this.$router.go(0)
    }
  },
};
</script>

<style>
.bg-overlay {
  position: relative;
  z-index: 2;
  background-position: center center;
  background-image: url("../assets/bg-img/bg-4.jpg");
  background-size: cover;
}

.outer-container{
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-around;
}

.mv-container {
  /* display: flex; */
  padding: 50px 100px;
  /* margin: 0px 100px; */
}

.mv-container .title {
  margin-bottom: 10px;
}

.mv-container .mv-wrap {
  width: 700px;
  margin-right: 35px;
}

.mv-container .mv-wrap .date {
  margin-right: 25px;
}

.mv-container .mv-wrap .date,
.mv-container .mv-wrap .number {
  color: #bebebe;
  font-size: 14px;
}

.mv-container .mv-wrap .video-wrap {
  width: 100%;
  height: 390px;
  margin-bottom: 20px;
}

.mv-container .mv-wrap .video-wrap video {
  border-radius: 5px;
  height: 100%;
  width: 100%;
  outline: none;
}

.mv-container .mv-wrap .info-wrap {
  margin-bottom: 50px;
}

.mv-container .mv-wrap .info-wrap .singer-info {
  display: flex;
  align-items: center;
  margin-bottom: 35px;
}

.mv-container .mv-wrap .info-wrap .singer-info .avatar-wrap {
  width: 70px;
  height: 70px;
  border-radius: 50%;
  margin-right: 10px;
  overflow: hidden;
}

.mv-container .mv-wrap .info-wrap .singer-info .avatar-wrap img {
  height: 100%;
}

.mv-container .mv-wrap .info-wrap .mv-info .title {
  font-size: 30px;
}

.mv-container .mv-wrap .info-wrap .mv-info .desc {
  font-size: 15px;
  margin-top: 20px;
}

.mv-container .mv-wrap .comment-wrap {
  margin-bottom: 70px;
}

.mv-container .mv-wrap .comment-wrap .title {
  font-size: 25px;
}

.mv-container .mv-wrap .comment-wrap .title .number {
  color: black;
  font-size: 20px;
}

.mv-container .mv-wrap .comment-wrap .item {
  display: flex;
  padding-top: 20px;
}

.mv-container .mv-wrap .comment-wrap .item .icon-wrap {
  margin-right: 15px;
}

.mv-container .mv-wrap .comment-wrap .item .icon-wrap img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.mv-container .mv-wrap .comment-wrap .item:not(:last-child) .content-wrap {
  border-bottom: 1px solid #f2f2f1;
}

.mv-container .mv-wrap .comment-wrap .item .content-wrap {
  padding-bottom: 20px;
  flex: 1;
}

.mv-container .mv-wrap .comment-wrap .item .content-wrap .name {
  color: #517eaf;
  font-size: 14px;
}

.mv-container .mv-wrap .comment-wrap .item .content-wrap .comment {
  font-size: 14px;
}

.mv-container .mv-wrap .comment-wrap .item .content-wrap .content,
.mv-container .mv-wrap .comment-wrap .item .content-wrap .re-content {
  margin-bottom: 10px;
}

.mv-container .mv-wrap .comment-wrap .item .content-wrap .re-content {
  padding: 10px;
  background-color: #e6e5e6;
  border-radius: 5px;
}

.mv-recommend {
  flex: 1;
  width: 500px;
}

.mv-recommend .title{
  margin: 50px 0;
}

.mv-recommend .mvs .items {
  display: flex;
  flex-wrap: wrap;
}

.mv-recommend .mvs .items .item {
  cursor: pointer;
  width: 100%;
  display: flex;
  align-items: center;
  padding: 10px;
  cursor: pointer;
}

.mv-recommend .mvs .items .item:hover {
  background-color: #f5f5f5;
}

.mv-recommend .mvs .items .item .img-wrap {
  width: 180px;
  position: relative;
  margin-right: 10px;
}

.mv-recommend .mvs .items .item .img-wrap > .icon-play {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 40px;
  height: 40px;
  color: #dd6d60;
  font-size: 20px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(255, 255, 255, 0.8);
  opacity: 0;
}

.mv-recommend .mvs .items .item .img-wrap > .icon-play::before {
  transform: translateX(3px);
}

.mv-recommend .mvs .items .item .img-wrap:hover > .icon-play {
  opacity: 1;
}

.mv-recommend .mvs .items .item .img-wrap img {
  width: 100%;
  border-radius: 5px;
}

.mv-recommend .mvs .items .item .img-wrap .num-wrap {
  position: absolute;
  color: white;
  top: 0;
  right: 0;
  display: flex;
  align-content: center;
  font-size: 15px;
  padding-right: 5px;
  padding-top: 2px;
}

.mv-recommend .mvs .items .item .img-wrap .num-wrap .icon-play {
  font-size: 12px;
  display: flex;
  align-items: center;
  margin-right: 5px;
}

.mv-recommend .mvs .items .item .img-wrap .time {
  position: absolute;
  bottom: 5px;
  right: 5px;
  color: white;
  font-size: 15px;
}

.mv-recommend .mvs .items .item .info-wrap {
  flex: 1;
}

.mv-recommend .mvs .items .item .info-wrap .name {
  font-size: 15px;
}

.mv-recommend .mvs .items .item .info-wrap .singer {
  font-size: 14px;
  color: #c5c5c5;
}
</style>
