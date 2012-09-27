package net.ujin.recruit.service;

import java.util.List;

import net.ujin.recruit.model.UjinCustomerDataModel;
import net.ujin.recruit.model.UjinFinancesDataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
	@Autowired
	private MailSender mailSender;
	@Autowired
	private SimpleMailMessage alertMailMessage;

	public void sendAlertMail(String alert) {

		SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
		mailMessage.setText(alert);
		mailSender.send(mailMessage);

	}

	public void sendMail(String year, String month, String day, UjinCustomerDataModel customer,
			List<UjinFinancesDataModel> financesList) {
		
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("ujin.recruit@gmail.com");
		message.setTo(customer.getCustomerEmail());
		message.setSubject("유진인력에서 " + customer.getCustomerName() + "에게 메일드립니다.");
		StringBuilder html = new StringBuilder();
		html.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>");
		html.append("<html>");
		html.append("<head>");
		html.append("	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
		html.append("</head>");
		html.append("<body>");
		html.append("<table>");
		html.append("<tr>");
		html.append("<td><h2>"+year+"년 "+month+"월 "+ day + "일"+"</h2></td>");
		html.append("</tr>");
		html.append("</table><br />");
		html.append("<table>");
		html.append("<tr>");
		html.append("<th>이름</th>");
		html.append("<th>금액</th>");
		html.append("<th>비고</th>");
		html.append("</tr>");
		for(UjinFinancesDataModel dataModel :  financesList) {
			html.append("<tr>");
			html.append("<td>"+dataModel.getfName()+"</td>");
			html.append("<td>"+dataModel.getfMoney()+"</td>");
			html.append("<td>"+dataModel.getfEtc()+"</td>");
			html.append("</tr>");
		}
		html.append("</table>");
		html.append("<h2>유진 인력에서 보내드립니다.</h2>");
		html.append("<h3>이장순</h3><h3>핸드폰 : 010-8300-2404</h3><h3>이메일 : ujin.recruit@gmail.com</h3>");
		html.append("</body>");
		html.append("</html>");
		message.setText(html.toString());
		mailSender.send(message);
	}
}
