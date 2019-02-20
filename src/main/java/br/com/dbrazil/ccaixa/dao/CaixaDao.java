package br.com.dbrazil.ccaixa.dao;

import java.util.List;

import br.com.dbrazil.ccaixa.entidade.Caixa;

public interface CaixaDao extends DaoGenerico<Caixa, Integer> {

	public List<Caixa> listarPeloNome(String nome);
	
}
