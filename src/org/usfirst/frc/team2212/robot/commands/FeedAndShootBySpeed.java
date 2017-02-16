package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.Robot;
import org.usfirst.frc.team2212.robot.subsystems.Feeder;

import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FeedAndShootBySpeed extends CommandGroup {

	public FeedAndShootBySpeed(Supplier<Double> wantedSpeed) {
		addParallel(new MoveLimitedSubsystem(Robot.feeder, Feeder.feedingSpeed));
		addSequential(new ShootBySpeed(wantedSpeed));
	}

	public FeedAndShootBySpeed(double wantedSpeed) {
		this(() -> wantedSpeed);
	}

}
