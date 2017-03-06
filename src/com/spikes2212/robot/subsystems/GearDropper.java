package com.spikes2212.robot.subsystems;


import java.util.function.Supplier;


import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.LimitedSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;

import com.spikes2212.robot.Robot;
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
	public static final Supplier<Double> OPENING_SPEED = ConstantHandler.addConstantDouble("GearDropper-OPENING_SPEED",
			0.8);
	public static final Supplier<Double> CLOSING_SPEED = ConstantHandler.addConstantDouble("GearDropper-CLOSING_SPEED",
			-0.8);

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
	
	@Override
	protected void initDefaultCommand(){
		setDefaultCommand(new MoveLimitedSubsystem(Robot.gearDropper, CLOSING_SPEED));
	}
}
