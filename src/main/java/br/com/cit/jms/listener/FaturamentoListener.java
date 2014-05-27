package br.com.cit.jms.listener;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import br.com.cit.service.ProcessarRecebimentoMensagemService;
import br.com.cit.service.factory.IFaturamento;

/**
 * Lister que escuta a fila de faturamento.
 * @author ramon
 *
 */
public class FaturamentoListener implements MessageListener, ExceptionListener{

	private static Connection CONNECTION;
	
	private ConnectionFactory connectionFactory;
	private String queueName;
	private String selectors;
	
	/**
	 * Start o listener
	 * @throws JMSException
	 */
	public void start() throws JMSException {
        CONNECTION = connectionFactory.createConnection();
        Session session = CONNECTION.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(session.createQueue(queueName));
        consumer.setMessageListener(this);
        CONNECTION.start();
    }
	
	/**
	 * Para o processo de listener.
	 * @throws JMSException
	 */
	public void stop() throws JMSException {
		if(CONNECTION!=null){
			CONNECTION.stop();
			CONNECTION.close();
		}
	}
	
	@Override
	public void onMessage(Message arg0) {
		try {
			if(arg0 instanceof TextMessage){
				TextMessage x = (TextMessage)arg0;
				IFaturamento bindObjectCte = ProcessarRecebimentoMensagemService.getInstance().bindObjectCte(x.getText());
				System.out.println(bindObjectCte.getClass().getSimpleName()+" "+bindObjectCte.getCte().getHeaderRequestBilling().getIdentifySystem()+" "+bindObjectCte.getCte().getHeaderRequestBilling().getProvisoryNumberNF());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onException(JMSException arg0) {
		System.out.println("Erro="+arg0.getMessage());
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
