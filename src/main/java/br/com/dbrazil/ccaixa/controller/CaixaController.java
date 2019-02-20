package br.com.dbrazil.ccaixa.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.dbrazil.ccaixa.entidade.Caixa;
import br.com.dbrazil.ccaixa.neg.CaixaNeg;
import br.com.dbrazil.ccaixa.util.exception.ValidarException;
import br.com.dbrazil.ccaixa.util.faces.GenericFaces;

@ManagedBean
@ViewScoped
public class CaixaController extends GenericFaces {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{caixaNeg}")
	private CaixaNeg caixaNeg;

	private Caixa caixa;
	private Caixa caixaSelecionado;
	private List<Caixa> listaCaixa;

	public CaixaController() {
		super();
	}

	public void novo() {
		caixa = new Caixa();
	}

	public void salvar() {
		try {
			this.caixaNeg.salvar(this.caixa);
			this.addInfoMensagem("Caixa salvo com sucesso.");
			this.novo();
			this.listar();
		} catch (ValidarException e) {
			this.addInfoMensagem(e.getMessage());
		}
	}

	public void editar() {
		if (this.caixaSelecionado != null) {
			this.caixa = this.caixaSelecionado;
		}
	}

	public void excluir() {
		if (this.caixaSelecionado != null) {
			try {
				this.caixaNeg.deletar(caixaSelecionado);
				this.listar();
			} catch (ValidarException e) {
				this.addInfoMensagem(e.getMessage());
			}
		} else {
			this.addInfoMensagem("Selecione um ambiente.");
		}
	}

	public List<Caixa> listar() {
		return caixaNeg.listaTudo();
	}

	public void limpar() {
		this.caixa = null;
		this.caixaSelecionado = null;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa ambiente) {
		this.caixa = ambiente;
	}

	public Caixa getCaixaSelecionado() {
		return caixaSelecionado;
	}

	public void setCaixaSelecionado(Caixa ambienteSelecionado) {
		this.caixaSelecionado = ambienteSelecionado;
	}

	public List<Caixa> getListaCaixa() {
		return listaCaixa;
	}

	public void setListaCaixa(List<Caixa> listaCaixa) {
		this.listaCaixa = listaCaixa;
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
	
}
