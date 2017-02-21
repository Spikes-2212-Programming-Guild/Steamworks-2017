package com.spikes2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.commands.shooting.ShootLinear;
import com.spikes2212.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class FeedAndShootLinear extends CommandGroup {
	public FeedAndShootLinear(Supplier<Double> distance) {
		addParallel(new ShootLinear(distance));
		addSequential(new WaitCommand(FeedAndShootByDistance.WAIT_TIME.get()));
		addSequential(new MoveLimitedSubsystem(Robot.feeder, Feeder.SPEED));
	}
}
