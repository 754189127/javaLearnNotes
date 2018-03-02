#Windows 下 Redis
github下载地址：https://github.com/MSOpenTech/redis

安装启动
https://www.cnblogs.com/yougmi/p/6119259.html
http://blog.csdn.net/blick__winkel/article/details/77986481


1、启动 redis-server redis.windows.conf

2、redis设置成windows下的服务 redis-server --service-install redis.windows-service.conf --loglevel verbose

3、常用的redis服务命令。

卸载服务：redis-server --service-uninstall

开启服务：redis-server --service-start

停止服务：redis-server --service-stop
