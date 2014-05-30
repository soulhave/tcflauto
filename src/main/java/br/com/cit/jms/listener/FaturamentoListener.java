package br.com.cit.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import br.com.cit.service.ProcessarEnvioMensagemService;
import br.com.cit.service.ProcessarRecebimentoMensagemService;
import br.com.cit.service.factory.IFaturamento;
import br.com.cit.service.factory.IXml;
import br.com.cit.util.UtilJMS;

/**
 * Lister que escuta a fila de faturamento.
 * @author ramon
 *
 */
public class FaturamentoListener extends AbstractListener{

	@Override
	public void onMessage(Message arg0) {
		try {
			if(arg0 instanceof TextMessage){
				TextMessage x = (TextMessage)arg0;
				IXml bind = ProcessarRecebimentoMensagemService.getInstance().bindObjectCte(x.getText());
				IFaturamento bindObjectCte = (IFaturamento)bind;
				if(bindObjectCte.getCte().getHeaderRequestBilling().getIdentifySystem().equals(getSelectors())){ //Verifica se é do seletor
					System.out.println(bindObjectCte.getClass().getSimpleName()+" "+bindObjectCte.getCte().getHeaderRequestBilling().getIdentifySystem()+" CTE-"+bindObjectCte.getCte().getHeaderRequestBilling().getProvisoryNumberNF()+(bindObjectCte.getAssociado()!=null?" ASSOCIADO-"+bindObjectCte.getAssociado().getHeaderRequestBilling().getProvisoryNumberNF():""));
					((ProcessarEnvioMensagemService)UtilJMS.obterContexto().getBean(UtilJMS.PROCESSAR_ENVIO_MESSAGE_LISTENER)).enviarMensagemUNICOM("Teste:"+bindObjectCte.getCte().getHeaderRequestBilling().getIdentifySystem()+" CTE-"+bindObjectCte.getCte().getHeaderRequestBilling().getProvisoryNumberNF()+(bindObjectCte.getAssociado()!=null?" ASSOCIADO-"+bindObjectCte.getAssociado().getHeaderRequestBilling().getProvisoryNumberNF():""));
					x.acknowledge();
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
