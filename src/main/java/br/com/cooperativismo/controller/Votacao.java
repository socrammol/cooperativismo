package br.com.cooperativismo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cooperativismo.domain.dto.BaseResponseDTO;
import br.com.cooperativismo.domain.dto.CadastroVotacaoDTO;
import br.com.cooperativismo.domain.dto.VotarDTO;
import br.com.cooperativismo.service.VotacaoService;

@RestController
@RequestMapping("/votacao")
public class Votacao extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(Votacao.class);
	@Autowired
	VotacaoService votacaoService;

	@PostMapping(path = "/cadastro")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<BaseResponseDTO> cadastrar(@RequestBody CadastroVotacaoDTO cadastro) {
		try {
			logger.info("cadastro da pauta:" + cadastro.getNomePauta());
			return ok(votacaoService.cadastro(cadastro));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return error(e.getMessage());
		}
	}

	@PatchMapping(path = "/abrir-votacao/{idPauta}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<BaseResponseDTO> abrirVotacao(@PathVariable Long idPauta) {
		try {
			logger.info("Abrindo votação para a pauta:" + idPauta);
			return ok(votacaoService.abrirVotacao(idPauta));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return error(e.getMessage());
		}
	}

	@PostMapping(path = "/votar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<BaseResponseDTO> votar(@RequestBody VotarDTO votar) {
		try {
			logger.info("votação ocorrendo para o usuario com id:"+ votar.getIdUsuario() + " na pauta:" + votar.getIdPauta());
			return ok(votacaoService.votar(votar));
		} catch (Exception e) {
			return error(e.getMessage());
		}
	}

	@GetMapping(path = "busca/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<BaseResponseDTO> buscar() {
		try {
			logger.info("buscando todas as pautas");
			return ok(votacaoService.bucasrTodos());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return error(e.getMessage());
		}
	}

	@GetMapping(path = "/busca/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<BaseResponseDTO> buscaUnica(@PathVariable Long id) {
		try {
			logger.info("buscando a pauta: " + id);
			return ok(votacaoService.buscaUnica(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return error(e.getMessage());
		}
	}

}
