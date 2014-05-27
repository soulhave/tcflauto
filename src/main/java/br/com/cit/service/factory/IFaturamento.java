package br.com.cit.service.factory;

import br.com.cit.jaxb.generated.RequestBillingElement;


public interface IFaturamento {
	public RequestBillingElement getCte();
	public RequestBillingElement getAssociado();
}
