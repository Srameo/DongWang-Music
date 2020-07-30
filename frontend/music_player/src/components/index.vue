<template>
	<!-- 页面顶部组件 -->
	<div id="index">
		<!-- 滚动条返回顶部 -->
		<transition>
			<div id="scrollUp" @click="goBackToTop" :style="btn_style">
				回
			</div>
		</transition>
		<div class="header-area" :style="this.style">
			<!--   页面标题  -->
			<!-- Navbar Area -->
			<div class="oneMusic-main-menu">
				<div class="classy-nav-container breakpoint-off">
					<div class="container">
						<!-- Menu -->
						<nav class="classy-navbar justify-content-between" id="oneMusicNav">

							<!-- Nav brand -->
							<div class="col-2 ">
								<router-link to="/index" class="nav-brand"><img src="../assets/core-img/logo.png" alt="">
								</router-link>
							</div>
							<!-- 后退前进刷新 -->
							<div class="col-1 ">
								<el-tooltip content="后退一步" :enterable="false" placement="bottom" effect="light">
									<i @click="back()" class="el-icon-arrow-left" style="color:Gold;"></i>
								</el-tooltip>
								<el-tooltip content="前进一步" :enterable="false" placement="bottom" effect="light">
									<i @click="go()" class="el-icon-arrow-right" style="color:lightgreen;"></i>
								</el-tooltip>
								<el-tooltip content="刷新页面" :enterable="false" placement="bottom" effect="light">
									<i @click="reload()" class="el-icon-refresh-right" style="color:white;"></i>
								</el-tooltip>
							</div>
							<!-- Menu -->
							<div class="classy-menu col-12">

								<!-- Close Button -->
								<div class="classycloseIcon">
									<div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
								</div>

								<!-- Nav Start -->
								<div class="classynav">
									<ul>
										<li>
											<router-link to="/main">主页</router-link>
										</li>
										<li>
											<router-link to="/album">我的收藏</router-link>
										</li>
										<li>
											<router-link to="/mvs">歌曲MV</router-link>
										</li>
										<li>
											<router-link to="/rank">排行榜</router-link>
										</li>
										<li>
											<router-link to="/contact">联系我们</router-link>
										</li>
									</ul>

									<!-- Login/Register & Cart Button -->
									<div class="login-register-cart-button d-flex align-items-center">
										<!-- 搜索框-->
										<div class="cart-btn">
											<div class="col-lg-9 ">
												<div class="el-input el-input--small el-input--prefix">
													<!-- 搜索框 -->
													<input type="text" autocomplete="off" placeholder="搜索" class="el-input__inner" v-model="inputValue"
													 @keyup.enter="toResult" />
													<span class="el-input__prefix">
														<i class="el-input__icon el-icon-search"></i>
													</span>
												</div>
											</div>
										</div>
										<!-- Login/Register -->
										<div class="login-register-btn mr-50 ">
											<router-link to="/login" id="loginBtn" v-if="notLogin">登录 </router-link>
											<router-link to="/register" id="registerBtn" v-if="notLogin">注册</router-link>
											<el-button type="text" v-else @click="logout">Quit</el-button>
										</div>

										<!-- Cart Button   考虑模态框-->
										<div class="cart-btn">
                                            <a class="chuanpu-picture"><p><span class="icon-favorites "></span></p></a>
                                        </div>
										<!-- <href="//music.163.com/outchain/player?type=2&id=36990266&auto=1&height=66"><p><span class="icon-favorites "></span></p></a> -->

									</div>
								</div>
								<!-- Nav End -->

							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div>
			<transition name="el-fade-in-linear">
				<router-view ref='router-v'></router-view>
			</transition>
		</div>

		<!-- ##### Header Area End -->
		<!-- player在这里很诡异 会影响login！ -->
		<!-- 播放标签 -->
		<!-- <div class="player">-->
		<!-- autoplay 自动播放 -->
		<!-- <audio :src='musicUrl' controls autoplay></audio> -->
		<!-- </div> -->
		<!-- player end -->
		<!--  -->
		<!-- ##### Footer Area Start ##### -->
		<div class="footer-area">
			<div class="container">
				<div class="row d-flex flex-wrap align-items-center">
					<div class="col-12 col-md-6">
						<router-link to="/main"><img src="../assets/core-img/logo.png" alt=""></router-link>
						<p class="copywrite-text">
							<router-link to="/contact">Copyright &copy;2020 All rights reserved | 中软小组007 </router-link>
							<i class="fa fa-heart-o" aria-hidden="true"></i>
						</p>
					</div>

					<div class="col-12 col-md-6">
						<div class="footer-nav">
							<ul>
								<li>
									<router-link to="/main">主页</router-link>
								</li>
								<li>
									<router-link to="/mvs">歌曲MV</router-link>
								</li>
								<li>
									<router-link to="/rank">排行榜</router-link>
								</li>
								<li>
									<router-link to="/contact">联系我们</router-link>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- ##### Footer Area Start ##### -->
	</div>
