package net.dreamlu.weixin.config;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.wxaapp.WxaConfig;
import com.jfinal.wxaapp.WxaConfigKit;
import net.dreamlu.weixin.properties.DreamWeixinProperties;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public class WeixinAppConfig implements InitializingBean {
	private final DreamWeixinProperties weixinProperties;

	public WeixinAppConfig(DreamWeixinProperties weixinProperties) {
		this.weixinProperties = weixinProperties;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		boolean isdev = weixinProperties.isDevMode();
		ApiConfigKit.setDevMode(isdev);
		List<ApiConfig> list = weixinProperties.getWxConfigs();
		for (ApiConfig apiConfig : list) {
			ApiConfigKit.putApiConfig(apiConfig);
		}
		WxaConfig wxaConfig = weixinProperties.getWxaConfig();
		WxaConfigKit.setDevMode(isdev);
		WxaConfigKit.setWxaConfig(wxaConfig);
	}
}
