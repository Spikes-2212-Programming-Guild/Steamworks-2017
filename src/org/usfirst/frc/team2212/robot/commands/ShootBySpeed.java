package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.Robot;
import org.usfirst.frc.team2212.robot.subsystems.Shooter;

import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystemWithPID;

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
