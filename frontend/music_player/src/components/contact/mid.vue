<template>
    <section class="contact-area section-padding-100-0">
        <div class="container">
            <div class="row">
                <!-- 左侧联系方式 -->
                <div class="col-12 col-lg-4">
                    <div class="contact-content mb-100">
                        <!-- Title -->
                        <div class="contact-title mb-50">
                            <h5>Contact Info</h5>
                        </div>

                        <!-- Single Contact Info -->
                        <div class="single-contact-info d-flex align-items-center">
                            <div class="icon mr-30">
                                <span class="icon-placeholder"></span>
                            </div>
                            <p>南开大学 | 津南区 | 天津</p>
                        </div>

                        <!-- Single Contact Info -->
                        <div class="single-contact-info d-flex align-items-center">
                            <div class="icon mr-30">
                                <span class="icon-smartphone"></span>
                            </div>
                            <p>WeChat：ly1799956902</p>
                        </div>

                        <!-- Single Contact Info -->
                        <div class="single-contact-info d-flex align-items-center">
                            <div class="icon mr-30">
                                <span class="icon-mail"></span>
                            </div>
                            <p>ly1799956902@163.com</p>
                        </div>
                        <!-- 这五个跳转！没办法登录 只能跳转页面 具体参照title -->
                        <!-- Contact Social Info -->
                        <div class="contact-social-info mt-50">
                            <a href="#" data-toggle="tooltip" data-placement="top" title="Pinterest"><i class="fa fa-wechat" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="top" title="Facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="top" title="Twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="top" title="Dribbble"><i class="fa fa-qq" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="top" title="Behance"><i class="fa fa-weibo" aria-hidden="true"></i></a>
                        </div>

                    </div>
                </div>
                <!-- 腾讯地图 -->
                <div class="col-12 col-lg-4" >
                    <div class="map-area mb-100"> 
                        <iframe width="230" 
                        height="340" 
                        frameborder="0"
                        scrolling="no"
                        marginheight="0"
                        marginwidth="0"
                        src="http://apis.map.qq.com/tools/poimarker?type=0&marker=coord:38.987672,117.34208;title:我的位置;addr:天津市津南区软件学院&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&referer=myapp">
                        </iframe>
                    </div> 
                </div>
                <div class="col-12 col-lg-1" ></div>
                <div class="col-12 col-lg-3" >
                    <div >
                        <el-image :src="require('../../assets/core-gif/IMG_5858.gif')"></el-image>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
export default {
    name: 'mid'
}
</script>

<style scoped>

</style>