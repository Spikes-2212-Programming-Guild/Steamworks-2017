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
    	
    	Supplier<Double> StartSpeed = ConstantHandler.addConstantDouble("StartSpeed", 0.5);
    	Supplier<Double> StartTime = ConstantHandler.addConstantDouble("StartTime", 3);
    	
    	Supplier<Double> TurnToHopperSpeed = ConstantHandler.addConstantDouble("TurnToHopperSpeed", 0.5);
    	Supplier<Double> MoveToHopperSpeed = ConstantHandler.addConstantDouble("MoveToHopperSpeed", 0.5);
    	Supplier<Double> MoveToHopperTime = ConstantHandler.addConstantDouble("MoveToHopperTime", 3);
    	
    	Supplier<Double> DeltaSpeed = ConstantHandler.addConstantDouble("DeltaSpeed", 0.5);
    	Supplier<Double> DeltaTime = ConstantHandler.addConstantDouble("DeltaTime", 3);
    	
    	Supplier<Double> TurnToBoilerSpeed = ConstantHandler.addConstantDouble("TurnToHopperSpeed", 0.5);
    	Supplier<Double> MoveToBoilerSpeed = ConstantHandler.addConstantDouble("MoveToHopperSpeed", 0.5);
    	Supplier<Double> MoveToBoilerTime = ConstantHandler.addConstantDouble("MoveToHopperTime", 3);
    	
        addSequential(new DriveTank(drivetrain, StartSpeed, StartSpeed), StartTime.get());
        addSequential(new OrientateToHopper(TurnToHopperSpeed));
        addSequential(new DriveTank(drivetrain, MoveToHopperSpeed, MoveToHopperSpeed),MoveToHopperTime.get());
        
        addSequential(new DriveTank(drivetrain, DeltaSpeed, DeltaSpeed), DeltaTime.get());
        addSequential(new OrientateToBoiler(TurnToBoilerSpeed));
        addSequential(new DriveTank(drivetrain, MoveToBoilerSpeed, MoveToBoilerSpeed),MoveToBoilerTime.get());
         
    }
}
