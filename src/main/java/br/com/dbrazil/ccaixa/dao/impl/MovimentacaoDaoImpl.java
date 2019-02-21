package br.com.dbrazil.ccaixa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		sql.append("and m.caixa = :idCaixa ");

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

	@Override
	public Map<String, Double> panoramico(Integer mes, Integer ano, Caixa caixa) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("mm.entrada_mes, ");
		sql.append("mm.saida_mes, ");
		sql.append("(entrada_mes - saida_mes) saldo_mes, ");
		sql.append("m.entrada, ");
		sql.append("m.saida, ");
		sql.append("(entrada - saida) saldo ");
		sql.append("from ");
		sql.append("(select ");
		sql.append("sum(case when tipo = 'E' then valor else 0 end) as entrada_mes, ");
		sql.append("sum(case when tipo = 'S' then valor else 0 end) as saida_mes ");
		sql.append("from movimentacao ");
		sql.append("where extract(month from data) = :mes ");
		sql.append("and extract(year from data) = :ano ");
		sql.append("and caixa = :idCaixa) mm, ");
		sql.append("(select ");
		sql.append("sum(case when tipo = 'E' then valor else 0 end) as entrada, ");
		sql.append("sum(case when tipo = 'S' then valor else 0 end) as saida ");
		sql.append("from movimentacao m) m ");

		Query query = super.getEntityManager().createNativeQuery(sql.toString());
		query.setParameter("mes", mes);
		query.setParameter("ano", ano);
		query.setParameter("idCaixa", caixa.getId());

		Map<String, Double> retorno = new HashMap<String, Double>();
		Object obj = query.getSingleResult();
		retorno.put("entradaMes", (Double) ((Object[]) obj)[0] == null ? Double.valueOf(0) : (Double) ((Object[]) obj)[0]);
		retorno.put("saidaMes", (Double) ((Object[]) obj)[1] == null ? Double.valueOf(0) : (Double) ((Object[]) obj)[1]);
		retorno.put("saldoMes", (Double) ((Object[]) obj)[2] == null ? Double.valueOf(0) : (Double) ((Object[]) obj)[2]);
		retorno.put("entrada", (Double) ((Object[]) obj)[3] == null ? Double.valueOf(0) : (Double) ((Object[]) obj)[3]);
		retorno.put("saida", (Double) ((Object[]) obj)[4] == null ? Double.valueOf(0) : (Double) ((Object[]) obj)[4]);
		retorno.put("saldo", (Double) ((Object[]) obj)[5] == null ? Double.valueOf(0) : (Double) ((Object[]) obj)[5]);
		return retorno;
	}

}
