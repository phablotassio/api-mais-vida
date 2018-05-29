package com.phablo.mais.vida.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*classe usada para o tratamento de execoes */

@ControllerAdvice
public class MaisVidaApiExceptionHandler extends  ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	/* lanca uma excecao quando se tenta inserir um dado invalido e uma mensagem para o usuario / dev  */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagemUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Erro> erros =  Arrays.asList(new Erro(mensagemUser, mensagemDev));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	/* lanca uma excecao quando algum campo deixa de ser preenchido e uma mensagem para o usuario / dev  */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = criarListaErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	/* lanca uma excecao quando nao se enccontra um recurso e uma mensagem para o usuario / dev */
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> emptyResultDataAccessException (EmptyResultDataAccessException ex,WebRequest request) {
		
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvoldedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvoldedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request); 
		
	}
	
	/*lanca uma excecao quando se viola alguma constraint do BD e uma mensagem para o usuario / dev */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,WebRequest request){
		String mensagemUsuario = messageSource.getMessage("operacao-nao-permitida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvoldedor = ExceptionUtils.getRootCauseMessage(ex);
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvoldedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request); 
	}
	
	/* metodo que captura todos erros */
	private List<Erro> criarListaErros(BindingResult resultado){
		
		List<Erro> erros = new ArrayList<>();
		for(FieldError field : resultado.getFieldErrors()) {
			String mensagemUser = messageSource.getMessage(field, LocaleContextHolder.getLocale());
			String mensagemDev = field.toString();
			erros.add(new Erro(mensagemUser, mensagemDev));
		}
		
		
		return erros;
	}
	
	/*classe estatica para manipular as mensagens de erro*/
	public static class Erro{
		

		private String mensagemUser;
		private String mensagemDev;
		 
		public Erro(String mensagemUser,String mensagemDev) {
			super();
			this.mensagemDev = mensagemDev;
			this.mensagemUser = mensagemUser;
		}
		public String getMensagemDev() {
			return mensagemDev;
		}
		public void setMensagemDev(String mensagemDev) {
			this.mensagemDev = mensagemDev;
		}
		public String getMensagemUser() {
			return mensagemUser;
		}
		public void setMensagemUser(String mensagemUser) {
			this.mensagemUser = mensagemUser;
		}
		
		
		
	}

}
