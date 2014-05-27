package br.com.cit.service.factory;

import br.com.cit.jaxb.generated.CompositeRequestBillingElement;
import br.com.cit.jaxb.generated.CompositeRequestBillingElement.RequestBillingCTE;
import br.com.cit.jaxb.generated.CompositeRequestBillingElement.RequestBillingCTMC;
import br.com.cit.jaxb.generated.CompositeRequestBillingElement.RequestBillingCTeVLI;
import br.com.cit.jaxb.generated.RequestBillingElement;

public class FaturamentoFactory {
	private static FaturamentoFactory S_INSTANCE;

	private FaturamentoFactory() {
	}

	public static FaturamentoFactory getInstance() {
		if (S_INSTANCE == null)
			S_INSTANCE = new FaturamentoFactory();
		return S_INSTANCE;
	}

	private FaturamentoCTe getFaturamentoCTe(RequestBillingElement cte) {
		return new FaturamentoCTe(cte);
	}

	private FaturamentoCTeCTMC getFaturamentoCTeCTMC(RequestBillingElement cte, RequestBillingElement ctmc) {
		return new FaturamentoCTeCTMC(cte, ctmc);
	}

	private FaturamentoCTeVLi getFaturamentoCTeVLi(RequestBillingElement cte, RequestBillingElement ctevli) {
		return new FaturamentoCTeVLi(ctevli, ctevli);
	}
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	public IFaturamento createFactory(Object o) {
		if(o instanceof RequestBillingElement){
			RequestBillingElement requestBilling = (RequestBillingElement)o;
			return getFaturamentoCTe(requestBilling);
		}
		if(o instanceof CompositeRequestBillingElement){
			CompositeRequestBillingElement composite = (CompositeRequestBillingElement) o;
			RequestBillingCTE requestBillingCTe = composite.getRequestBillingCTE();
			RequestBillingCTeVLI requestBillingCTeVLI = composite.getRequestBillingCTeVLI();
			RequestBillingCTMC requestBillingCTMC = composite.getRequestBillingCTMC();
			//Retorna cte vli
			if(requestBillingCTeVLI!=null && requestBillingCTMC==null)
				return getFaturamentoCTeVLi(requestBillingCTe.getRequestBilling(),requestBillingCTeVLI.getRequestBilling());
			//Retorna CTMC
			if(requestBillingCTeVLI==null && requestBillingCTMC!=null)
				return getFaturamentoCTeCTMC(requestBillingCTe.getRequestBilling(),requestBillingCTMC.getRequestBilling());
			//Retorna CT-e com cte vli
			if(requestBillingCTeVLI!=null && requestBillingCTMC!=null)
				return getFaturamentoCTeVLi(requestBillingCTe.getRequestBilling(),requestBillingCTeVLI.getRequestBilling());
			//REtorna CTe sozinho
			if(requestBillingCTeVLI==null && requestBillingCTMC==null)
				return getFaturamentoCTe(requestBillingCTe.getRequestBilling());
		}
		
		return null;
	}
}
