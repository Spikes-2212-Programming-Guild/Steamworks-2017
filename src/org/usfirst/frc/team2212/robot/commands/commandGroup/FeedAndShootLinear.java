package org.usfirst.frc.team2212.robot.commands.commandGroup;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.Robot;
import org.usfirst.frc.team2212.robot.commands.shooting.ShootLinear;
import org.usfirst.frc.team2212.robot.subsystems.Feeder;

import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FeedAndShootLinear extends CommandGroup {
	public FeedAndShootLinear(Supplier<Double> distance) {
		addParallel(new MoveLimitedSubsystem(Robot.feeder, Feeder.SPEED));
		addSequential(new ShootLinear(distance));
	}
}
