package br.com.dbrazil.ccaixa.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.dbrazil.ccaixa.dao.MovimentacaoDao;
import br.com.dbrazil.ccaixa.entidade.Movimentacao;

@Repository("movimentacaoDao")
public class MovimentacaoDaoImpl extends DaoGenericoJpa<Movimentacao, Integer> implements MovimentacaoDao {

	private final Logger LOGGER = (Logger) LoggerFactory.getLogger(MovimentacaoDaoImpl.class);

	@Override
	public List<Movimentacao> listarPeloNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
