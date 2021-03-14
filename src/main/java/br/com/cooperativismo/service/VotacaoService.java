package br.com.cooperativismo.service;

import org.springframework.stereotype.Service;

import br.com.cooperativismo.domain.dto.CadastroVotacaoDTO;

@Service
public class VotacaoService {

	private static final String PAUTA_INVALIDA = "Pauta invalida";

	public Object cadastro(CadastroVotacaoDTO cadastro) {
		if(cadastro.getNomePauta().isEmpty()) {
			throw new IllegalArgumentException(PAUTA_INVALIDA);
		}
		return null;
	}

}
