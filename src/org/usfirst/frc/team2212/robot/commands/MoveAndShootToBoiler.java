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
    	
    	Supplier<Double> movingSpeedMoveAndShootToBoiler = ConstantHandler.addConstantDouble("movingSpeedMoveAndShootToBoiler", 0.5);
		Supplier<Double> turningSpeedMoveAndShootToBoiler = ConstantHandler.addConstantDouble("turningSpeedMoveAndShootToBoiler", 0.5);
		Supplier<Double> moveToBoilerTimeMoveAndShootToBoiler = ConstantHandler.addConstantDouble("moveToBoilerTimeMoveAndShootToBoiler", 1);
		Supplier<Double> movingTimeMoveAndShootToBoiler = ConstantHandler.addConstantDouble("movingTimeMoveAndShootToBoiler", 4);
		
		addSequential(new DriveTank(drivetrain, movingSpeedMoveAndShootToBoiler, movingSpeedMoveAndShootToBoiler), movingTimeMoveAndShootToBoiler.get());
		addSequential(new OrientateToBoiler(turningSpeedMoveAndShootToBoiler));
		addSequential(new DriveTank(drivetrain, movingSpeedMoveAndShootToBoiler, movingSpeedMoveAndShootToBoiler), moveToBoilerTimeMoveAndShootToBoiler.get());
		addSequential(new ShootToBoiler());
    }
}
