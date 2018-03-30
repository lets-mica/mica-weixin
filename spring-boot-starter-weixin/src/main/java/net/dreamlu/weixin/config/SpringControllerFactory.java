package net.dreamlu.weixin.config;

import com.jfinal.core.Controller;
import com.jfinal.core.ControllerFactory;
import com.jfinal.core.JFinal;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringControllerFactory extends ControllerFactory {

	private final ApplicationContext applicationContext;

	public SpringControllerFactory() {
		this.applicationContext = WebApplicationContextUtils.getWebApplicationContext(JFinal.me().getServletContext());
	}

	@Override
	public Controller getController(Class<? extends Controller> controllerClass) throws InstantiationException, IllegalAccessException {
		return applicationContext.getBean(controllerClass);
	}
}
