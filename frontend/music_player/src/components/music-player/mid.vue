<template>
    <section class="contact-area section-padding-100-0">
        <div class="container">
            <div class="row">
                <div class="col-12 col-lg-4">
                    <!-- 歌曲信息界面 -->
                    <el-image :src="img"></el-image>
                    <span>{{name}}</span>
                    <div v-for="singer in singers" :key="singer.id">
                        <span>
                            {{singer.name}}
                        </span>
                    </div>
                </div>
                <div class="col-12 col-lg-6">
                    <!-- 所有评论 -->
                    <div v-for="(c, index) in cmts" :key="index">
                        <comment :txt="c"></comment>
                    </div>
                </div>
                <div class="col-12 col-lg-2">
                    <!-- 进行评论 -->
                    <div class="form-group">
                        <textarea name="message" class="form-control" id="message" cols="30" rows="10" placeholder="你的评论"></textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div v-for="music in recommendMusics" :key="music.id">
                    <recommend-music :id="music.id" :singers="music.singers" :name="music.name"></recommend-music>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
import RecommendMusic from './recommend-music'
import Comment from './comment'

export default {
    props: ['id'],
    components: {
        RecommendMusic,
        Comment
    },
    data() {
        return {
            name: "憨憨",
            singers:[{id: 1, name: "憨憨"}, {id: 2, name: "智障"}],
            cmts: ["111", "222", "333", "444"],
            recommendMusics: [],
            img: require('../../assets/bg-img/breadcumb.jpg'),
            name: "",
        }
    }
}
</script>

<style scoped>
</style>