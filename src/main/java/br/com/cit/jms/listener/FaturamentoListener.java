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

public class FaturamentoListener implements MessageListener, ExceptionListener{

	private ConnectionFactory connectionFactory;
	private String queueName;
	private String selectors;
	
	public void start() throws JMSException {
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(session.createQueue(queueName));
        consumer.setMessageListener(this);
        connection.start();
    }
	
	@Override
	public void onMessage(Message arg0) {
		try {
			if(arg0 instanceof TextMessage){
				TextMessage x = (TextMessage)arg0;
				System.out.println("XML="+x.getText());
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
