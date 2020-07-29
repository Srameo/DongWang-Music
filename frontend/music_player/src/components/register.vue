<template>
  <div class="register-container">
    <img src="../assets/core-img/login_background.jpg" class="background-image">
		<div class="register-box">
			<!-- 表单logo -->
			<div class="register-header">
				<img src="../assets/core-img/logo.png" />
			</div>
			<!-- 表单输入区域 -->
			<div class="register-body">
				<h3>用户注册</h3>
        <el-form ref="registerFormRef" :model="registerForm" :rules="registerRules" label-width="0" class="register-form">
				  <!-- 用户名 -->
					<el-form-item class="input-area" prop="username">
				    <el-input v-model="registerForm.username" prefix-icon="el-icon-user" placeholder="用户名"></el-input>
				  </el-form-item>
					<!-- 密码 -->
					<el-form-item class="input-area" prop="password">
				    <el-input v-model="registerForm.password" prefix-icon="el-icon-view" placeholder="密码"></el-input>
				  </el-form-item>
          <!-- 选择喜好 -->
          <el-form-item class="input-area">
            <!-- <span class="demonstration">请选择喜好</span> -->
            <el-cascader
              :options="options"
              :props="props"
              collapse-tags
              clearable
              @change="styleChange"
              placeholder="请选择喜好的风格"></el-cascader>
          </el-form-item>
          <!-- 邮箱 -->
          <el-form-item class="input-area" prop="email">
				    <el-input v-model="registerForm.email" prefix-icon="el-icon-view" placeholder="邮箱"></el-input>
				  </el-form-item>
          <!-- 年龄 -->
          <el-form-item class="input-area" prop="age">
				    <el-input v-model.number="registerForm.age" prefix-icon="el-icon-view" placeholder="年龄"></el-input>
				  </el-form-item>
          <!-- 性别 -->
          <el-form-item class="input-area">
            <el-radio-group v-model.number="registerForm.gender">
              <el-radio :label="0">男</el-radio>
              <el-radio :label="1">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <!-- 验证码 -->
          <el-form-item class="input-area" prop="verifyCode">
				    <el-input v-model="registerForm.verifyCode" prefix-icon="el-icon-view" placeholder="验证码"></el-input>
				  </el-form-item>
					<!-- 按钮 -->
					<el-form-item class="btns">
						<el-button type="primary" @click="registerConfirm">注册</el-button>
            <el-button type="info" @click="sendVerifyCode" v-if="notSendVerifyCode">发送验证码</el-button>
            <el-button type="info" v-else disabled>已发送</el-button>
				  </el-form-item>
				 </el-form>
			</div>
		</div>
	</div>
</template>

