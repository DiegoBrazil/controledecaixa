package br.com.dbrazil.ccaixa.entidade;

public enum TipoMovimentacao {

	E("Entrada"), 
	S("Saída");

	String descricao;

	TipoMovimentacao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

}
