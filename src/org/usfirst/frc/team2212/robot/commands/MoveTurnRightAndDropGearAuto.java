package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveTurnRightAndDropGearAuto extends CommandGroup {

	public MoveTurnRightAndDropGearAuto(TankDrivetrain drivetrain) {
		Supplier<Double> movingSpeedMoveTurnRightAndDropGearAuto = ConstantHandler.addConstantDouble("movingSpeedMoveTurnRightAndDropGearAuto", 0.5);
		Supplier<Double> turningSpeedMoveTurnRightAndDropGearAuto = ConstantHandler.addConstantDouble("turningSpeedMoveTurnRightAndDropGearAuto", 0.5);
		Supplier<Double> moveToGearTimeMoveTurnRightAndDropGearAuto = ConstantHandler.addConstantDouble("moveToGearTimeMoveTurnRightAndDropGearAuto", 1);
		Supplier<Double> movingTimeMoveTurnRightAndDropGearAuto = ConstantHandler.addConstantDouble("movingTimeMoveTurnRightAndDropGearAuto", 4);
		
		addSequential(new DriveTank(drivetrain, movingSpeedMoveTurnRightAndDropGearAuto, movingSpeedMoveTurnRightAndDropGearAuto), movingTimeMoveTurnRightAndDropGearAuto.get());
		addSequential(new OrienateToGear(turningSpeedMoveTurnRightAndDropGearAuto));
		addSequential(new DriveTank(drivetrain, movingSpeedMoveTurnRightAndDropGearAuto, movingSpeedMoveTurnRightAndDropGearAuto), moveToGearTimeMoveTurnRightAndDropGearAuto.get());
		addSequential(new DropGear());
	}
}
