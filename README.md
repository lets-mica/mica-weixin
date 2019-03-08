## 说明
jfinal weixin 的 spring boot starter，这个starter是为了方便boot用户使用。

具体demo请查看：`spring-boot-weixin-demo` 和 [JFinal-weixin文档](https://gitee.com/jfinal/jfinal-weixin/wikis/pages?title=Home)

## 推荐
- Spring boot 微服务，高效开发之 mica 工具集：https://gitee.com/596392912/mica

- `pig` 宇宙最强微服务（架构师必备）：https://gitee.com/log4j/pig

- `SpringBlade` 完整的线上解决方案（系统生产必备）：https://gitee.com/smallc/SpringBlade

加入【如梦技术】Spring QQ群：479710041，了解更多。

## Jar包依赖（最新）
```xml
<dependency>
    <groupId>net.dreamlu</groupId>
    <artifactId>spring-boot-starter-weixin</artifactId>
    <version>1.3.3</version>
</dependency>
```

## 使用
### 消息
#### 公众号
1. 继承`DreamMsgControllerAdapter`，实现需要重写的消息。

2. 类添加注解`@WxMsgController`，注解value为你的消息地址，使用/weixin/wx，已经组合[@RequestMapping和@Controller]

### 小程序
1. 继承`DreamWxaMsgController`，实现需要重写的消息。

2. 添加注解`@WxMsgController`，注解value为你的消息地址，使用/weixin/wxa，已经组合[@RequestMapping和@Controller]

### Api
- 类添加`@WxApi`，注解value为你的消息地址，使用/weixin/api，已经组合[@RequestMapping和@Controller]

### 配置
| 配置项 | 默认值 | 说明 |
| ----- | ------ | ------ |
| dream.weixin.access-token-cache | dreamWeixinCache | 缓存名，需要开启spring cache |
| dream.weixin.app-id-key | appId | 多公众号参数名，如：/weixin/wx?appId=xxx |
| dream.weixin.dev-mode | false | 开发模式 |
| dream.weixin.url-patterns | /weixin/* | weixin 消息处理spring拦截器url前缀 |
| dream.weixin.wx-configs | 公众号的配置 | 多公众号配置 |
| dream.weixin.wxa-config | 小程序配置 | 小程序配置 |

`注意`：
- demo中的`application.yml`
```yml
dream:
  weixin:
    dev-mode: true
    wx-configs:
      - appId: wx9803d1188fa5fbda
        appSecret: db859c968763c582794e7c3d003c3d87
      - appId: wxc03edcd008ad1e70
        appSecret: 11ed9e2b8e3e3c131e7be320a42b2b5a
        token: 123456
    wxa-config:
      app-id: wx4f53594f9a6b3dcb
      app-secret: eec6482ba3804df05bd10895bace0579
```

- cache使用spring的cache，需要`@EnableCaching`开启。
- `access-token-cache`建议配置有效时间7100秒。

## 更新说明
>## 2019-03-07 v1.3.3
> 升级到 `gradle 5.2.1`。

> 升级 `JFinal` 到 `3.6`。

> 升级 `JFinal Weixin` 到 `2.3`。

> 使用 `mica-auto` 生成 `spring.factories`、`devtools` 配置。

> `InMsg` 消息对象采用 `request` 存储，去掉 `@WxMsgController` 中的 Scope 配置，将消息控制器还原为单例。

>## 2018-12-23 v1.3.2
> 修复 `SpringAccessTokenCache` 没有配置的问题，感谢 qq:`A梦的小C` 反馈。

>## 2018-12-23 v1.3.1
> `WeixinAppConfig` 改为实现 `SmartInitializingSingleton`。

>## 2018-05-03 v1.3.0
> 弃用`@EnableDreamWeixin`，导入jar包即可享用。
> 将消息路由改为spring接管。

## 微信公众号

![如梦技术](docs/dreamlu-weixin.jpg)

精彩内容每日推荐！
