package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.ImageProcessingConstants;
import org.usfirst.frc.team2212.robot.Robot;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OrientToTwoTargets extends CommandGroup {
	public static PIDSource leftSource = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public double pidGet() {
			return -ImageProcessingConstants.TWO_OBJECTS_CENTER.get();
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};
	public static PIDSource rightSource = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public double pidGet() {
			return ImageProcessingConstants.TWO_OBJECTS_CENTER.get();
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};

	public OrientToTwoTargets(Supplier<Double> rotateSpeedSupplier, double KP, double KI, double KD, double tolerance) {
		addSequential(new TurnToTwoTargets(rotateSpeedSupplier));
		addSequential(new DriveTankWithPID(Robot.drivetrain, leftSource,
				rightSource, 0, 0, KP, KI, KD, tolerance));
	}
}
