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
    	
    	Supplier<Double> movingSpeed = ConstantHandler.addConstantDouble("MoveStraightAndDropGearAuto-movingSpeed", 0.5);
    	Supplier<Double> movingTime = ConstantHandler.addConstantDouble("MoveStraightAndDropGearAuto-movingTime", 3);
    	
        addSequential(new DriveTank(drivetrain, movingSpeed, movingSpeed), movingTime.get());
        addSequential(new DropGear());
    }
}
