 <template>
  <div>
    <section class="breadcumb-area bg-img bg-overlay">
      <div class="bradcumbContent">
        <span class="sub-title">没人比我更懂</span>
        <h2>
          最新
          <div>
            <el-avatar src="http://p2.music.126.net/_ldY-Tf5dCIubyyeATSrdA==/109951164322846413.jpg"></el-avatar>
          </div>MV
        </h2>
      </div>
    </section>
    <div class="mvs-container">
      <div class="filter-wrap">
        <div class="seciton-wrap">
          <!-- 分类切换 地区 -->
          <span class="section-type">地区:</span>
          <ul class="tabs-wrap">
            <li class="tab">
              <span class="title" :class="{ active: area == '全部' }" @click="area = '全部'">全部</span>
            </li>
            <li class="tab">
              <span class="title" :class="{ active: area == '内地' }" @click="area = '内地'">内地</span>
            </li>
            <li class="tab">
              <span class="title" :class="{ active: area == '港台' }" @click="area = '港台'">港台</span>
            </li>
            <li class="tab">
              <span class="title" :class="{ active: area == '欧美' }" @click="area = '欧美'">欧美</span>
            </li>
            <li class="tab">
              <span class="title" :class="{ active: area == '日本' }" @click="area = '日本'">日本</span>
            </li>
            <li class="tab">
              <span class="title" :class="{ active: area == '韩国' }" @click="area = '韩国'">韩国</span>
            </li>
          </ul>
        </div>
        <div class="type-wrap">
          <span class="type-type">类型:</span>
          <ul class="tabs-wrap">
            <li class="tab">
              <span class="title" :class="{ active: type == '全部' }" @click="type = '全部'">全部</span>
            </li>
            <li class="tab">
              <span class="title" :class="{ active: type == '官方版' }" @click="type = '官方版'">官方版</span>
            </li>
            <li class="tab">
              <span class="title" :class="{ active: type == '原声' }" @click="type = '原声'">原声</span>
            </li>
            <li class="tab">
              <span class="title" :class="{ active: type == '现场版' }" @click="type = '现场版'">现场版</span>
            </li>
            <li class="tab">
              <span class="title" :class="{ active: type == '网易出品' }" @click="type = '网易出品'">网易出品</span>
            </li>
          </ul>
        </div>
        <div class="order-wrap">
          <span class="order-type">排序:</span>
          <ul class="tabs-wrap">
            <li class="tab">
              <span class="title" :class="{ active: order == '上升最快' }" @click="order = '上升最快'">上升最快</span>
            </li>
            <li class="tab">
              <span class="title" :class="{ active: order == '最热' }" @click="order = '最热'">最热</span>
            </li>
            <li class="tab">
              <span class="title" :class="{ active: order == '最新' }" @click="order = '最新'">最新</span>
            </li>
          </ul>
        </div>
      </div>
      <!-- mv容器 -->
      <!-- 推荐MV -->
      <div class="mvs">
        <div class="items">
          <div class="item" v-for="(item, index) in list" :key="index">
            <div class="img-wrap" @click="toMv(item.id)">
              <img :src="item.cover" alt />
              <span class="iconfont icon-play" ><i class="el-icon-caret-right"></i></span>
              <div class="num-wrap">
                <div class="iconfont icon-play"></div>
                <div class="num">{{ item.playCount }}</div>
              </div>
            </div>
            <div class="info-wrap">
              <div class="name">{{ item.name }}</div>
              <div class="singer">{{ item.artistName }}</div>
            </div>
          </div>
        </div>
        <!-- 分页器 -->
        <el-pagination
          @current-change="handleCurrentChange"
          background
          layout="prev, pager, next"
          :total="total"
          :current-page="page"
          :page-size="5"
          :limit="limit"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
