package br.com.dbrazil.ccaixa.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.dbrazil.ccaixa.entidade.Caixa;
import br.com.dbrazil.ccaixa.entidade.Mes;
import br.com.dbrazil.ccaixa.entidade.Movimentacao;
import br.com.dbrazil.ccaixa.neg.MovimentacaoNeg;
import br.com.dbrazil.ccaixa.util.faces.GenericFaces;

@ManagedBean
@ViewScoped
public class PanoramicoController extends GenericFaces {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{movimentacaoNeg}")
	private MovimentacaoNeg movimentacaoNeg;

	private Integer ano = 2010;
	private Mes mes = Mes.JAN;
	private Caixa caixa;
	private List<Caixa> listaCaixa;
	private Integer entradasMes;
	private Integer saidasMes;
	private Integer saldoMes;
	private Integer entradass;
	private Integer saidas;
	private Integer saldo;
	private List<Movimentacao> movimentacoesMes;

	public PanoramicoController() {
		super();
	}

	public List<Movimentacao> listar() {
		return movimentacaoNeg.listaTudo();
	}

	/**
	 * @return the ano
	 */
	public Integer getAno() {
		return ano;
	}

	/**
	 * @param ano the ano to set
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
	}

	/**
	 * @return the mes
	 */
	public Mes getMes() {
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(Mes mes) {
		this.mes = mes;
	}

	/**
	 * @return the caixa
	 */
	public Caixa getCaixa() {
		return caixa;
	}

	/**
	 * @param caixa the caixa to set
	 */
	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	/**
	 * @return the movimentacoesMes
	 */
	public List<Movimentacao> getMovimentacoesMes() {
		return movimentacoesMes;
	}

	/**
	 * @param movimentacoesMes the movimentacoesMes to set
	 */
	public void setMovimentacoesMes(List<Movimentacao> movimentacoesMes) {
		this.movimentacoesMes = movimentacoesMes;
	}

	/**
	 * @return the movimentacaoNeg
	 */
	public MovimentacaoNeg getMovimentacaoNeg() {
		return movimentacaoNeg;
	}

	/**
	 * @param movimentacaoNeg the movimentacaoNeg to set
	 */
	public void setMovimentacaoNeg(MovimentacaoNeg movimentacaoNeg) {
		this.movimentacaoNeg = movimentacaoNeg;
	}

	public Mes[] getMeses() {
		return Mes.values();
	}

}
