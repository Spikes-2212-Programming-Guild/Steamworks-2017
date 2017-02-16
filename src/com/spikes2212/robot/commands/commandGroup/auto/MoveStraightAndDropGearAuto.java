package com.spikes2212.robot.commands.commandGroup.auto;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.subsystems.GearDropper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveStraightAndDropGearAuto extends CommandGroup {

	public static final Supplier<Double> movingSpeed = ConstantHandler
			.addConstantDouble("MoveStraightAndDropGearAuto-movingSpeed", 0.5);
	public static final Supplier<Double> movingTime = ConstantHandler
			.addConstantDouble("MoveStraightAndDropGearAuto-movingTime", 3);

	public MoveStraightAndDropGearAuto() {
		addSequential(new DriveTank(Robot.drivetrain, movingSpeed, movingSpeed), movingTime.get());
		addSequential(new MoveLimitedSubsystem(Robot.gearDropper, GearDropper.OPENING_SPEED));
	}
}
