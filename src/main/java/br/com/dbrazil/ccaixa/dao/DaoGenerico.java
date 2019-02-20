package br.com.dbrazil.ccaixa.dao;

import java.io.Serializable;
import java.util.List;

import br.com.dbrazil.ccaixa.entidade.Entidade;

public interface DaoGenerico<T extends Entidade, PK extends Serializable> {

	public void save(T entidade);

	public void saveOrUpdate(T entidade);

	public void update(T entidade);

	public void removeLogicamente(T entidade);

	public List<T> listaTudo();
	
	public List<T> listaTudoPaginado(int pagina, int qtdPorPagina);

	public List<T> listaTudoNaoDeletadoPaginado(int pagina, int qtdPorPagina);

	public List<T> listaTudoNaoDeletado();

	public T buscaPorId(PK id);

	public void rebind(T entidade);

}
