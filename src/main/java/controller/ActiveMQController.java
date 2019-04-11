package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import po.Message;

import com.google.gson.GsonBuilder;

import po.Mail;
import service.impl.ProducerImpl;
import service.impl.TopicImpl;
import websocket.MyWebSocketHandler;
/**
 * 生产者
 * @author 开发
 *
 */
@Controller
public class ActiveMQController {
	
	MyWebSocketHandler handler=new MyWebSocketHandler();
	
	@Autowired
	ProducerImpl producer;//队列发送消息
	@Autowired
	TopicImpl topic;//主题发送消息
	
	
	
	/**
	 * (p2p)生产者
	 * @param mail	生产消息
	 * @throws Exception
	 */
	@RequestMapping(value="/produce",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public void produce(@ModelAttribute("mail")Mail mail) throws Exception{
			Message msg = new Message();
			msg.setText("向队列myquene添加一条消息:"+mail.toString());
			//通过socket通知所有人
			handler.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
			//发送消息到队列中
			producer.sendMail(mail);
	}
	
	/**
	 * (发布/订阅)生产者
	 * @param mail	生产的消息
	 * @throws Exception
	 */
	@RequestMapping(value="/topic",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public void topic(@ModelAttribute("mail")Mail mail) throws Exception{
		Message msg = new Message();
		msg.setText("向话题mytopic发布一条消息:"+mail.toString());
		//socket通知所有人
		handler.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
		//向主题发布消息
		topic.sendMail(mail);
	}
	
	/**
	 * 访问接口
	 * @param request
	 * @return
	 */
	@RequestMapping("demo")
	public String demo(HttpServletRequest request){
		request.getSession().setAttribute("uid", (long)11);
		return "demo";
	}
	
}
