package br.com.cooperativismo.domain.dto;

import java.time.LocalDateTime;

public class CadastroVotacaoDTO {
	private String nomePauta;
	private LocalDateTime termino;

	@Override
	public String toString() {
		return "CadastroVotacaoDTO [nomePauta=" + nomePauta + ", termino=" + termino + "]";
	}

	public String getNomePauta() {
		return nomePauta;
	}

	public void setNomePauta(String nomePauta) {
		this.nomePauta = nomePauta;
	}

	public LocalDateTime getTermino() {
		return termino;
	}

	public void setTermino(LocalDateTime termino) {
		this.termino = termino;
	}

}
