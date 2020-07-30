<template>
  <div class="contact-mid">
    <el-row :gutter="50">
      <el-col :span="8">
        <div class="detail-info">
          <div style="height:20px"></div>
          <h4>Contact Info</h4>
          <div class="staff-info">
            <div class="staff-info-item">
              <span class="el-icon-location-outline location-icon"></span>
              <p>南开大学 | 津南区 | 天津</p>
            </div>
            <div class="staff-info-item">
              <span class="el-icon-phone-outline location-icon"></span>
              <p>WeChat：ly1799956902</p>
            </div>
            <div class="staff-info-item">
              <span class="el-icon-message location-icon"></span>
              <p>ly1799956902@163.com</p>
            </div>
          </div>
          <div class="contact-icons">
            <a href="#" data-toggle="tooltip" data-placement="top" title="Pinterest"><i class="fa fa-wechat" aria-hidden="true"></i></a>
            <a href="#" data-toggle="tooltip" data-placement="top" title="Facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a>
            <a href="#" data-toggle="tooltip" data-placement="top" title="Twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a>
            <a href="#" data-toggle="tooltip" data-placement="top" title="Dribbble"><i class="fa fa-qq" aria-hidden="true"></i></a>
            <a href="#" data-toggle="tooltip" data-placement="top" title="Behance"><i class="fa fa-weibo" aria-hidden="true"></i></a>
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="map-area mb-100 box-style"> 
          <iframe width="230" 
          height="0" 
          frameborder="0"
          scrolling="no"
          marginheight="0"
          marginwidth="0"
          src="http://apis.map.qq.com/tools/poimarker?type=0&marker=coord:38.987672,117.34208;title:我的位置;addr:天津市津南区软件学院&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&referer=myapp">
          </iframe>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="message-box">
          <div class="input-header">
            <input type="text" placeholder="Name" v-model="name">
            <input type="text" placeholder="Email" v-model="email">
            <input type="text" placeholder="Subject" v-model="subject">
          </div>
          <div class="input-message">
            <textarea cols="30" rows="10" placeholder="Message" v-model="message"></textarea>
          </div>
          <el-button type="text" class="send-button" @click="submitComment">Send >></el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "mid",
  data(){
    return{
      name: "",
      email: "",
      subject: "",
      message: ""
    }
  },
  methods: {
    submitComment(){
      
      this.$alert('我们已收到您的意见，会尽快处理，感谢您的反馈', '发送意见', {
        confirmButtonText: '确定',
        callback: action => {
          this.name = "";
          this.email = "";
          this.subject = "";
          this.message = "";
        }
      });
    }
  }
}
</script>

<style>
  .contact-mid {
    width: 100%;
    height: 400px;
    margin: 80px 0;
    display: flex;
    justify-content: center;
  }
  .detail-info{
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }
  .contact-mid .el-row{
    width: 80%;
    height: 100%;
  }
  .contact-mid .el-col{
    height: 100%;
  }
  .contact-mid .staff-info{
    width: 100%;
  }
  .contact-mid .staff-info-item{
    margin: 10px 0;
    width: 100%;
    height: 50px;
    display: flex;
    justify-content: center;
  }
  .contact-mid .location-icon{
    margin: 0 10px;
    box-sizing: border-box;
    padding-top: 7px;
  }
  .contact-mid .contact-icons a{
    margin: 0 10px;
  }
  .contact-mid .box-style{
    height: 100%;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  }
  .contact-mid .message-box{
    height: 100%;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
  }
  .message-box .send-button{
    color: black;
    font-weight: bold;
  }
  .message-box .input-header{
    width: 100%;
    display: flex;
    justify-content: center;
  }
  .input-header input{
    width: 20%;
    box-sizing: border-box;
    margin: 0 15px;
    margin-top: 10px;
    border-radius: 20px;
    border: 1px solid #ececec;
    font-style: italic;
    background-color: #f5f5f5;
  }
  .input-message textarea{
    resize: none;
    border-radius: 10px;
    background-color: #f5f5f5;
    border: 1px solid #ececec;
  }
</style>