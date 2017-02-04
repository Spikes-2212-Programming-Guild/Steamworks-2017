package org.usfirst.frc.team2212.robot.subsystems;

import java.util.function.Supplier;

import com.ctre.CANTalon;
import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.LimitedSubsystem;

import edu.wpi.first.wpilibj.PIDSource;

public class Climber extends LimitedSubsystem {
	private CANTalon motor1, motor2;
	public static final Supplier<Double> MAX_CURRENT = ConstantHandler.addConstantDouble("Climber-MAX_CURRENT", 20);

	public Climber(CANTalon motor1, CANTalon motor2) {
		this.motor1 = motor1;
		this.motor2 = motor2;
	}

	@Override
	public boolean isMin() {
		// TODO Auto-generated method sub
		return false;
	}

	public double getCurrent1() {
		if (motor1 != null)
			return motor1.getOutputCurrent();
		return 0;
	}

	public double getCurrent2() {
		if (motor2 != null)
			return motor2.getOutputCurrent();
		return 0;
	}

	@Override
	public boolean isMax() {
		if (Math.max(motor1.getOutputCurrent(), motor2.getOutputCurrent()) >= MAX_CURRENT.get()) {
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
		motor1.set(speed);
		motor2.set(speed);
	}
}
