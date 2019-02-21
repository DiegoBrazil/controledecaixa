package br.com.dbrazil.ccaixa.neg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.dbrazil.ccaixa.dao.MovimentacaoDao;
import br.com.dbrazil.ccaixa.entidade.Movimentacao;
import br.com.dbrazil.ccaixa.util.exception.ValidarException;

@Service("movimentacaoNeg")
public class MovimentacaoNeg extends GenericNegImpl<Movimentacao, Integer> {

	@Autowired
	public void setDao(MovimentacaoDao dao) {
		super.setDao(dao);
	}

	@Override
	protected boolean validarCampos(Movimentacao movimentacao) throws ValidarException {
		if (movimentacao == null) {
			throw new ValidarException("Movimentacao informada inválido.");
		}
		if (movimentacao.getDescricao() == null) {
			throw new ValidarException("Informe a descrição!");
		}
		if (movimentacao.getDescricao().trim().isEmpty()) {
			throw new ValidarException("Informe a descrição!");
		}
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void salvar(Movimentacao movimentacao) throws ValidarException {
		this.salvarOuAtualizar(movimentacao);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deletar(Movimentacao movimentacao) throws ValidarException {
		try {
			if (movimentacao.getId() == null) {
				throw new ValidarException("Selecione um movimentacao!");
			}
			this.excluir(movimentacao);
		} catch (Exception e) {
			throw new ValidarException("Houve um erro ao excluir!");
		}
	}

	public List<Movimentacao> listaPorMesAno(Integer mes, Integer ano) {
		return ((MovimentacaoDao) getDao()).listaPorMesAno(mes, ano);
	}

}
