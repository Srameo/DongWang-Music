<template>
    <div>
        <top :musicname="name" :id="id"></top>
        <mid :id="id" :singers="singers" :name="name"></mid>
    </div>
</template>

<script>
import Mid from './music-player/mid'
import Top from './music-player/top'
import axios from 'axios'
export default {
    components: {
        Top,
        Mid
    },
    data() {
        return {
            id: 60008,
			name: "",
			singers: []
        }
    },
	mounted() {
		axios({
			url: 'http://127.0.0.1:8882/music/get',
			method: 'post',
			params: {
				id: this.id
			}
		}).then(res => {
			console.log(res.data);
			this.name = res.data.songInfo.name;
			this.singers = res.data.songInfo.singers;
		}).catch(err => {
			console.log(err);
		})
	}
}
</script>

<style scoped>

</style>