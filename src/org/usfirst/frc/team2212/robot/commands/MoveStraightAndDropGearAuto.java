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
    	
    	Supplier<Double> speed = ConstantHandler.addConstantDouble("speed", 0.5);
    	Supplier<Double> time = ConstantHandler.addConstantDouble("time", 3);
    	
        addSequential(new DriveTank(drivetrain, speed, speed), time.get());
        addSequential(new DropGear());
    }
}
