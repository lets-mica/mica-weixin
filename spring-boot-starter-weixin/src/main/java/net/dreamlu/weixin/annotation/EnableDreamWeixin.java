package net.dreamlu.weixin.annotation;

import net.dreamlu.weixin.config.DreamWeixinAutoConfiguration;
import net.dreamlu.weixin.properties.DreamWeixinProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
	DreamWeixinProperties.class,
	DreamWeixinAutoConfiguration.class
})
public @interface EnableDreamWeixin {}
