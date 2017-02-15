package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.ImageProcessingConstants;
import org.usfirst.frc.team2212.robot.Robot;
import org.usfirst.frc.team2212.robot.subsystems.Feeder;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveDropGearMoveToBoilerAndShootAuto extends CommandGroup {

	public static final Supplier<Double> turnToBoilerSpeed = ConstantHandler
			.addConstantDouble("MoveDropGearMoveToBoilerAndShootAuto-turnToBoilerSpeed", 0.5);
	public static final Supplier<Double> moveToBoilerSpeed = ConstantHandler
			.addConstantDouble("MoveDropGearMoveToBoilerAndShootAuto-moveToBoilerSpeed", 0.5);
	public static final Supplier<Double> moveToBoilerTime = ConstantHandler
			.addConstantDouble("MoveDropGearMoveToBoilerAndShootAuto-moveToBoilerTime", 3);

	public MoveDropGearMoveToBoilerAndShootAuto() {

		addSequential(new MoveTurnLeftAndDropGearAuto()); // Moves Turns Left
															// And Drops Gear
		// moves to the boiler turns to it and shoots
		addSequential(new DriveTank(Robot.drivetrain, moveToBoilerSpeed, moveToBoilerSpeed), moveToBoilerTime.get());
		addSequential(new OrientToBoiler(turnToBoilerSpeed));
		addParallel(new MoveLimitedSubsystem(Robot.feeder, Feeder.SPEED));
		addSequential(new ShootByDistance(ImageProcessingConstants.distanceToBoiler));
	}
}
