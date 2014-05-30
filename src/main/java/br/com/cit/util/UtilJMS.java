package br.com.cit.util;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UtilJMS {
	private static final String IDENTIFY_SYSTEM = "identifySystem";
	public static final String FATURAMENTO_MESSAGE_LISTENER = "faturamentoMessageListener";
	public static final String ACK_MESSAGE_LISTENER = "ackMessageListener";
	public static final String PROCESSAR_ENVIO_MESSAGE_LISTENER = "processarEnvioMensagemService";
	
	/**
	 * 
	 * @return
	 * @throws JMSException
	 */
	public static Connection conectionByListener(ConnectionFactory connectionFactory, String queueName, MessageListener m) throws JMSException {
		Connection con = connectionFactory.createConnection();
	    Session session = con.createSession(false, Session.CLIENT_ACKNOWLEDGE);
	    MessageConsumer consumer = session.createConsumer(session.createQueue(queueName));
	    consumer.setMessageListener(m);
	    return con;
	}

	/**
	 * File System
	 * @return
	 */
	public static FileSystemXmlApplicationContext obterContexto() {
		return new FileSystemXmlApplicationContext("D:/Ambiente-ControleEntregas/workspace/tcflAuto/src/main/resources/spring/applicationContext.xml");
	}

	
	/**
	 * Enviar mensagem 
	 * @param connectionFactory
	 * @param queueName
	 * @return
	 * @throws JMSException
	 */
	public static void connectionBySend(String xml,ConnectionFactory connectionFactory, String queueName, String seletor) throws JMSException {
		Connection con = connectionFactory.createConnection();
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer mensageSender = session.createProducer(session.createQueue(queueName));
		Message createMessage = session.createTextMessage(xml);
		createMessage.setStringProperty(IDENTIFY_SYSTEM, seletor);
		mensageSender.send(createMessage);
		con.close();
	}
}
