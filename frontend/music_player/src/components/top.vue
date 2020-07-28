<template>
	<!-- 页面顶部组件 -->
	<div id="top" v-waves>
<template>
	<!-- 主页 -->
	<div class="index">
		<index-top></index-top>
		<index-main></index-main>
	</div>
</template>
<script>
import Top from '../index/Top.vue'
import Main from '../index/Main.vue'

export default {
	data () {
		return {
		}
	},
	methods: {
	},
	components: {
		'index-top': Top,
		'index-main': Main
	}
}
</script>
<style lang="stylus" scoped>
.index
	display: flex
	flex-direction: column
	height: 100vh
</style>
		<div class="search">
			<!-- 搜索框 -->
			<el-input size="small" prefix-icon="el-icon-search" v-model="inputValue" placeholder="搜索" @keyup.native.enter="toResult">
			</el-input>
		</div>
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
