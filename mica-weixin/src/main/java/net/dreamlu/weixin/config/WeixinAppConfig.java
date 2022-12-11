package net.dreamlu.weixin.config;

import com.jfinal.json.JacksonFactory;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.utils.JsonUtils;
import com.jfinal.wxaapp.WxaConfig;
import com.jfinal.wxaapp.WxaConfigKit;
import lombok.RequiredArgsConstructor;
import net.dreamlu.weixin.cache.SpringAccessTokenCache;
import net.dreamlu.weixin.properties.DreamWeixinProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信应用配置
 *
 * @author L.cm
 */
@AutoConfiguration
@RequiredArgsConstructor
public class WeixinAppConfig implements SmartInitializingSingleton {
	private final DreamWeixinProperties weiXinProperties;
	private final SpringAccessTokenCache accessTokenCache;
	private final ObjectProvider<WxConfigLoader> provider;

	@Override
	public void afterSingletonsInstantiated() {
		boolean isDev = weiXinProperties.isDevMode();
		ApiConfigKit.setDevMode(isDev);
		ApiConfigKit.setAccessTokenCache(accessTokenCache);
		List<WxConf> wxConfList = new ArrayList<>(weiXinProperties.getWxConfigs());
		for (WxConf conf : wxConfList) {
			ApiConfig config = new ApiConfig();
			if (StrKit.notBlank(conf.getAppId())) {
				config.setAppId(conf.getAppId());
			}
			if (StrKit.notBlank(conf.getAppSecret())) {
				config.setAppSecret(conf.getAppSecret());
			}
			if (StrKit.notBlank(conf.getToken())) {
				config.setToken(conf.getToken());
			}
			if (StrKit.notBlank(conf.getEncodingAesKey())) {
				config.setEncodingAesKey(conf.getEncodingAesKey());
			}
			config.setEncryptMessage(conf.isMessageEncrypt());
			ApiConfigKit.putApiConfig(config);
		}
		List<WxaConf> wxaConfList = new ArrayList<>(weiXinProperties.getWxaConfigs());
		for (WxaConf conf : wxaConfList) {
			WxaConfig config = new WxaConfig();
			if (StrKit.notBlank(conf.getAppId())) {
				config.setAppId(conf.getAppId());
			}
			if (StrKit.notBlank(conf.getAppSecret())) {
				config.setAppSecret(conf.getAppSecret());
			}
			if (StrKit.notBlank(conf.getToken())) {
				config.setToken(conf.getToken());
			}
			if (StrKit.notBlank(conf.getEncodingAesKey())) {
				config.setEncodingAesKey(conf.getEncodingAesKey());
			}
			config.setMessageEncrypt(conf.isMessageEncrypt());
			WxaConfigKit.setWxaConfig(config);
		}
		WxaConfigKit.setDevMode(isDev);
		if (WxaMsgParser.JSON == weiXinProperties.getWxaMsgParser()) {
			WxaConfigKit.useJsonMsgParser();
		}
		if ("jackson".equalsIgnoreCase(weiXinProperties.getJsonType())) {
			JsonUtils.setJsonFactory(JacksonFactory.me());
		}
		// 自定义的加载器
		WxConfigLoader configLoader = provider.getIfAvailable();
		// 兼容老板 spring boot
		if (configLoader == null) {
			configLoader = WxConfigLoader.DEFAULT;
		}
		List<ApiConfig> apiConfigList = configLoader.loadWx();
		if (apiConfigList != null && !apiConfigList.isEmpty()) {
			for (ApiConfig config : apiConfigList) {
				ApiConfigKit.putApiConfig(config);
			}
		}
		List<WxaConfig> wxaConfigList = configLoader.loadWxa();
		if (wxaConfigList != null && !wxaConfigList.isEmpty()) {
			for (WxaConfig config : wxaConfigList) {
				WxaConfigKit.setWxaConfig(config);
			}
		}
	}

}
