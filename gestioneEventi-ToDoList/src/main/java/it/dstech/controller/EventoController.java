package it.dstech.controller;

import java.time.LocalDateTime;
import java.util.TimeZone;

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

@Controller
public class EventoController {

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
		LocalDateTime dateTime = evento.getScadenza();
		dateTime.minusMinutes(5);
		Sched sched = new Sched();
		sched.setDate(dateTime);
		System.out.println(dateTime);
		int minute = dateTime.getMinute();
		int hours = dateTime.getHour();
		int day = dateTime.getDayOfMonth();
		int month = dateTime.getMonth().getValue();
		String expression = " 0 " + minute + " " + hours + " " + day + " " + month + " ?";
		CronTrigger trigger = new CronTrigger(expression, TimeZone.getTimeZone(TimeZone.getDefault().getID()));
		scheduler.schedule(sched, trigger);
        eventoService.save(evento);
		return login();
	}
}
