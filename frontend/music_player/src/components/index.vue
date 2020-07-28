<template>
	<!-- 主页 -->
	<div class="index">
		<index-top></index-top>
		<!-- <index-main></index-main> -->
	</div>
</template>
<script>
import Top from '../index/top.vue'

export default {
	data () {
		return {
		}
	},
	methods: {
	},
	components: {
		'index-top': Top,
		// 'index-main': Main
	}
}
</script>
<style >
</style>
