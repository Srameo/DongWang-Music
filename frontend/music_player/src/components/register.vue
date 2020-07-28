<template>
  <div class="register-container">
		<h1>{{registerForm.style}}</h1>
    <img src="../assets/core-img/login_background.jpg" class="background-image">
		<div class="register-box">
			<!-- 表单logo -->
			<div class="register-header">
				<img src="../assets/core-img/logo.png" />
			</div>
			<!-- 表单输入区域 -->
			<div class="register-body">
				<h4>用户注册</h4>
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
              @change="styleChange"></el-cascader>
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
          <el-form-item class="input-area" prop="gender">
            <el-radio-group v-model.number="registerForm.gender">
              <el-radio :label="0">男</el-radio>
              <el-radio :label="1">女</el-radio>
            </el-radio-group>
          </el-form-item>
					<!-- 按钮 -->
					<el-form-item class="btns">
						<el-button type="primary">注册</el-button>
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
				// 表单数据
				registerForm: {
					username: "",
          password: "",
          email: "",
          age: "",
          gender: 0,
          style: []
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
					]
        },
        // 风格喜好
        props: { 
          multiple: true,
        },
        options: [{
          value: 0,
          label: '东南',
          children: [{
            value: 2,
            label: '',

          }, {
            value: 7,
            label: '',
            
          }, {
            value: 12,
            label: '',
            
          }]
        }, 
        
        {
          value: 17,
          label: '西北',
          children: [{
            value: 18,
            label: '',
            
          }, {
            value: 21,
            label: '',
            
          }]
        }]
			}
		},
		methods: {
			styleChange(value){
        console.log(value);
      }
		}
	}
</script>

<style scoped>
	.register-container{
		width: 100%;
		height: 100%;
	}
	.background-image{
		width: 100%;
		height:100%;
	}
	.register-box{
		width: 50%;
		height: 80%;
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
		display: flex;
		flex-direction: column;
		justify-content: space-around;
	}
	.btns{
		display: flex;
		justify-content: center;
	}
	.input-area{
    padding: 0 10px;
    display: flex;
    flex-direction: row;
	}
	.el-input__inner{
		background-color: transparent;
	}
</style>
