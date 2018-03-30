package net.dreamlu.weixin.properties;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.wxaapp.WxaConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("dream.weixin")
public class DreamWeixinProperties {

	/**
	 * JFinal filter拦截的路由
	 */
	@Getter
	@Setter
	private String urlPatterns = "/weixin/*";

	/**
	 * 是否开发模式
	 */
	@Getter
	@Setter
	private boolean devMode = false;

	/**
	 * accessTokenCache
	 * Spring cache name
	 */
	@Getter
	@Setter
	private String accessTokenCache = "dreamWeixinCache";

	@Getter
	@Setter
	private String appIdKey = "appId";

	@Getter
	private List<ApiConfig> wxConfigs = new ArrayList<ApiConfig>();

	@Getter
	@Setter
	private WxaConfig wxaConfig = new WxaConfig();
}
