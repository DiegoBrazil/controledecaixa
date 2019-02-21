package br.com.dbrazil.ccaixa.entidade;

public enum Mes {
	JAN(1,"Janeiro"),
	FEV(2,"Fevereiro"),
	MAR(3,"Março"),
	ABR(4,"Abril"),
	MAI(5,"Maio"),
	JUN(6,"Junho"),
	JUL(7,"Julho"),
	AGO(8,"Agosto"),
	SET(9,"Setembro"),
	OUT(10,"Outubro"),
	NOV(11,"Novembro"),
	DEZ(12,"Dezembro");
	
	Integer valor;
	String descricao;
	
	Mes(Integer valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	/**
	 * @return the valor
	 */
	public Integer getValor() {
		return valor;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
}
