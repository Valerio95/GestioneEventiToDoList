package it.dstech.model;

import java.time.LocalDateTime;

import javax.mail.MessagingException;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import it.dstech.service.MailService;


public class Sched implements Runnable {

	private MailService mail;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy HH:mm")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime date;
	
	public Sched(MailService mail) {
		this.mail=mail;
	}


 public void run(){
	try {
		mail.inviaMail("valProietti1995@gmail.com", "Avviso chiusura evento", "L'evento si chiuderà tra 5 minuti");

	} catch (MessagingException e) {
		
		e.printStackTrace();
	}
		
	
}



public LocalDateTime getDate() {
	return date;
}

public void setDate(LocalDateTime date) {
	this.date = date;
}


}