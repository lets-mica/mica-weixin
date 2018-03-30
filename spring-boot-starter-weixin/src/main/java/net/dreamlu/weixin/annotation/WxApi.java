package net.dreamlu.weixin.annotation;

import java.lang.annotation.*;

/**
 * 微信Api接口注解用于拦截器
 *
 * @author L.cm
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WxApi {

//	/**
//	 * 目前不支持多小程序
//	 * @return {ApiType}
//	 */
//	ApiType value() default ApiType.WX;

}
