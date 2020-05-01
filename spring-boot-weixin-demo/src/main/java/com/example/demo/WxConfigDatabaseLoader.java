package com.example.demo;

import net.dreamlu.weixin.config.WxConf;
import net.dreamlu.weixin.config.WxConfigLoader;
import net.dreamlu.weixin.config.WxaConf;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * 微信配置加载器，用于自定义实现
 *
 * @author L.cm
 */
@Configuration
public class WxConfigDatabaseLoader implements WxConfigLoader {

	@Override
	public List<WxConf> loadWx() {
		WxConf wxConf = new WxConf();
		wxConf.setAppId("wxc03edcd008ad1e70");
		wxConf.setAppSecret("11ed9e2b8e3e3c131e7be320a42b2b5a");
		wxConf.setToken("123456");
		return Collections.singletonList(wxConf);
	}

	@Override
	public List<WxaConf> loadWxa() {
		return Collections.emptyList();
	}
}
