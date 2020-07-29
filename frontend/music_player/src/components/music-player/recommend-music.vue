<template>
    <div class="container">
        <div class="row">
            <el-image class="col-12 col-lg-4" :src="img"></el-image>
            <div class="col-12 col-lg-4">
                <div class="row">
                    {{name}}
                </div>
                <div class="row">
                    <span v-for="singer in singers" :key="singer.id">{{singer.name}}</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    // props: [singers, name, id],
    data() {
        return {
            img: require('../../assets/bg-img/breadcumb.jpg'),
            singers: [],
            name: ""
        }
    }
}
</script>

<style scoped>

</style>