package com.spikes2212.robot;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.robot.commands.OrientateAndMoveToGear;
import com.spikes2212.robot.commands.orientation.OrientToBoiler;
import com.spikes2212.robot.commands.orientation.OrientToGear;
import com.spikes2212.robot.commands.orientation.OrientToTwoTargets;
import com.spikes2212.robot.commands.shooting.ShootByDistance;
import com.spikes2212.robot.commands.shooting.ShootBySpeed;
import com.spikes2212.robot.commands.shooting.ShootLinear;
import com.spikes2212.robot.subsystems.BallBlocker;
import com.spikes2212.robot.subsystems.Climber;
import com.spikes2212.robot.subsystems.Feeder;
import com.spikes2212.robot.subsystems.GearDropper;
import com.spikes2212.robot.subsystems.Picker;
import com.spikes2212.utils.RunnableCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI /* GEVALD */ {
	public final Joystick j = new Joystick(0);

	public OI() {
		SmartDashboard.putData("move-BallBlocker-up",
				new MoveLimitedSubsystem(Robot.ballBlocker, BallBlocker.UP_SPEED));
		SmartDashboard.putData("move-BallBlocker-down",
				new MoveLimitedSubsystem(Robot.ballBlocker, BallBlocker.DOWN_SPEED));
		SmartDashboard.putData("open-GearDropper",
				new MoveLimitedSubsystem(Robot.gearDropper, GearDropper.OPENING_SPEED));
		SmartDashboard.putData("close-GearDropper",
				new MoveLimitedSubsystem(Robot.gearDropper, GearDropper.CLOSING_SPEED));
		SmartDashboard.putData("Climb", new MoveLimitedSubsystem(Robot.climber, Climber.SPEED));
		SmartDashboard.putData("Feed", new MoveLimitedSubsystem(Robot.feeder, Feeder.SPEED));
		SmartDashboard.putData("Pick", new MoveLimitedSubsystem(Robot.picker, Picker.SPEED));
		SmartDashboard.putData("ShootBySpeed",
				new ShootBySpeed(ConstantHandler.addConstantDouble("shooterSpeed", 300))); // inches
																							// per
																							// second
		SmartDashboard.putData("ShootByDistance", new ShootByDistance(ImageProcessingConstants.distanceToBoiler));
		SmartDashboard.putData("ShootLinear", new ShootLinear(ImageProcessingConstants.distanceToBoiler));
		SmartDashboard.putData("MoveForwards", new DriveTank(Robot.drivetrain, 0.2, 0.2));
		SmartDashboard.putData("MoveBackwards", new DriveTank(Robot.drivetrain, -0.2, -0.2));
		SmartDashboard.putData("OrientGears", new OrientateAndMoveToGear(this::getRotation, this::getForward));
		SmartDashboard.putData("Change to Boiler Camera",
				new RunnableCommand(() -> ImageProcessingConstants.NETWORK_TABLE.putNumber("currentCamera",
						OrientToBoiler.BOILER_CAMERA_ID.get())));
		SmartDashboard.putData("Change to Gear Camera", new RunnableCommand(() -> ImageProcessingConstants.NETWORK_TABLE
				.putNumber("currentCamera", OrientToGear.GEAR_CAMERA_ID.get())));

	}

	public static double adjustInput(double value) {
		return Math.abs(value) * value;
	}

	public double getRotation() {
		return adjustInput(-j.getX());
	}

	public double getForward() {
		return adjustInput(-j.getY());
	}
}
