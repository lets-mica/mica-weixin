package net.dreamlu.weixin.config;

import net.dreamlu.weixin.aspect.WxApiAspect;
import net.dreamlu.weixin.cache.SpringAccessTokenCache;
import net.dreamlu.weixin.properties.DreamWeixinProperties;
import net.dreamlu.weixin.spring.MsgInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableConfigurationProperties(DreamWeixinProperties.class)
public class DreamWeixinAutoConfiguration {
	private final CacheManager cacheManager;
	private final DreamWeixinProperties weixinProperties;

	public DreamWeixinAutoConfiguration(CacheManager cacheManager, DreamWeixinProperties weixinProperties) {
		this.cacheManager = cacheManager;
		this.weixinProperties = weixinProperties;
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

	@Configuration
	public class MsgConfiguration extends WebMvcConfigurerAdapter {
		private final DreamWeixinProperties properties;

		public MsgConfiguration(DreamWeixinProperties properties) {
			this.properties = properties;
		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			String urlPattern = properties.getUrlPatterns();
			MsgInterceptor httpCacheInterceptor = new MsgInterceptor(properties);
			registry.addInterceptor(httpCacheInterceptor)
					.addPathPatterns(urlPattern);
		}
	}
}
