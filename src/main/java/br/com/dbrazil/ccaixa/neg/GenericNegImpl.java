package br.com.dbrazil.ccaixa.neg;

import java.io.Serializable;
import java.util.List;

import br.com.dbrazil.ccaixa.dao.DaoGenerico;
import br.com.dbrazil.ccaixa.entidade.Entidade;
import br.com.dbrazil.ccaixa.util.exception.ValidarException;

public abstract class GenericNegImpl<T extends Entidade, PK extends Serializable> {

	private DaoGenerico<T, PK> dao;
	
	public GenericNegImpl() {
		super();
	}
	
	public GenericNegImpl(DaoGenerico<T, PK> dao){
		this.dao = dao;
	}
	
	protected abstract boolean validarCampos(T t)throws ValidarException;
	
	protected void setDao(DaoGenerico<T, PK> dao) {
		this.dao = dao;
	}
	
	public DaoGenerico<T, PK> getDao() {
		return dao;
	}
	
	public void salvar(T t) throws ValidarException{
		if (validarCampos(t)) {
			this.dao.save(t);
		}
	}
	
	public void atualizar(T t) throws ValidarException {
		if (validarCampos(t)){
			this.dao.update(t);
		}
	}
	
	public void salvarOuAtualizar(T t) throws ValidarException{
		if (validarCampos(t)){
			this.dao.saveOrUpdate(t);
		}
	}
	
	public void removerLogicamente(T t)throws ValidarException{
		this.dao.removeLogicamente(t);
	}
	
	public T buscarPorId(PK id){
		return this.dao.buscaPorId(id);
	}
	
	public void rebind(T objeto) {
		this.dao.rebind(objeto);
	}
	
	public List<T> listaTudoNaoDeletado(){
		return this.dao.listaTudoNaoDeletado();
	}
	
	public List<T> listaTudo(){
		return this.dao.listaTudo();
	}	
	
	public List<T> listaTudoPaginado(int pagina, int qtdPorPagina) {
		return this.dao.listaTudoPaginado(pagina, qtdPorPagina);
	}
	
}
