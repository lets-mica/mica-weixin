package net.dreamlu.weixin.config;

import com.jfinal.config.*;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.json.JacksonFactory;
import com.jfinal.template.Engine;
import net.dreamlu.weixin.annotation.WxMsgController;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Map;

public class DreamWeixinConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		me.setJsonFactory(new JacksonFactory());
		me.setControllerFactory(new SpringControllerFactory());
	}

	@Override
	public void configRoute(Routes me) {
		findAnnotatedBeans(me);
	}

	@Override
	public void configEngine(Engine me) {}

	@Override
	public void configPlugin(Plugins me) {}

	@Override
	public void configInterceptor(Interceptors me) {}

	@Override
	public void configHandler(Handlers me) {}

	/**
	 * Find the names of beans annotated with
	 * @param me Routes
	 */
	public static void findAnnotatedBeans(Routes me) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(JFinal.me().getServletContext());
		Map<String, Controller> controllerMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, Controller.class);
		for (Controller controller : controllerMap.values()) {
			Class<? extends Controller> controllerClass = controller.getClass();
			WxMsgController wxController = AnnotationUtils.findAnnotation(controllerClass, WxMsgController.class);
			if (wxController != null) {
				me.add(wxController.value(), controllerClass);
			}
		}
	}
}
