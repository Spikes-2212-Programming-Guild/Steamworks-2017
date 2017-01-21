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
    	
    	Supplier<Double> movingSpeed = ConstantHandler.addConstantDouble("movigSpeed", -0.5);
		Supplier<Double> turningSpeed = ConstantHandler.addConstantDouble("turningSpeed", -0.5);
		Supplier<Double> moveToBoilerTime = ConstantHandler.addConstantDouble("moveToBoilerTime", 1);
		Supplier<Double> movingTime = ConstantHandler.addConstantDouble("movingTime", 4);
		
		addSequential(new DriveTank(drivetrain, movingSpeed, movingSpeed), movingTime.get());
		addSequential(new OrientateToBoiler(turningSpeed));
		addSequential(new DriveTank(drivetrain, movingSpeed, movingSpeed), moveToBoilerTime.get());
		addSequential(new ShootToBoiler());
    }
}
