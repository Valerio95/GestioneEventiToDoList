package it.dstech.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import it.dstech.model.Evento;
import it.dstech.model.Sched;
import it.dstech.service.EventoService;
import it.dstech.service.MailService;

@Controller
public class EventoController {

	@Autowired
	private MailService mail;
	
	@Autowired
	TaskScheduler scheduler;
	
	@Autowired
	private EventoService eventoService;

	
	@GetMapping(value = { "/"})
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		modelAndView.addObject("listaEventi", eventoService.listaEventi());
		modelAndView.addObject("evento", new Evento());

		return modelAndView;
	}
	
	
	

	@PostMapping("/creaEvento")
	public ModelAndView creaEvento(Evento evento) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(evento.getData(), formatter);
        evento.setScadenza(dateTime);
		LocalDateTime dateTime2 = evento.getScadenza().minusMinutes(5);
		Sched sched = new Sched(mail);
		sched.setDate(dateTime2);
		System.out.println(sched.getDate());
		int minute = dateTime2.getMinute();
		int hours = dateTime2.getHour();
		int day = dateTime2.getDayOfMonth();
		int month = dateTime2.getMonth().getValue();
		String expression = " 0 " + minute + " " + hours + " " + day + " " + month + " ?";
		CronTrigger trigger = new CronTrigger(expression, TimeZone.getTimeZone(TimeZone.getDefault().getID()));
		scheduler.schedule(sched, trigger);
        eventoService.save(evento);
		return login();
	}
	
	
	
}
