package ca.mcgill.ecse211.HelloWorld;

import lejos.hardware.Button;

/*
* HelloWorld 
98oexample: prints to screen and waits for button press.
*/

public class HelloWorld {
	public static void main(String[] args) {
		// Print to LCD
		System.out.println("Hello World!");
		
		// Wait for a button press before exiting
		Button.waitForAnyPress();
	}
}