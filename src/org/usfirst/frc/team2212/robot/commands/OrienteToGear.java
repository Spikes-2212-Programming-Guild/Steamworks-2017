package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.ImageProssecingConstants;
import org.usfirst.frc.team2212.robot.Robot;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OrienteToGear extends CommandGroup {

	public OrienteToGear(Supplier<Double> rotateSpeedSupplier, double KP, double KI, double KD, double tolerance) {
		addSequential(new OrienteToTwoObjects(rotateSpeedSupplier));
		addSequential(new DriveTankWithPID(Robot.drivetrain, ImageProssecingConstants.leftSource,
				ImageProssecingConstants.rightSource,0,
				0, KP, KI, KD, tolerance));
	}
}
