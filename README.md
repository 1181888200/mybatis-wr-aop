# mybatis-wr-aop
mybatis通过springAOP实现主从库读写分离

测试方法：

1. 首先我们在本地创建test_master主库 和 test_slave从数据库

2. 在各自数据库执行db.sql

3. 然后将项目导入到开发工具中，修改application.properties文件中的数据库对应的名称和密码

4. 启动项目，找到MybatisWrApplicationTests类进行对应的测试

5. 先将com.lwl.aop2里面的类注释掉，先测通过注解的方式实现读写分离

6. 测试完5之后，再将com.lwl.aop2打开，com.lwl.aop关闭，测试通过正则表达式方式测试
