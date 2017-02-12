package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.ImageProcessingConstants;
import org.usfirst.frc.team2212.robot.Robot;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;

import edu.wpi.first.wpilibj.Timer;

public class TurnToTwoTargets extends DriveArcade {
	private double lastTimeNotOnTarget = Timer.getFPGATimestamp();
	public static final Supplier<Double> WAIT_TIME = ConstantHandler.addConstantDouble("TurnToTwoTargets-WAIT_TIME",
			0.25);

	public TurnToTwoTargets(Supplier<Double> rotateSpeedSupplier) {
		super(Robot.drivetrain, () -> 0.0, rotateSpeedSupplier);

	}

	@Override
	protected boolean isFinished() {
		boolean state = ImageProcessingConstants.NETWORK_TABLE.getBoolean("isUpdated1", false);
		if (!state) {
			lastTimeNotOnTarget = Timer.getFPGATimestamp();
		}
		return Timer.getFPGATimestamp() - lastTimeNotOnTarget >= WAIT_TIME.get();
	}
}
