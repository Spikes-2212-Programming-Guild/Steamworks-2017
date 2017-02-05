package org.usfirst.frc.team2212.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2212.robot.commands.ExampleCommand;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveArcade;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.utils.RunnableCommand;
import com.spikes2212.utils.XboXUID;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI /* GEVALD */ {

	private DrivingMethod drivingMethod = DrivingMethod.TWO_JOYSTICK_ARCADE;
	private double directionMultiplier = 1;
	private double speedMultiplier = 1;
	// Joysticks
	private Joystick driverRightJoystick = new Joystick(0);
	private Joystick driverLeftJoystick = new Joystick(1);
	private XboXUID driverXbox = new XboXUID(3);

	// Driver joystick buttons
	private JoystickButton setPickerForward = new JoystickButton(driverRightJoystick, 2);
	private JoystickButton setPickerBackward = new JoystickButton(driverRightJoystick, 3);
	private JoystickButton setSpeedToHalf = new JoystickButton(driverRightJoystick, 4);
	private JoystickButton setSpeedToNormal = new JoystickButton(driverRightJoystick, 5);

	// Driver xbox buttons
	private Button setPickerForwardXbox = driverXbox.getYellowButton();
	private Button setPickerBackwardXbox = driverXbox.getBlueButton();
	private Button setSpeedToHalfXbox = driverXbox.getRedButton();
	private Button setSpeedToNormalXbox = driverXbox.getGreenButton();

	// Switching driving method buttons
	private Button setToDriveTankJoystick;
	private Button setToDriveArcadeTwoJoystick;
	private Button setToDriveArcadeOneJoystick;
	private Button setToDriveTankJoystickXbox;
	private Button setToDriveArcadeTwoJoystickXbox;
	private Button setToDriveArcadeOneJoystickXbox;

	public OI() {
		initJoystickDriver();
		setToDriveTankJoystick.whenPressed(
				new DriveTank(Robot.Drivetrain, () -> (adjustInput(driverLeftJoystick.getY()) * speedMultiplier * directionMultiplier),
						() -> (adjustInput(driverRightJoystick.getY()) * speedMultiplier * directionMultiplier)));
		setToDriveArcadeOneJoystick.whenPressed(
				new DriveArcade(Robot.Drivetrain, () -> (adjustInput(driverRightJoystick.getY()) * speedMultiplier * directionMultiplier),
						() -> adjustInput(driverRightJoystick.getX())));

		setToDriveArcadeTwoJoystick.whenPressed(
				new DriveArcade(Robot.Drivetrain, () -> (adjustInput(driverRightJoystick.getY()) * speedMultiplier * directionMultiplier),
						() -> adjustInput(driverLeftJoystick.getY())));

		setToDriveTankJoystickXbox.whenPressed(
				new DriveTank(Robot.Drivetrain, () -> (adjustInput(driverXbox.getLeftY()) * speedMultiplier * directionMultiplier),
						() -> (adjustInput(driverXbox.getRightY()) * speedMultiplier * directionMultiplier)));
		setToDriveArcadeOneJoystickXbox.whenPressed(
				new DriveArcade(Robot.Drivetrain, () -> (adjustInput(driverXbox.getRightY()) * speedMultiplier * directionMultiplier),
						() -> adjustInput(driverXbox.getRightX())));
		setToDriveArcadeTwoJoystickXbox.whenPressed(
				new DriveArcade(Robot.Drivetrain, () -> (adjustInput(driverXbox.getRightY()) * speedMultiplier * directionMultiplier),
						() -> adjustInput(driverXbox.getLeftY())));
	}

	private void initJoystickDriver() {
		setPickerForward.whenPressed(new RunnableCommand(() -> directionMultiplier = 1));
		setPickerBackward.whenPressed(new RunnableCommand(() -> directionMultiplier = -1));
		setSpeedToHalf.whenPressed(new RunnableCommand(() -> speedMultiplier = 0.5));
		setSpeedToNormal.whenPressed(new RunnableCommand(() ->  speedMultiplier = 1));
	}

	private void initXboxDriver() {
		setPickerForwardXbox.whenPressed(new RunnableCommand(() -> directionMultiplier = 1));
		setPickerBackwardXbox.whenPressed(new RunnableCommand(() -> directionMultiplier = -1));
		setSpeedToHalfXbox.whenPressed(new RunnableCommand(() -> speedMultiplier = 0.5));
		setSpeedToNormalXbox.whenPressed(new RunnableCommand(() ->  speedMultiplier = 1));
	}

	private double adjustInput(double input) {
		return input * Math.abs(input);
	}
}
