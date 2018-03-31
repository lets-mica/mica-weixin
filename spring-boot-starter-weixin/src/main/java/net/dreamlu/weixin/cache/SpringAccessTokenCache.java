package net.dreamlu.weixin.cache;

import com.jfinal.weixin.sdk.cache.IAccessTokenCache;
import org.springframework.cache.Cache;

public class SpringAccessTokenCache implements IAccessTokenCache {
	private final String ACCESS_TOKEN_PREFIX = "dream-weixin:token:";
	private final Cache cache;

	public SpringAccessTokenCache(Cache cache) {
		this.cache = cache;
	}

	@Override
	public String get(String key) {
		return cache.get(ACCESS_TOKEN_PREFIX + key, String.class);
	}

	@Override
	public void set(String key, String jsonValue) {
		cache.put(ACCESS_TOKEN_PREFIX + key, jsonValue);
	}

	@Override
	public void remove(String key) {
		cache.evict(ACCESS_TOKEN_PREFIX + key);
	}
}
