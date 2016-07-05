package com.exam.register;

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
 * ���ʼ��ķ���
 * @author Administrator
 *
 */
public class SetEmail {     
	   private static String host;  
	    private static String username;  
	    private static String password;  
	    private static String from;  
	    private static String nick;  
	  
	    
	    
	    static {  
	        try {  
	            // Test Data  
	            host = "smtp.qq.com";  
	            username = "1614460197@qq.com";  
	            password = "huangmai2262008";  
	            from = "1614460197@qq.com";  
	            nick = "��ѿ���ϵͳ";  
	            // nick + from �������ķ�������Ϣ  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    /** 
	     * �����ʼ� 
	     *  
	     * @param to 
	     *            �ռ����б���","�ָ� 
	     * @param subject 
	     *            ���� 
	     * @param body 
	     *            ���� 
	     * @param filepath 
	     *            �����б�,�޸�������null 
	     * @return 
	     * @throws MessagingException 
	     * @throws AddressException 
	     * @throws UnsupportedEncodingException 
	     */  
	    public boolean sendMail(String to, String subject, String body,  List<String> filepath) throws AddressException, MessagingException,  UnsupportedEncodingException {  
	        // ��������  
	        if (body == null) {  
	            body = "";  
	        }  
	        if (subject == null) {  
	            subject = "������";  
	        }  
	        // ����Properties����  
	        Properties props = System.getProperties();  
	        // �����ż�������  
	        props.put("mail.smtp.host", host);  
	        props.put("mail.smtp.auth", "true"); // ͨ����֤  
	        // �õ�Ĭ�ϵĶԻ�����  
	        Session session = Session.getInstance(props, null);  
	        // ����һ����Ϣ������ʼ������Ϣ�ĸ���Ԫ��  
	        MimeMessage msg = new MimeMessage(session);  
	        nick = MimeUtility.encodeText(nick);  
	        msg.setFrom(new InternetAddress(nick + "<" + from + ">"));  
	        // �����ռ����б�  
	        if (to != null && to.trim().length() > 0) {  
	            String[] arr = to.split(",");  
	            int receiverCount = arr.length;  
	            if (receiverCount > 0) {  
	                InternetAddress[] address = new InternetAddress[receiverCount];  
	                for (int i = 0; i < receiverCount; i++) {  
	                    address[i] = new InternetAddress(arr[i]);  
	                }  
	                msg.addRecipients(Message.RecipientType.TO, address);  
	                msg.setSubject(subject);  
	                // �����BodyPart�����뵽�˴�������Multipart��  
	                Multipart mp = new MimeMultipart();  
	                // ��������  
	                if (filepath != null && filepath.size() > 0) {  
	                    for (String filename : filepath) {  
	                        MimeBodyPart mbp = new MimeBodyPart();  
	                        // �õ�����Դ  
	                        FileDataSource fds = new FileDataSource(filename);  
	                        // �õ�������������BodyPart  
	                        mbp.setDataHandler(new DataHandler(fds));  
	                        // �õ��ļ���ͬ������BodyPart  
	                        mbp.setFileName(fds.getName());  
	                        mp.addBodyPart(mbp);  
	                    }  
	                    MimeBodyPart mbp = new MimeBodyPart();  
	                    mbp.setText(body);  
	                    mp.addBodyPart(mbp);  
	                    // ���߼����е�����Ԫ��  
	                    filepath.clear();  
	                    // Multipart���뵽�ż�  
	                    msg.setContent(mp);  
	                } else {  
	                    // �����ʼ�����  
	                    msg.setText(body);  
	                }  
	                // �����ż�ͷ�ķ�������  
	                msg.setSentDate(new Date());  
	                msg.saveChanges();  
	                // �����ż�  
	                Transport transport = session.getTransport("smtp");  
	                transport.connect(host, username, password); 
	                System.out.println(host+ username+ password);
	                transport.sendMessage(msg,  
	                        msg.getRecipients(Message.RecipientType.TO)); 
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
	  /*
	    //�����ʼ�����
	    public static void main(String[] args){  
//	        List<String> filepath = new ArrayList<String>();  
//	        filepath.add("C:/Users/Administrator/Desktop/ֽ��ͼ��PSD�ز�/paper_lon.png");  
	    	 RegisterUtils registerUtils=new RegisterUtils();
	    	 String Number=null;
	    	String Numbers=registerUtils.RandomAuthCode(Number);
	    	 System.out.println(Numbers);
	        try {
				sendMail("573059382@qq.com", "��֤�룺", Numbers,  null);
			} catch (Exception e) {
				System.out.println("�����ַ����");
				e.printStackTrace();
			}  
	    }  */
}
