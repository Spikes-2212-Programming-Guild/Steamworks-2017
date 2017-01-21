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
    	Supplier<Double> movingSpeedMoveDropGearMoveToBoilerAndShootAuto = ConstantHandler.addConstantDouble("movingSpeedMoveDropGearMoveToBoilerAndShootAuto", 0.5);
    	Supplier<Double> movingTimeMoveDropGearMoveToBoilerAndShootAuto = ConstantHandler.addConstantDouble("movingTimeMoveDropGearMoveToBoilerAndShootAuto", 3);
    	
    	Supplier<Double> turnToGearSpeedMoveDropGearMoveToBoilerAndShootAuto = ConstantHandler.addConstantDouble("turnToGearSpeedMoveDropGearMoveToBoilerAndShootAuto", 3);
    	
    	Supplier<Double> turnToBoilerSpeedMoveDropGearMoveToBoilerAndShootAuto = ConstantHandler.addConstantDouble("turnToBoilerSpeedMoveDropGearMoveToBoilerAndShootAuto", 0.5);
    	Supplier<Double> moveToBoilerSpeedMoveDropGearMoveToBoilerAndShootAuto = ConstantHandler.addConstantDouble("moveToBoilerSpeedMoveDropGearMoveToBoilerAndShootAuto", 0.5);
    	Supplier<Double> moveToBoilerTimeMoveDropGearMoveToBoilerAndShootAuto = ConstantHandler.addConstantDouble("moveToBoilerTimeMoveDropGearMoveToBoilerAndShootAuto", 3);
    	
        addSequential(new DriveTank(drivetrain, movingSpeedMoveDropGearMoveToBoilerAndShootAuto, movingSpeedMoveDropGearMoveToBoilerAndShootAuto), movingTimeMoveDropGearMoveToBoilerAndShootAuto.get());
        addSequential(new OrienateToGear(turnToGearSpeedMoveDropGearMoveToBoilerAndShootAuto));
        addSequential(new DropGear());
        
        addSequential(new DriveTank(drivetrain, moveToBoilerSpeedMoveDropGearMoveToBoilerAndShootAuto, moveToBoilerSpeedMoveDropGearMoveToBoilerAndShootAuto), moveToBoilerTimeMoveDropGearMoveToBoilerAndShootAuto.get());
        addSequential(new OrientateToBoiler(turnToBoilerSpeedMoveDropGearMoveToBoilerAndShootAuto));
        addSequential(new ShootToBoiler());
    }
}
