package br.com.cooperativismo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cooperativismo.domain.dto.BaseResponseDTO;
import br.com.cooperativismo.domain.dto.CadastroUsuarioDTO;
import br.com.cooperativismo.service.UsuarioService;

@RestController
@RequestMapping("/usuario")

public class Usuario extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(Usuario.class);
	@Autowired
	UsuarioService usuarioService;

	@PostMapping(path = "/cadastro")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<BaseResponseDTO> cadastrar(@RequestBody CadastroUsuarioDTO cadastro) {
		try {
			logger.info("Efetuando cadastro do usuario:" + cadastro.getNome());
			return ok(usuarioService.cadastro(cadastro));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return error(e.getMessage());
		}
	}
	@GetMapping(path = "/busca/{cpf}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<BaseResponseDTO> buscaUnica(@PathVariable String cpf) {
		try {
			logger.info("Buscando cpf" + cpf);
			return ok(usuarioService.buscaUnica(cpf));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return error(e.getMessage());
		}
	}
	
	@GetMapping(path = "busca/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<BaseResponseDTO> buscar() {
		try {
			logger.info("Buscando todos os usuarios");
			return ok(usuarioService.bucasrTodos());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return error(e.getMessage());
		}
	}
	

}
