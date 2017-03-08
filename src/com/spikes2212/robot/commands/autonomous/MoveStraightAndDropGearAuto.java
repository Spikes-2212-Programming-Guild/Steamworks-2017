package com.spikes2212.robot.commands.autonomous;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.commands.OrientateAndMoveToGear;
import com.spikes2212.robot.commands.orientation.OrientToGear;
import com.spikes2212.robot.subsystems.GearDropper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveStraightAndDropGearAuto extends CommandGroup {
	// Do not forget, gears is BACKWARDS
	public static final Supplier<Double> movingSpeed = ConstantHandler
			.addConstantDouble("MoveStraightAndDropGearAuto-movingSpeed", -0.3);
	public static final Supplier<Double> movingTime = ConstantHandler
			.addConstantDouble("MoveStraightAndDropGearAuto-movingTime", 2);
	public static final Supplier<Double> afterOrientionTime = ConstantHandler
			.addConstantDouble("MoveStraightAndDropGearAuto-afterOrientionTime", 2.7);
	public static final Supplier<Double> reverseTime = ConstantHandler
			.addConstantDouble("MoveStraightAndDropGearAuto-reverseTime", 0.3);
	public static final Supplier<Double> reverseSpeed = ConstantHandler
			.addConstantDouble("MoveStraightAndDropGearAuto-reverseSpeed", 0.3);

	public MoveStraightAndDropGearAuto() {
		addSequential(new DriveTank(Robot.drivetrain, movingSpeed, movingSpeed), movingTime.get());
		addSequential(new OrientToGear(() -> 0.0));
		addSequential(new DriveArcade(Robot.drivetrain, movingSpeed.get(), 0), afterOrientionTime.get());
		addSequential(new MoveLimitedSubsystem(Robot.gearDropper, GearDropper.OPENING_SPEED));
		addParallel(new MoveLimitedSubsystem(Robot.gearDropper, 0));
		addSequential(new DriveArcade(Robot.drivetrain, reverseSpeed, () -> 0.0), reverseTime.get());
	}
}
