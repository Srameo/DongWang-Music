<template>
    <section class="contact-area section-padding-100-0">
        <div class="container">
            <div class="row">
                <div class="col-12 col-lg-4">
                    <!-- 歌曲信息界面 -->
                    <el-image :src="img"></el-image>
                    <span>{{nme}}</span>
                    <div v-for="singer in singers" :key="singer.id">
                        <span style="'{float: left}'">
                            {{singer.name}}
                        </span>
                    </div>
                </div>
                <div class="col-12 col-lg-4">
                    <!-- 所有评论 -->
                    <div v-for="(c, index) in cmts" :key="index">
                        <comment :txt="c"></comment>
                    </div>
                </div>
                <div class="col-12 col-lg-4">
					<!-- 推荐音乐 -->
                    <div v-for="music in recommendMusics" :key="music.id">
                        <recommend-music :id="music.id" :singers="music.singers" :name="music.name"></recommend-music>
                    </div>
                </div>
            </div>
            <div class="contact-form-area row">
                <!-- 进行评论 -->
                <div class="col-12">
                    <div class="form-group">
                        <textarea name="message" class="form-control" id="message" cols="30" rows="10" placeholder="我的评论"></textarea>
                    </div>
                </div>
                <div class="col-12 text-center">
                    <button class="btn oneMusic-btn mt-30" type="submit">Send<i class="fa fa-angle-double-right"></i></button>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
import RecommendMusic from './recommend-music'
import Comment from './comment'
import axios from 'axios'

export default {
    props: ['id', 'singers', 'nme'],
    components: {
        RecommendMusic,
        Comment
    },
    data() {
        return {
            // name: "憨憨",
            // singers:[{id: 1, name: "憨憨"}, {id: 2, name: "智障"}],
            cmts: ["111", "222", "333", "444"],
            recommendMusics: [],
            img: require('../../assets/bg-img/breadcumb.jpg'),
            // name: "",
        }
    },
	methods:{
		
		async getMusicInfo(id) {
			axios({
				url: 'http://192.168.1.5:8882/music/get',
				method: 'post',
				params: {
					id: id
				}
			}).then(res => {
				this.recommendMusics.push(res.data.songInfo)
			}).catch(err => {
				console.log(err);
			})
		}
	},
	mounted() {
		axios({
			url: 'http://192.168.1.5:8882/recommend/music',
			method: 'post',
			params: {
				id: this.id
			}
		}).then(res => {
			console.log(res.data);
			this.recommendMusics = new Array()
			for (let i = 0; i < res.data.ids.length; i++) {
				this.getMusicInfo(res.data.ids[i])
			}
			console.log(this.recommendMusics)
		}).catch(err => {
			console.log(err);
		})
	}
}
</script>

<style scoped>
</style>