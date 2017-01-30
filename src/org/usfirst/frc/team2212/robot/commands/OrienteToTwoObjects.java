package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.ImageProssecingConstants;
import org.usfirst.frc.team2212.robot.Robot;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;

public class OrienteToTwoObjects extends DriveArcade {

	public OrienteToTwoObjects(Supplier<Double> rotateSpeedSupplier) {
		super(Robot.drivetrain, () -> 0.0, rotateSpeedSupplier);

	}

	@Override
	protected boolean isFinished() {
		return ImageProssecingConstants.NETWORK_TABLE.getBoolean("isUpdated1", false);
	}
}
