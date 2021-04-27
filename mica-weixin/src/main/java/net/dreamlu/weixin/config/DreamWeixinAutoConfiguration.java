package net.dreamlu.weixin.config;

import lombok.RequiredArgsConstructor;
import net.dreamlu.weixin.cache.SpringAccessTokenCache;
import net.dreamlu.weixin.properties.DreamWeixinProperties;
import net.dreamlu.weixin.spring.MsgInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 微信自动配置
 *
 * @author L.cm
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(DreamWeixinProperties.class)
public class DreamWeixinAutoConfiguration {

	@Bean
	public SpringAccessTokenCache springAccessTokenCache(CacheManager cacheManager,
														 DreamWeixinProperties properties) {
		return new SpringAccessTokenCache(cacheManager, properties);
	}

	@Configuration
	@RequiredArgsConstructor
	public static class MsgConfiguration extends WebMvcConfigurerAdapter {
		private final DreamWeixinProperties properties;

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			String urlPattern = properties.getUrlPatterns();
			MsgInterceptor httpCacheInterceptor = new MsgInterceptor(properties);
			registry.addInterceptor(httpCacheInterceptor)
				.addPathPatterns(urlPattern);
		}
	}
}
