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
    	Supplier<Double> speed = ConstantHandler.addConstantDouble("speed", 0.5);
    	Supplier<Double> time = ConstantHandler.addConstantDouble("time", 3);
    	
    	Supplier<Double> moveToGearSpeed = ConstantHandler.addConstantDouble("moveToGearSpeed", 0.5);
    	Supplier<Double> moveToGearTime = ConstantHandler.addConstantDouble("moveToGearTime", 3);
    	
    	Supplier<Double> turnToBoilerSpeed = ConstantHandler.addConstantDouble("turnToBoilerSpeed", 0.5);
    	Supplier<Double> moveToBoilerSpeed = ConstantHandler.addConstantDouble("moveToBoilerSpeed", 0.5);
    	Supplier<Double> moveToBoilerTime = ConstantHandler.addConstantDouble("msoveToBoilerTime", 3);
    	
        addSequential(new DriveTank(drivetrain, speed, speed), time.get());
        addSequential(new DropGear());
        
        addSequential(new DriveTank(drivetrain, moveToGearSpeed, moveToGearSpeed), moveToGearSpeed.get());
        addSequential(new OrientateToBoiler(turnToBoilerSpeed));
        addSequential(new DriveTank(drivetrain, moveToBoilerSpeed, moveToBoilerSpeed),moveToBoilerTime.get());
    }
}
