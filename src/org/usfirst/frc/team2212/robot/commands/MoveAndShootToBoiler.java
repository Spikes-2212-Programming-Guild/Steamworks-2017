package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveAndShootToBoiler extends CommandGroup {

    public MoveAndShootToBoiler(TankDrivetrain drivetrain) {
    	
    	Supplier<Double> movingSpeed = ConstantHandler.addConstantDouble("MoveAndShootToBoiler-movingSpeed", 0.5);
		Supplier<Double> turningSpeed = ConstantHandler.addConstantDouble("MoveAndShootToBoiler-turningSpeed", 0.5);
		Supplier<Double> movingToBoilerTime = ConstantHandler.addConstantDouble("MoveAndShootToBoiler-movingToBoilerTime", 1);
		Supplier<Double> movingTime = ConstantHandler.addConstantDouble("MoveAndShootToBoiler-movingTime", 4);
		
		addSequential(new DriveTank(drivetrain, movingSpeed, movingSpeed), movingTime.get());
		addSequential(new OrientToBoiler(turningSpeed));
		addSequential(new DriveTank(drivetrain, movingSpeed, movingSpeed), movingToBoilerTime.get());
		addSequential(new ShootToBoiler());
    }
}
