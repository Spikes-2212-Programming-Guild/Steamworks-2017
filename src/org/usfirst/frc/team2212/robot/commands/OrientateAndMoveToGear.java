package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.Robot;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;

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
