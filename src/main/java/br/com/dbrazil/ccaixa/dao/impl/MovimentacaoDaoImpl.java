package br.com.dbrazil.ccaixa.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.dbrazil.ccaixa.dao.MovimentacaoDao;
import br.com.dbrazil.ccaixa.entidade.Caixa;
import br.com.dbrazil.ccaixa.entidade.Movimentacao;

@Repository("movimentacaoDao")
public class MovimentacaoDaoImpl extends DaoGenericoJpa<Movimentacao, Integer> implements MovimentacaoDao {

	private final Logger LOGGER = (Logger) LoggerFactory.getLogger(MovimentacaoDaoImpl.class);

	@Override
	public List<Movimentacao> listaPorMesAnoCaixa(Integer mes, Integer ano, Caixa caixa) {

		StringBuilder sql = new StringBuilder();
		sql.append("select m.descricao, m.valor from movimentacao m ");
		sql.append("where extract(month from m.data) = :mes ");
		sql.append("and extract(year from m.data) = :ano ");
		sql.append("and m.caixa_id = :idCaixa ");

		Query query = super.getEntityManager().createNativeQuery(sql.toString());
		query.setParameter("mes", mes);
		query.setParameter("ano", ano);
		query.setParameter("idCaixa", caixa.getId());

		List<Movimentacao> retorno = new ArrayList<Movimentacao>();
		for (Object obj : query.getResultList()) {
			Movimentacao movimentacao = new Movimentacao();
			movimentacao.setDescricao((String) ((Object[]) obj)[0]);
			movimentacao.setValor((Double) ((Object[]) obj)[1]);
			retorno.add(movimentacao);
		}
		return retorno;
	}

}
