package mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 
 * ClassName: MailAuthenticator 
 * @Description: 服务器邮箱登录验证
 * @author 
 * @date 2015-10-13
 */
public class MailAuthenticator extends Authenticator{
	
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(userName,password);
	}
}
