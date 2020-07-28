<template>
	<!-- 页面顶部组件 -->
	<div id="index" >
      <div class="header-area"> <!--   页面标题  -->
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
                        <!-- <a href="index.html" class="nav-brand"><img src="img/core-img/timg.jpg" alt=""></a> -->
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
                                    <li><a href="index.html">主页</a></li>
                                    <li><a href="recommend.html">我的收藏</a></li>
                                    <li><a href="albums-store.html">歌曲</a></li>
                                    <li><a href="event.html">排行榜</a></li>
                                    <li><a href="contact.html">联系我们</a></li>
                                    <li><a href="#">其他</a>
                                        <ul class="dropdown">
                                            <li><a href="index.html">主页</a></li>
                                            <li><a href="event.html">我的收藏</a></li>
                                            <li><a href="albums-store.html">歌曲</a></li>
                                            <li><a href="event.html">排行榜</a></li>
                                            <li><a href="contact.html">联系我们</a></li>
                                            <li><a href="elements.html">数据（可用）</a></li>
                                            <li><a href="login.html">登录</a></li>
                                        </ul>
                                    </li>
                                </ul>

                                <!-- Login/Register & Cart Button -->
                                <div class="login-register-cart-button d-flex align-items-center">
                                    <!-- 搜索框-->
                                    <div class="cart-btn">
                                            <div class="col-lg-9 ">
                                                <div class="search">
                                                    <!-- 搜索框 -->
                                                    <el-input size="small" prefix-icon="el-icon-search" v-model="inputValue" placeholder="搜索" @keyup.native.enter="toResult">
                                                    </el-input>
                                                </div>
                                            </div>
                                    </div >
                                    <!-- Login/Register -->
                                    <div class="login-register-btn mr-50 ">
                                        <a href="login.html" id="loginBtn">Login </a>                                             
                                        <a href="register.html" id="registerBtn">Register</a>
                                    </div>

                                    <!-- Cart Button   考虑模态框-->
                                    <div class="cart-btn">
                                        <a class="chuanpu-picture" href="img/core-img/timg.jpg"><p><span class="icon-favorites "></span></p></a>
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
        <!-- <transition name="el-fade-in-linear"> -->
			<router-view></router-view>
		<!-- </transition> -->
    </div>
   
    <!-- ##### Header Area End ##### -->

	</div>
</template>
<script>
export default {
	data () {
		return {
			inputValue: ''
		}
	},
	methods: {
		toResult () {
			// 判断输入内容是否为空
			if (!this.inputValue.trim()) {
				return this.$message('warning', '请输入内容')
			}
			// 携带参数跳转路由
			this.$router.push({
				name: 'searchResult',
				params: {
					search: this.inputValue
				}
			})
			// 清空搜索框内容
			this.inputValue = ''
		},
		back () { // 后退
			this.$router.go(-1)
		},
		go () { // 前进
			this.$router.go(1)
		},
		reload () { // 刷新
			this.$router.go(0)
		}
	}
}
</script>
<style >

</style>
