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
	 * �����������û�ע���ʱ��ֱ�Ӹ��ҵ����䷢�������豸�š�
	 * ���Ǻ��������£��ʼ������ķ��ͣ�����ȫ
	 * ���Ը���RSA�ǶԳƼ����㷨��ֱ�Ӻͷ�����ͨ�ţ����ܴ��ź�QQ�����ע��
	 * */
	public void receive() {
		Store store = null;
		Folder folder = null;
		Properties props = new Properties();
		// �洢�����ʼ�������ʹ�õ�Э�飬������POP3Ϊ��
		props.setProperty("mail.store.protocol", "pop3");
		// ���ý����ʼ��������ĵ�ַ�����ﻹ��������163Ϊ��
		props.setProperty("mail.pop3.host", "pop3.163.com");
		// ���������½�һ���ʼ��Ự.
		Session session = Session.getInstance(props);
		// �ӻỰ�����л��POP3Э���Store����
		try {
			store = session.getStore("pop3");
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		// �����Ҫ�鿴�����ʼ�����ϸ��Ϣ����Ҫ����Debug��־
		session.setDebug(false);
		// �����ʼ�������
		try {
			store.connect("pop3.163.com", "18256079056@163.com", "Y024459");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		// ��ȡ�ʼ����������ռ���

		try {
			folder = store.getFolder("INBOX");
			// ��ֻ��Ȩ�޴��ռ���
			folder.open(Folder.READ_ONLY);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		// ��ȡ�ռ����е��ʼ���Ҳ����ʹ��getMessage(int �ʼ��ı��)����ȡ����ĳһ���ʼ�
		Message message[];
		try {
			message = folder.getMessages();
			for (int i = 0; i < message.length; i++) {
				System.out.println();
				System.out.println();
				System.out.println("-------------------��" + (i + 1) + "���ʼ�------------------");
				String strType = message[i].getContentType();
				System.out.println("form" + message[i].getFrom()[0].toString());
				System.out.println("�ʼ����ͣ�" + strType);
				Multipart multipart = null;
				if (strType.contains("multipart/alternative;")) {
					try {
						multipart = (Multipart) message[i].getContent();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						if (multipart.getBodyPart(0).getContentType().equals("text/plain;")) {
							// TODO Ҫ��ȡ���豸���к��𣬲�����Ƿ������豸��Ҫ��
							String imei = multipart.getBodyPart(0).getContent().toString();
							System.out.println(imei);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			// �ر�����
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
		  //�ռ���
		  msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail,false));
		  //����
		  msg.setSubject("FlyDragonLock");
		  //����
		  msg.setText(mailContent);
		  //��������
		  msg.setSentDate(new Date());

		  Transport.send(msg);
		
	}    
}
