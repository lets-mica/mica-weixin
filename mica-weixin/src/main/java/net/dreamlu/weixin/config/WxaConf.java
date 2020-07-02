package net.dreamlu.weixin.config;

import lombok.Getter;
import lombok.Setter;

/**
 * 小程序的配置
 *
 * @author L.cm
 */
@Getter
@Setter
public class WxaConf {
	private String appId;
	private String appSecret;
	private String token;
	private String encodingAesKey;
	/**
	 * 消息加密与否
 	 */
	private boolean messageEncrypt = false;
}
