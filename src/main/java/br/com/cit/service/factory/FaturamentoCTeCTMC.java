package br.com.cit.service.factory;

import br.com.cit.jaxb.generated.RequestBillingElement;

public class FaturamentoCTeCTMC implements IFaturamento{
	private RequestBillingElement cte;
	
	private RequestBillingElement ctmc;

	public RequestBillingElement getCte() {
		return cte;
	}

	public void setCte(RequestBillingElement cte) {
		this.cte = cte;
	}

	public FaturamentoCTeCTMC(RequestBillingElement cte, RequestBillingElement ctmc) {
		super();
		this.cte = cte;
		this.ctmc = ctmc;
	}

	public RequestBillingElement getCtmc() {
		return ctmc;
	}

	public void setCtmc(RequestBillingElement ctmc) {
		this.ctmc = ctmc;
	}
}
