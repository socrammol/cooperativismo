package br.com.cooperativismo.domain.dto;

import br.com.cooperativismo.enumerator.EnumVoto;

public class VotarDTO {
	private Long idUsuario;
	private Long idPauta;
	private EnumVoto voto;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(Long idPauta) {
		this.idPauta = idPauta;
	}

	public EnumVoto getVoto() {
		return voto;
	}

	public void setVoto(EnumVoto voto) {
		this.voto = voto;
	}

}
