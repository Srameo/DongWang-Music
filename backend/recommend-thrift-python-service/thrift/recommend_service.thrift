# 声明 Java 包名
namespace java com.music.thrift.recommend
# 声明 python 包名
namespace py recommend.api

# 定义服务，给出服务名称
service RecommendService {
    # 进行推荐
    bool recommend();
}