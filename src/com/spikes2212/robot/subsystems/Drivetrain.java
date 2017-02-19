package com.spikes2212.robot.subsystems;


import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.utils.DoubleSpeedcontroller;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends TankDrivetrain {

	public static final double DISTANCE_PER_PULSE = 4 * Math.PI / 1440; // 1440 pulses per revolution,4 inch wheel
	private SpeedController leftSpeedcontroller;
	private SpeedController rightSpeedcontroller;
	private Encoder leftEncoder;
	private Encoder rightEncoder;

	public Drivetrain(SpeedController leftSpeedcontroller, SpeedController rightSpeedcontroller, Encoder leftEncoder,
			Encoder rightEncoder) {
		this.leftSpeedcontroller = leftSpeedcontroller;
		this.rightSpeedcontroller = rightSpeedcontroller;
		this.rightSpeedcontroller.setInverted(true);
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
	}

	@Override
	public void setLeft(double speedLeft) {
		leftSpeedcontroller.set(speedLeft);
	}

	@Override
	public void setRight(double speedRight) {
		rightSpeedcontroller.set(speedRight);
	}

	@Override
	public PIDSource getLeftPIDSource() {
		return leftEncoder;
	}

	@Override
	public PIDSource getRightPIDSource() {
		return rightEncoder;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveArcade(Robot.drivetrain, Robot.oi::getForwardRight, Robot.oi::getRotation));
	}
}
