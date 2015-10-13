package mail;

import java.util.Properties;

import javax.mail.Session;

/**
 * 
 * ClassName: SimpleMailSender 
 * @Description: 简单的邮件发送
 * @author 
 * @date 2015-10-13
 */
public class SimpleMailSender {
	//读取配置文件
	private final transient Properties properties = System.getProperties();
	//邮箱登录验证
	private transient MailAuthenticator authenticator;
	//邮箱session
	private transient Session session;
	
	
	
}
