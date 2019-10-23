## 目的
体验

## 简介
这是是一个基于Spring Boot & MyBatis的项目.

## 特征&提供
- 统一响应结果封装及生成工具
- 统一异常处理
- 常用基础方法抽象封装
- 使用Druid Spring Boot Starter 集成Druid数据库连接池与监控
- 集成MyBatis
- 使用代码生成器根据表名生成对应的Model、Mapper、MapperXML、Service、ServiceImpl、Controller等基础代码

## 项目目录说明
- 源码目录：sql : mysql数据库脚本;
- 源码目录：src\main\java : 存储源码;
- 资源目录：src\main\resources : 存储静态资源；动态页面；配置文件等；
- 目标输出：target : 存储编译文件；
- application.yml: 系统启动配置文件；

## 开发建议
- 表名，建议使用小写，多个单词使用下划线拼接
- 开发规范建议遵循阿里巴巴Java开发手册（[最新版下载](https://github.com/alibaba/p3c))
- 建议在公司内部使用[ShowDoc](https://github.com/star7th/showdoc)、[SpringFox-Swagger2](https://github.com/springfox/springfox) 、[RAP](https://github.com/thx/RAP)等开源项目来编写、管理API文档
 
## 技术选型&文档
- Spring Boot（[查看Spring Boot学习&使用指南](http://www.jianshu.com/p/1a9fd8936bd8)）
- MyBatis（[查看官方中文文档](http://www.mybatis.org/mybatis-3/zh/index.html)）
- Druid Spring Boot Starter（[查看官方中文文档](https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter/)）

## 建议
1. 热部署相关配置请勿上传到git.

## 感谢
1. 参考了bootdo开源项目 http://www.bootdo.com/
2. 参考了若依开源项目 http://ruoyi.vip/
3. 代码全部开源 请遵循GPL协议
