package br.com.cooperativismo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cooperativismo.domain.Pauta;
import br.com.cooperativismo.domain.Usuario;
import br.com.cooperativismo.domain.Voto;
import br.com.cooperativismo.domain.dto.CadastroVotacaoDTO;
import br.com.cooperativismo.domain.dto.VotarDTO;
import br.com.cooperativismo.repository.PautaRepository;
import br.com.cooperativismo.repository.UsuarioRepository;
import br.com.cooperativismo.repository.VotoRepository;
import br.com.cooperativismo.repository.VotoRepositoryCustom;

@Service
public class VotacaoService implements criaVoto {
	@Autowired
	PautaRepository pautaRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	VotoRepository votoRepository;
	@Autowired
	VotoRepositoryCustom votoRepositoryCustom;
	private static final String PAUTA_INVALIDA = "Pauta invalida";
	private static final String USUARIO_INVALIDO = "Usuario invalido";
	private static final String PAUTA_ENCERRADA = "Pauta Encerrada";
	private static final String USUARIO_JA_VOTOU = "Usuario Ja votou";

	public Object cadastro(CadastroVotacaoDTO cadastro) {
		if (cadastro.getNomePauta().isEmpty()) {
			throw new IllegalArgumentException(PAUTA_INVALIDA);
		}
		return cadastroPauta(cadastro);
	}

	private Pauta cadastroPauta(CadastroVotacaoDTO cadastro) {
		Pauta pauta = new Pauta();
		if (cadastro.getTermino() != null) {
			pauta.setFim(cadastro.getTermino());
		}
		pauta.setAtivo(true);
		pauta.setNome(cadastro.getNomePauta());
		return pautaRepository.save(pauta);
	}

	public Object votar(VotarDTO votar) {
		Optional<Pauta> pauta = pautaRepository.findById(votar.getIdPauta());
		Optional<Usuario> user = usuarioRepository.findById(votar.getIdUsuario());
		Voto voto = votoRepository.save(criaVoto(votar, pauta, user));
		pauta.get().adicionaVoto(voto);
		return pautaRepository.save(pauta.get());

	}

	private Voto criaVoto(VotarDTO votar, Optional<Pauta> pauta, Optional<Usuario> user) {
		validaDados(pauta, user);
		Voto voto = new Voto();
		voto.setPauta(pauta.get());
		voto.setUser(user.get());
		voto.setVoto(votar.getVoto().getDescricao());
		return voto;
	}

	private void validaDados(Optional<Pauta> pauta, Optional<Usuario> user) {
		if (!pauta.isPresent()) {
			throw new IllegalArgumentException(PAUTA_INVALIDA);
		}
		if (!user.isPresent()) {
			throw new IllegalArgumentException(USUARIO_INVALIDO);
		}
		if (!pauta.get().getAtivo()) {
			throw new IllegalArgumentException(PAUTA_ENCERRADA);
		}
		Optional<Voto> voto = votoRepository.findByUserAndPauta(user, pauta);
		if (voto.isPresent()) {
			throw new IllegalArgumentException(USUARIO_JA_VOTOU);
		}
	}

	public Object abrirVotacao(Long idPauta) {
		Optional<Pauta> pauta = pautaRepository.findById(idPauta);
		if (!pauta.isPresent()) {
			throw new IllegalArgumentException(PAUTA_INVALIDA);
		}
		pauta.get().setAtivo(true);
		pauta.get().setAberto(true);
		pauta.get().setFim(LocalDateTime.now().plusMinutes(1));
		pautaRepository.save(pauta.get());
		return "Pauta Iniciada Com sucesso ";
	}

	public Object bucasrTodos() {
		List<RetornoPautaDTO> retornoPauta = votoRepositoryCustom.buscas(null);
		return retornoPauta;
	}

	public Object buscaUnica(Long id) {
		return votoRepositoryCustom.buscas(id);
	}

}
