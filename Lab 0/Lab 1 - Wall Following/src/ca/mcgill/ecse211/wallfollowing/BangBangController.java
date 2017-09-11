package ca.mcgill.ecse211.wallfollowing;

import lejos.hardware.motor.*;

public class BangBangController implements UltrasonicController {

  private final int bandCenter;
  private final int bandwidth;
  private final int motorLow;
  private final int motorHigh;
  private int distance;

  public BangBangController(int bandCenter, int bandwidth, int motorLow, int motorHigh) {
    // Default Constructor
    this.bandCenter = bandCenter;
    this.bandwidth = bandwidth;
    this.motorLow = motorLow;
    this.motorHigh = motorHigh;
    WallFollowingLab.leftMotor.setSpeed(motorHigh); // Start robot moving forward
    WallFollowingLab.rightMotor.setSpeed(motorHigh);
    // WallFollowingLab.leftMotor.forward();
    // WallFollowingLab.rightMotor.forward();
    WallFollowingLab.leftMotor.backward();
    WallFollowingLab.rightMotor.backward();
  }

  @Override
  public void processUSData(int distance) {
    this.distance = distance;
    // TODO: process a movement based on the us distance passed in (BANG-BANG style)
    // ASSUME COUNTERCLOCKWISE MOVEMENT
    // if distance too far, slow down left wheel
    if (distance > bandCenter) {		// no idea if 10cm is an appropriate distance?
    	WallFollowingLab.leftMotor.setSpeed(motorLow);
    	WallFollowingLab.rightMotor.setSpeed(motorHigh);
    }
    // if distance too close, slow down right wheel
    else if (distance < bandCenter) {
    	WallFollowingLab.leftMotor.setSpeed(motorHigh);
    	WallFollowingLab.rightMotor.setSpeed(motorLow);
    }
    // if distance correct, set both wheels to high
    else if (distance == bandCenter) {
    	WallFollowingLab.leftMotor.setSpeed(motorHigh);
    	WallFollowingLab.rightMotor.setSpeed(motorHigh);
    }
  }

  @Override
  public int readUSDistance() {
    return this.distance;
  }
}
