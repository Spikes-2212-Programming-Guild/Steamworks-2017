package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.subsystems.Shooter;

import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystemWithPID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootBySpeed extends MoveLimitedSubsystemWithPID {
	private Shooter shooter;

	public ShootBySpeed(Shooter shooter, Supplier<Double> wantedSpeed, double KP, double KI, double KD) {
		this(shooter, wantedSpeed.get(), KP, KI, KD);
	}

	public ShootBySpeed(Shooter shooter, double wantedSpeed, double KP, double KI, double KD) {
		super(shooter, wantedSpeed, KP, KI, KD, 0);
		this.shooter = shooter;
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		super.end();
		shooter.stopShooting();
	}
}
