package org.usfirst.frc.team2212.robot.subsystems;

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
	
	private Supplier<Double> acceleration = ConstantHandler.addConstantDouble("acceleration" , 0.01);
	private CANTalon motor;
	private Encoder encoder;
	public static final double DISTANCE_PER_PULSE = 1; // TODO check the real
														// value
	private double speed;

	public Shooter(CANTalon motor, Encoder encoder) {
		encoder.setPIDSourceType(PIDSourceType.kRate);
		encoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		this.encoder = encoder;
		this.motor = motor;
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

	public void stopShooting() {
		motor.set(0);
	}
}
