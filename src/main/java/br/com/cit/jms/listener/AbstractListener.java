package br.com.cit.jms.listener;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageListener;

import br.com.cit.util.UtilJMS;

public abstract class AbstractListener implements MessageListener, ExceptionListener{

	private static Connection CONNECTION;
	private ConnectionFactory connectionFactory;
	private String queueName;
	private String selectors;

	/**
	 * Start o listener
	 * @throws JMSException
	 */
	public void start() throws JMSException {
		CONNECTION = UtilJMS.conectionByListener(connectionFactory, queueName, this);
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