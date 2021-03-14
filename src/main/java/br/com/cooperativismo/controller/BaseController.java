package br.com.cooperativismo.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;
import com.google.gson.JsonObject;

import br.com.cooperativismo.domain.dto.BaseResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	protected static final String ERROR = "error";
	protected static final String NOT_FOUND = "not found";
	protected static final String SUCCESS = "success";
	protected static final String OCORREU_UM_ERRO = "Ocorreu um erro desconhecido. Contate o administrador do sistema.";

	protected ResponseEntity<BaseResponseDTO> ok(Object data) {
		return success(HttpStatus.OK.value(), data);
	}
	
	protected ResponseEntity<BaseResponseDTO> notFound() {
		BaseResponseDTO response = new BaseResponseDTO();
		response.setCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
	}

	protected ResponseEntity<BaseResponseDTO> error(Object data) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), data);
	}

	protected ResponseEntity<BaseResponseDTO> errorForbidden(Object data) {
		return errorForbidden(HttpStatus.FORBIDDEN.value(), data);
	}

	protected ResponseEntity<BaseResponseDTO> success(Integer codeStatus, Object data) {
		BaseResponseDTO response = new BaseResponseDTO();
		response.setCode(codeStatus);
		response.setData(data);
		response.setMessage(SUCCESS);
		return ResponseEntity.ok(response);
	}
	
	protected ResponseEntity<BaseResponseDTO> okNoContent() {
		BaseResponseDTO response = new BaseResponseDTO();
		response.setCode(HttpStatus.NO_CONTENT.value());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	private ResponseEntity<BaseResponseDTO> error(Integer codeStatus, Object data) {
		BaseResponseDTO response = new BaseResponseDTO();
		response.setCode(codeStatus);
		response.setData(data);
		response.setMessage(ERROR);
		return ResponseEntity.status(codeStatus).body(response);
	}

	private ResponseEntity<BaseResponseDTO> errorForbidden(Integer codeStatus, Object data) {
		BaseResponseDTO response = new BaseResponseDTO();
		response.setCode(codeStatus);
		response.setData(data);
		response.setMessage(ERROR);
		return ResponseEntity.status(codeStatus).body(response);
	}

	protected void logTiming(String endpoint, double milliseconds) {
		JsonObject json = new JsonObject();
		json.addProperty("duracao", milliseconds);
		json.addProperty("endpoint", endpoint);
		json.addProperty("funcionalidade", "timing");
		logger.info(json.toString());
	}
}