package br.com.cooperativismo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Voto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Usuario user;
	private String Voto;
	@ManyToOne
	private Pauta pauta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Usuario getUser() {
//		return user;
//	}
//
//	public void setUser(Usuario user) {
//		this.user = user;
//	}

	public String getVoto() {
		return Voto;
	}

	public void setVoto(String voto) {
		Voto = voto;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

//	@Override
//	public String toString() {
//		return "Voto [id=" + id + ", user=" + user + ", Voto=" + Voto + ", pauta=" + pauta + "]";
//	}

}
