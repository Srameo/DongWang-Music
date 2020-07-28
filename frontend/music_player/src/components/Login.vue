<template>
	<div class="login-container">
		<div class="login-box">
			<!-- 表单logo -->
			<div class="login-header">
				<img src="../assets/core-img/logo.png" />
			</div>
			<!-- 表单输入区域 -->
			<div class="login-body">
				<el-form ref="loginFormRef" :model="loginForm" label-width="0" class="login-form">
				  <!-- 用户名 -->
					<el-form-item>
				    <el-input v-model="loginForm.username"></el-input>
				  </el-form-item>
					<!-- 密码 -->
					<el-form-item>
				    <el-input v-model="loginForm.password" type="password"></el-input>
				  </el-form-item>
					<!-- 按钮 -->
					<el-form-item class="btns">
				    <el-button type="primary">登录</el-button>
						<el-button type="info">注册</el-button>
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
				loginForm: {
					username: "aaa",
					password: "aaa"
				}
			}
		}
	}
</script>

<style scoped">
</style>
