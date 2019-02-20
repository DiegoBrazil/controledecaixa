package br.com.dbrazil.ccaixa.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.dbrazil.ccaixa.dao.DaoGenerico;
import br.com.dbrazil.ccaixa.entidade.Entidade;

public abstract class DaoGenericoJpa<E extends Entidade, PK extends Serializable> implements DaoGenerico<E, PK> {

	protected final Class<E> classe;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public DaoGenericoJpa() {
		classe = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(E entidade) {
		this.getEntityManager().persist(entidade);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(E entidade) {
		this.getEntityManager().merge(entidade);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdate(E entidade) {
		if (entidade.getId() == null){
			this.getEntityManager().persist(entidade);			
		} else {
			this.getEntityManager().merge(entidade);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeLogicamente(E obj) {
		Entidade entidade = this.getEntityManager().find(this.classe, ((Entidade) obj).getId());
		this.getEntityManager().merge(entidade);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public E buscaPorId(PK id) {
		return (E) this.getEntityManager().find(this.classe, id);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void rebind(E object) {
		this.getEntityManager().refresh(object);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<E> listaTudo() {
		Query query = this.getEntityManager().createQuery("select e from "+ this.getNomeEntidade() + " e");		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> listaTudoNaoDeletadoPaginado(int pagina, int qtdPorPagina) {
		Query query = this.getEntityManager().createQuery("select e from " + this.getNomeEntidade() + " e where e.ativo=true");
		query.setFirstResult(pagina);
		query.setMaxResults(qtdPorPagina);
		return query.getResultList();
	}

	private String getNomeEntidade() {
		return this.classe.getSimpleName();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<E> listaTudoNaoDeletado() {
		Query query = this.getEntityManager().createQuery("select e from " + this.getNomeEntidade() + " e where e.ativo=true");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> listaTudoPaginado(int pagina, int qtdPorPagina) {
		Query query = this.getEntityManager().createQuery("select e from "+ this.getNomeEntidade() + " e");
		query.setFirstResult(pagina);
		query.setMaxResults(qtdPorPagina);		
		return query.getResultList();
	}
}
