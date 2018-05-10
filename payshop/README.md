# payshop

## Runtime Environment
- [Spring Framework 4.1.4.RELEASE](http://projects.spring.io/spring-framework)
- [MyBatis 3.2.8](http://www.mybatis.org/mybatis-3/)
- [Druid 1.0.13](https://github.com/alibaba/druid)
- [Redis 2.7.3](https://redis.io)
- [Ehcache 2.6](http://www.ehcache.org/downloads/)
- [Lucene 5.3.1](http://lucene.apache.org/)
- [Tomcat 7](http://tomcat.apache.org/)
- [MySQL 5.6](http://www.mysql.com/)

## Quick Process
* `zsshop.sql`
- pc首页 [http://localhost:8080](http://localhost:8080)
- 后台管理 [http://localhost:8080/login](http://localhost:8080/login)
- 首页2 [http://localhost:8080/web1](http://localhost:8080/web1)
- wap [http://localhost:8080/wap1](http://localhost:8080/wap1)
- 首页3 [http://localhost:8080/youhong](http://localhost:8080/youhong)
* 后台管理 username: admin, password: admin

### 已完成功能
- 后台 用户管理 角色管理 菜单管理 组织管理 日志管理
- 监控 jvm监控 ehcache监控 durid数据库监控
- 商城 商品管理 首页菜单管理 楼层管理 商品类别 订单管理 文章管理
- 商城前台 主页菜单 楼层 文章 商品展示，商品详情展示，购物 添加商品到购物车，结算 微信支付，支付宝支付。

### 技术要点
- 登录用户的浏览记录存redis，hash存储 一周过期
- log4j2 通过配置直接将数据存入logstash，然后通过elk展示分析

## Results
![wap](http://www.wailian.work/images/2018/05/10/wap.png)
![homepage](http://www.wailian.work/images/2018/05/10/homepage.png)
![backyard](http://www.wailian.work/images/2018/05/10/backyard.png)

## Tips
* Commits on 2017-12-06
* Modified: `resources.properties`
* Deleted files:
    - `WebContent/static/...`
    - `WebContent/WEB-INF/lib/`

## Links
- [payshop](https://gitee.com/JiaGou-XiaoGe/payshop)