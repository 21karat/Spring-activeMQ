
package websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * WebSocket握手请求的拦截器. 检查握手请求和响应, 对WebSocketHandler传递属性
 * @author 开发
 *
 */
public class HandShake implements HandshakeInterceptor {
	/**
	 * 在握手之前执行该方法, 继续握手返回true, 中断握手返回false. 通过attributes参数设置WebSocketSession的属性
	 */
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		System.out.println("Websocket:用户[ID:" + ((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute("uid") + "]已经建立连接");
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			// 标记用户
			Long uid = (Long) session.getAttribute("uid");
			if(uid!=null){
				attributes.put("uid", uid);
			}else{
				return false;
			}
		}
		return true;
	}
	/**
	 * 在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头.
	 */
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
	}

}
