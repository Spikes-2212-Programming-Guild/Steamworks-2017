package com.spikes2212.robot.commands.autonomous;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.robot.ImageProcessingConstants;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.commands.FeedAndShootByDistance;
import com.spikes2212.robot.commands.orientation.OrientToBoiler;
import com.spikes2212.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveAndShootToBoilerAuto extends CommandGroup {

	public static final Supplier<Double> movingSpeed = ConstantHandler
			.addConstantDouble("MoveAndShootToBoiler-movingSpeed", 0.5);
	public static final Supplier<Double> turningSpeed = ConstantHandler
			.addConstantDouble("MoveAndShootToBoiler-turningSpeed", 0.5);
	public static final Supplier<Double> movingToBoilerTime = ConstantHandler.addConstantDouble("MoveToBoilerTime",
			0.5);
	public static final Supplier<Double> movingTime = ConstantHandler
			.addConstantDouble("MoveAndShootToBoiler-movingTime", 4);

	public MoveAndShootToBoilerAuto() {
		addSequential(new DriveTank(Robot.drivetrain, movingSpeed, movingSpeed), movingTime.get());
		addSequential(new OrientToBoiler(turningSpeed));
		addSequential(new DriveTank(Robot.drivetrain, movingSpeed, movingSpeed), movingToBoilerTime.get());
		addSequential(new FeedAndShootByDistance(ImageProcessingConstants.distanceToBoiler));
	}
}
