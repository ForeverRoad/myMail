package myMail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;


/**
 * 
 * ClassName: SendMail 
 * @Description: 邮箱发送类
 * @author 
 * @date 2015-10-13
 */
public class SendMail {
	@Test
	public void sendMail() throws IOException{
		//接收者 的邮箱地址
		String to = "237512382@qq.com";
		//邮件主题
		String subject = "test";
		//邮件内容
		String content = "www.baidu.com";
		//发送的附件
		File sendFile = new File("f://345.txt");
		//是否需要身份认证  
        MyAuthenticator authenticator = null;
		//读取配置文件
		Properties properties = new Properties();
		//根据class获取配置文件的位置
		InputStream in = null;
		try {
			in = Object.class.getResourceAsStream("/email.propertis");
			properties.load(in);
			
			authenticator = new MyAuthenticator();
			authenticator.setUserName(properties.getProperty("userName"));
			authenticator.setPassword(properties.getProperty("password"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(in!=null){
				in.close();
			}
		}
		
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session  
        Session sendMailSession = Session  
                .getDefaultInstance(properties, authenticator);
		
        // 根据session创建一个邮件消息  
        Message mailMessage = new MimeMessage(sendMailSession);  
		
        // 创建邮件发送者地址  
        Address from;
        Address toUser;
		try {
			from = new InternetAddress(properties.getProperty("userName"));
			//输入发送者邮箱地址
	        mailMessage.setFrom(from);
	        
	        // 创建邮件的接收者地址，并设置到邮件消息中  
            toUser = new InternetAddress(to);  
            mailMessage.setRecipient(Message.RecipientType.TO, toUser);
            
            //设置邮件消息的主题  
            mailMessage.setSubject(subject);
            //设置内容
            //mailMessage.setText(content);
            
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);
            
            // 添加附件的内容
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(sendFile);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
                
            //MimeUtility.encodeWord可以避免文件名乱码
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(sendFile.getName()));
            multipart.addBodyPart(attachmentBodyPart);
            
            // 将multipart对象放到message中
            mailMessage.setContent(multipart);
            // 保存邮件
            mailMessage.saveChanges();
            
            
            //发送邮件
            Transport.send(mailMessage);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
       
	}
	
}
