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
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信应用配置
 *
 * @author L.cm
 */
@Configuration
@RequiredArgsConstructor
public class WeixinAppConfig implements SmartInitializingSingleton {
	private final DreamWeixinProperties weixinProperties;
	private final SpringAccessTokenCache accessTokenCache;
	private final ObjectProvider<WxConfigLoader> provider;

	@Override
	public void afterSingletonsInstantiated() {
		boolean isdev = weixinProperties.isDevMode();
		ApiConfigKit.setDevMode(isdev);
		ApiConfigKit.setAccessTokenCache(accessTokenCache);
		WxConfigLoader configLoader = provider.getIfAvailable();
		// 兼容老板 spring boot
		if (configLoader == null) {
			configLoader = WxConfigLoader.DEFAULT;
		}
		List<WxConf> wxConfList = new ArrayList<>(weixinProperties.getWxConfigs());
		wxConfList.addAll(configLoader.loadWx());
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
		List<WxaConf> wxaConfList = new ArrayList<>(weixinProperties.getWxaConfigs());
		wxaConfList.addAll(configLoader.loadWxa());
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
		WxaConfigKit.setDevMode(isdev);
		if (WxaMsgParser.JSON == weixinProperties.getWxaMsgParser()) {
			WxaConfigKit.useJsonMsgParser();
		}
		if ("jackson".equalsIgnoreCase(weixinProperties.getJsonType())) {
			JsonUtils.setJsonFactory(JacksonFactory.me());
		}
	}

}
