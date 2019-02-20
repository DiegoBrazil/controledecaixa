package br.com.dbrazil.ccaixa.util.faces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.dbrazil.ccaixa.util.exception.ValidarException;

public abstract class GenericFaces implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	/**
     * @param String destino
     * @param String header
     * @param String msg
     */
	protected void enviaMensagem(String destino,String header, String msg){
		FacesContext context = FacesContext.getCurrentInstance(); 
		context.addMessage(destino, new FacesMessage(header, msg));
	}
	
	/**
     * @param String header
     * @param String msg
     */
	protected void enviaMensagem(String header, String msg){
		FacesContext context = FacesContext.getCurrentInstance(); 
		context.addMessage(null, new FacesMessage(header, msg));
	}
	
	/**
	 * Adiciona uma mensagem de info no contexto do faces
	 * @param msg
	 */
	protected void addInfoMensagem(String msg){
		FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
		retornaContexto().addMessage(null, ms);
	}
	
	/**
	 * Adiciona uma mensagem de erro no contexto do faces
	 * @param msg
	 */
	protected void addErrorMensagem(String msg){
		FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
		retornaContexto().addMessage(null, ms);
	}
	
	/**
	 * Adiciona uma mensagem de erro no contexto do faces utilizando-se da exece��o
	 * @param ex
	 */
	protected void addErrorMensagem(Exception ex){
		FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null);
		retornaContexto().addMessage(null, ms);
	}
	
	protected void addErrorMensagem(ValidarException ex){
		if(ex.getMessage()!= null){
			retornaContexto().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
		}
		if(ex.getErros() != null){
			for (String err : ex.getErros()) {
				retornaContexto().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, err, null));
			}
		}
	}
	
	private FacesContext retornaContexto(){
		return FacesContext.getCurrentInstance();
	}
	
	/**
	 * Retorna uma lista de SelectItem contento a op��o Sim e N�o
	 * @return
	 */
	protected List<SelectItem> retornaListaSimNao() {
		List<SelectItem> listaSimNao =new ArrayList<SelectItem>();
		listaSimNao.add(new SelectItem("S", "Sim"));
		listaSimNao.add(new SelectItem("N", "N�o"));
		
		return listaSimNao;
	}
	
}