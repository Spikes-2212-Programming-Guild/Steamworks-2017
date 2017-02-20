package com.spikes2212.robot;

import com.spikes2212.robot.commands.FeedAndShootByDistance;
import com.spikes2212.robot.commands.FeedAndShootLinear;
import com.spikes2212.robot.commands.OrientateAndMoveToGear;
import com.spikes2212.robot.commands.orientation.OrientToBoiler;
import com.spikes2212.robot.subsystems.BallBlocker;
import com.spikes2212.robot.subsystems.Climber;
import com.spikes2212.robot.subsystems.GearDropper;
import com.spikes2212.robot.subsystems.Picker;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


import com.spikes2212.genericsubsystems.commands.MoveLimitedSubsystem;
import com.spikes2212.utils.RunnableCommand;
import com.spikes2212.utils.XboXUID;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI /* GEVALD */ {

	// TODO CHANGE ALL SPEEDS!

	// controllers
	private Joystick driverRight = new Joystick(2);
	private Joystick driverLeft = new Joystick(1);

	private Joystick navigatorJoystick = new Joystick(2);
	private XboXUID navigatorXbox = new XboXUID(0);

	// driverRight buttons TODO maybe there are more buttons
	private Button switchToFrontCameraButton;
	private Button switchToRearCameraButton;
	private Button orientateAndMoveToGearButton;
	private Button orientToBoiler;

	// joystick navigator buttons
	private Button dropGearButton;
	private Button raiseBallBlockerButton;
	private Button lowerBallBlockerButton;
	private Button shootFuelButton;
	private Button pickFuelButton;
	private Button climbRopeButton;

	// Xbox navigator buttons
	private Button dropGearXbox;
	private Button raiseBallBlockerXbox;
	private Button lowerBallBlockerXbox;
	private Button shootFuelXbox;
	private Button pickFuelXbox;
	private Button climbRopeXbox;

	// constructor
	public OI() {
		initJoystickDriver();
		initXboxNavigator();
	}

	// sets all commands and buttons connected to joystick driver
	private void initJoystickDriver() {
		switchToFrontCameraButton = new JoystickButton(driverRight, 4);
		switchToRearCameraButton = new JoystickButton(driverRight, 5);
		orientateAndMoveToGearButton = new JoystickButton(driverRight, 3);
		orientToBoiler = new JoystickButton(driverRight, 2);
		
		switchToFrontCameraButton.whenPressed(new RunnableCommand(() -> Robot.camerasHandler.switchCamera(1)));
		switchToRearCameraButton.whenPressed(new RunnableCommand(() -> Robot.camerasHandler.switchCamera(0)));
		orientateAndMoveToGearButton.whileHeld(new OrientateAndMoveToGear(this::getRotation, this::getForwardRight));
		orientToBoiler.whileHeld(new OrientToBoiler(this::getRotation));
	}

	// sets all commands and buttons connected to navigatorJoystick
	private void initJoystickNavigator() {
		dropGearButton = new JoystickButton(navigatorJoystick, 4);
		raiseBallBlockerButton = new JoystickButton(navigatorJoystick, 3);
		lowerBallBlockerButton = new JoystickButton(navigatorJoystick, 2);
		shootFuelButton = new JoystickButton(navigatorJoystick, 1);
		pickFuelButton = new JoystickButton(navigatorJoystick, 5);
		climbRopeButton = new JoystickButton(navigatorJoystick, 6);

		dropGearButton.whileHeld(new MoveLimitedSubsystem(Robot.gearDropper, GearDropper.OPENING_SPEED));
		raiseBallBlockerButton.whileHeld(new MoveLimitedSubsystem(Robot.ballBlocker, BallBlocker.UP_SPEED));
		lowerBallBlockerButton.whileHeld(new MoveLimitedSubsystem(Robot.ballBlocker, BallBlocker.DOWN_SPEED));
		shootFuelButton.whileHeld(new FeedAndShootLinear(ImageProcessingConstants.distanceToBoiler));
		pickFuelButton.whileHeld(new MoveLimitedSubsystem(Robot.picker, Picker.SPEED));
		climbRopeButton.whenPressed(new MoveLimitedSubsystem(Robot.climber, Climber.SPEED));
	}

	// sets all commands connected to navigatorXbox
	private void initXboxNavigator() {
		dropGearXbox = navigatorXbox.getYellowButton();
		raiseBallBlockerXbox = navigatorXbox.getUpButton();
		lowerBallBlockerXbox = navigatorXbox.getDownButton();
		shootFuelXbox = navigatorXbox.getGreenButton();
		pickFuelXbox = navigatorXbox.getRtButton();
		climbRopeXbox = navigatorXbox.getLtButton();

		dropGearXbox.whileHeld(new MoveLimitedSubsystem(Robot.gearDropper, GearDropper.OPENING_SPEED));
		raiseBallBlockerXbox.whenPressed(new MoveLimitedSubsystem(Robot.ballBlocker, BallBlocker.UP_SPEED));
		lowerBallBlockerXbox.whenPressed(new MoveLimitedSubsystem(Robot.ballBlocker, BallBlocker.DOWN_SPEED));
		shootFuelXbox.toggleWhenPressed(new FeedAndShootLinear(ImageProcessingConstants.distanceToBoiler));
		pickFuelXbox.toggleWhenPressed(new MoveLimitedSubsystem(Robot.picker, Picker.SPEED));
		climbRopeXbox.toggleWhenPressed(new MoveLimitedSubsystem(Robot.climber, Climber.SPEED));
	}

	// receives input, returns the adjusted input for better sensitivity
	private double adjustInput(double input) {
		return input * Math.abs(input);
	}

	// returns the adjusted value of the Rotate
	// switch this to switch between the 2 drive arcade methods
	public double getRotation() {
		return adjustInput(-driverLeft.getX());
	}

	// returns the adjusted value of the driving right joystick's y
	public double getForwardRight() {
		return adjustInput(-driverRight.getY());
	}

	// returns the adjusted value of the driving left joystick's y
	public double getForwardLeft() {
		return adjustInput(-driverLeft.getY());
	}
}