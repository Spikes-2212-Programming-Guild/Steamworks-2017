package com.spikes2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.commands.shooting.ShootBySpeed;
import com.spikes2212.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class FeedAndShootBySpeed extends CommandGroup {

	public FeedAndShootBySpeed(Supplier<Double> wantedSpeed) {
		addParallel(new ShootBySpeed(wantedSpeed));
		addSequential(new WaitCommand(FeedAndShootByDistance.WAIT_TIME.get()));
		addSequential(new MoveLimitedSubsystem(Robot.feeder, Feeder.SPEED));
	}

	public FeedAndShootBySpeed(double wantedSpeed) {
		this(() -> wantedSpeed);
	}

}
