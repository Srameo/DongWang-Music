<template>
  <!-- 首页 -->
  <!-- ##### Hero Area Start ##### -->
  <div>
    <div class="hero-area">
      <div class="hero-slides">
        <!-- Single Hero Slide -->
        <el-carousel height="950px" :autoplay="true" :interval="2000" :indicator-position="none">
          <el-carousel-item>
            <div class="single-hero-slide d-flex align-items-center justify-content-center">
              <!-- Slide Img    主页轮播图-->
              <div class="slide-img bg-img bg-img-1"></div>
              <!-- Slide Content -->
              <div class="container">
                <div class="row">
                  <div class="col-12">
                    <div class="hero-slides-content text-center">
                      <h6 data-animation="fadeInUp" data-delay="50ms">Nobody knows</h6>
                      <h2 data-animation="fadeInUp" data-delay="100ms">
                        没人比我更懂音乐
                        <span>没人比我更懂音乐</span>
                      </h2>
                      <h2 data-animation="fadeInUp" data-delay="300ms">
                        Music
                        <span>Music</span>
                      </h2>
                      <h6 data-animation="fadeInUp" data-delay="300ms">better than me!</h6>
                      <!-- <h6 data-animation="fadeInUp" data-delay="100ms">Latest album</h6>
                      <h2 data-animation="fadeInUp" data-delay="300ms">Beyond Time </h2>-->
                      <router-link
                        v-if="notLogin"
                        to="/login"
                        data-animation="fadeInUp"
                        data-delay="500ms"
                        class="btn oneMusic-btn mt-50"
                      >
                        让我懂你
                        <i class="el-icon-s-promotion"></i>
                      </router-link>
                      <router-link
                        v-else
                        to="/rank"
                        data-animation="fadeInUp"
                        data-delay="500ms"
                        class="btn oneMusic-btn mt-50"
                      >
                        让我懂你
                        <i class="el-icon-s-promotion"></i>
                      </router-link>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </el-carousel-item>
          <el-carousel-item>
            <!-- Single Hero Slide -->
            <div class="single-hero-slide d-flex align-items-center justify-content-center">
              <!-- Slide Img -->
              <div class="slide-img bg-img bg-img-2"></div>
              <!-- Slide Content -->
              <div class="container">
                <div class="row">
                  <div class="col-12">
                    <div class="hero-slides-content text-center">
                      <h6 data-animation="fadeInUp" data-delay="50ms">Nobody knows</h6>
                      <h2 data-animation="fadeInUp" data-delay="100ms">
                        没人比我更懂音乐
                        <span>没人比我更懂音乐</span>
                      </h2>
                      <h2 data-animation="fadeInUp" data-delay="300ms">
                        Music
                        <span>Music</span>
                      </h2>
                      <h6 data-animation="fadeInUp" data-delay="300ms">better than me!</h6>
                      <router-link
                        v-if="notLogin"
                        to="/login"
                        data-animation="fadeInUp"
                        data-delay="500ms"
                        class="btn oneMusic-btn mt-50"
                      >
                        让我懂你
                        <i class="el-icon-s-promotion"></i>
                      </router-link>
                      <router-link
                        v-else
                        to="/rank"
                        data-animation="fadeInUp"
                        data-delay="500ms"
                        class="btn oneMusic-btn mt-50"
                      >
                        让我懂你
                        <i class="el-icon-s-promotion"></i>
                      </router-link>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
    <!-- ##### Hero Area End ##### -->
    <!--轮播结束-->
    <!-- <bottom></bottom> -->
  </div>
</template>

<script>
import bottom from "./main/bottom";

export default {
  name: "main",
  components: {
    bottom,
  },
  inject: ["reload"],
  data() {
    return {
      notLogin: true,
    };
  },
  mounted() {
    this.checkLogin();
    console.log("执行了main");
    console.log(this.notLogin);
  },
  methods: {
    checkLogin() {
      if (window.sessionStorage.getItem("userToken") != null) {
        this.notLogin = false;
      } else {
        this.notLogin = true;
      }
    },
  },
};
</script>

<style>
</style>