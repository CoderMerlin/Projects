package com.exam.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
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

/**
 * 发送邮件
 * @author Administrator
 *
 */
public class SendEmail {
	private String host; //使用哪种邮箱
	private String username; //账号
	private String password; //密码 
	private String from; //使用哪个邮箱账号
	private String nick; // 

	/**
	 * 构造函数
	 * @param host 使用哪种邮箱
	 * @param username 账号
	 * @param password 密码 
	 * @param from 使用哪个邮箱账号
	 * @param nick 名称
	 */
	public SendEmail(String host,String username,String password,String from,String nick){
		this.host=host;
		this.username=username;
		this.password=password;
		this.from=from;
		this.nick=nick;
	}

	/** 
     * 发送邮件 
     *  
     * @param to 收件人列表，以","分割 
     * @param title 标题 
     * @param body 内容 
     * @param filepath 附件列表,无附件传递null 
     * @return 
     * @throws MessagingException 
     * @throws AddressException 
     * @throws UnsupportedEncodingException 
     */  
	public boolean sendMail(String to, String title, String body,List<String> filepath) throws AddressException, MessagingException,  
	UnsupportedEncodingException {  
		
		if (body == null) { // 参数修饰   
			body = "";  
		}  
		if (title == null) {  
			title = "无主题";  
		}  
		
		Properties props = System.getProperties();  // 创建Properties对象  
		
		props.put("mail.smtp.host", host);  // 创建信件服务器  
		props.put("mail.smtp.auth", "true"); // 通过验证  
		
		Session session = Session.getDefaultInstance(props, null);  // 得到默认的对话对象  
		
		MimeMessage msg = new MimeMessage(session);  // 创建一个消息，并初始化该消息的各项元素  
		nick = MimeUtility.encodeText(nick);  
		msg.setFrom(new InternetAddress(nick + "<" + from + ">"));  
		
		if (to != null && to.trim().length() > 0) {  // 创建收件人列表  
			String[] arr = to.split(",");  
			int receiverCount = arr.length;  
			if (receiverCount > 0) {  
				InternetAddress[] address = new InternetAddress[receiverCount];  
				for (int i = 0; i < receiverCount; i++) {  
					address[i] = new InternetAddress(arr[i]);  
				}  
				msg.addRecipients(Message.RecipientType.TO, address);  
				msg.setSubject(title);  
			
				Multipart mp = new MimeMultipart();  	// 后面的BodyPart将加入到此处创建的Multipart中  
				  
				if (filepath != null && filepath.size() > 0) {  // 附件操作
					for (String filename : filepath) {  
						MimeBodyPart mbp = new MimeBodyPart();  
						
						FileDataSource fds = new FileDataSource(filename);  // 得到数据源  
						
						mbp.setDataHandler(new DataHandler(fds));  // 得到附件本身并至入BodyPart  
						
						mbp.setFileName(fds.getName());  // 得到文件名同样至入BodyPart  
						mp.addBodyPart(mbp);  
					}  
					MimeBodyPart mbp = new MimeBodyPart();  
					mbp.setText(body);  
					mp.addBodyPart(mbp);  
					
					filepath.clear();  // 移走集合中的所有元素  
					
					msg.setContent(mp);  // Multipart加入到信件  
				} else {  
					
					msg.setText(body);  // 设置邮件正文  
				}  
				
				msg.setSentDate(new Date());  // 设置信件头的发送日期  
				msg.saveChanges();  
			
				Transport transport = session.getTransport("smtp");  	// 发送信件  
				transport.connect(host, username, password);  
				transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));  
				transport.close();  
				return true;  
			} else {  
				System.out.println("None receiver!");  
				return false;  
			}  
		} else {  
			System.out.println("None receiver!");  
			return false;  
		}  
	}  
}
