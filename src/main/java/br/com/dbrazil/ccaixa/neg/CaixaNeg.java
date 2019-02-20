package br.com.dbrazil.ccaixa.neg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.dbrazil.ccaixa.dao.CaixaDao;
import br.com.dbrazil.ccaixa.entidade.Caixa;
import br.com.dbrazil.ccaixa.util.exception.ValidarException;

@Service("caixaNeg")
public class CaixaNeg extends GenericNegImpl<Caixa, Integer> {

	@Autowired
	public void setDao(CaixaDao dao) {
		super.setDao(dao);
	}

	@Override
	protected boolean validarCampos(Caixa caixa) throws ValidarException {
		if (caixa == null) {
			throw new ValidarException("Caixa informada inválido.");
		}
		if (caixa.getDescricao() == null) {
			throw new ValidarException("Informe a descrição!");
		}
		if (caixa.getDescricao().trim().isEmpty()) {
			throw new ValidarException("Informe a descrição!");
		}
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void salvar(Caixa ambiente) throws ValidarException {
		this.salvarOuAtualizar(ambiente);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void salvarOuAtualizar(Caixa ambiente) throws ValidarException {
		this.verificarCaixaExistente(ambiente);
		super.salvarOuAtualizar(ambiente);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deletar(Caixa ambiente) throws Exception {
		if (ambiente.getId() == null) {
			throw new ValidarException("Selecione uma caixa!");
		}
		super.removerLogicamente(ambiente);
	}

	public List<Caixa> listarPeloNome(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			return new ArrayList<Caixa>();
		}
		return ((CaixaDao) getDao()).listarPeloNome(nome);
	}

	private void verificarCaixaExistente(Caixa obj) throws ValidarException {
		Caixa caixa = this.buscarPorId(obj.getId());
		if (caixa != null) {
			if (caixa.getId() != null) {
				if (caixa.getId() != obj.getId()) {
					throw new ValidarException("Caixa já cadastrado!");
				}
			}
		}
	}
}
