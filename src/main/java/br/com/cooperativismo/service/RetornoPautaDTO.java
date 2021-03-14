package br.com.cooperativismo.service;

import java.util.Optional;

import br.com.cooperativismo.domain.Pauta;

public class RetornoPautaDTO {
	private Pauta pauta;
	private Long votosSim;
	private Long votosNao;
	private String ativo;
	private String aberto;
	
	

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getAberto() {
		return aberto;
	}

	public void setAberto(String aberto) {
		this.aberto = aberto;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Optional<Pauta> optional) {
		this.pauta = optional.get();
	}

	public Long getVotosSim() {
		return votosSim;
	}

	public void setVotosSim(Long votosSim) {
		this.votosSim = votosSim;
	}

	public Long getVotosNao() {
		return votosNao;
	}

	public void setVotosNao(Long votosNao) {
		this.votosNao = votosNao;
	}

}
