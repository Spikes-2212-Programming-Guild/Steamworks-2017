package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.Robot;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OrientateAndMoveToGear extends CommandGroup {
	
	public static final Supplier<Double> movingSpeed = ConstantHandler
			.addConstantDouble("OrientateAndMoveToGear-movingSpeed", 0.5);
	public static final Supplier<Double> moveToGearTime = ConstantHandler
			.addConstantDouble(" OrientateAndMoveToGear-moveToGearTime", 1);

    public OrientateAndMoveToGear(Supplier<Double> turningSpeed) {
    	addSequential(new OrientToGear(turningSpeed));
		addSequential(new DriveTank(Robot.drivetrain, movingSpeed, movingSpeed), moveToGearTime.get());
    }
}
