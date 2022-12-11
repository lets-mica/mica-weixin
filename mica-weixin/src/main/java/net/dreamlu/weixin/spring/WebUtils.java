package net.dreamlu.weixin.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * web工具类
 *
 * @author L.cm
 */
@Slf4j
class WebUtils {

	/**
	 * 返回json
	 *
	 * @param response HttpServletResponse
	 * @param text     文本
	 */
	public static void renderText(HttpServletResponse response, String text) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset:utf-8;");
		try (PrintWriter out = response.getWriter()) {
			out.append(text);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
