package br.com.dbrazil.ccaixa.dao;

import java.util.List;

import br.com.dbrazil.ccaixa.entidade.Caixa;
import br.com.dbrazil.ccaixa.entidade.Movimentacao;

public interface MovimentacaoDao extends DaoGenerico<Movimentacao, Integer> {

	public List<Movimentacao> listaPorMesAnoCaixa(Integer mes, Integer ano, Caixa caixa);

}
