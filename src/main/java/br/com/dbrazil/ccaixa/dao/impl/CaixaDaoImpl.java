package br.com.dbrazil.ccaixa.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.dbrazil.ccaixa.dao.CaixaDao;
import br.com.dbrazil.ccaixa.entidade.Caixa;

@Repository("caixaDao")
public class CaixaDaoImpl extends DaoGenericoJpa<Caixa, Integer> implements CaixaDao {

	private final Logger LOGGER = (Logger) LoggerFactory.getLogger(CaixaDaoImpl.class);

	@Override
	public List<Caixa> listarPeloNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
