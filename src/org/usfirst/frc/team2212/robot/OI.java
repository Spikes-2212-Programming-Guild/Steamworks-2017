package org.usfirst.frc.team2212.robot;

import org.usfirst.frc.team2212.robot.commands.FeedAndShootByDistance;
import org.usfirst.frc.team2212.robot.commands.FeedAndShootBySpeed;
import org.usfirst.frc.team2212.robot.commands.FeedAndShootLinear;

import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI /* GEVALD */ {
	public OI() {
		SmartDashboard.putData("move-BallBlocker-up", new MoveLimitedSubsystem(Robot.ballBlocker, 0.5));
		SmartDashboard.putData("move-BallBlocker-down", new MoveLimitedSubsystem(Robot.ballBlocker, -0.5));
		SmartDashboard.putData("open-GearDropper", new MoveLimitedSubsystem(Robot.gearDropper, 0.5));
		SmartDashboard.putData("close-GearDropper", new MoveLimitedSubsystem(Robot.gearDropper, -0.5));
		SmartDashboard.putData("Climb", new MoveLimitedSubsystem(Robot.climber, 0.5));
		SmartDashboard.putData("Feed", new MoveLimitedSubsystem(Robot.feeder, 0.5));
		SmartDashboard.putData("Pick", new MoveLimitedSubsystem(Robot.picker, 0.5));
		SmartDashboard.putData("Shoot", new MoveLimitedSubsystem(Robot.shooter, 0.5));
		SmartDashboard.putData("ShootBySpeed", new FeedAndShootBySpeed(0.5));
		SmartDashboard.putData("ShootByDistance", new FeedAndShootByDistance(() -> 0.5));
		SmartDashboard.putData("ShootLinear", new FeedAndShootLinear(() -> 0.5));
		SmartDashboard.putData("MoveForwards", new DriveTank(Robot.drivetrain, 0.5, 0.5));
		SmartDashboard.putData("MoveBackwards", new DriveTank(Robot.drivetrain, -0.5, -0.5));

	}
}
