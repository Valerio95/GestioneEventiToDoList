package it.dstech.model;

import java.time.LocalDateTime;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import it.dstech.service.MailService;

public class Sched implements Runnable {

	@Autowired
	private MailService mailService;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy HH:mm")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime date;
	
	public Sched() {
		// TODO Auto-generated constructor stub
	}

@Override
 public void run(){
	try {
		mailService.inviaMail("valProietti1995@gmail.com", "Avviso chiusura evento", "L'evento si chiuderà tra 5 minuti");
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}

public MailService getMailService() {
	return mailService;
}

public void setMailService(MailService mailService) {
	this.mailService = mailService;
}

public LocalDateTime getDate() {
	return date;
}

public void setDate(LocalDateTime date) {
	this.date = date;
}


}