// 导入 axios
import axios from "axios";
export default {
  name: "mvs",
  data() {
    return {
      // 总条数
      total: 20,
      // 页码
      page: 1,
      // 页容量
      limit: 8,
      // 地区
      area: "全部",
      // 类型
      type: "全部",
      // 排序
      order: "上升最快",
      // 查询的结果
      list: [],
    };
  },
  // 侦听器
  watch: {
    area() {
      // 返回第一页
      this.page = 1;
      // 获取数据
      this.getList();
    },
    type() {
      // 返回第一页
      this.page = 1;
      // 获取数据
      this.getList();
    },
    order() {
      // 返回第一页
      this.page = 1;
      // 获取数据
      this.getList();
    },
  },
  created() {
    // 获取数据
    this.getList();
  },
  methods: {
    // 获取列表数据
    getList() {
      // 获取数据
      axios({
        url: "https://autumnfish.cn/mv/all",
        method: "get",
        params: {
          area: this.area,
          type: this.type,
          order: this.order,
          // 数量
          limit: this.limit,
          // 偏移值 分页 (页码-1)*数量
          offset: (this.page - 1) * this.limit,
        },
      }).then((res) => {
        // console.log(res)
        this.list = res.data.data;
        // 处理次数
        for (let i = 0; i < this.list.length; i++) {
          if (this.list[i].playCount > 100000) {
            this.list[i].playCount =
              parseInt(this.list[i].playCount / 10000) + "万";
          }
        }

        // 保存总条数
        // 接口问题 有count 就赋值
        if (res.data.count) {
          this.total = res.data.count;
        }
      });
    },
    // 页码改变的回调函数
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`)
      // 保存页面 重新获取数据
      this.page = val;
      this.getList();
    },
    toMv (id) {
			this.$router.push(`/mv?q=${id}`)
		}
  },
};
</script>

<style>
.bg-overlay {
  position: relative;
  z-index: 2;
  background-position: center center;
  background-image: url("../assets/core-img/mv.jpg");
  background-size: cover;
}
.mvs-container {
  padding: 50px 100px;
  margin: 0px 100px;
}

.mvs-container .filter-wrap > div {
  margin-bottom: 30px;
  vertical-align: baseline;
  display: flex;
  align-items: center;
}

.mvs-container .filter-wrap > div span {
  font-size: 14px;
  height: 100%;
}

.mvs-container .filter-wrap > div span.title {
  margin: 20px;
  color: gray;
  cursor: pointer;
  padding: 5px 20px;
}

.mvs-container .filter-wrap > div span.title.active {
  color: #dd6d60;
  background-color: #fcf6f5;
  border-radius: 20px;
}

.mvs-container .filter-wrap > div span.title::before {
  content: "", pos;
}

.mvs-container .filter-wrap > div ul {
  display: flex;
}

.mvs-container .filter-wrap > div ul li:not(:last-child) {
  border-right: 1px solid #f2f2f1;
}

.mvs-container .mvs .items {
  display: flex;
  justify-content: flex-start;
  flex-wrap: wrap;
}

.mvs-container .mvs .items .item {
  width: 250px;
  cursor: pointer;
  margin-right: 25px;
  margin-bottom: 30px;
}

.mvs-container .mvs .items .item .img-wrap {
  width: 100%;
  position: relative;
}

.mvs-container .mvs .items .item .img-wrap img {
  width: 100%;
  border-radius: 5px;
}

.mvs-container .mvs .items .item .img-wrap .num-wrap {
  position: absolute;
  color: white;
  top: 0;
  right: 0;
  display: flex;
  align-content: center;
  font-size: 15px;
  padding-right: 5px;
  padding-top: 2px;
}

.mvs-container .mvs .items .item .img-wrap .num-wrap {
  font-size: 12px;
  display: flex;
  align-items: center;
  margin-right: 5px;

}
.mvs .items .item .img-wrap > .icon-play {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 40px;
  height: 40px;
  color: #dd6d60;
  font-size: 20px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(255, 255, 255, 0.8);
  opacity: 0;
}

 .mvs .items .item .img-wrap .num-wrap >.icon-play::before {
  transform: translateX(3px);
}
.mvs .items .item .img-wrap .num-wrap :hover > .icon-play {
  opacity: 1;
}
.mvs-container .mvs .items .item .info-wrap .name {
  font-size: 15px;
}

.mvs-container .mvs .items .item .info-wrap .singer {
  font-size: 14px;
  color: #c5c5c5;
}
</style>
