docker环境

1.jenkins 编译。
http://127.0.0.1:8899/
工作目录：/opt/jenkins

2.tomcat目录：/opt/tomcat/

cp_workspace.sh 
```
cp /opt/jenkins/workspace/dev-test1/target/dev-test1.war /opt/tomcat/up_dir
cp /opt/jenkins/workspace/dev-test2/target/dev-test2.war /opt/tomcat/up_dir
cp /opt/jenkins/workspace/dev-test3/target/dev-test3.war /opt/tomcat/up_dir
。。。
```
从jenkins里，把编译结果文件拷出。

解压war文件：
  进入tomcat容器。  -v /opt/tomcat:/opt/tomcat/webapps 
  运行：up_root.sh 
  jar -xvf ../../up_dir/$project.war
  注：进入对应的ROOT目录下执行。jar命令不支持指定目标路径。

3.启动容器

```
docker run -d -p 8084:8080 \
  --name dev-test2 \
  -e JAVA_OPTS='-XX:PermSize=64M -XX:MaxPermSize=512m -Xmx1024m -Xms128m' \
  -v /opt/tomcat/dev-test2:/opt/tomcat/webapps \
  registry.cn-hangzhou.aliyuncs.com/wang19/jdk7-tomcat7
```

4.容器操作

$docker_id 容器ID
查看日志 docker logs -f $docker_id
进入 docker exec -it $docker_id /bin/bash

5.海鸥操作界面：http://127.0.0.1:10086/containers

方便start,stop。不要delete。(如果删除返回步骤3)

docker build -t demo/service-user:v1 .
docker run -d --name service-user-9501 --net=host demo/service-user:v1 /opt/dubbo/bin/start.sh 9501
docker run -d --name service-user-9502 --net=host demo/service-user:v1 /opt/dubbo/bin/start.sh 9502

