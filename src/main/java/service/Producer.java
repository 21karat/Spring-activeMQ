package service;

import po.Mail;

/**
 * 发送消息接口
 * @author 开发
 *
 */
public interface Producer {
	//向(队列/主题)中添加消息
	public void sendMail(Mail mail);
}
