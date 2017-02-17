package com.spikes2212.robot.commands.shooting;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystemWithPID;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.subsystems.Shooter;

/**
 *
 */
public class ShootBySpeed extends MoveLimitedSubsystemWithPID {

	public ShootBySpeed(Supplier<Double> wantedSpeed) {
		this(wantedSpeed.get());
	}

	public ShootBySpeed(double wantedSpeed) {
		super(Robot.shooter, wantedSpeed, Shooter.KP.get(), Shooter.KI.get(), Shooter.KD.get(), 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}
}
