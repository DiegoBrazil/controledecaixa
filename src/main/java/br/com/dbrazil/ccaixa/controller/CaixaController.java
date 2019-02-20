package br.com.dbrazil.ccaixa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dbrazil.ccaixa.entidade.Caixa;
import br.com.dbrazil.ccaixa.util.faces.GenericFaces;

@ManagedBean
@ViewScoped
public class CaixaController extends GenericFaces {

	private static final long serialVersionUID = 1L;

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
		this.addInfoMensagem("Teste");
	}

	public void editar() {
		if (this.caixaSelecionado != null) {
			this.caixa = this.caixaSelecionado;
		}
	}

	public void excluir() {
		this.addInfoMensagem("Teste");
	}

	public List<Caixa> listar() {
		return new ArrayList<Caixa>();
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

}
