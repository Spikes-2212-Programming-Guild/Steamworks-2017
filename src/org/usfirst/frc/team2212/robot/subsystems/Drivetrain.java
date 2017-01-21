package org.usfirst.frc.team2212.robot.subsystems;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.utils.DoubleSpeedcontroller;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends TankDrivetrain {
	DoubleSpeedcontroller leftDoubleSpeedcontroller;
	DoubleSpeedcontroller rightDoubleSpeedcontroller;

	public Drivetrain(DoubleSpeedcontroller leftDoubleSpeedcontroller,
			DoubleSpeedcontroller rightDoubleSpeedcontroller) {
		this.leftDoubleSpeedcontroller = leftDoubleSpeedcontroller;
		this.rightDoubleSpeedcontroller = rightDoubleSpeedcontroller;
	}

	@Override
	public void setLeft(double speedLeft) {
		leftDoubleSpeedcontroller.set(speedLeft);
	}

	@Override
	public void setRight(double speedRight) {
		rightDoubleSpeedcontroller.set(speedRight);
	}

	@Override
	public PIDSource getLeftPIDSource() {
		return null;
	}

	@Override
	public PIDSource getRightPIDSource() {
		return null;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
