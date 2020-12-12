package net.dreamlu.weixin.config;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.wxaapp.WxaConfig;

import java.util.Collections;
import java.util.List;

/**
 * 微信配置加载器，用于实现数据库读取自定义配置等
 *
 * @author L.cm
 */
public interface WxConfigLoader {
	WxConfigLoader DEFAULT = new WxConfigLoader() {};

	/**
	 * 加载微信配置
	 *
	 * @return 微信配置列表
	 */
	default List<ApiConfig> loadWx() {
		return Collections.emptyList();
	}

	/**
	 * 加载小程序配置
	 *
	 * @return 小程序配置列表
	 */
	default List<WxaConfig> loadWxa() {
		return Collections.emptyList();
	}

}
