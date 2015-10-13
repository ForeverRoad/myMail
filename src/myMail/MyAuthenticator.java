package myMail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 
 * ClassName: MyAuthenticator 
 * @Description: 邮箱验证类，重写PasswordAuthentication方法
 * @author 
 * @date 2015-10-13
 */
public class MyAuthenticator extends Authenticator{
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
	//重写
	protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }

	
}
