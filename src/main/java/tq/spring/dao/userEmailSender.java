package tq.spring.dao;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import tq.spring.dto.userEmailDto;

public class userEmailSender {

	@Autowired
	protected JavaMailSender mailSender;
	
	public void SendMail(userEmailDto email) throws Exception {
		
		MimeMessage msg=mailSender.createMimeMessage();
		
		try {
				msg.setSubject(email.getSubject());
				msg.setText(email.getContent());
				msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email.getReceiver()));
		
		}	catch(MessagingException msge) {
				msge.printStackTrace();
		}
		
		try {
				mailSender.send(msg);
		}	catch(MailException maile) {
				maile.printStackTrace();
		}
		
	}
}
