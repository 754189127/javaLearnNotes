# 开发规范

* 好好学习，天天向上。
* 码出高效，码出质量。
* 精益求精，精雕细琢。

### 参考
https://github.com/alibaba/p3c
熟读阿里巴巴Java开发手册，review的主要参考依据。插件工具用起来,规范用工具固化下去。

打开Window->Preferences->Java->Code Style->Code Templates
点击"Import"，导入模板eclipse-codetemplate.xml文件。


### java开发技术栈：
mysql，redis，mongodb，rocketmq，shiro，spring-boot，dubbo，swagger，docker。

### 闭环原则

团队开发周期：产品需求->计划会->开发过程->演示会->回顾会->改进

个人开发周期：需求说明->测试用例->代码实现->本地测试通过->提交代码->内网测试通过->预发布->上线->监控->数据反馈

自动化测试：单元测试->集成测试->回归测试->线上监控->数据反馈

代码分支机制：issues->建分支->修改代码->提交分支->发起merge request->review->代码合并->删除分支->关闭issues。

  在commit message里面使用#issue, 比如#8, github就会自动关联issue 8跟这个commit.
  http://www.ruanyifeng.com/blog/2015/12/git-workflow.html

gitlab-ci.yml规约

http://blog.csdn.net/wmq880204/article/details/70141771

https://docs.gitlab.com/ce/ci/README.html


### 单元测试
静态代码分析：Sonar进行代码分析


各层定义自己的数据类，以维持各层自己的稳定。数据字段解耦。增加转换操作。

从数据库向页面传数据，要通过dao->do-service->bo->controller->vo。

从页面向数据库传数据，要通过vo->controller->bo->service->do->dao

```
各层职责示例：
用户登录：
controller 检查提交的字段是否为空(用户名，密码，图形校验码，短信校验码)
service：具体业务逻辑。
  调用dao(获取用户)。
比较密码，校验次数，校验图形校验
  调用manager(生成token,发短信，记日志)
  调用dao(更新登录次数)
```
  


----
项目分层。

dao: 与存储交互。mysql,redis,mongodb.

manager:组合dao，第三方。rocketmq,封装第三方。通用处理方法，比如缓存，消息，推送，邮件。

service:面向服务，用业务语言。命名上要有可读性。

### dubbo规约
GroupID格式： com.xxx.分层.业务.角色

dubbo的设计原则：http://dubbo.io/books/dubbo-dev-book/principals/code-detail.html

服务化最佳实践：http://dubbo.io/books/dubbo-user-book/best-practice.html

当不兼容时，先升级一半提供者为新版本，再将消费者全部升为新版本，然后将剩下的一半提供者升为新版本。

服务接口增加方法，或服务模型增加字段，可向后兼容，删除方法或删除字段，将不兼容，枚举类型新增字段也不兼容，需通过变更版本号升级。

如果是在返回值中用了 Enum，并新增了 Enum 值，建议先升级服务消费方，这样服务提供方不会返回新值。

如果是在传入参数中用了 Enum，并新增了 Enum 值，建议先升级服务提供方，这样服务消费方不会传入新值。


### swagger规约
API：get，post。swagger说明。访问控制，基本参数校验。


### mysql规约

```
T save(T t);
void delete(ID id);
void delete(T t);
void delete(List<T> tList);
T findOne(ID id);
T findOne(T t);
List<T> findAll();
List<T> findAll(T t);
Page<T> findAll(T t, PageRequest pageRequest);
List<T> findAll(Iterable<ID> ids);
long count();
long count(T t);
```

### redis规约

### mongodb规约

### rocketmq规约

### shiro规约

### UDDD驱动开发

### 日志规约
埋点，日志，全链路跟踪。

	* 如果不可恢复或需要报警，打印 ERROR 日志。提供解决方案和联系人。
	* 如果可恢复异常，或瞬时的状态不一致，打印 WARN 日志。WARN 日志每周汇总发送通知。
	* 正常运行时的中间状态提示，打印 INFO 日志。
	* 调试信息，打印DEBUG。


### 发布规约
确保系统可平滑更新，优先级最高。

接口提供版本号，兼容版本号不变，不兼容版本号上升。



### 编码规约
1.历史文件禁止大面积格式化，会干扰文件对比。




### 版本更新说明
|版本号|更新日期|备注|
| -- | -- |--|
|1.0.0|2018.2.26|大纲|

