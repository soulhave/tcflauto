package br.com.cit.service.factory;

import br.com.cit.jaxb.generated.RequestBillingElement;


public interface IFaturamento extends IXml {
	public RequestBillingElement getCte();
	public RequestBillingElement getAssociado();
}
