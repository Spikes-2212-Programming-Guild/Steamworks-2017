package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveTurnLeftAndDropGearAuto extends CommandGroup {

	 public MoveTurnLeftAndDropGearAuto(TankDrivetrain drivetrain) {
	    	Supplier<Double> MovingSpeed = ConstantHandler.addConstantDouble("MovigSpeed", 0.5);
	    	Supplier<Double> TurningSpeed = ConstantHandler.addConstantDouble("TurningSpeed", 0.5);
	    	Supplier<Double> SecendMovementTime = ConstantHandler.addConstantDouble("SecendMovementTime", 1);
	    	Supplier<Double> FirstMovementTime = ConstantHandler.addConstantDouble("FirstMovementTime", 4);
	        addSequential(new DriveTank(drivetrain, MovingSpeed, MovingSpeed), FirstMovementTime.get());
	        addSequential(new OrienateToGear(TurningSpeed.get()));
	        addSequential(new DriveTank(drivetrain, MovingSpeed, MovingSpeed), SecendMovementTime.get());
	    }
}
