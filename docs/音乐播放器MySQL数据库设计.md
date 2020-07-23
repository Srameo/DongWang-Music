## MySQL数据库设计

### users表

存储用户信息

|   名称   |  类型   |           说明           |
| :------: | :-----: | :----------------------: |
|    id    |   int   |       用户信息主键       |
| username | varchar |          用户名          |
| password | varchar |           密码           |
|  email   | varchar | 用户邮箱，用来接收验证码 |
|   age    |   int   |         用户年龄         |
|  gender  | varchar |         用户性别         |
| location | varchar |        用户所在地        |

### music表

存取歌曲信息

| 名称 |  类型   |      说明      |
| :--: | :-----: | :------------: |
|  id  |   int   | 音乐的唯一表识 |
| name | varchar |    音乐名字    |
| num  |   int   |  歌曲播放次数  |

### stars表

用户收藏歌曲

|   名称    |   类型   |         说明          |
| :-------: | :------: | :-------------------: |
|    uid    |   int    | 外键，对应users表的id |
|    mid    |   int    | 外键，对应music表的id |
| star_time | datetime |       收藏时间        |

### comment表

用户对歌曲的评论

|     名称     |   类型   |         说明          |
| :----------: | :------: | :-------------------: |
|     uid      |   int    | 外键，对应users表的id |
|     mid      |   int    | 外键，对应music表的id |
|   content    |   text   |       评论内容        |
| comment_time | datetime |       评论时间        |

### history_list表

用户的历史播放记录

|   名称    |   类型   |         说明          |
| :-------: | :------: | :-------------------: |
|    uid    |   int    | 外键，对应users表的id |
|    mid    |   int    | 外键，对应music表的id |
| play_time | datetime |       播放时间        |

### singers表

歌手信息表

|   名称   |  类型   |      说明      |
| :------: | :-----: | :------------: |
|    id    |   int   | 歌手的唯一表识 |
|   name   | varchar |    歌手姓名    |
|  gender  | varchar |    歌手性别    |
| location | varchar |  歌手所属地区  |

### singers_music表

歌手与歌曲中间表

| 名称 | 类型 |     说明     |
| :--: | :--: | :----------: |
| sid  | int  | 外键，歌手id |
| mid  | int  | 外键，歌曲id |

### style表

喜好风格

|  名称  |  类型   |     说明     |
| :----: | :-----: | :----------: |
|   id   |   int   | 风格唯一表识 |
| detail | varchar | 风格具体信息 |

### user-style表

user与style的中间表

| 名称 | 类型 |       说明       |
| :--: | :--: | :--------------: |
| uid  | int  | 外键，对应用户id |
| sid  | int  | 外键，对应风格id |

### music-style表

music与style的中间表

| 名称 | 类型 |       说明       |
| :--: | :--: | :--------------: |
| mid  | int  | 外键，对应歌曲id |
| sid  | int  | 外键，对应风格id |