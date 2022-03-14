package it.unibo.radarSystem22.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.mock.LedMock;

public class TestLed {

	
	@BeforeEach
	public void up() {
		System.out.println("up");
	}
	
	@AfterEach
	public void down() {
		System.out.println("down");
	}
	
	@Test
	public void testLedMockOn() {
		System.out.println("test LedMock");
		
		//ILed led = DeviceFactory.createLed();
		ILed led = new LedMock();
		assertTrue(! led.getState());
		
		led.turnOn();
		assertTrue(led.getState());
		
	}
	

	@Test
	public void testLedMockOff() {
		System.out.println("test LedMock");
		//DomainSystemConfig.simulation = true;
		
		//ILed led = DeviceFactory.createLed();
		ILed led = new LedMock();
		assertTrue(! led.getState());
		
		led.turnOff();
		assertTrue(!led.getState());
		
	}
}
