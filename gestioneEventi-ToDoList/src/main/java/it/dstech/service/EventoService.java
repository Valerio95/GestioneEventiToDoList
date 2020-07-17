package it.dstech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dstech.model.Evento;
import it.dstech.repository.EventoRepository;


@Service
@Transactional
public class EventoService {
	@Autowired
	private EventoRepository eventoRepository;

	public void save(Evento evento) {
		eventoRepository.save(evento);
	}
	public Evento get(Long id) {
		return eventoRepository.findById(id).get();
	}
	public void delete(Long id) {
		eventoRepository.deleteById(id);
	}
	public List<Evento> listaEventi() {
		return (List<Evento>) eventoRepository.findAll();
	}
}
