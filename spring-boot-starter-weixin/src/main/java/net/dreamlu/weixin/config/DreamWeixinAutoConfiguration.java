package net.dreamlu.weixin.config;

import com.jfinal.core.JFinalFilter;
import net.dreamlu.weixin.aspect.WxApiAspect;
import net.dreamlu.weixin.cache.SpringAccessTokenCache;
import net.dreamlu.weixin.properties.DreamWeixinProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@AutoConfigureAfter(DreamWeixinProperties.class)
public class DreamWeixinAutoConfiguration {
	private final CacheManager cacheManager;
	private final DreamWeixinProperties weixinProperties;

	public DreamWeixinAutoConfiguration(CacheManager cacheManager, DreamWeixinProperties weixinProperties) {
		this.cacheManager = cacheManager;
		this.weixinProperties = weixinProperties;
	}

	@Bean
	public FilterRegistrationBean jfinalFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		JFinalFilter jfinalFilter = new JFinalFilter();
		registration.setFilter(jfinalFilter);
		registration.addUrlPatterns(weixinProperties.getUrlPatterns());
		// 添加JFinal configClass参数
		registration.addInitParameter("configClass", DreamWeixinConfig.class.getName());
		registration.setName("jfinalFilter");
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registration;
	}

	@Bean
	public WeixinAppConfig weixinAppConfig() {
		return new WeixinAppConfig(weixinProperties);
	}

	@Bean
	public SpringAccessTokenCache springAccessTokenCache() {
		Cache cache = cacheManager.getCache(weixinProperties.getAccessTokenCache());
		return new SpringAccessTokenCache(cache);
	}

	@Bean
	public WxApiAspect wxApiAspect() {
		return new WxApiAspect(weixinProperties);
	}
}
