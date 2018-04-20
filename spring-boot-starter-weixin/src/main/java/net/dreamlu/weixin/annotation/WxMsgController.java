package net.dreamlu.weixin.annotation;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 微信消息控制器
 *
 * @author L.cm
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public @interface WxMsgController {

	String value() default "/";

}
