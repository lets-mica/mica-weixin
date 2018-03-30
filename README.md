## 说明
jfinal weixin 的 spring boot starter，这个starter是为了方便boot用户使用。

具体demo请查看：`spring-boot-weixin-demo`

## Jar包依赖
```xml
<dependency>
    <groupId>net.dreamlu</groupId>
    <artifactId>spring-boot-starter-weixin</artifactId>
    <version>1.0.0</version>
</dependency>
```

`说明`：依赖`spring-boot-starter-aop`

## 使用
### 启用微信
```java
@EnableDreamWeixin
```

### 消息
#### 公众号
1. 继承`MsgControllerAdapter`，实现需要重写的消息。

2. 添加注解`@WxMsgController`，注解value为你的消息地址，使用/weixin/wx

### 小程序
1. 继承`WxaMsgController`，实现需要重写的消息。

2. 添加注解`@WxMsgController`，注解value为你的消息地址，使用/weixin/wxa

### Api
1. 使用传统的spring的控制器即可

2. 添加`@WxApi`注解

### 配置
| 配置项 | 默认值 |
| ----- | ------ |
| dream.weixin.access-token-cache | dreamWeixinCache | 缓存名，需要开启spring cache |
| dream.weixin.app-id-key | appId | 对公众号参数名，如：/weixin/wx?appId=xxx |
| dream.weixin.dev-mode | false | 开发模式 |
| dream.weixin.url-patterns | /weixin/* | JFinal-weixin 过滤器url前缀 |
| dream.weixin.wx-configs | 公众号的配置 | 多公众号配置 |
| dream.weixin.wxa-config | 小程序配置 | 小程序配置 |

`注意`：
- 可参考demo中的[application.yml](spring-boot-weixin-demo/src/main/resources/application.yml)
- cache使用spring的cache，需要`@EnableCaching`开启。
- `access-token-cache`建议配置有效时间7100秒。

## 捐助共勉
 <img src="https://gitee.com/uploads/images/2018/0311/153544_5afb12b1_372.jpeg" width="250px"/>
 <img src="https://gitee.com/uploads/images/2018/0311/153556_679db579_372.jpeg" width="250px"/>

## VIP群
捐助￥199即可加入如梦技术VIP，捐助后联系QQ: 596392912

#### VIP权益
1. spring boot版安全框架（maven + spring boot + spring security + thymeleaf）

2. 技术资料共享

3. spring cloud脚手架（改造中）

4. 更多私有Git资源，
