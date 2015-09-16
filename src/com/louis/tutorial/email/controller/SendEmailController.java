package com.louis.tutorial.email.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@PropertySource("classpath:config.properties")
@Controller
public class SendEmailController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private Environment env;
	
	/**
	 * 測試發送html email
	 * 
	 * @param recipient
	 * @param subject
	 * @param message
	 * @return
	 * @throws MessagingException
	 */
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public String doSendEmail(@RequestParam("recipient") String recipient,
			@RequestParam("subject") String subject,
			@RequestParam("message") String message) throws MessagingException {

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,
				"UTF-8");
		helper.setFrom(env.getProperty("gmail.from"));
		helper.setTo(recipient);
		helper.setSubject(subject);
		helper.setText(message, true);

		mailSender.send(mimeMessage);

		return "Result";
	}

	/**
	 * 發送email經由模板
	 * 
	 * @param request
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 * @throws TemplateException
	 */
	@RequestMapping(value = "/sendEmailTemplate", method = RequestMethod.POST)
	public String doSendEmailTemp(HttpServletRequest request)
			throws MessagingException, IOException, TemplateException {

		//step 1. get email template
		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(servletContext, "MailTemplate");
		Template template = cfg.getTemplate("rwd-html-mail-template.ftl");

		//step 2. get member info from database
		Map<String, String> rootMap = new HashMap<String, String>();
		rootMap.put("name", "Louis Tsai");
		rootMap.put("cardNo", "xxxx-xxxx-xxxx-xxxx");
		rootMap.put("time", "2015/07/01 21:00");
		rootMap.put("money", "3,000");
		Writer out = new StringWriter();
		template.process(rootMap, out);

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,
				"UTF-8");
		helper.setFrom(env.getProperty("gmail.from"));
		helper.setTo("sell0802@gmail.com");
		helper.setSubject("8月份信用卡交易通知!");
		helper.setText(out.toString(), true);
		
		mailSender.send(mimeMessage);

		return "Result";
	}

}
