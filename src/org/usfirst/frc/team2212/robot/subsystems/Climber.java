package org.usfirst.frc.team2212.robot.subsystems;

import com.ctre.CANTalon;
import com.spikes2212.genericsubsystems.LimitedSubsystem;

import edu.wpi.first.wpilibj.PIDSource;

public class Climber extends LimitedSubsystem {
	private CANTalon motor;
	public static final double MAX_CURRENT = 25;

	public Climber(CANTalon motor) {
		this.motor = motor;
	}

	@Override
	public boolean isMin() {
		// TODO Auto-generated method sub
		return false;
	}

	public double getCurrent() {
		return motor.getOutputCurrent();
	}

	@Override
	public boolean isMax() {
		if (motor.getOutputCurrent() >= MAX_CURRENT) {
			return true;
		} else {
			return false;
		}
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
