package it.unibo.radarSystem22.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.mock.LedMock;
import it.unibo.radarSystem22.mock.SonarMock;

public class TestSonar {

	
	@BeforeEach
	public void up() {
		System.out.println("up");
	}
	
	@AfterEach
	public void down() {
		System.out.println("down");
	}
	
	@Test
	public void testSonarMockOn() throws InterruptedException {
		System.out.println("test Sonar Mock");
		
		//ILed led = DeviceFactory.createLed();
		int init_distance = 90;
		int tick_ms = 250; int tick_cm = 10;
		ISonar sonar = new SonarMock(init_distance, tick_cm, tick_ms);
		sonar.activate();
		
		assertTrue(sonar.isActive());
		
		Thread.sleep(tick_ms);
		
		assertEquals(init_distance- tick_cm,sonar.getDistance().getVal());
		Thread.sleep(tick_ms*4);
		assertEquals(init_distance- tick_cm*5,sonar.getDistance().getVal());
		
		sonar.deactivate();
		assertTrue(!sonar.isActive());
		
	}
	
}
