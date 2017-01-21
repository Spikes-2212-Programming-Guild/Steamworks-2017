package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveStraightAndDropGearAuto extends CommandGroup {

    public MoveStraightAndDropGearAuto(TankDrivetrain drivetrain) {
    	
    	Supplier<Double> movingSpeedMoveStraightAndDropGearAuto = ConstantHandler.addConstantDouble("movingSpeedMoveStraightAndDropGearAuto", 0.5);
    	Supplier<Double> movingTimeMoveStraightAndDropGearAuto = ConstantHandler.addConstantDouble("movingTimeMoveStraightAndDropGearAuto", 3);
    	
        addSequential(new DriveTank(drivetrain, movingSpeedMoveStraightAndDropGearAuto, movingSpeedMoveStraightAndDropGearAuto), movingTimeMoveStraightAndDropGearAuto.get());
        addSequential(new DropGear());
    }
}
