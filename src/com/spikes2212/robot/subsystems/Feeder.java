package com.spikes2212.robot.subsystems;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.LimitedSubsystem;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 */
public class Feeder extends LimitedSubsystem {

	private SpeedController motor;
	public static final Supplier<Double> SPEED = ConstantHandler.addConstantDouble("Feeder-SPEED", 0.35);

	public Feeder(SpeedController motor) {
		this.motor = motor;
		this.motor.setInverted(true);
	}

	@Override
	public boolean isMin() {
		return false;
	}

	@Override
	public boolean isMax() {
		return false;
	}

	@Override
	public PIDSource getPIDSource() {
		return null;
	}

	@Override
	protected void move(double speed) {
		motor.set(speed);
	}
}
