package com.spikes2212.robot.subsystems;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.LimitedSubsystem;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Picker extends LimitedSubsystem {

	public static final Supplier<Double> SPEED = ConstantHandler.addConstantDouble("Picker-SPEED", 0.75);

	private SpeedController motor;

	public Picker(SpeedController motor) {
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
