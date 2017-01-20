package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveToHopperMoveToBoilerAndShootAuto extends CommandGroup {

    public MoveToHopperMoveToBoilerAndShootAuto(TankDrivetrain drivetrain) {
    	
    	Supplier<Double> startSpeed = ConstantHandler.addConstantDouble("startSpeed", 0.5);
    	Supplier<Double> startTime = ConstantHandler.addConstantDouble("startTime", 3);
    	
    	Supplier<Double> turnToHopperSpeed = ConstantHandler.addConstantDouble("turnToHopperSpeed", 0.5);
    	Supplier<Double> moveToHopperSpeed = ConstantHandler.addConstantDouble("moveToHopperSpeed", 0.5);
    	Supplier<Double> moveToHopperTime = ConstantHandler.addConstantDouble("moveToHopperTime", 3);
    	
    	Supplier<Double> moveFromHopperToBoilerSpeed = ConstantHandler.addConstantDouble("moveFromHopperToBoilerSpeed", 0.5);
    	Supplier<Double> moveFromHopperToBoilerTime = ConstantHandler.addConstantDouble("moveFromHopperToBoilerTime", 3);
    	
    	Supplier<Double> turnToBoilerSpeed = ConstantHandler.addConstantDouble("turnToHopperSpeed", 0.5);
    	Supplier<Double> moveToBoilerSpeed = ConstantHandler.addConstantDouble("toveToHopperSpeed", 0.5);
    	Supplier<Double> moveToBoilerTime = ConstantHandler.addConstantDouble("tSoveToHopperTime", 3);
    	
        addSequential(new DriveTank(drivetrain, startSpeed, startSpeed), startTime.get());
        addSequential(new OrientateToHopper(turnToHopperSpeed));
        addSequential(new DriveTank(drivetrain, moveToHopperSpeed, moveToHopperSpeed),moveToHopperTime.get());
        
        addSequential(new DriveTank(drivetrain, moveFromHopperToBoilerSpeed, moveFromHopperToBoilerSpeed), moveFromHopperToBoilerTime.get());
        addSequential(new OrientateToBoiler(turnToBoilerSpeed));
        addSequential(new DriveTank(drivetrain, moveToBoilerSpeed, moveToBoilerSpeed),moveToBoilerTime.get());
         
    }
}
