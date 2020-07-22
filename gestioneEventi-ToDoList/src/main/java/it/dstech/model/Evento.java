package it.dstech.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "esame_id")
	private Long id;
	
	private String titolo;
	
	private String descrizione;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private String data;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy HH:mm")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime scadenza;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDateTime getScadenza() {
		return (LocalDateTime)scadenza;
	}

	public void setScadenza(LocalDateTime scadenza) {
		this.scadenza = scadenza;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	

	

	

	
	
	
}
