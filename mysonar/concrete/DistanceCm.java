package it.unibo.radarSystem22.domain.concrete;

import it.unibo.radarSystem22.domain.interfaces.IDistance;

public class DistanceCm implements IDistance{
	
	private int val;
	
	public DistanceCm (int distance) {
		this.val = distance;
	}
	@Override
	public int getVal() {
		return val;
	}
	
	public void setVal(int distance) {
		this.val = distance;
	}
	
	@Override
	public String toString(){
		return "Distance: " + this.getVal() + " cm";
	}
	

}
