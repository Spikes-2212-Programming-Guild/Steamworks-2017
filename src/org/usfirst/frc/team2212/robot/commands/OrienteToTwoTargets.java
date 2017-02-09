package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.ImageProcessingConstants;
import org.usfirst.frc.team2212.robot.Robot;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OrienteToTwoTargets extends CommandGroup {

	public OrienteToTwoTargets(Supplier<Double> rotateSpeedSupplier, double KP, double KI, double KD, double tolerance) {
		addSequential(new OrienteToTwoObjects(rotateSpeedSupplier));
		addSequential(new DriveTankWithPID(Robot.drivetrain, ImageProcessingConstants.leftSource,
				ImageProcessingConstants.rightSource, 0, 0, KP, KI, KD, tolerance));
	}
}
