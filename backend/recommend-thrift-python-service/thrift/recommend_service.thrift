# 声明 Java 包名
namespace java com.music.thrift.recommend
# 声明 python 包名
namespace py recommend.api

# 定义服务，给出服务名称
service RecommendService {
    # 进行推荐
    bool recommendUser(1:i64 unum, 2:i64 mnum);
    bool recommendMusic(1:i64 mnum, 2:i64 tnum);

    # 返回具体用户的推荐歌曲
    list<i64> getUserRecommendDetail(1:i64 uid);

    # 返回具体歌曲的推荐
    list<i64> getMusicRecommendDetail(1:i64 mid);
}