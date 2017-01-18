package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveDropGearMoveToBoilerAndShootAuto extends CommandGroup {

    public MoveDropGearMoveToBoilerAndShootAuto(TankDrivetrain drivetrain) {
    	Supplier<Double> Speed = ConstantHandler.addConstantDouble("Speed", 0.5);
    	Supplier<Double> Time = ConstantHandler.addConstantDouble("Time", 3);
    	
    	Supplier<Double> SecondSpeed = ConstantHandler.addConstantDouble("SecondSpeed", 0.5);
    	Supplier<Double> SecondTime = ConstantHandler.addConstantDouble("SecondTime", 3);
    	
    	Supplier<Double> TurnToBoilerSpeed = ConstantHandler.addConstantDouble("TurnToBoilerSpeed", 0.5);
    	Supplier<Double> MoveToBoilerSpeed = ConstantHandler.addConstantDouble("MoveToBoilerSpeed", 0.5);
    	Supplier<Double> MoveToBoilerTime = ConstantHandler.addConstantDouble("MoveToBoilerTime", 3);
    	
        addSequential(new DriveTank(drivetrain, Speed, Speed), Time.get());
        addSequential(new DropGear());
        
        addSequential(new DriveTank(drivetrain, SecondSpeed, SecondSpeed), SecondTime.get());
        addSequential(new OrientateToBoiler(TurnToBoilerSpeed));
        addSequential(new DriveTank(drivetrain, MoveToBoilerSpeed, MoveToBoilerSpeed),MoveToBoilerTime.get());
    }
}
