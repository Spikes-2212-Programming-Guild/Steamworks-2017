package com.spikes2212.robot.commands.orientation;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.ImageProcessingConstants;
import com.spikes2212.robot.Robot;

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
