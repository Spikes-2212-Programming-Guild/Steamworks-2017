package org.usfirst.frc.team2212.robot.subsystems;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.utils.DoubleSpeedcontroller;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends TankDrivetrain {
	DoubleSpeedcontroller leftDoubleSpeedcontroller;
	DoubleSpeedcontroller rightDoubleSpeedcontroller;
	Encoder leftEncoder;
	Encoder rightEncoder;

	public Drivetrain(DoubleSpeedcontroller leftDoubleSpeedcontroller, DoubleSpeedcontroller rightDoubleSpeedcontroller,
			Encoder leftEncoder, Encoder rightEncoder) {
		this.leftDoubleSpeedcontroller = leftDoubleSpeedcontroller;
		this.rightDoubleSpeedcontroller = rightDoubleSpeedcontroller;
		this.leftEncoder=leftEncoder;
		this.rightEncoder=rightEncoder;
	}

	@Override
	public void setLeft(double speedLeft) {
		leftDoubleSpeedcontroller.set(speedLeft);
	}

	@Override
	public void setRight(double speedRight) {
		rightDoubleSpeedcontroller.set(speedRight);
	}

	/**
	 * I will never ever leave my leptop on
	 * @return 
	 */
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
		// TODO Auto-generated method stub
	}
}
