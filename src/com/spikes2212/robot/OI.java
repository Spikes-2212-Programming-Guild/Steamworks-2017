package com.spikes2212.robot;


import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import com.spikes2212.robot.commands.shooting.ShootByDistance;
import com.spikes2212.robot.commands.shooting.ShootBySpeed;
import com.spikes2212.robot.commands.shooting.ShootLinear;
import com.spikes2212.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI /* GEVALD */ {
    public OI() {
        SmartDashboard.putData("move-BallBlocker-up", new MoveLimitedSubsystem(Robot.ballBlocker, BallBlocker.UP_SPEED));
        SmartDashboard.putData("move-BallBlocker-down", new MoveLimitedSubsystem(Robot.ballBlocker, BallBlocker.DOWN_SPEED));
        SmartDashboard.putData("open-GearDropper", new MoveLimitedSubsystem(Robot.gearDropper, GearDropper.OPENING_SPEED));
        SmartDashboard.putData("close-GearDropper", new MoveLimitedSubsystem(Robot.gearDropper, GearDropper.CLOSING_SPEED));
        SmartDashboard.putData("Climb", new MoveLimitedSubsystem(Robot.climber, Climber.SPEED));
        SmartDashboard.putData("Feed", new MoveLimitedSubsystem(Robot.feeder, Feeder.SPEED));
        SmartDashboard.putData("Pick", new MoveLimitedSubsystem(Robot.picker, Picker.SPEED));
        SmartDashboard.putData("ShootBySpeed", new ShootBySpeed(200)); // inches per second
        SmartDashboard.putData("ShootByDistance", new ShootByDistance(ImageProcessingConstants.distanceToBoiler));
        SmartDashboard.putData("ShootLinear", new ShootLinear(ImageProcessingConstants.distanceToBoiler));
        SmartDashboard.putData("MoveForwards", new DriveTank(Robot.drivetrain, 0.5, 0.5));
        SmartDashboard.putData("MoveBackwards", new DriveTank(Robot.drivetrain, -0.5, -0.5));

    }
}
