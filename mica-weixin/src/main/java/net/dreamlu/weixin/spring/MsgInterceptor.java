package net.dreamlu.weixin.spring;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.wxaapp.WxaConfigKit;
import lombok.RequiredArgsConstructor;
import net.dreamlu.weixin.annotation.WxApi;
import net.dreamlu.weixin.properties.DreamWeixinProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * 消息拦截器
 *
 * @author L.cm
 */
@RequiredArgsConstructor
public class MsgInterceptor extends HandlerInterceptorAdapter {
	private static final Log logger = LogFactory.getLog(MsgInterceptor.class);
	private final DreamWeixinProperties weixinProperties;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 非控制器请求直接跳出
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 如果是 api 接口
		Class<?> beanType = handlerMethod.getBeanType();
		// 处理接口业务
		WxApi wxApi = AnnotationUtils.getAnnotation(beanType, WxApi.class);
		String appId = request.getParameter(weixinProperties.getAppIdKey());
		if (wxApi != null) {
			if (StringUtils.hasText(appId)) {
				ApiConfigKit.setThreadLocalAppId(appId);
			}
			return true;
		}
		// 判断是否多公众号，将 appId 与当前线程绑定，以便在后续操作中方便获取ApiConfig对象：
		Object bean = handlerMethod.getBean();
		boolean isWx = bean instanceof MsgController;
		String token;
		if (isWx) {
			if (StringUtils.hasText(appId)) {
				ApiConfigKit.setThreadLocalAppId(appId);
				token = ApiConfigKit.getApiConfig(appId).getToken();
			} else {
				token = ApiConfigKit.getApiConfig().getToken();
			}
		} else {
			if (StringUtils.hasText(appId)) {
				WxaConfigKit.setThreadLocalAppId(appId);
				token = WxaConfigKit.getWxaConfig(appId).getToken();
			} else {
				token = WxaConfigKit.getWxaConfig().getToken();
			}
		}
		// 如果是服务器配置请求，则配置服务器并返回
		if (isConfigServerRequest(request)) {
			configServer(request, response, token);
			return false;
		}
		// 对开发测试更加友好
		if (ApiConfigKit.isDevMode()) {
			return true;
		} else {
			// 签名检测
			if (checkSignature(request, token)) {
				return true;
			} else {
				WebUtils.renderText(response, "签名验证失败，请确定是微信服务器在发送消息过来");
				return false;
			}
		}

	}

	/**
	 * 检测签名
	 */
	private boolean checkSignature(HttpServletRequest request, String token) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		if (StrKit.isBlank(signature) || StrKit.isBlank(timestamp) || StrKit.isBlank(nonce)) {
			logger.error("check signature failure");
			return false;
		}
		if (checkSignature(token, signature, timestamp, nonce)) {
			return true;
		} else {
			logger.error("check signature failure: " +
				" signature = " + signature +
				" timestamp = " + timestamp +
				" nonce = " + nonce);
			return false;
		}
	}

	/**
	 * 是否为开发者中心保存服务器配置的请求
	 */
	private boolean isConfigServerRequest(HttpServletRequest request) {
		return StrKit.notBlank(request.getParameter("echostr"));
	}

	/**
	 * 配置开发者中心微信服务器所需的 url 与 token
	 *
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 */
	private void configServer(HttpServletRequest request, HttpServletResponse response, String token) {
		// 通过 echostr 判断请求是否为配置微信服务器回调所需的 url 与 token
		String echostr = request.getParameter("echostr");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		boolean isOk = checkSignature(token, signature, timestamp, nonce);
		if (isOk && !response.isCommitted()) {
			WebUtils.renderText(response, echostr);
		} else {
			logger.error("验证失败：configServer");
		}
	}

	private boolean checkSignature(String token, String signature, String timestamp, String nonce) {
		String[] array = new String[]{token, timestamp, nonce};
		Arrays.sort(array);
		String tempStr = array[0] + array[1] + array[2];
		tempStr = HashKit.sha1(tempStr);
		return tempStr.equalsIgnoreCase(signature);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		ApiConfigKit.removeThreadLocalAppId();
		WxaConfigKit.removeThreadLocalAppId();
	}

}
