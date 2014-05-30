package br.com.cit.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import br.com.cit.service.ProcessarEnvioMensagemService;
import br.com.cit.service.ProcessarRecebimentoMensagemService;
import br.com.cit.service.factory.AckCTe;
import br.com.cit.service.factory.IXml;
import br.com.cit.util.UtilJMS;

/**
 * Lister que escuta a fila de faturamento.
 * @author ramon
 *
 */
public class AckListener extends AbstractListener{

	@Override
	public void onMessage(Message arg0) {
		try {
			if(arg0 instanceof TextMessage){
				TextMessage x = (TextMessage)arg0;
				IXml bindObjectAck = ProcessarRecebimentoMensagemService.getInstance().bindObjectCte(x.getText());
				AckCTe ackElement = (AckCTe)bindObjectAck;
				
				if(ackElement.getAck().getIdentifySystem().equals(getSelectors())){ //Verifica se é do seletor 
					System.out.println(ackElement.getClass().getSimpleName()+" "+ackElement.getAck().getIdentifySystem()+" Stage - "+ackElement.getAck().getReturnStage()+" CTE-"+ackElement.getAck().getProvisoryNumberNF());
					((ProcessarEnvioMensagemService)UtilJMS.obterContexto().getBean(UtilJMS.PROCESSAR_ENVIO_MESSAGE_LISTENER)).enviarMensagemUNICOM("Teste:"+ackElement.getAck().getIdentifySystem()+" Stage - "+ackElement.getAck().getReturnStage()+" CTE-"+ackElement.getAck().getProvisoryNumberNF());
					x.acknowledge();
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
