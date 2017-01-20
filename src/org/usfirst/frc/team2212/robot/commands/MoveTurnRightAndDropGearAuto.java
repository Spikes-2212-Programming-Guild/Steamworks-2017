package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveTurnRightAndDropGearAuto extends CommandGroup {

	public MoveTurnRightAndDropGearAuto(TankDrivetrain drivetrain) {
		Supplier<Double> movingSpeed = ConstantHandler.addConstantDouble("movigSpeed", 0.5);
		Supplier<Double> turningSpeed = ConstantHandler.addConstantDouble("turningSpeed", -0.5);
		Supplier<Double> moveToGearTime = ConstantHandler.addConstantDouble("moveToGearTime", 1);
		Supplier<Double> movingTime = ConstantHandler.addConstantDouble("movingTime", 4);
		
		addSequential(new DriveTank(drivetrain, movingSpeed, movingSpeed), movingTime.get());
		addSequential(new OrienateToGear(turningSpeed));
		addSequential(new DriveTank(drivetrain, movingSpeed, movingSpeed), moveToGearTime.get());
		addSequential(new DropGear());
	}
}
