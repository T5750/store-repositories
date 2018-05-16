# 后台管理系统

## 技术选型
- 核心框架：Spring Boot 1.5
- 安全框架：Apache Shiro 1.3
- 视图框架：Spring MVC 4.3
- 持久层框架：MyBatis 3.3
- 定时器：Quartz 2.3
- 数据库连接池：Druid 1.0
- 日志管理：SLF4J 1.7、Log4j
- 页面交互：Vue2.x

## 教程
### 1. 数据库
1.1 安装`MySQL`
- [MySQL-5.6](https://mirrors.tuna.tsinghua.edu.cn/mysql/downloads/MySQL-5.6/)
- [mysql-5.6.38-winx64.zip](https://mirrors.tuna.tsinghua.edu.cn/mysql/downloads/MySQL-5.6/mysql-5.6.38-winx64.zip)
- [mysql-5.6.38-win32.zip](https://mirrors.tuna.tsinghua.edu.cn/mysql/downloads/MySQL-5.6/mysql-5.6.38-win32.zip)

1.2 安装`Navicat for MySQL`

1.3 新建`backyard-plus`数据库，字符集`utf-8`，并导入`backyard-plus.sql`

### 2. Java
- 安装`jdk-7u79-windows-i586`或`jdk-7u80-windows-x64`

### 3. 运行项目
- 在命令行中，执行```java -jar backyard-plus-1.1.0.jar```
- 或者，导入项目，运行`BackyardApplication`

### 4. 访问项目
- 后台管理系统```http://localhost:8080/backyard-plus```
- 用户名`admin`或`test`, 密码`123456`
- 后台接口```http://localhost:8080/backyard-plus/swagger/index.html```