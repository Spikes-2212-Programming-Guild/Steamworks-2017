package org.usfirst.frc.team2212.robot.subsystems;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.utils.DoubleSpeedcontroller;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends TankDrivetrain {
	
	private SpeedController leftSpeedcontroller;
	private SpeedController rightSpeedcontroller;
	private Encoder leftEncoder;
	private Encoder rightEncoder;

	public Drivetrain(SpeedController leftSpeedcontroller, SpeedController rightSpeedcontroller,
			Encoder leftEncoder, Encoder rightEncoder) {
		this.leftSpeedcontroller = leftSpeedcontroller;
		this.rightSpeedcontroller = rightSpeedcontroller;
		this.leftEncoder=leftEncoder;
		this.rightEncoder=rightEncoder;
		leftEncoder.setDistancePerPulse(Constants.Speed.DISTANCE_PER_PULSE);
		rightEncoder.setDistancePerPulse(Constants.Speed.DISTANCE_PER_PULSE);
	}

	@Override
	public void setLeft(double speedLeft) {
		leftSpeedcontroller.set(-speedLeft);
	}

	@Override
	public void setRight(double speedRight) {
		rightSpeedcontroller.set(speedRight);
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
