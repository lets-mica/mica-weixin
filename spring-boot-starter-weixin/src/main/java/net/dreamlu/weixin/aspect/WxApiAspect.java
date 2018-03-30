package net.dreamlu.weixin.aspect;

import com.jfinal.weixin.sdk.api.ApiConfigKit;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.weixin.annotation.WxApi;
import net.dreamlu.weixin.properties.DreamWeixinProperties;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Order
@Slf4j
public class WxApiAspect {

	private final DreamWeixinProperties weixinProperties;

	public WxApiAspect(DreamWeixinProperties weixinProperties) {
		this.weixinProperties = weixinProperties;
	}

	@Around("@annotation(wxApi)")
	public Object aroundWxApi(ProceedingJoinPoint point, WxApi wxApi) throws Throwable {
		// 目前不支持多小程序，所以不用判断是否为小程序
		HttpServletRequest request = getRequest();
		try {
			String appId = request.getParameter(weixinProperties.getAppIdKey());
			ApiConfigKit.setThreadLocalAppId(appId);
			return point.proceed();
		} finally {
			ApiConfigKit.removeThreadLocalAppId();
		}
	}

	/**
	 * 获取 HttpServletRequest
	 * @return {HttpServletRequest}
	 */
	private HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
}
