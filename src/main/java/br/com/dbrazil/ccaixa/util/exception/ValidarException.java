package br.com.dbrazil.ccaixa.util.exception;

import java.util.List;

public class ValidarException extends Exception {

	private static final long serialVersionUID = 8053642679118979536L;

	private List<String> erros;

	public ValidarException(List<String> erros) {
		super();
		this.erros = erros;
	}

	public ValidarException() {
		super();
	}

	public ValidarException(String msg) {
		super(msg);
	}

	public ValidarException(String msg, Throwable t) {
		super(msg, t);
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

}
