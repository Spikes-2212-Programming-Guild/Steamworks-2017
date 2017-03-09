package com.spikes2212.robot.commands.autonomous;

import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.robot.Robot;
import com.spikes2212.robot.commands.orientation.OrientToGear;
import com.spikes2212.robot.subsystems.GearDropper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *This is a demo class, that turns for the left gear but doesn't drop the gear in the end
 */
public class MoveTurnRightAuto extends CommandGroup {

	public MoveTurnRightAuto() {
		addSequential(
				new DriveTank(Robot.drivetrain, MoveStraightAndDropGearAuto.movingSpeed,
						MoveStraightAndDropGearAuto.movingSpeed),
				MoveTurnRightAndDropGearAuto.movingStraightTime.get());
		addSequential(new OrientToGear(() -> (MoveTurnRightAndDropGearAuto.turningSpeed.get())));
		addSequential(new DriveArcade(Robot.drivetrain, MoveStraightAndDropGearAuto.movingSpeed, () -> 0.0),
				MoveTurnRightAndDropGearAuto.moveToGearTime.get());
		// addSequential(new MoveLimitedSubsystem(Robot.gearDropper,
		// GearDropper.OPENING_SPEED));
		// addParallel(new MoveLimitedSubsystem(Robot.gearDropper, 0));
		// addSequential(new DriveArcade(Robot.drivetrain,
		// MoveStraightAndDropGearAuto.reverseSpeed, () -> 0.0),
		// MoveStraightAndDropGearAuto.reverseTime.get());
	}
}
