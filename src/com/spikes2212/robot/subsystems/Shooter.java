package com.spikes2212.robot.subsystems;

import java.util.function.Supplier;

import com.ctre.CANTalon;
import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.LimitedSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 *
 */
public class Shooter extends LimitedSubsystem {

	public static final Supplier<Double> acceleration = ConstantHandler.addConstantDouble("Shooter-acceleration", 0.01);
	private CANTalon motor;
	private Encoder encoder;
	public static final Supplier<Double> KP = ConstantHandler.addConstantDouble("Shooter KP", 1);
	public static final Supplier<Double> KI = ConstantHandler.addConstantDouble("Shooter KI", 0.1);
	public static final Supplier<Double> KD = ConstantHandler.addConstantDouble("Shooter KD", 0.1);
	public static final double DISTANCE_PER_PULSE = 4 * Math.PI / 20; // 20 pulses per revolution,4 inch wheel

	private double speed;

	public Shooter(CANTalon motor, Encoder encoder) {
		encoder.setPIDSourceType(PIDSourceType.kRate);
		encoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		this.encoder = encoder;
		this.motor = motor;
		motor.setInverted(true);
		speed = 0;
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
		return encoder;
	}

	@Override
	protected void move(double additionalSpeed) {
		speed += additionalSpeed * acceleration.get();
		motor.set(speed);
	}

	@Override
	public void stop() {
		motor.set(0);
	}
}
