package org.usfirst.frc.team2212.robot.commands.orientation;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.ImageProcessingConstants;
import org.usfirst.frc.team2212.robot.Robot;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OrientToTwoTargets extends CommandGroup {

	public static final Supplier<Double> KP = ConstantHandler.addConstantDouble("OrientToTwoObjects-KP", 0);
	public static final Supplier<Double> KI = ConstantHandler.addConstantDouble("OrientToTwoObjects-KI", 0);
	public static final Supplier<Double> KD = ConstantHandler.addConstantDouble("OrientToTwoObjects-KD", 0);
	public static final Supplier<Double> TOLERANCE = ConstantHandler.addConstantDouble("OrientToTwoObjects-TOLERANCE",
			0);

	public OrientToTwoTargets(Supplier<Double> rotateSpeedSupplier, double KP, double KI, double KD, double tolerance) {
		addSequential(new TurnToTwoTargets(rotateSpeedSupplier));
		addSequential(new DriveTankWithPID(Robot.drivetrain, leftOrientationSource, rightOrientationSource, 0, 0, KP,
				KI, KD, tolerance));
	}

	private static PIDSource leftOrientationSource = new PIDSource() {

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
	private static PIDSource rightOrientationSource = new PIDSource() {

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
}
