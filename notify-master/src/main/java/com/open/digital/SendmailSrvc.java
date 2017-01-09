package com.open.digital;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.open.digital.model.EmailData;
import com.open.digital.model.EmailRes;
import com.open.digital.model.SrvcResModel;
import com.open.digital.util.Utils;

/**
 * @author pradyroy
 * @version 1.0
 * @since 2016-11-11
 */
@Service
public class SendmailSrvc {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private String hostname;
	
	@Autowired
	public SendmailSrvc(@Value("${smtp.hostname}") String hostname) {
		this.hostname = hostname;
	}

	@Autowired
	protected Utils utils;

	protected static final String send_success = "send_success";
	protected static final String send_fail = "send_fail";
	

	public SendmailSrvc() {

	}

	protected SrvcResModel<EmailRes> sendmail(String logid, EmailData emailData) {
		logger.debug(logid + Utils.LOGDLMTR + utils.getjson("dataflow", "input") + Utils.LOGDLMTR + emailData.toString());
		SrvcResModel<EmailRes> srvcResModel = new SrvcResModel<EmailRes>();
		EmailRes emailRes = javaxmailsend(emailData);	
		srvcResModel.setModel(emailRes);
		if (emailRes.isMailSent()){
			srvcResModel.setHttpstatus(HttpStatus.OK);
			srvcResModel.setResmsgkey(send_success);
			srvcResModel.setResmsgval("Your email has been sent.");
		} else{
			srvcResModel.setHttpstatus(HttpStatus.INTERNAL_SERVER_ERROR);
			srvcResModel.setResmsgkey(send_fail);
			srvcResModel.setResmsgval("Your email has not been sent.");
		}
		logger.info(logid + Utils.LOGDLMTR + utils.getjson("dataflow", "output") + Utils.LOGDLMTR + srvcResModel.toString());
		return srvcResModel;
	}
	
	private EmailRes javaxmailsend(EmailData emailData) {
		EmailRes emailRes = new EmailRes();
		emailRes.setToEmail(emailData.getToEmail());
		emailRes.setMailSubject(emailData.getMailSubject());
		emailRes.setSentDate(new Date().toString());
		Properties smtpProps = new Properties();
		smtpProps.put("mail.smtp.host", hostname);
		// smtpProps.put("mail.smtp.auth", "false");
		// smtpProps.put("mail.smtp.starttls.enable", "false");
		// smtpProps.put("mail.smtp.port", "25");
		// Get the default Session object.
		Session session = Session.getDefaultInstance(smtpProps);
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(emailData.getFromEmail(), emailData.getFromName()));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailData.getToEmail()));
			// Set Subject: header field
			message.setSubject(emailData.getMailSubject());
			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
			// Now set the actual message
			messageBodyPart.setText(emailData.getMailBody());
			// Create a multipart message
			Multipart multipart = new MimeMultipart();
			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			// Send the complete message parts
			message.setContent(multipart);
			// Send message
			Transport.send(message);
			emailRes.setSentDate(new Date().toString());
			emailRes.setMailSent(true);
		} catch (UnsupportedEncodingException e) {
			emailRes.setSentDate(new Date().toString());
			emailRes.setMailSent(false);
			e.printStackTrace();
		} catch (MessagingException e) {
			emailRes.setSentDate(new Date().toString());
			emailRes.setMailSent(false);
			e.printStackTrace();
		} catch (Exception e) {
			emailRes.setSentDate(new Date().toString());
			emailRes.setMailSent(false);
			e.printStackTrace();
		}
		return emailRes;
	}
}
