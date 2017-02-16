package com.spikes2212.robot.commands.commandGroup;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.commands.shooting.ShootByDistance;
import com.spikes2212.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FeedAndShootByDistance extends CommandGroup {

	public FeedAndShootByDistance(Supplier<Double> distance) {
		addParallel(new MoveLimitedSubsystem(Robot.feeder, Feeder.SPEED));
		addSequential(new ShootByDistance(distance));
	}
}
