package com.spikes2212.robot.commands.autonomous;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveStraightAuto extends CommandGroup {

	public MoveStraightAuto() {
		addSequential(new DriveArcade(Robot.drivetrain, MoveStraightAndDropGearAuto.movingSpeed, () -> 0.0), 5);
	}

}
