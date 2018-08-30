package cn.ycl.net;

import java.io.IOException;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	public static final String ADMIN_MAIL = "251121753@qq.com";
	/**
	 * 本来是想让用户注册的时候直接给我的邮箱发送他的设备号。
	 * 但是后来查了下，邮件以明文发送，不安全
	 * 所以改用RSA非对称加密算法，直接和服务器通信，加密串号和QQ邮箱号注册
	 * */
	public void receive() {
		Store store = null;
		Folder folder = null;
		Properties props = new Properties();
		// 存储接收邮件服务器使用的协议，这里以POP3为例
		props.setProperty("mail.store.protocol", "pop3");
		// 设置接收邮件服务器的地址，这里还是以网易163为例
		props.setProperty("mail.pop3.host", "pop3.163.com");
		// 根据属性新建一个邮件会话.
		Session session = Session.getInstance(props);
		// 从会话对象中获得POP3协议的Store对象
		try {
			store = session.getStore("pop3");
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		// 如果需要查看接收邮件的详细信息，需要设置Debug标志
		session.setDebug(false);
		// 连接邮件服务器
		try {
			store.connect("pop3.163.com", "18256079056@163.com", "Y024459");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		// 获取邮件服务器的收件箱

		try {
			folder = store.getFolder("INBOX");
			// 以只读权限打开收件箱
			folder.open(Folder.READ_ONLY);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		// 获取收件箱中的邮件，也可以使用getMessage(int 邮件的编号)来获取具体某一封邮件
		Message message[];
		try {
			message = folder.getMessages();
			for (int i = 0; i < message.length; i++) {
				System.out.println();
				System.out.println();
				System.out.println("-------------------第" + (i + 1) + "封邮件------------------");
				String strType = message[i].getContentType();
				System.out.println("form" + message[i].getFrom()[0].toString());
				System.out.println("邮件类型：" + strType);
				Multipart multipart = null;
				if (strType.contains("multipart/alternative;")) {
					try {
						multipart = (Multipart) message[i].getContent();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						if (multipart.getBodyPart(0).getContentType().equals("text/plain;")) {
							// TODO 要获取的设备序列号吗，并检查是否满足设备号要求
							String imei = multipart.getBodyPart(0).getContent().toString();
							System.out.println(imei);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			// 关闭连接
			folder.close(false);
			store.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void sendMail(String toMail, String mailContent) throws Exception {
		  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		  // Get a Properties object
		  Properties props = System.getProperties();
		  props.setProperty("mail.smtp.host", "smtp.aliyun.com");
		  props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		  props.setProperty("mail.smtp.socketFactory.fallback", "false");
		  props.setProperty("mail.smtp.port", "465");
		  props.setProperty("mail.smtp.socketFactory.port", "465");
		  props.put("mail.smtp.auth", "true");
		  final String username = "flydragonlock@aliyun.com";
		  final String password = "Y024459";
		  Session session = Session.getDefaultInstance(props, new Authenticator(){
		      protected PasswordAuthentication getPasswordAuthentication() {
		          return new PasswordAuthentication(username, password);
		      }});

		  session.setDebug(false);
		  Message msg = new MimeMessage(session);
		  msg.setFrom(new InternetAddress(username));
		  //收件人
		  msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail,false));
		  //主题
		  msg.setSubject("FlyDragonLock");
		  //内容
		  msg.setText(mailContent);
		  //发送日期
		  msg.setSentDate(new Date());

		  Transport.send(msg);
		
	}    
}
