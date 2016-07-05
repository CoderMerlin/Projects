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
 * �����ʼ�
 * @author Administrator
 *
 */
public class SendEmail {
	private String host; //ʹ����������
	private String username; //�˺�
	private String password; //���� 
	private String from; //ʹ���ĸ������˺�
	private String nick; // 

	/**
	 * ���캯��
	 * @param host ʹ����������
	 * @param username �˺�
	 * @param password ���� 
	 * @param from ʹ���ĸ������˺�
	 * @param nick ����
	 */
	public SendEmail(String host,String username,String password,String from,String nick){
		this.host=host;
		this.username=username;
		this.password=password;
		this.from=from;
		this.nick=nick;
	}

	/** 
     * �����ʼ� 
     *  
     * @param to �ռ����б���","�ָ� 
     * @param title ���� 
     * @param body ���� 
     * @param filepath �����б�,�޸�������null 
     * @return 
     * @throws MessagingException 
     * @throws AddressException 
     * @throws UnsupportedEncodingException 
     */  
	public boolean sendMail(String to, String title, String body,List<String> filepath) throws AddressException, MessagingException,  
	UnsupportedEncodingException {  
		
		if (body == null) { // ��������   
			body = "";  
		}  
		if (title == null) {  
			title = "������";  
		}  
		
		Properties props = System.getProperties();  // ����Properties����  
		
		props.put("mail.smtp.host", host);  // �����ż�������  
		props.put("mail.smtp.auth", "true"); // ͨ����֤  
		
		Session session = Session.getDefaultInstance(props, null);  // �õ�Ĭ�ϵĶԻ�����  
		
		MimeMessage msg = new MimeMessage(session);  // ����һ����Ϣ������ʼ������Ϣ�ĸ���Ԫ��  
		nick = MimeUtility.encodeText(nick);  
		msg.setFrom(new InternetAddress(nick + "<" + from + ">"));  
		
		if (to != null && to.trim().length() > 0) {  // �����ռ����б�  
			String[] arr = to.split(",");  
			int receiverCount = arr.length;  
			if (receiverCount > 0) {  
				InternetAddress[] address = new InternetAddress[receiverCount];  
				for (int i = 0; i < receiverCount; i++) {  
					address[i] = new InternetAddress(arr[i]);  
				}  
				msg.addRecipients(Message.RecipientType.TO, address);  
				msg.setSubject(title);  
			
				Multipart mp = new MimeMultipart();  	// �����BodyPart�����뵽�˴�������Multipart��  
				  
				if (filepath != null && filepath.size() > 0) {  // ��������
					for (String filename : filepath) {  
						MimeBodyPart mbp = new MimeBodyPart();  
						
						FileDataSource fds = new FileDataSource(filename);  // �õ�����Դ  
						
						mbp.setDataHandler(new DataHandler(fds));  // �õ�������������BodyPart  
						
						mbp.setFileName(fds.getName());  // �õ��ļ���ͬ������BodyPart  
						mp.addBodyPart(mbp);  
					}  
					MimeBodyPart mbp = new MimeBodyPart();  
					mbp.setText(body);  
					mp.addBodyPart(mbp);  
					
					filepath.clear();  // ���߼����е�����Ԫ��  
					
					msg.setContent(mp);  // Multipart���뵽�ż�  
				} else {  
					
					msg.setText(body);  // �����ʼ�����  
				}  
				
				msg.setSentDate(new Date());  // �����ż�ͷ�ķ�������  
				msg.saveChanges();  
			
				Transport transport = session.getTransport("smtp");  	// �����ż�  
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
