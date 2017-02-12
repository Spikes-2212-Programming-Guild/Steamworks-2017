package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.ImageProcessingConstants;
import org.usfirst.frc.team2212.robot.Robot;
import org.usfirst.frc.team2212.robot.subsystems.GearDropper;
import org.usfirst.frc.team2212.robot.subsystems.Shooter;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveDropGearMoveToBoilerAndShootAuto extends CommandGroup {

	public static final Supplier<Double> movingSpeed = ConstantHandler
			.addConstantDouble("MoveDropGearMoveToBoilerAndShootAuto-movingSpeed", 0.5);
	public static final Supplier<Double> movingTime = ConstantHandler
			.addConstantDouble("MoveDropGearMoveToBoilerAndShootAuto-movingTime", 3);

	public static final Supplier<Double> turnToGearSpeed = ConstantHandler
			.addConstantDouble("MoveDropGearMoveToBoilerAndShootAuto-turnToGearSpeed", 3);

	public static final Supplier<Double> turnToBoilerSpeed = ConstantHandler
			.addConstantDouble("MoveDropGearMoveToBoilerAndShootAuto-turnToBoilerSpeed", 0.5);
	public static final Supplier<Double> moveToBoilerSpeed = ConstantHandler
			.addConstantDouble("DropGearMoveToBoilerAndShootAuto-moveToBoilerSpeedMove", 0.5);
	public static final Supplier<Double> moveToBoilerTime = ConstantHandler
			.addConstantDouble("MoveDropGearMoveToBoilerAndShootAuto-moveToBoilerTime", 3);

	public MoveDropGearMoveToBoilerAndShootAuto() {

		addSequential(new DriveTank(Robot.drivetrain, movingSpeed, movingSpeed), movingTime.get());
		addSequential(new OrientToGear(turnToGearSpeed));
		addSequential(new MoveLimitedSubsystem(Robot.gearDropper, GearDropper.OPEN_SPEED));

		addSequential(new DriveTank(Robot.drivetrain, moveToBoilerSpeed, moveToBoilerSpeed), moveToBoilerTime.get());
		addSequential(new OrientToBoiler(turnToBoilerSpeed));
		addSequential(new ShootByDistance(Robot.shooter, ImageProcessingConstants.distance, Shooter.KP, Shooter.KI,
				Shooter.KD));
	}
}
