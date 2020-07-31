# 没人比我更懂音乐

![trump](../frontend/music_player/src/assets/core-img/timg.jpg)

- [没人比我更懂音乐](#没人比我更懂音乐)
- [简介](#简介)
- [使用技术栈](#使用技术栈)
  - [前端](#前端)
    - [框架](#框架)
  - [后端](#后端)
    - [hadoop](#hadoop)
    - [docker](#docker)
    - [数据库](#数据库)
    - [数据缓存](#数据缓存)
    - [框架](#框架-1)
    - [微服务](#微服务)
- [文件结构](#文件结构)
  - [backend](#backend)
    - [message-thrift-***](#message-thrift-)
    - [recommend-thrift-***](#recommend-thrift-)
    - [user-edge-service](#user-edge-service)
  - [docs](#docs)
  - [frontend](#frontend)
    - [music_player](#music_player)
    - [其他](#其他)
  - [mapred](#mapred)
  - [matlab](#matlab)
  - [recommend-python](#recommend-python)
  - [scripts](#scripts)
    - [mysql](#mysql)
    - [mongo](#mongo)
  - [spider](#spider)
- [测试](#测试)
  - [算法测试](#算法测试)
    - [数据来源](#数据来源)
  - [项目打包时测试](#项目打包时测试)
    - [Hadoop](#hadoop-1)
    - [mysql](#mysql-1)
- [贡献者](#贡献者)
- [鸣谢](#鸣谢)

# 简介
一个基于协同过滤推荐系统的人工智障网站

# 使用技术栈

## 前端
### 框架
- vue 

## 后端
### hadoop
- v3.1.3
### docker
- version 19.03.12, build 48a66213fe
### 数据库
- mysql v8.0.20
- MongoDB v4.2.8
### 数据缓存
- redis v6.0.5
### 框架
- springboot v2.3.1RELEASE

### 微服务
- thrift v0.13.0

# 文件结构

## backend
### message-thrift-***
用于发送注册时发送验证码的 thrift 服务
### recommend-thrift-***
用于推荐时候的 thrift 服务
### user-edge-service
用于实现相关服务接口的 springboot 项目

## docs
相关文档

## frontend
### music_player
vue 项目源代码
### 其他
相关的 html 模版文件

## mapred
测试 mapreduce 功能的相关代码

## matlab
算法的设计和测试过程中用到的 matlab 脚本

## recommend-python
推荐算法搬运到 python 中后的测试代码

## scripts
### mysql
创建数据库时使用的相关代码
### mongo
使用 MongoDB 数据预处理时候的相关代码

## spider
数据获取以及储存使用的代码

# 测试

## 算法测试
### 数据来源
- [算法数据来源](http://grouplens.org/datasets/hetrec-2011/)
- [歌曲MV数据来源](https://music.163.com)

## 项目打包时测试
### Hadoop
> windows环境需要安装hdfs的winutils
自动测试能否运行Mapreduce

### mysql
> 使用者需要修改 application.properties 中数据库的地址
测试与服务器 Mysql 的链接

# 贡献者
> 以下五位同学均为南开大学软件工程18级学生
> ~~排名无先后顺序~~

- [靳鑫](https://github.com/Srameo)
- [刘茵](https://github.com/Echhoo)
- [刘宇恒](https://github.com/lyhbalance)
- [郝可欣](https://github.com/hkx-hkx)
- [岳斌鹏](https://github.com/NKyuer)

# 鸣谢
[网易云API](https://github.com/Binaryify/NeteaseCloudMusicApi)