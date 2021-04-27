package net.dreamlu.weixin.cache;

import com.jfinal.weixin.sdk.cache.IAccessTokenCache;
import lombok.RequiredArgsConstructor;
import net.dreamlu.weixin.properties.DreamWeixinProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Objects;

/**
 * 基于 spring cache 的 weixin token 缓存
 *
 * @author L.cm
 */
@RequiredArgsConstructor
public class SpringAccessTokenCache implements IAccessTokenCache {
	private final static String ACCESS_TOKEN_PREFIX = "dream-weixin:token:";
	private final CacheManager cacheManager;
	private final DreamWeixinProperties properties;

	@Override
	public String get(String key) {
		return getCache().get(ACCESS_TOKEN_PREFIX + key, String.class);
	}

	@Override
	public void set(String key, String jsonValue) {
		getCache().put(ACCESS_TOKEN_PREFIX + key, jsonValue);
	}

	@Override
	public void remove(String key) {
		getCache().evict(ACCESS_TOKEN_PREFIX + key);
	}

	private Cache getCache() {
		String accessTokenCacheName = properties.getAccessTokenCache();
		Cache cache = cacheManager.getCache(accessTokenCacheName);
		return Objects.requireNonNull(cache, "AccessToken cache is null.");
	}

}
