package br.com.dbrazil.ccaixa.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.dbrazil.ccaixa.entidade.Movimentacao;
import br.com.dbrazil.ccaixa.neg.MovimentacaoNeg;
import br.com.dbrazil.ccaixa.util.exception.ValidarException;
import br.com.dbrazil.ccaixa.util.faces.GenericFaces;

@ManagedBean
@ViewScoped
public class MovimentacaoController extends GenericFaces {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{movimentacaoNeg}")
	private MovimentacaoNeg movimentacaoNeg;

	private Movimentacao movimentacao;
	private Movimentacao movimentacaoSelecionado;
	private List<Movimentacao> listaMovimentacao;

	public MovimentacaoController() {
		super();
	}

	public void novo() {
		movimentacao = new Movimentacao();
	}

	public void salvar() {
		try {
			this.movimentacaoNeg.salvar(this.movimentacao);
			this.addInfoMensagem("Movimentacao salvo com sucesso.");
			this.novo();
			this.listar();
		} catch (ValidarException e) {
			this.addInfoMensagem(e.getMessage());
		}
	}

	public void editar() {
		if (this.movimentacaoSelecionado != null) {
			this.movimentacao = this.movimentacaoSelecionado;
		}
	}

	public void excluir() {
		if (this.movimentacaoSelecionado != null) {
			try {
				this.movimentacaoNeg.deletar(movimentacaoSelecionado);
				this.listar();
			} catch (ValidarException e) {
				this.addInfoMensagem(e.getMessage());
			}
		} else {
			this.addInfoMensagem("Selecione um ambiente.");
		}
	}

	public List<Movimentacao> listar() {
		return movimentacaoNeg.listaTudo();
	}

	public void limpar() {
		this.movimentacao = null;
		this.movimentacaoSelecionado = null;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao ambiente) {
		this.movimentacao = ambiente;
	}

	public Movimentacao getMovimentacaoSelecionado() {
		return movimentacaoSelecionado;
	}

	public void setMovimentacaoSelecionado(Movimentacao ambienteSelecionado) {
		this.movimentacaoSelecionado = ambienteSelecionado;
	}

	public List<Movimentacao> getListaMovimentacao() {
		return listaMovimentacao;
	}

	public void setListaMovimentacao(List<Movimentacao> listaMovimentacao) {
		this.listaMovimentacao = listaMovimentacao;
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
	
}
