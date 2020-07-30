<!--
 * @Author: your name
 * @Date: 2020-07-29 13:46:36
 * @LastEditTime: 2020-07-29 15:27:49
 * @LastEditors: your name
 * @Description: In User Settings Edit
 * @FilePath: \music_player\src\components\MusicPlayer.vue
-->
<template>
  <div>
    <top :musicname="name" :id="id"></top>
    <mid :id="id" :singers="singers" :nme="name" :tags="tags" :num="num" ref="mid"></mid>
  </div>
</template>

<script>
import Mid from "./music-player/mid";
import Top from "./music-player/top";
import axios from "axios";
export default {
  components: {
    Top,
    Mid,
  },
  data() {
    return {
      id: this.$route.query.id,
      name: "",
      singers: [],
      tags: [],
      num: 0,
    };
  },
  methods: {
    refresh() {
      this.id = this.$route.query.id;
      console.log("parent refresh");
      axios({
        url: "http://127.0.0.1:8882/music/get",
        method: "post",
        params: {
          id: this.id,
        },
      })
        .then((res) => {
          console.log(res.data);
          this.name = res.data.songInfo.name;
          this.singers = res.data.songInfo.singers;
          this.tags = res.data.songInfo.tags;
		  this.num = res.data.songInfo.num;
		  this.$refs['mid'].refresh();
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  mounted() {
    axios({
      url: "http://127.0.0.1:8882/music/get",
      method: "post",
      params: {
        id: this.id,
      },
    })
      .then((res) => {
        console.log(res.data);
        this.name = res.data.songInfo.name;
        this.singers = res.data.songInfo.singers;
        this.tags = res.data.songInfo.tags;
        this.num = res.data.songInfo.num;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>
</style>
