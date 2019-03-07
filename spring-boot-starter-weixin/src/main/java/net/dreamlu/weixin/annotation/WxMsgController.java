package net.dreamlu.weixin.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping
@Controller
public @interface WxMsgController {

	/**
	 * Alias for {@link RequestMapping#value}.
	 *
	 * @return {String[]}
	 */
	@AliasFor(annotation = RequestMapping.class)
	String[] value() default {};

}
