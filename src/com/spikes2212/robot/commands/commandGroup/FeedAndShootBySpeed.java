package com.spikes2212.robot.commands.commandGroup;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.commands.shooting.ShootBySpeed;
import com.spikes2212.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FeedAndShootBySpeed extends CommandGroup {

	public FeedAndShootBySpeed(Supplier<Double> wantedSpeed) {
		addParallel(new MoveLimitedSubsystem(Robot.feeder, Feeder.SPEED));
		addSequential(new ShootBySpeed(wantedSpeed));
	}

	public FeedAndShootBySpeed(double wantedSpeed) {
		this(() -> wantedSpeed);
	}

}
