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
	    	Supplier<Double> movingSpeedMoveTurnLeftAndDropGearAuto = ConstantHandler.addConstantDouble("movingSpeedMoveTurnLeftAndDropGearAuto", -0.5);
	    	Supplier<Double> turningSpeedMoveTurnLeftAndDropGearAuto = ConstantHandler.addConstantDouble("turningSpeedMoveTurnLeftAndDropGearAuto", -0.5);
	    	Supplier<Double> moveToGearTimeMoveTurnLeftAndDropGearAuto = ConstantHandler.addConstantDouble("moveToGearTimeMoveTurnLeftAndDropGearAuto", 1);
	    	Supplier<Double> movingTimeMoveTurnLeftAndDropGearAuto = ConstantHandler.addConstantDouble("movingTimeMoveTurnLeftAndDropGearAuto", 4);
	      
	    	addSequential(new DriveTank(drivetrain, movingSpeedMoveTurnLeftAndDropGearAuto, movingSpeedMoveTurnLeftAndDropGearAuto), movingTimeMoveTurnLeftAndDropGearAuto.get());
	        addSequential(new OrienateToGear(turningSpeedMoveTurnLeftAndDropGearAuto));
	        addSequential(new DriveTank(drivetrain, movingSpeedMoveTurnLeftAndDropGearAuto, movingSpeedMoveTurnLeftAndDropGearAuto), moveToGearTimeMoveTurnLeftAndDropGearAuto.get());
	        addSequential(new DropGear());
	    }
}
