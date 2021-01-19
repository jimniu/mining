package com.isd.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendMail {
	String host;

	public SendMail(String instr) {
		host = instr;
	}

	@SuppressWarnings("static-access")
	public void sendMail(String from, String fromname, String to, String cc, String bcc, String subject, String content, String name, String pass, String attachment) {
		// 获得属性，并生成Session对象
		String encode = "UTF-8";
		Properties props = new Properties();
		Session sendsession;
		Transport transport;
		MimeMessage message = null;
		BodyPart messageBodyPart = new MimeBodyPart();
		Multipart multipart = new MimeMultipart();
		try {
			sendsession = Session.getInstance(props, null);
			// 向属性中写入SMTP服务器的地址
			props.put("mail.smtp.host", host);
			// 设置SMTP服务器需要权限认证
			props.put("mail.smtp.auth", "true");
			// 设置输出调试信息
			// sendsession.setDebug(true);
			// 根据Session生成Message对象
			message = new MimeMessage(sendsession);
			// 设置发信人地址
			message.setFrom(new InternetAddress(from, fromname, "GB2312"));
			// message.setFrom(new InternetAddress(from));
			// 设置收信人地址,逗号分割
			InternetAddress[] toList = new InternetAddress().parse(to);
			message.setRecipients(Message.RecipientType.TO, toList);
			//设置CC
			if(cc!=null&&cc.length()>0)
			{
				InternetAddress[] ccList = new InternetAddress().parse(cc);
				message.setRecipients(Message.RecipientType.CC, ccList);
			}
			//设置bcc
			if(bcc!=null&&bcc.length()>0)
			{
				InternetAddress[] bccList = new InternetAddress().parse(bcc);
				message.setRecipients(Message.RecipientType.BCC, bccList);
			}
					
			// 设置e-mail标题
			message.setSubject(subject, encode);
			// 设置e-mail发送时间
			message.setSentDate(new Date());
			// 设置e-mail内容
			message.setText(content, encode);
			// 建立第一部分：文本正文
			messageBodyPart.setContent(content, "text/html;charset="+encode);// 给BodyPart对象设置内容和格式/编码方式
			multipart.addBodyPart(messageBodyPart);
			if (!attachment.equals("")) {
				// 建立第二部分：附件
				messageBodyPart = new MimeBodyPart();
				// 获得附件
				DataSource source = new FileDataSource(attachment);
				// 设置附件的数据处理器
				messageBodyPart.setDataHandler(new DataHandler(source));
				// 设置附件文件名
				messageBodyPart.setFileName(attachment);
				// 加入第二部分
				multipart.addBodyPart(messageBodyPart);
			}
			// 将多部分内容放到e-mail中
			message.setContent(multipart);
			// 保存对于e-mail的修改
			message.saveChanges();
			// 根据Session生成Transport对象
			transport = sendsession.getTransport("smtp");
			// 连接到SMTP服务器
			transport.connect(host, name, pass);
			// 发送e-mail
			transport.sendMessage(message, message.getAllRecipients());
			// 关闭Transport连接
			transport.close();
		} catch (MessagingException m) {
			System.out.println(m.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}