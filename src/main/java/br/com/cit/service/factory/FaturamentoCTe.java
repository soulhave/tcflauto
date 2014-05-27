package br.com.cit.service.factory;

import br.com.cit.jaxb.generated.RequestBillingElement;

public class FaturamentoCTe implements IFaturamento{
	private RequestBillingElement cte;

	public FaturamentoCTe(RequestBillingElement cte) {
		super();
		this.cte = cte;
	}

	public RequestBillingElement getCte() {
		return cte;
	}

	public void setCte(RequestBillingElement cte) {
		this.cte = cte;
	} 
}
