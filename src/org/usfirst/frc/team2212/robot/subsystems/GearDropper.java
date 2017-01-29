package org.usfirst.frc.team2212.robot.subsystems;

import com.spikes2212.genericsubsystems.LimitedSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearDropper extends LimitedSubsystem{
	
	private SpeedController motor;
	private DigitalInput max;
	private DigitalInput min;
	
	public GearDropper(SpeedController motor, DigitalInput max, DigitalInput min) {
		this.max=max;
		this.min=min;
		this.motor=motor;
	}

	@Override
	public boolean isMin() {
		return min.get();
	}

	@Override
	public boolean isMax() {
		return max.get();
	}

	@Override
	public PIDSource getPIDSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void move(double speed) {
		motor.set(speed);
	}
}

