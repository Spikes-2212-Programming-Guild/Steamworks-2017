package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.Robot;
import org.usfirst.frc.team2212.robot.subsystems.Shooter;

import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystemWithPID;

/**
 *
 */
public class ShootByVoltage extends MoveLimitedSubsystem {

	public ShootByVoltage(Supplier<Double> wantedVoltage) {
		super(Robot.shooter, wantedVoltage);
	}

	public ShootByVoltage(double wantedSpeed) {
		this(() -> wantedSpeed);
	}
}
