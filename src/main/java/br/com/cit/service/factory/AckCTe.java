package br.com.cit.service.factory;

import br.com.cit.jaxb.generated.LegacyAckElement;

public class AckCTe implements IXml{
	
	private LegacyAckElement ack;

	public AckCTe(LegacyAckElement ackElement) {
		this.ack = ackElement;
	}

	public LegacyAckElement getAck() {
		return ack;
	}

	public void setAck(LegacyAckElement ack) {
		this.ack = ack;
	}

}