</template>
<script>
	export default {
		name: 'index',
		provide() {
			return {
				reload: this.reload
			}
		},
		data() {
			return {
				inputValue: '',
				opacity: 0,
				style: {
					background: `rgba(0, 0, 0,100)`
				},
				btn_style: {
				},
				notLogin: true,
			}
		},
		mounted() {
			window.addEventListener("scroll", this.windowScroll);
			this.checkLogin();
		},
		destroyed() {
			window.removeEventListener("scroll", this.windowScroll); //销毁滚动事件
		},
		methods: {
			goBackToTop: function() {
				window.pageYOffset = 0;
				document.documentElement.scrollTop = 0;
				document.body.scrollTop = 0;
			},
			windowScroll: function() {
				let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
				this.opacity = Math.abs(Math.round(scrollTop)) / 400;
				this.style = {
					background: `rgba(0, 0, 0,${this.opacity})`
				}
				this.btn_style = {
					background: `rgba(255, 255, 255,${this.opacity})`,
					opacity: `${this.opacity}`
				}
			},
			// 
			toResult() {
				// 非空判断
				if (this.inputValue == '') {
					// 提示用户
					this.$message.warning('请输入内容')
				} else {
					// 去搜索页 携带数据
					this.$router.push('/result?q=' + this.inputValue)
					this.$refs['router-v'].refresh();
				}
			},
			checkLogin() {
				if (window.sessionStorage.getItem("userToken") != null) {
					this.notLogin = false
				} else {
					this.notLogin = true
				}
			},
			logout() {
				window.sessionStorage.clear();
				this.$router.push({
					path: "/"
				});
				this.reload();
			},
			back() { // 后退
				this.$router.go(-1)
			},
			go() { // 前进
				this.$router.go(1)
			},
			reload() { // 刷新
				this.$router.go(0)
			}
		}
	}
</script>
<style>
	
	#scrollUp {
  background-color: #000000;
  right: 50px;
  position: fixed;
  z-index: 1000;
  border-radius: 0;
  bottom: 50px;
  box-shadow: 0 2px 6px 0 rgba(0, 0, 0, 0.3);
  color: grey;
  font-size: 24px;
  height: 40px;
  line-height: 40px;
  right: 50px;
  text-align: center;
  width: 40px;
  -webkit-transition-duration: 500ms;
  transition-duration: 500ms;
  box-shadow: 0 1px 5px 2px rgba(0, 0, 0, 0.15); }
  @media only screen and (max-width: 767px) {
    #scrollUp {
      right: 30px;
      bottom: 30px; } }
  #scrollUp:hover {
    background-color: #fff;
  }
  
	.header-area {
		position: absolute;
		z-index: 1000;
		width: 100%;
		top: 20px;
		left: 0;
		z-index: 1000;
	}

	#loginBtn {
		margin: 0 10px;
	}
</style>
