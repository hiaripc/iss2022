package it.unibo.radarSystem22.mock;

import it.unibo.radarSystem22.domain.interfaces.ILed;

public class LedMock implements ILed{
	
	private boolean status;
	
	public LedMock() {
		this.status = false;
	}
	@Override
	public void turnOn() {
		this.status = true;
		
	}

	@Override
	public void turnOff() {
		this.status = false;
	}

	@Override
	public boolean getState() {
		return this.status;
	}
	
	

}
