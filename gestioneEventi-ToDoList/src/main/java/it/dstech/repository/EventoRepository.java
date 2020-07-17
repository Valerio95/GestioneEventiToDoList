package it.dstech.repository;

import org.springframework.stereotype.Repository;
import it.dstech.model.Evento;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface EventoRepository extends CrudRepository<Evento, Long> {

}
