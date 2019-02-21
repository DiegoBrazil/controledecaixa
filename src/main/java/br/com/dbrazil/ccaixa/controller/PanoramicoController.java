package br.com.dbrazil.ccaixa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.dbrazil.ccaixa.entidade.Caixa;
import br.com.dbrazil.ccaixa.entidade.Mes;
import br.com.dbrazil.ccaixa.entidade.Movimentacao;
import br.com.dbrazil.ccaixa.neg.CaixaNeg;
import br.com.dbrazil.ccaixa.neg.MovimentacaoNeg;
import br.com.dbrazil.ccaixa.util.faces.GenericFaces;

@ManagedBean
@ViewScoped
public class PanoramicoController extends GenericFaces {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{movimentacaoNeg}")
	private MovimentacaoNeg movimentacaoNeg;

	@ManagedProperty("#{caixaNeg}")
	private CaixaNeg caixaNeg;

	private Integer ano;
	private Mes mes;
	private Caixa caixa;
	private List<Caixa> listaCaixa;
	private List<Integer> listaAno;
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

	@PostConstruct
	public void init() {
		listaCaixa = caixaNeg.listaTudo();
		caixa = listaCaixa.get(1);
		mes = Mes.JAN;
		listaAno = new ArrayList<Integer>();
		listaAno.add(2010);
		listaAno.add(2011);
		listaAno.add(2012);
		ano = listaAno.get(0);
	}

	public void calcular() {
		movimentacoesMes = movimentacaoNeg.listaPorMesAnoCaixa(mes.getValor(), ano, caixa);
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

	/**
	 * @return the caixaNeg
	 */
	public CaixaNeg getCaixaNeg() {
		return caixaNeg;
	}

	/**
	 * @param caixaNeg the caixaNeg to set
	 */
	public void setCaixaNeg(CaixaNeg caixaNeg) {
		this.caixaNeg = caixaNeg;
	}

	/**
	 * @return the listaCaixa
	 */
	public List<Caixa> getListaCaixa() {
		return listaCaixa;
	}

	/**
	 * @param listaCaixa the listaCaixa to set
	 */
	public void setListaCaixa(List<Caixa> listaCaixa) {
		this.listaCaixa = listaCaixa;
	}

	public Mes[] getMeses() {
		return Mes.values();
	}

	public List<Integer> getListaAno() {
		return listaAno;
	}

	public void setListaAno(List<Integer> listaAno) {
		this.listaAno = listaAno;
	}

}
