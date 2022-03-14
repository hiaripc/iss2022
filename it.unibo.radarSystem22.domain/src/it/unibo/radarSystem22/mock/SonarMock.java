package it.unibo.radarSystem22.mock;

import it.unibo.radarSystem22.concrete.DistanceCm;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;

public class SonarMock implements ISonar{
	
	private int MAX;
	private int tick_ms; 
	private int tick_cm;
	private DistanceCm distance_cm;
	private boolean alive;
	
	public SonarMock(int MAX_distance, int tick_cm, int tick_ms) {
		this.MAX = MAX_distance;
		this.tick_ms = tick_ms;
		this.tick_cm = tick_cm;
		this.distance_cm = new DistanceCm(MAX);
		this.alive = false;
		
	}
	
	@Override
	public void activate() {
		alive = true;

		new Thread() {
			public void run() {
				int count = 1;
				System.out.println("Sonar started!");
				try {						
					while(alive) {
						Thread.sleep(tick_ms);
						updateDistance(MAX - tick_cm*count);
						count++;
					
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}.start();
	}
	
		
	

	@Override
	public void deactivate() {
		alive = false;
	}
	

	public void updateDistance(int distance) {
		if (distance < 0) distance = 0;
		this.distance_cm = new DistanceCm(distance);
	}
	@Override
	public IDistance getDistance() {
		return this.distance_cm;
	}
	

	@Override
	public boolean isActive() {
		return alive;
	}

}
