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
    	Supplier<Double> movingSpeed = ConstantHandler.addConstantDouble("MoveDropGearMoveToBoilerAndShootAuto-movingSpeed", 0.5);
    	Supplier<Double> movingTime = ConstantHandler.addConstantDouble("MoveDropGearMoveToBoilerAndShootAuto-movingTime", 3);
    	
    	Supplier<Double> turnToGearSpeed = ConstantHandler.addConstantDouble("MoveDropGearMoveToBoilerAndShootAuto-turnToGearSpeed", 3);
    	
    	Supplier<Double> turnToBoilerSpeed = ConstantHandler.addConstantDouble("MoveDropGearMoveToBoilerAndShootAuto-turnToBoilerSpeed", 0.5);
    	Supplier<Double> moveToBoilerSpeed = ConstantHandler.addConstantDouble("DropGearMoveToBoilerAndShootAuto-moveToBoilerSpeedMove", 0.5);
    	Supplier<Double> moveToBoilerTime = ConstantHandler.addConstantDouble("MoveDropGearMoveToBoilerAndShootAuto-moveToBoilerTime", 3);
    	
        addSequential(new DriveTank(drivetrain, movingSpeed, movingSpeed), movingTime.get());
        addSequential(new OrientToGear(turnToGearSpeed));
        addSequential(new DropGear());
        
        addSequential(new DriveTank(drivetrain, moveToBoilerSpeed, moveToBoilerSpeed), moveToBoilerTime.get());
        addSequential(new OrientToBoiler(turnToBoilerSpeed));
        addSequential(new ShootToBoiler());
    }
}
