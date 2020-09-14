## 说明
jfinal weixin 的 spring boot starter，这个starter是为了方便boot用户使用。

具体demo请查看：`mica-weixin-demo` 和 [JFinal-weixin文档](https://gitee.com/jfinal/jfinal-weixin/wikis/pages?title=Home)

## 开源推荐
- Spring boot 微服务，高效开发之 mica 工具集：[https://gitee.com/596392912/mica](https://gitee.com/596392912/mica)
- `Avue` 一款基于vue可配置化的神奇框架：[https://gitee.com/smallweigit/avue](https://gitee.com/smallweigit/avue)
- `pig` 宇宙最强微服务（架构师必备）：[https://gitee.com/log4j/pig](https://gitee.com/log4j/pig)
- `SpringBlade` 完整的线上解决方案（企业开发必备）：[https://gitee.com/smallc/SpringBlade](https://gitee.com/smallc/SpringBlade)
- `IJPay` 支付SDK让支付触手可及：[https://gitee.com/javen205/IJPay](https://gitee.com/javen205/IJPay)

## Jar包依赖（最新）
```xml
<dependency>
    <groupId>net.dreamlu</groupId>
    <artifactId>mica-weixin</artifactId>
    <version>2.0.2</version>
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
| dream.weixin.access-token-cache | dreamWeixinCache#7100s | 缓存名，需要开启spring cache |
| dream.weixin.app-id-key | appId | 多公众号参数名，如：/weixin/wx?appId=xxx |
| dream.weixin.dev-mode | false | 开发模式 |
| dream.weixin.url-patterns | /weixin/* | weixin 消息处理spring拦截器url前缀 |
| dream.weixin.wx-configs | 公众号的配置 | 多公众号配置 |
| dream.weixin.wxa-configs | 小程序配置 | 小程序配置 |

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
        encodingAesKey: xxx
        messageEncrypt: true
    wxa-configs:
      - app-id: wx4f53594f9a6b3dcb
        app-secret: eec6482ba3804df05bd10895bace0579
```

- `access-token-cache`建议配置有效时间7100秒。

## 更新说明
### 2020-07-07 2.0.1
- 修复 java 编译版本，采用 java8。

### 2020-07-05 v2.0.0
- 改名为 mica-weixin，提升 mica 品牌。
- 内置 @EnableCaching。
- 更改缓存名为 `dreamWeixinCache#7100s`，方便和 `mica-redis` 一起使用。
- 升级 jfinal、jfinal-weixin 版本。

### 2020-05-01 v1.4.0
* 支持多小程序消息，**注意：** yml 配置 key 由 dream.weixin.wxa-config 改为 dream.weixin.wxa-configs。
* 支持自定义公众号和小程序配置加载，可用于从数据库等加载配置。
* 升级 jfinal、jfinal-weixin 版本。

### 2019-08-01 v1.3.6
* 升级 jfinal、jfinal-weixin、okhttp 版本。

### 2019-03-17 v1.3.5
* 解决单公众号下消息报错

### 2019-03-17 v1.3.4
* 解决小程序，启用并设置消息推送配置校验不通过的问题。

### 2019-03-07 v1.3.3
* 升级到 `gradle 5.2.1`。
* 升级 `JFinal` 到 `3.6`。
* 升级 `JFinal Weixin` 到 `2.3`。
* 使用 `mica-auto` 生成 `spring.factories`、`devtools` 配置。
* `InMsg` 消息对象采用 `request` 存储，去掉 `@WxMsgController` 中的 Scope 配置，将消息控制器还原为单例。

### 2018-12-23 v1.3.2
* 修复 `SpringAccessTokenCache` 没有配置的问题，感谢 qq:`A梦的小C` 反馈。

### 2018-12-23 v1.3.1
* `WeixinAppConfig` 改为实现 `SmartInitializingSingleton`。

### 2018-05-03 v1.3.0
* 弃用`@EnableDreamWeixin`，导入jar包即可享用。
* 将消息路由改为spring接管。

## 微信公众号

![如梦技术](docs/dreamlu-weixin.jpg)

精彩内容每日推荐！
