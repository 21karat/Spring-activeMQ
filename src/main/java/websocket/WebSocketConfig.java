package websocket;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
/**
 * 拦截器配置重写抽象方法
 * @author 开发
 *
 */
@Component
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	@Resource
	MyWebSocketHandler handler;
	/** 拦截器配置**/
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(handler, "/ws").addInterceptors(new HandShake());

		registry.addHandler(handler, "/ws/sockjs").addInterceptors(new HandShake()).withSockJS();
	}

}
/*public abstract class WebMvcConfigurerAdapter implements WebMvcConfigurer {
     配置路径匹配参数
  public void configurePathMatch(PathMatchConfigurer configurer) {}
     配置Web Service或REST API设计中内容协商,即根据客户端的支持内容格式情况来封装响应消息体，如xml,json
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {}
     配置路径匹配参数
  public void configureAsyncSupport(AsyncSupportConfigurer configurer) {}
     使得springmvc在接口层支持异步
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {}
     注册参数转换和格式化器
  public void addFormatters(FormatterRegistry registry) {}
     注册配置的拦截器
  public void addInterceptors(InterceptorRegistry registry) {}
     自定义静态资源映射
  public void addResourceHandlers(ResourceHandlerRegistry registry) {}
  cors跨域访问
  public void addCorsMappings(CorsRegistry registry) {}
     配置页面直接访问，不走接口
  public void addViewControllers(ViewControllerRegistry registry) {}
     注册自定义的视图解析器
  public void configureViewResolvers(ViewResolverRegistry registry) {}
     注册自定义控制器(controller)方法参数类型
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {}
     注册自定义控制器(controller)方法返回类型
  public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {}
     重载会覆盖掉spring mvc默认注册的多个HttpMessageConverter
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {}
     仅添加一个自定义的HttpMessageConverter,不覆盖默认注册的HttpMessageConverter
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {}
     注册异常处理
  public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {}
     多个异常处理，可以重写次方法指定处理顺序等
  public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {}
}*/