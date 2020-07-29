<template>
  <div class="result-container">
    <section class="breadcumb-area bg-img bg-overlay" >
        <div class="bradcumbContent">
            <!-- <span class="sub-title">找到 {{ count }} 个结果</span> -->
            <h2><i class="el-icon-service" style="color:lightblue;"></i>{{ $route.query.q }}</h2>
        </div>
    </section>
    <div class="hello">
        <el-tabs v-model="activeIndex">
            <el-tab-pane label="歌曲" name="songs">
                <table class="el-table">
                <thead>
                    <th></th> 
                    <th>音乐标题</th>
                    <th>歌手</th>
                    <th>标签</th>
                    <th>播放量</th>
                </thead>
                <tbody>
                    <tr
                    v-for="(item, index) in songList"
                    :key="index"
                    class="el-table__row"
                    >
                    <!-- <button @click="playMusic(item.id)"></button> -->
                        <td>{{index+1}}</td>
                        <td>
                            <div class="song-wrap">
                            <div class="name-wrap">
                                <!-- 歌名 -->
                                <span>{{ item.name }}</span>
                                <!-- mv的图标 -->
                                <span v-if="item.mvid != 0" class="iconfont icon-mv "><i class="el-icon-video-play"></i></span>
                            </div>
                            <!-- 二级标题 -->
                            <!-- <span v-if="item.alias.length != 0">{{ item.alias[0] }}</span> -->
                            </div>
                        </td>
                        <td>{{ item.singers[0].name }}</td>
                        <td><span v-for="tag in item.tags" :key="tag">{{ tag }} </span></td>
                        <td>{{ item.num }}</td>
                    </tr>
                </tbody>
                </table>
            </el-tab-pane>
            <el-tab-pane label="歌单" name="lists">
                <div class="items">
                <div
                    v-for="(item, index) in playlists"
                    :key="index"
                    class="item"
                    @click="toPlaylist(item.id)"
                >
                    <div class="img-wrap">
                    <div class="num-wrap">
                        播放量:
                        <span class="num">{{ item.playCount }}</span>
                    </div>
                    <img :src="item.coverImgUrl" alt="" />
                    <span class="iconfont icon-play"><i class ="el-icon-caret-right"></i></span>
                    </div>
                    <p class="name">{{ item.name }}</p>
                </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="MV" name="mv">
                <div class="items mv">
                <div v-for="(item, index) in mv" :key="index" class="item" @click="toMV(item.id)">
                    <div class="img-wrap">
                    <!-- 封面 -->
                    <img :src="item.cover" alt="" />
                    <span class="iconfont icon-play"><i class ="el-icon-caret-right"></i></span>
                    <div class="num-wrap">
                        <div class="iconfont icon-play"></div>
                        <!-- 播放次数 -->
                        <div class="num">{{ item.playCount }}</div>
                    </div>
                    <!-- 持续时间 -->
                    <span class="time">{{ item.duration }}</span>
                    </div>
                    <div class="info-wrap">
                    <!-- mv名 -->
                    <div class="name">{{ item.name }}</div>
                    <!-- 歌手名 -->
                    <div class="singer">{{ item.artistName }}</div>
                    </div>
                </div>
                </div>
            </el-tab-pane>
        </el-tabs>
    </div>
  </div>
</template>

<script>
  // 导入 aixos
  import axios from 'axios'
//   import top from './contact/top'
  // import bgImg from '../assets/bg-img/bg-3.jpg'
  export default {
    name: 'result',
    data() {
      return {
        img: bgImg,
        // tab切换时 会改变的数据
        activeIndex: 'songs',
        // 保存 查询歌曲
        songList: [],
        // 保存歌单的字段
        playlists: [],
        // 保存mv的字段
        mv: [],
        // 搜索结果的总数
        count: 0
      }
    },
    // 生命周期钩子 回调函数
    created() {
		this.refresh()
    },
    // 侦听器
    watch: {
      activeIndex() {
        // songs 歌曲
        // lists 歌单
        // mv    mv
        let type = 1

        // 获取个数
        let limit = 10
        switch (this.activeIndex) {
          case 'songs':
            type = 1
            limit = 10
            break
          case 'lists':
            type = 1000
            limit = 10
            break
          case 'mv':
            type = 1004
            limit =8
            break

          default:
            break
        }
		if(type === 1) {
			this.refresh()
			return
		}

        axios({
          url: 'https://autumnfish.cn/search',
          method: 'get',
          params: {
            keywords: this.$route.query.q,
            type, // type:type,
            // 获取的数据量
            limit // limit: limit
          }
        }).then(res => {
          console.log(res)
		  console.log(type)
          if (type == 1000) {
            // 歌单的逻辑
            this.playlists = res.data.result.playlists
            // 总数
            this.count = res.data.result.playlistCount
            // 处理 播放次数
            for (let i = 0; i < this.playlists.length; i++) {
              if (this.playlists[i].playCount > 100000) {
                this.playlists[i].playCount =
                  parseInt(this.playlists[i].playCount / 10000) + '万'
              }
            }
          } else {
            // 保存mv数据
            this.mv = res.data.result.mvs
            // 总数
            this.count = res.data.result.mvCount
            // 处理数据
            for (let i = 0; i < this.mv.length; i++) {
              // 时间
              let min = parseInt(this.mv[i].duration / 1000 / 60)
              let sec = parseInt((this.mv[i].duration / 1000) % 60)
              if (min < 10) {
                min = '0' + min
              }
              if (sec < 10) {
                sec = '0' + sec
              }
              this.mv[i].duration = min + ':' + sec

              // 播放次数
              if (this.mv[i].playCount > 100000) {
                this.mv[i].playCount =
                  parseInt(this.mv[i].playCount / 10000) + '万'
              }
            }
          }
        })
      }
    },
    // 方法
    methods: {
		refresh() {
			axios({
			  url: 'http://127.0.0.1:8882/search',
			  method: 'post',
			  params: {
			    key: this.$route.query.q,
			  }
			}).then(res => {
			  console.log(res)
			
			  this.songList = res.data.data
			  console.log(this.songList )
			  // 计算歌曲时间
			  // for (let i = 0; i < this.songList.length; i++) {
			  //   let min = parseInt(this.songList[i].duration / 1000 / 60)
			  //   let sec = parseInt((this.songList[i].duration / 1000) % 60)
			  //   if (min < 10) {
			  //     min = '0' + min
			  //   }
			  //   if (sec < 10) {
			  //     sec = '0' + sec
			  //   }
			  //   // console.log(min + '|' + sec)
			  //   this.songList[i].duration = min + ':' + sec
			  // }
			  // 保存总数
			  this.count = res.data.data.length
			})
			this.InitializeData()
			this.getDataByKeywordsAsync({
						V: this,
						// keywords: this.search
					})
		},
      // 去mv详情页
      toMV(id){
        this.$router.push(`/mv?q=${id}`)
      },
      // 去歌单详情页
      toPlaylist(id){
        // 跳转并携带数据即可
        this.$router.push(`/playlist?q=${id}`)
      },
      playMusic(id) {
        axios({
          url: 'https://autumnfish.cn/song/url',
          method: 'get',
          params: {
            id // id:id
          }
        }).then(res => {
          // console.log(res)
          let url = res.data.data[0].url
          // console.log(this.$parent.musicUrl)
          // 设置给父组件的 播放地址
          this.$parent.musicUrl = url
        })
      }
    }
  }
