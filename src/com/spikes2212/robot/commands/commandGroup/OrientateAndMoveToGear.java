package com.spikes2212.robot.commands.commandGroup;

import java.util.function.Supplier;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.commands.orientation.OrientToGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OrientateAndMoveToGear extends CommandGroup {

	public OrientateAndMoveToGear(Supplier<Double> turningSpeed, Supplier<Double> forwardsSpeed) {
		addSequential(new OrientToGear(turningSpeed));
		addSequential(new DriveArcade(Robot.drivetrain, forwardsSpeed, () -> 0.0));
	}
}
