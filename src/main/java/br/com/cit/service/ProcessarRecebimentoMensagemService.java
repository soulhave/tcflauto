package br.com.cit.service;


/**
 * 
 * @author ramon
 *
 */
public class ProcessarRecebimentoMensagemService extends MensagemTCFLAbstractService {

	private static ProcessarRecebimentoMensagemService S_INSTANCE;
	
	private ProcessarRecebimentoMensagemService() {
	}
	
	public static ProcessarRecebimentoMensagemService getInstance() {
		if(S_INSTANCE==null)
			S_INSTANCE = new ProcessarRecebimentoMensagemService();
		return S_INSTANCE;
	}
	
}