<script>
	export default{
		data(){
			return{
        // 其他数据
        notSendVerifyCode: true,
        // 表单数据
				registerForm: {
					username: "",
          password: "",
          email: "",
          age: "",
          gender: 0,
          style: [],
          verifyCode: ""
				},
				// 表单校验
				registerRules: {
					username: [
						{ required: true, message: '用户名为必填项', trigger: 'blur' },
            { min: 5, max: 20, message: '长度在 5 ~ 20 个字符', trigger: 'blur' }
					],
					password: [
						{ required: true, message: '密码为必填项', trigger: 'blur' },
            { min: 8, max: 16, message: '长度在 8 ~ 16 个字符', trigger: 'blur' }
          ],
          email: [
						{ required: true, message: '邮箱为必填项', trigger: 'blur' },
            { min: 5, max: 40, message: '长度在 5 ~ 40 个字符', trigger: 'blur' }
          ],
          age: [
						{ required: true, message: '年龄为必填项', trigger: 'blur' }
          ],
          verifyCode: [
						{ required: true, message: '验证码为必填项', trigger: 'blur' }
          ]
        },
        // 风格喜好
        props: { 
          multiple: true,
        },
        options: [
          {
          value: 0,
          label: '语种',
          children: [{
            value: 1,
            label: '华语',

          }, {
            value: 2,
            label: '欧美',
            
          }, {
            value: 3,
            label: '日语',
            
          }, {
            value: 4,
            label: '韩语',
            
          }, {
            value: 5,
            label: '粤语',
            
          }]
          }, 
          
          {
            value: 0,
            label: '风格',
            children: [{
              value: 6,
              label: '流行',
              
            }, {
              value: 7,
              label: '摇滚',
              
            }, 
            {
              value: 8,
              label: '民谣',
              
            }, {
              value: 9,
              label: '电子',
              
            }, {
              value: 10,
              label: '舞曲',
              
            }, {
              value: 11,
              label: '说唱',
              
            }, {
              value: 12,
              label: '轻音乐',
              
            }, {
              value: 13,
              label: '爵士',
              
            }, {
              value: 14,
              label: '乡村',
              
            }, {
              value: 15,
              label: 'R&B/Soul',
              
            }, {
              value: 16,
              label: '古典',
              
            }, {
              value: 17,
              label: '民族',
              
            }, {
              value: 18,
              label: '英伦',
              
            }, {
              value: 19,
              label: '金属',
              
            }, {
              value: 20,
              label: '朋克',
              
            }, {
              value: 21,
              label: '蓝调',
              
            }, {
              value: 22,
              label: '雷鬼',
              
            }, {
              value: 23,
              label: '世界音乐',
              
            }, {
              value: 24,
              label: '拉丁',
              
            }, {
              value: 25,
              label: 'New Age',
              
            }, {
              value: 26,
              label: '古风',
              
            }, {
              value: 27,
              label: '后摇',
              
            }, {
              value: 28,
              label: 'Bossa Nova',
              
            }]
          },

          {
            value: 0,
            label: '场景',
            children: [{
              value: 29,
              label: '清晨',
              
            }, {
              value: 30,
              label: '夜晚',
              
            }, {
              value: 31,
              label: '学习',
              
            }, {
              value: 32,
              label: '工作',
              
            }, {
              value: 33,
              label: '午休',
              
            }, {
              value: 34,
              label: '下午茶',
              
            }, {
              value: 35,
              label: '地铁',
              
            }, {
              value: 36,
              label: '驾车',
              
            }, {
              value: 37,
              label: '运动',
              
            }, {
              value: 38,
              label: '旅行',
              
            }, {
              value: 39,
              label: '散步',
              
            }, {
              value: 40,
              label: '酒吧',
              
            }]
          },

          {
            value: 0,
            label: '情感',
            children: [{
              value: 41,
              label: '怀旧',
              
            }, {
              value: 42,
              label: '清新',
              
            }, {
              value: 43,
              label: '浪漫',
              
            }, {
              value: 44,
              label: '伤感',
              
            }, {
              value: 45,
              label: '治愈',
              
            }, {
              value: 46,
              label: '放松',
              
            }, {
              value: 47,
              label: '孤独',
              
            }, {
              value: 48,
              label: '感动',
              
            }, {
              value: 49,
              label: '兴奋',
              
            }, {
              value: 50,
              label: '快乐',
              
            }, {
              value: 51,
              label: '安静',
              
            }, {
              value: 52,
              label: '思念',
              
            }]
          },

          {
            value: 0,
            label: '主题',
            children: [{
              value: 53,
              label: '综艺',
              
            }, {
              value: 54,
              label: '影视原声',
              
            }, {
              value: 55,
              label: 'ACG',
              
            }, {
              value: 56,
              label: '儿童',
              
            }, {
              value: 57,
              label: '校园',
              
            }, {
              value: 58,
              label: '游戏',
              
            }, {
              value: 59,
              label: '70后',
              
            }, {
              value: 60,
              label: '80后',
              
            }, {
              value: 61,
              label: '90后',
              
            }, {
              value: 62,
              label: '网络歌曲',
              
            }, {
              value: 63,
              label: 'KTV',
              
            }, {
              value: 64,
              label: '经典',
              
            }, {
              value: 65,
              label: '翻唱',
              
            }, {
              value: 66,
              label: '吉他',
              
            }, {
              value: 67,
              label: '钢琴',
              
            }, {
              value: 68,
              label: '器乐',
              
            }, {
              value: 69,
              label: '榜单',
              
            }, {
              value: 70,
              label: '00后',
              
            }, ]
          }
        
        ]
			}
		},
		methods: {
			styleChange(value){
        // console.log(value);
        this.registerForm.style.length = 0;
        for(let i=0;i<value.length;i++){
          for(let j=0;j<value[i].length;j++){
            if(value[i][j]!=0){
              // console.log(value[i][j]);
              this.registerForm.style.push(value[i][j]);
            }
            
          }
        }
        console.log(this.registerForm.style);
      },

      registerConfirm(){
        this.$refs.registerFormRef.validate(async valid =>{
					// console.log(valid);
					if(!valid) return;
					const result = await this.$http.post("/user/register");
					console.log(result);
				})
      },

      async sendVerifyCode(){
        this.notSendVerifyCode = false;
        const {data: res} = await this.$http.post("/user/sendVerifyCode");
        console.log(res);
      }

		}
	}
</script>

<style scoped>
	.register-container{
		width: 100%;
    height: 100%;
    position: relative;
	}
	.background-image{
		width: 100%;
		height:100%;
	}
	.register-box{
		width: 50%;
		height: 60%;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		display: flex;
		flex-direction: column;
		justify-content: space-around;
		align-items: center;
		background-color: rgba(255,255,255, 0.5);
	}
	.register-header{
		width: 50%;
		height: 50px;
	}
	.register-header img{
		width: 100%;
		height: 100%;
	}
	.register-body{
		width: 50%;
		height: 450px;
		text-align: center;
  }
  .register-body h4{
    color: black;
  }
	.register-form{
		width: 100%;
		height: 100%;
	}
	.btns{
		display: flex;
		justify-content: center;
	}
	.input-area{
    padding: 0 10px;
    margin: 15px 0;
    display: flex;
    flex-direction: row;
    width: 100%;
    justify-content: center;
	}
</style>
