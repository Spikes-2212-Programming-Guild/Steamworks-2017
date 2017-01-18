package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveAndDropGearAuto extends CommandGroup {

    public MoveAndDropGearAuto(TankDrivetrain drivetrain) {
    	Supplier<Double> Speed = ConstantHandler.addConstantDouble("Speed", 0.5);
    	Supplier<Double> Time = ConstantHandler.addConstantDouble("Time", 3);
    	
        addSequential(new DriveTank(drivetrain, Speed, Speed), Time.get());
        addSequential(new DropGear());
    }
}
