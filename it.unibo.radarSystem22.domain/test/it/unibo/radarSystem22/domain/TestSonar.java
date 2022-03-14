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
		ISonar sonar = new SonarMock(90, 10, 250);
		sonar.activate();
		
		assertTrue(sonar.isActive());
		
		Thread.sleep(250);
		
		assertEquals(90-10,sonar.getDistance().getVal());
		Thread.sleep(250);
		assertEquals(90-10*2,sonar.getDistance().getVal());
		
		sonar.deactivate();
		assertTrue(!sonar.isActive());
		
	}
	
}