</script>

<style>
.bg-overlay {
  position: relative;
  z-index: 2;
  background-position: center center;
  background-image: url(../assets/bg-img/breadcumb.jpg);
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
.el-tabs__active-bar {
  background-color: #c3473a;
}

.el-tabs__item:hover,
.el-tabs__item.is-active {
  color: #c3473a;
}

.el-table th > .cell {
  font-weight: normal;
  color: black;
}

.el-pagination {
  margin-top: 50px;
  text-align: center;
}

.el-pagination.is-background .el-pager li:not(.disabled).active {
  background-color: #dd6d60;
}

.el-table {
  border-bottom: none;
  border-collapse: collapse;
}

.el-table.playlit-table th:nth-child(2) {
  width: 130px;
}

.el-table.playlit-table th:nth-child(3) {
  width: 300px;
}

.el-table.playlit-table th:nth-child(4) {
  width: 200px;
}

.el-table tbody {
  border-bottom: none;
}

.el-table td {
  border-bottom: none;
}

.el-table td:first-child {
  padding-left: 10px;
}

.el-table th {
  font-weight: normal;
}

.el-table th:first-child {
  width: 50px;
}

.el-table th:nth-child(2) {
  width: 300px;
}

.el-table th:nth-child(3) {
  width: 200px;
}

.el-table .song-wrap > span {
  margin-top: 20px;
  display: inline-block;
  color: #bebebe;
}

.el-table .song-wrap .icon-mv {
  padding-left: 5px;
  color: #dd6d60;
}

.el-table .img-wrap {
  position: relative;
  width: 70px;
  height: 70px;
}

.el-table .img-wrap img {
  width: 70px;
  height: 70px;
  border-radius: 5px;
}

.el-table .img-wrap .icon-play {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 25px;
  height: 25px;
  color: #dd6d60;
  font-size: 12px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(255, 255, 255, 0.8);
}

.el-table .img-wrap .icon-play::before {
  transform: translateX(1px);
}

.el-table tr:nth-child(2n) {
  background-color: #fafafa;
}

.el-table tr:hover {
  background-color: #f5f7fa;
}

.discovery-container .el-carousel__container {
  height: 230px;
}
 .icon-mv {
  padding-left: 5px;
  color: #dd6d60;
}
.hello {
    margin: 120px
}
.result-container .el-tabs__item {
  font-size: 18px;
}

.result-container .el-table td,
.result-container .el-table th.is-leaf {
  border-bottom: none;
}

.result-container .items {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
}

.result-container .items .item {
  width: 200px;
  cursor: pointer;
  margin-right: 20px;
  margin-bottom: 20px;
}

.result-container .items .item .img-wrap {
  width: 100%;
  position: relative;
}

.result-container .items .item .img-wrap > .icon-play {
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

.result-container .items .item .img-wrap > .icon-play::before {
  transform: translateX(3px);
}

.result-container .items .item .img-wrap:hover > .icon-play {
  opacity: 1;
}

.result-container .items .item .img-wrap img {
  width: 100%;
  border-radius: 5px;
}

.result-container .items .item .img-wrap .num-wrap {
  position: absolute;
  color: white;
  top: 0;
  right: 0;
  display: flex;
  align-items: center;
  font-size: 15px;
  padding-right: 5px;
  padding-top: 2px;
}

.result-container .items .item .img-wrap .num-wrap .icon-play {
  font-size: 12px;
  display: flex;
  align-items: center;
  margin-right: 5px;
}

.result-container .items .item .img-wrap .time {
  position: absolute;
  bottom: 5px;
  right: 5px;
  color: white;
  font-size: 15px;
}

.result-container .items .item .name {
  font-size: 15px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
}

.result-container .items .item .singer {
  font-size: 14px;
  color: #c5c5c5;
}

.result-container .items.mv .item {
  width: 250px;
}

</style>
