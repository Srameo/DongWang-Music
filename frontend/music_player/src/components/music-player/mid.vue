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
							<!-- <i class="el-icon-user"></i> -->
							<span class="singer-name">
								{{ singer.name }}
							</span>
						</div>
						<ul class="song-info">
							<li>
								<div>标签: <span v-for="(tag, index) in tags">{{tag }} </span></div>
							</li>
							<li>播放量：{{num}}</li>
						</ul>
					</div>
				</div>
				<el-tabs v-model="activeName">
					<!-- 推荐歌曲 -->
					<el-tab-pane label="推荐歌曲" name="first">
						<el-table :data="recommendMusics" style="width: 100%">
							<el-table-column prop="name" label="歌曲名称" style="width: 50%">
							</el-table-column>
							<el-table-column prop="singer_name" label="歌手" style="width: 50%">
							</el-table-column>
						</el-table>
					</el-tab-pane>
					<el-tab-pane label="评论" name="second">
						<!-- 所有评论 -->
						<div class="comment-item" v-for="(c, index) in cmts" :key="index">
							<comment :txt="c.content"></comment>
							<span>{{ c.commentTime }}</span>
						</div>
					</el-tab-pane>
				</el-tabs>
			</div>
			<div class="contact-form-area row" v-if="uid">
				<!-- 进行评论 -->
				<div class="col-12">
					<div class="form-group">
						<h2>评论</h2>
						<textarea name="message" class="form-control" id="message" cols="30" rows="10" placeholder="我的评论" v-model="cmt"></textarea>
					</div>
				</div>
				<div class="col-12 text-center">
					<button class="btn oneMusic-btn mt-15" type="submit" @click="comment">
						Send<i class="fa fa-angle-double-right"></i>
					</button>
				</div>
			</div>
		</div>

		<div class="player">
			<audio :src='musicUrl' controls autoplay></audio>
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
				musicUrl: ""
			};
		},
		methods: {
			// 播放歌曲
			playMusic() {
				let params = new URLSearchParams();
				params.append("id", this.id)
				this.$http.post("/music/play", params)
				axios({
					url: 'https://autumnfish.cn/song/url',
					method: 'get',
					params: {
						id: this.id
					}
				}).then(res => {
					// console.log(res)
					let url = res.data.data[0].url
					// console.log(this.$parent)
					// 直接获取父组件，可以修改任意的值
					this.musicUrl = url;
				})
			},
			toCloud() {
				window.open("https://music.163.com/#/song?id=" + this.id)
				// 播放次数
			},
			async getMusicInfo(id) {
				axios({
						url: "http://127.0.0.1:8882/music/get",
						method: "post",
						params: {
							id: id,
						},
					})
					.then((res) => {
						let data = res.data.songInfo;
						data.singer_name = data.singers[0].name
						this.recommendMusics.push(data);
					})
					.catch((err) => {
						console.log(err);
					});
			},
			comment() {
				console.log(this.cmt)
				if (this.cmt === "") {
					this.$message.warning('请输入内容')
					return
				}
				let params = new URLSearchParams();
				params.append("uid", this.uid)
				params.append("mid", this.id)
				params.append("text", this.cmt)
				this.$http.post("/music/comment", params).then(res => {
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
						})
				})
			}
		},
		created() {
			this.playMusic()
			let token = window.sessionStorage.getItem('userToken')
			if (token) {
				console.log(token)
				let params = new URLSearchParams();
				params.append("token", token)
				this.$http.post("/user/authentication",
					params).then(res => {
					console.log(res.data)
					this.uid = res.data.id
					let p = new URLSearchParams();
					p.append("uid", this.uid)
					p.append("mid", this.id)
					this.$http.post("/music/history",
						p)
				}).catch(err => {
					console.log(err)
				})
			} else {
				this.uid = null
			}
		},
		mounted() {

			console.log(this.singers)
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
	};
</script>

<style scoped>
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

	.song-info {
		margin-top: 20px;
	}

	.singer-name {
		font-size: 16px;
	}

	.comment-item {
		position: relative;
		padding: 15px 0 15px 56px;
		zoom: 1;
		border-bottom-width: 1px;
		border-bottom-style: solid;
		border-color: #ededed;
	}

	.form-control {
		position: relative;
		overflow: hidden;
		height: 102px;
		border: solid 1px #ececec;
		background-color: #f5f5f5;
	}
</style>
