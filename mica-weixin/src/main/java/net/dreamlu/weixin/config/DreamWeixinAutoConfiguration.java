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
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * 微信自动配置
 *
 * @author L.cm
 */
@EnableCaching
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(DreamWeixinProperties.class)
public class DreamWeixinAutoConfiguration {

	@Bean
	public SpringAccessTokenCache springAccessTokenCache(CacheManager cacheManager,
														 DreamWeixinProperties properties) {
		return new SpringAccessTokenCache(cacheManager, properties);
	}

	@Configuration
	@RequiredArgsConstructor
	public static class MsgConfiguration implements WebMvcConfigurer {
		private final DreamWeixinProperties properties;

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			String urlPattern = properties.getUrlPatterns();
			MsgInterceptor httpCacheInterceptor = new MsgInterceptor(properties);
			registry.addInterceptor(httpCacheInterceptor)
				.addPathPatterns(urlPattern);
		}

		@Override
		public void configurePathMatch(PathMatchConfigurer configurer) {

		}

		@Override
		public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

		}

		@Override
		public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

		}

		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

		}

		@Override
		public void addFormatters(FormatterRegistry registry) {

		}

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {

		}

		@Override
		public void addCorsMappings(CorsRegistry registry) {

		}

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {

		}

		@Override
		public void configureViewResolvers(ViewResolverRegistry registry) {

		}

		@Override
		public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

		}

		@Override
		public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

		}

		@Override
		public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		}

		@Override
		public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

		}

		@Override
		public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

		}

		@Override
		public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

		}

		@Override
		public Validator getValidator() {
			return null;
		}

		@Override
		public MessageCodesResolver getMessageCodesResolver() {
			return null;
		}
	}
}
