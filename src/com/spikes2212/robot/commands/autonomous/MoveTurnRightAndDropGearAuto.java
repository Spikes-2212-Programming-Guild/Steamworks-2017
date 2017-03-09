package com.spikes2212.robot.commands.autonomous;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.commands.orientation.OrientToGear;
import com.spikes2212.robot.subsystems.GearDropper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveTurnRightAndDropGearAuto extends CommandGroup {

	public static final Supplier<Double> turningSpeed = ConstantHandler
			.addConstantDouble("MoveTurnRightAndDropGearAuto-turningSpeed", -0.35);
	public static final Supplier<Double> movingStraightTime = ConstantHandler
			.addConstantDouble("MoveTurnRightAndDropGearAuto-movingTime", 2.8);
	public static final Supplier<Double> moveToGearTime = ConstantHandler
			.addConstantDouble("MoveTurnRightAndDropGearAuto-moveToGearTime", 4);

	public MoveTurnRightAndDropGearAuto() {
		addSequential(new DriveTank(Robot.drivetrain, MoveStraightAndDropGearAuto.movingSpeed,
				MoveStraightAndDropGearAuto.movingSpeed), movingStraightTime.get());
		addSequential(new OrientToGear(turningSpeed));
		addSequential(new DriveArcade(Robot.drivetrain, MoveStraightAndDropGearAuto.movingSpeed, () -> 0.0),
				moveToGearTime.get());
		addSequential(new MoveLimitedSubsystem(Robot.gearDropper, GearDropper.OPENING_SPEED));
		addParallel(new MoveLimitedSubsystem(Robot.gearDropper, 0));
		addSequential(new DriveArcade(Robot.drivetrain, MoveStraightAndDropGearAuto.reverseSpeed, () -> 0.0),
				MoveStraightAndDropGearAuto.reverseTime.get());
	}
}
