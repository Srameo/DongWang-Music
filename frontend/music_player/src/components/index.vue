<template>
	<!-- 页面顶部组件 -->
	<div id="index" >
      <div class="header-area" :style="this.style"> <!--   页面标题  -->
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
                                    <li><router-link to="/main">主页</router-link></li>
                                    <li><a href="recommend.html">我的收藏</a></li>
                                    <li><a href="albums-store.html">歌曲</a></li>
                                    <li><a href="event.html">排行榜</a></li>
                                    <li><router-link to="/contact">联系我们</router-link></li>
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
                                        <router-link to="/login" id="loginBtn">Login </router-link>
                                        <router-link to="/register" id="registerBtn">Register</router-link>
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
        <transition name="el-fade-in-linear">
			<router-view></router-view>
		</transition>
    </div>
   
    <!-- ##### Header Area End -->  
    <!-- ##### Footer Area Start ##### -->
    <div class="footer-area">
        <div class="container">
            <div class="row d-flex flex-wrap align-items-center">
                <div class="col-12 col-md-6">
                    <router-link to="/main"><img src="../assets/core-img/logo.png" alt=""></router-link>
                    <p class="copywrite-text"><router-link to="/contact">Copyright &copy;2020 All rights reserved | 中软小组007 </router-link>
                    <i class="fa fa-heart-o" aria-hidden="true"></i>
                </p>
                </div>

                <div class="col-12 col-md-6">
                    <div class="footer-nav">
                        <ul>
                            <li><a href="index.html">Home</a></li>
                            <li><a href="albums-store.html">Albums</a></li>
                            <li><a href="event.html">Billboard</a></li>
                            <li><a href="contact.html">Contact</a></li>
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
    name:'index',
	data () {
		return {
            inputValue: '',
            opacity: 0,
            style: {background: `rgba(0, 0, 0,100)`},
		}
    },
    mounted() {
        window.addEventListener("scroll", this.windowScroll);
    },
    destroyed() {
        window.removeEventListener("scroll", this.windowScroll); //销毁滚动事件
    },
	methods: {
        windowScroll: function() {
            let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
			this.opacity = Math.abs(Math.round(scrollTop)) / 250;
			this.style = {background: `rgba(0, 0, 0,${this.opacity})`}
        },
        // 
        toResult() {
            // 非空判断
            if (this.inputValue == '') {
            // 提示用户
            this.$message.warning('请输入内容')
            }else{
            // 去搜索页 携带数据
            this.$router.push('/result?q='+this.inputValue)
            }
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
.header-area {
  position: absolute;
  z-index: 1000;
  width: 100%;
  top: 20px;
  left: 0;
  z-index: 1000; }
</style>
