package br.com.cit.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.cit.service.factory.FaturamentoFactory;
import br.com.cit.service.factory.IFaturamento;

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
