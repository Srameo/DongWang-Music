<template>
  <div class="login-container">
    <img src="../assets/core-img/login.jpg" class="background-image" />
    <div class="login-box">
      <!-- 表单logo -->
      <div class="login-header">
        <img src="../assets/core-img/logo.png" />
      </div>
      <!-- 表单输入区域 -->
      <div class="login-body">
        <el-form
          ref="loginFormRef"
          :model="loginForm"
          :rules="loginRules"
          label-width="0"
          class="login-form"
        >
          <!-- 用户名 -->
          <el-form-item class="input-area" prop="username">
            <el-input
              v-model="loginForm.username"
              prefix-icon="el-icon-user"
              placeholder="用户名"
              maxlength="10"
              show-word-limit
            ></el-input>
          </el-form-item>
          <!-- 密码 -->
          <el-form-item class="input-area" prop="password">
            <el-input
              v-model="loginForm.password"
              type="password"
              prefix-icon="el-icon-view"
              placeholder="密码"
            ></el-input>
          </el-form-item>
          <!-- 按钮 -->
          <el-form-item class="btns">
            <el-button type="primary" @click="login">登录</el-button>
            <el-button type="info" @click="redirectToRegister">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  inject: ["reload"],
  data() {
    return {
      // 表单数据
      loginForm: {
        username: "",
        password: "",
      },
      // 表单校验
      loginRules: {
        username: [
          { required: true, message: "用户名为必填项", trigger: "blur" },
          { min: 5, max: 20, message: "长度在 5 ~ 20 个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "密码为必填项", trigger: "blur" },
          { min: 8, max: 16, message: "长度在 8 ~ 16 个字符", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    login() {
      // console.log(111);
      this.$refs.loginFormRef.validate(async (valid) => {
        // console.log(valid);
        if (!valid) return;
        let params = new URLSearchParams();
        params.append("username", this.loginForm.username);
        params.append("password", this.loginForm.password);
        const { data: res } = await this.$http.post("/user/login", params);
        console.log(res);
        if (res.code == 0) {
          console.log(1);
          this.$alert("恭喜您登录成功", "登录状态", {
            confirmButtonText: "确定",
            callback: (action) => {
              this.$router.push({ path: "/" });
              window.sessionStorage.setItem("userToken", res.token);
              this.reload();
            },
          });
        } else {
          // 登陆失败
          console.log("登录失败");
          this.$message.error(res.message);
        }
      });
    },
    redirectToRegister() {
      this.$router.push({ path: "/register" });
    },
  },
};
</script>

<style scoped>
.login-container {
  width: 100%;
  height: 100%;
  position: relative;
}
.background-image {
  width: 100%;
  height: 100%;
}
.login-box {
  border-radius: 20px;
  width: 45%;
  height: 40%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -70%);
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  background-color: rgba(15, 2, 2, 0.4);
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.4);
}
.login-header {
  width: 55%;
  height: 50px;
}
.login-header img {
  width: 100%;
  height: 100%;
}
.login-body {
  width: 35%;
  height: 200px;
}
.login-form {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
.btns {
  display: flex;
  justify-content: center;
}
.input-area {
  padding: 0 10px;
}
.el-input__inner {
  background-color: transparent;
}
</style>
