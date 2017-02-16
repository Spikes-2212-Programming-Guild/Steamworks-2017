package com.spikes2212.robot.commands.commandGroup;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.commands.shooting.ShootLinear;
import com.spikes2212.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FeedAndShootLinear extends CommandGroup {
	public FeedAndShootLinear(Supplier<Double> distance) {
		addParallel(new MoveLimitedSubsystem(Robot.feeder, Feeder.SPEED));
		addSequential(new ShootLinear(distance));
	}
}
