package br.com.cit.service.factory;

import br.com.cit.jaxb.generated.RequestBillingElement;

public class FaturamentoCTeVLi implements IFaturamento{
	private RequestBillingElement cte;
	
	private RequestBillingElement cTeVLI;

	public RequestBillingElement getCte() {
		return cte;
	}

	public FaturamentoCTeVLi(RequestBillingElement cte, RequestBillingElement cTeVLI) {
		super();
		this.cte = cte;
		this.cTeVLI = cTeVLI;
	}

	public void setCte(RequestBillingElement cte) {
		this.cte = cte;
	}

	public RequestBillingElement getcTeVLI() {
		return cTeVLI;
	}

	public void setcTeVLI(RequestBillingElement cTeVLI) {
		this.cTeVLI = cTeVLI;
	}
}
