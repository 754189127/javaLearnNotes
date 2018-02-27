shiro 权限系统

[教程](http://jinnianshilongnian.iteye.com/blog/2018398)

[单点登录](http://blog.csdn.net/why15732625998/article/details/78647375)

[注入顺序](http://www.hillfly.com/2017/179.html)

[使用redis进行基于shiro的session集群共享](https://www.cnblogs.com/sunshine-2015/p/5686750.html)

[Shiro与Redis集成，集群下的session共享](http://blog.csdn.net/jrn1012/article/details/70373221)
[shiro实现session共享 有代码](https://www.cnblogs.com/jiangkuan/p/6189136.html)

[用MongoDB做Session存储](https://www.cnblogs.com/tpniu/p/8182421.html)


    <!-- Realm实现 -->
    <bean id="userRealm" class="com.up360.base.util.UpRealm">
    	<property name="name" value="up360Realm"/>
        <property name="credentialsMatcher" ref="credentialsMatcher"/>
        <property name="cachingEnabled" value="true"/>
        <property name="authenticationCachingEnabled" value="false"/>
        <property name="authenticationCacheName" value="authenticationCache"/>
        <property name="authorizationCachingEnabled" value="true"/>
        <property name="authorizationCacheName" value="authorizationCache"/>
        <property name="cacheManager" ref="upCacheManager"/>
    </bean>

    <!-- 安全管理器 -->
    <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        <property name="realm" ref="userRealm"/>
        <property name="sessionManager" ref="sessionManager"/>
        <property name="cacheManager" ref="upCacheManager"/>
        <property name="rememberMeManager" ref="rememberMeManager"/>
    </bean>

ShiroUtil

UpRealm

SecurityUtils.getSubject();

登出：subject.logout();

![调用图](http://dl2.iteye.com/upload/attachment/0093/9790/5e0e9b41-0cca-367f-8c87-a8398910e7a6.png)

默认提供的Realm:
![默认提供的Realm](http://dl2.iteye.com/upload/attachment/0094/0175/34062d4e-8ac5-378a-a9e2-4845f0828292.png)

boot里注意filter的顺序。默认把shiroFilter也加载进去了。需要调到前面。否则会把subject给重置掉。

Shiro的Realm 是属于Filte，因此在web.xml里面加载的时候会先加载Filete，然后才加载到Spring，
所以Realm中 @Autowired总是找不到bean，其实容器启动后，web.xml的配置加载顺序是
ServletContext--context-param--listener--filter--servlet


