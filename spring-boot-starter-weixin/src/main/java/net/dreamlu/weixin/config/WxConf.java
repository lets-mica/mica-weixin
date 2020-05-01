package net.dreamlu.weixin.config;

import lombok.Getter;
import lombok.Setter;

/**
 * 微信配置
 *
 * @author L.cm
 */
@Getter
@Setter
public class WxConf {
	private String token;
	private String appId;
	private String appSecret;
	private String encodingAesKey;
	/**
	 * 消息加密与否
 	 */
	private boolean messageEncrypt = false;
}
