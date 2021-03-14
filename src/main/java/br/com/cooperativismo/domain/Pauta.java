package br.com.cooperativismo.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Pauta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@OneToMany
	private List<Voto> Voto;
	private LocalDateTime inicio = LocalDateTime.now();
	private LocalDateTime fim = LocalDateTime.now().plusMinutes(1);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Voto> getVoto() {
		return Voto;
	}

	public void setVoto(List<Voto> voto) {
		Voto = voto;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFim() {
		return fim;
	}

	public void setFim(LocalDateTime fim) {
		this.fim = fim;
	}

	@Override
	public String toString() {
		return "Pauta [id=" + id + ", nome=" + nome + ", Voto=" + Voto + ", inicio=" + inicio + ", fim=" + fim + "]";
	}

}
