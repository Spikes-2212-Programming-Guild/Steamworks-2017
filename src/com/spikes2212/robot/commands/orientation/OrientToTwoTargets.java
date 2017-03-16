package com.spikes2212.robot.commands.orientation;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.robot.ImageProcessingConstants;
import com.spikes2212.robot.Robot;
import com.spikes2212.utils.RunnableCommand;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OrientToTwoTargets extends CommandGroup {

	public static final Supplier<Double> KP = ConstantHandler.addConstantDouble("OrientToTwoObjects-KP", 1.2);
	public static final Supplier<Double> KI = ConstantHandler.addConstantDouble("OrientToTwoObjects-KI", 0.055);
	public static final Supplier<Double> KD = ConstantHandler.addConstantDouble("OrientToTwoObjects-KD", 0);
	

	public OrientToTwoTargets(Supplier<Double> rotateSpeedSupplier, double tolerance) {
		addSequential(new RunnableCommand(() -> SmartDashboard.putBoolean("Oriented", false)));
		addSequential(new TurnToTwoTargets(rotateSpeedSupplier));
		addSequential(new DriveTankWithPID(Robot.drivetrain, leftOrientationSource, rightOrientationSource, 0, 0,
				KP.get(), KI.get(), KD.get(), tolerance));
		addSequential(new RunnableCommand(() -> SmartDashboard.putBoolean("Oriented", true)));
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
