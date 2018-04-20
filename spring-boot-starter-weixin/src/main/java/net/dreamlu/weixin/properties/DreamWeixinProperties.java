package net.dreamlu.weixin.properties;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.wxaapp.WxaConfig;
import lombok.Getter;
import lombok.Setter;
import net.dreamlu.weixin.config.WxaMsgParser;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("dream.weixin")
public class DreamWeixinProperties {

	/**
	 * JFinal filter拦截的路由，默认：/weixin/*
	 */
	@Getter
	@Setter
	private String urlPatterns = "/weixin/*";

	/**
	 * 是否开发模式，默认：false
	 */
	@Getter
	@Setter
	private boolean devMode = false;

	/**
	 * Spring cache name，需要开启spring cache，默认：dreamWeixinCache
	 */
	@Getter
	@Setter
	private String accessTokenCache = "dreamWeixinCache";

	/**
	 * 多公众号url挂参，默认：appId
	 */
	@Getter
	@Setter
	private String appIdKey = "appId";

	/**
	 * 多公众号配置
	 */
	@Getter
	private List<ApiConfig> wxConfigs = new ArrayList<ApiConfig>();

	/**
	 * 小程序配置
	 */
	@Getter
	@Setter
	private WxaConfig wxaConfig = new WxaConfig();

	/**
	 * 小程序消息解析，默认xml，支持json和xml
	 */
	@Getter
	@Setter
	private WxaMsgParser wxaMsgParser = WxaMsgParser.XML;

}
