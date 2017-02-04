package org.usfirst.frc.team2212.robot.utils;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class PIDOutputForSpeed implements PIDOutput {
	private double speed;
	private SpeedController motor;

	public PIDOutputForSpeed(SpeedController motor) {
		speed = 0;
		this.motor = motor;
	}

	@Override
	public void pidWrite(double output) {
		speed -= 0.01 * output;
		motor.set(speed);
	}

	public void stopMotor() {
		motor.set(0);
	}
}
