package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.Robot;
import org.usfirst.frc.team2212.robot.subsystems.GearDropper;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveTurnRightAndDropGearAuto extends CommandGroup {

	public static final Supplier<Double> turningSpeed = ConstantHandler
			.addConstantDouble("MoveTurnRightAndDropGearAuto-turningSpeed", 0.5);
	public static final Supplier<Double> movingTime = ConstantHandler
			.addConstantDouble("MoveTurnRightAndDropGearAuto-movingTime", 4);
	public static final Supplier<Double> moveToGearTime = ConstantHandler
			.addConstantDouble("MoveTurnLeftAndDropGearAuto-moveToGearTime", 1);

	public MoveTurnRightAndDropGearAuto() {
		addSequential(new DriveTank(Robot.drivetrain, MoveStraightAndDropGearAuto.movingSpeed,
				MoveStraightAndDropGearAuto.movingSpeed), movingTime.get());
		addSequential(new OrientateAndMoveToGear(turningSpeed, MoveStraightAndDropGearAuto.movingSpeed),
				moveToGearTime.get());
		addSequential(new MoveLimitedSubsystem(Robot.gearDropper, GearDropper.OPEN_SPEED));
	}
}
