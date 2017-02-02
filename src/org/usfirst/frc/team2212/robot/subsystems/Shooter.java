package org.usfirst.frc.team2212.robot.subsystems;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.LimitedSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.TalonSRX;

/**
 *
 */
public class Shooter extends LimitedSubsystem {

	private TalonSRX motor;
	private Encoder encoder;
	private Supplier<Double> distancePerPulse = ConstantHandler.addConstantDouble("distancePerPulse", 1); //TODO check the real value

	public Shooter(TalonSRX motor, Encoder encoder) {
		this.encoder = encoder;
		this.motor = motor;
		encoder.setPIDSourceType(PIDSourceType.kRate);
		encoder.setDistancePerPulse(distancePerPulse.get());
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
	protected void move(double speed) {
		motor.set(speed);
	}
}
