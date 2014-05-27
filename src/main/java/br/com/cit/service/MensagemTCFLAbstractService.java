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
public abstract class MensagemTCFLAbstractService {
	
	private static final String JAXB = "br.com.cit.jaxb.generated";
	
	/**
	 * Metodo realiza o parsing do XML
	 * retornado do TCFL.
	 * @param xml
	 * @return
	 */
	public IFaturamento bindObjectCte(String xml){
		JAXBContext jc;
		try {
			InputStream input = new ByteArrayInputStream(xml.getBytes());
			jc = JAXBContext.newInstance(JAXB);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			Object retornoSolicitacao = unmarshaller.unmarshal(input);
			return FaturamentoFactory.getInstance().createFactory(retornoSolicitacao);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}
