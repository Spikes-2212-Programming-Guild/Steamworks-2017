package com.spikes2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.commands.shooting.ShootByDistance;
import com.spikes2212.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class FeedAndShootByDistance extends CommandGroup {
	public static final Supplier<Double> WAIT_TIME = ConstantHandler
			.addConstantDouble("FeedAndShootByDistance-WAIT_TIME", 0.5);

	public FeedAndShootByDistance(Supplier<Double> distance) {
		addParallel(new ShootByDistance(distance));
		addSequential(new WaitCommand(WAIT_TIME.get()));
		addSequential(new MoveLimitedSubsystem(Robot.feeder, Feeder.SPEED));

	}
}
