package org.usfirst.frc.team2212.robot.subsystems;


import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.LimitedSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearDropper extends LimitedSubsystem {

	private SpeedController motor;
	private DigitalInput open;
	private DigitalInput close;
	public static final Supplier<Double> OPEN_SPEED = ConstantHandler.addConstantDouble("GEAR-DROPER-OPEN-SPEED", 0.2);
	public static final Supplier<Double> CLOSING_SPEED = ConstantHandler.addConstantDouble("GEAR-DROPPER-CLOSING-SPEED",
			-0.2);

	public GearDropper(SpeedController motor, DigitalInput open, DigitalInput close) {
		this.open = open;
		this.close = close;
		this.motor = motor;
	}

	@Override
	public boolean isMin() {
		return !close.get();
	}

	@Override
	public boolean isMax() {
		return !open.get();
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
