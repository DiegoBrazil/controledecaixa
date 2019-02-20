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
	public void salvar(Caixa caixa) throws ValidarException {
		this.salvarOuAtualizar(caixa);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deletar(Caixa caixa) throws ValidarException {
		try {
			if (caixa.getId() == null) {
				throw new ValidarException("Selecione um caixa!");
			}
			this.excluir(caixa);
		} catch (Exception e) {
			throw new ValidarException("Houve um erro ao excluir!");
		}
	}

	public List<Caixa> listarPeloNome(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			return new ArrayList<Caixa>();
		}
		return ((CaixaDao) getDao()).listarPeloNome(nome);
	}

}
