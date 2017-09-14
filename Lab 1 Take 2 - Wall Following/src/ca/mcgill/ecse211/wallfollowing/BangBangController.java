package ca.mcgill.ecse211.wallfollowing;

import lejos.hardware.motor.*;

public class BangBangController implements UltrasonicController {

  private final int bandCenter;
  private final int bandWidth;
  private final int motorLow;
  private final int motorHigh;
  private int distance;

  public BangBangController(int bandCenter, int bandwidth, int motorLow, int motorHigh) {
    // Default Constructor
    this.bandCenter = bandCenter;
    this.bandWidth = bandwidth;
    this.motorLow = motorLow;
    this.motorHigh = motorHigh;
    WallFollowingLab.leftMotor.setSpeed(motorHigh); // Start robot moving forward
    WallFollowingLab.rightMotor.setSpeed(motorHigh);
    WallFollowingLab.leftMotor.forward();
    WallFollowingLab.rightMotor.forward();
  }

  @Override
  public void processUSData(int distance) {
	this.distance = (int)(distance / Math.sqrt(2));
    // TODO: process a movement based on the us distance passed in (BANG-BANG style)
    // ASSUME COUNTERCLOCKWISE MOVEMENT
    // RIGHT WHEEL IS CONNECTED TO PORT D AND HAS A BLUE PIN ON TOP
    // if distance too far, slow down right wheel
    if (this.distance > bandCenter + (bandWidth/2)) {
    	WallFollowingLab.leftMotor.setSpeed(motorLow);
    	WallFollowingLab.rightMotor.setSpeed(motorHigh);
    }
    // if distance too close, slow down left wheel
    else if (this.distance < bandCenter - (bandWidth/2)) {
    	WallFollowingLab.leftMotor.setSpeed(motorHigh);
    	WallFollowingLab.rightMotor.setSpeed(motorLow);
    }
    // if distance correct, set both wheels to high
    else if (this.distance == bandCenter) {
    	WallFollowingLab.leftMotor.setSpeed(motorHigh);
    	WallFollowingLab.rightMotor.setSpeed(motorHigh);
    }
  }

  @Override
  public int readUSDistance() {
    return this.distance;
  }
}
