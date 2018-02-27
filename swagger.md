swagger是什么:

http://blog.csdn.net/u010368749/article/details/71214155
Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。总体目标是使客户端和文件系统作为服务器以同样的速度来更新。文件的方法，参数和模型紧密集成到服务器端的代码，允许API来始终保持同步。Swagger 让部署管理和使用功能强大的API从未如此简单。  

注意api版本和UI的对应。

<swagger.version>2.7.0</swagger.version>

<!-- swagger -->  
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>${swagger.version}</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>${swagger.version}</version>
</dependency>


http://localhost:8080/swagger-ui.html


http://www.th7.cn/Program/java/201706/1187146.shtml
Caused by: java.lang.ClassNotFoundException: com.fasterxml.classmate.members.ResolvedParameterizedMember
<dependency>
    <groupId>com.fasterxml</groupId>
    <artifactId>classmate</artifactId>
</dependency>


1. @Api
用在类上，说明该类的作用
@Api(value = "UserController", description = "用户相关api")

2. @ApiOperation
用在方法上，说明方法的作用
@ApiOperation(value = "查找用户", notes = "查找用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

3 @ApiImplicitParams
用在方法上包含一组参数说明

4. @ApiImplicitParam
用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
paramType：参数放在哪个地方
header–>请求参数的获取：@RequestHeader
query–>请求参数的获取：@RequestParam
path（用于restful接口）–>请求参数的获取：@PathVariable
body（不常用）
form（不常用）
name：参数名
dataType：参数类型
required：参数是否必须传
value：参数的意思
defaultValue：参数的默认值

5. @ApiResponses
用于表示一组响应

6. @ApiResponse
用在@ApiResponses中，一般用于表达一个错误的响应信息
code：数字，例如400
message：信息，例如”请求参数没填好”
response：抛出异常的类

7. @ApiModel
描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
@ApiModel(value = "用户实体类")

8. @ApiModelProperty
描述一个model的属性
@ApiModelProperty(value = "登录用户")

https://www.cnblogs.com/woshimrf/p/5863318.html

 //class 替换为你项目中SwaggerConfig的实际路径
<beanclass="com.xmsrs.hengyun.api.modules.utils.SwaggerConfig"/>

<!--Swagger资源重定向(仅作为后台使用不提供静态资源) -->
<mvc:resourceslocation="classpath:/META-INF/resources/"mapping="swagger-ui.html"/>
<mvc:resourceslocation="classpath:/META-INF/resources/webjars/"mapping="/webjars/**"/>
