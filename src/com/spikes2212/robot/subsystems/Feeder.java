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
	private SpeedController blender;
	public static final Supplier<Double> SPEED = ConstantHandler.addConstantDouble("Feeder-SPEED", 0.1);
	public static final Supplier<Double> BLENDER_SPEED = ConstantHandler.addConstantDouble("Feeder-BLENDER_SPEED",
			0.31);

	public Feeder(SpeedController motor, SpeedController blender) {
		this.motor = motor;
		this.blender = blender;
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
		blender.set(BLENDER_SPEED.get());
	}

	@Override
	public void stop() {
		super.stop();
		blender.set(0);
	}
}
