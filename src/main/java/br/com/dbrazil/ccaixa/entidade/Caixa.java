package br.com.dbrazil.ccaixa.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "caixa")
public class Caixa extends Entidade{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "descricao")
	private String descricao;

	@Column(name = "saldoInicial")
	private String saldoInicial;

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the saldoInicial
	 */
	public String getSaldoInicial() {
		return saldoInicial;
	}

	/**
	 * @param saldoInicial the saldoInicial to set
	 */
	public void setSaldoInicial(String saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	
}