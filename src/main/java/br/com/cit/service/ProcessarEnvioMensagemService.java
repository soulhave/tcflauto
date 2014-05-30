package br.com.cit.service;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import br.com.cit.util.UtilJMS;

public class ProcessarEnvioMensagemService extends MensagemTCFLAbstractService{
	private static ProcessarEnvioMensagemService S_INSTANCE;
	
	private ProcessarEnvioMensagemService() {
	}
	
	public static ProcessarEnvioMensagemService getInstance() {
		if(S_INSTANCE==null)
			S_INSTANCE = new ProcessarEnvioMensagemService();
		return S_INSTANCE;
	}
	
	private ConnectionFactory connectionFactory;
	private String queueName;
	private String selectors;
	
	public void enviarMensagemUNICOM(String xml) {
		try {
			UtilJMS.connectionBySend(xml,connectionFactory, queueName, selectors);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Enviar Mensagem
	 * @param tipoRetorno
	 */
	public void processaRetorno(String tipoRetorno){
		enviarMensagemUNICOM(tipoRetorno);
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getSelectors() {
		return selectors;
	}

	public void setSelectors(String selectors) {
		this.selectors = selectors;
	}
}
