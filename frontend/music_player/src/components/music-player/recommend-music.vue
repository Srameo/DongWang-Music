<template>
    <div class="container">
        <div class="row">
			<div class="col-12 col-lg-6">
				<!-- <el-image :src="img"></el-image> -->
                <div>
                    <h4>{{name}}</h4>
                </div>
			</div>
            <div class="col-12 col-lg-6">
                <div>
                    <span v-for="singer in singers" :key="singer.id">{{singer.name}}</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: ['singers', 'name', 'id'],
    data() {
        return {
            img: require('../../assets/bg-img/breadcumb.jpg'),
        }
    }
}
</script>

<style scoped>

</style>