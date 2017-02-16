package org.usfirst.frc.team2212.robot;

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
	private Joystick driverRight = new Joystick(0);
	private Joystick driverLeft = new Joystick(1);
	
	private Joystick navigatorJoystick = new Joystick(2);
	private XboXUID navigatorXbox = new XboXUID(3);
	
	// driverRight buttons TODO maybe there are more buttons 
	private Button switchToFrontCameraButton;
	private Button switchToRearCameraButton;
	
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
		initJoystickNavigator();
		initXboxNavigator();
	}

	// sets all commands and buttons connected to joystick driver
	private void initJoystickDriver() {
		switchToFrontCameraButton = new JoystickButton(driverRight, 0);
		switchToRearCameraButton = new JoystickButton(driverRight, 1);
		
		dropGearButton = new JoystickButton(navigatorJoystick, 0);
		raiseBallBlockerButton = new JoystickButton(navigatorJoystick, 1);
		lowerBallBlockerButton = new JoystickButton(navigatorJoystick, 2);
		shootFuelButton = new JoystickButton(navigatorJoystick, 3);
		pickFuelButton = new JoystickButton(navigatorJoystick, 4);
		climbRopeButton = new JoystickButton(navigatorJoystick, 5);
		
		switchToFrontCameraButton.whenPressed(new RunnableCommand(()->Robot.camerasHandler.switchCamera(1)));
		switchToRearCameraButton.whenPressed(new RunnableCommand(()->Robot.camerasHandler.switchCamera(0)));
	}
	
	// sets all commands and buttons connected to xbox driver
	private void initXBoxDriver() { 
		dropGearXbox = navigatorXbox.getBlueButton();
		raiseBallBlockerXbox = navigatorXbox.getUpButton();
		lowerBallBlockerXbox = navigatorXbox.getDownButton();
		shootFuelXbox = navigatorXbox.getRbButton();
		pickFuelXbox = navigatorXbox.getYellowButton();
		climbRopeXbox = navigatorXbox.getGreenButton();
	}
	
	// sets all commands connected to navigatorJoystick
	private void initJoystickNavigator(){
		dropGearButton.whenPressed(new MoveLimitedSubsystem(Robot.gearDropper, 0.7));
		raiseBallBlockerButton.whileHeld(new MoveLimitedSubsystem(Robot.ballBlocker, 0.7));
		lowerBallBlockerButton.whileHeld(new MoveLimitedSubsystem(Robot.ballBlocker, -0.7));
		shootFuelButton.whileHeld(new MoveLimitedSubsystem(Robot.shooter, 1));
		pickFuelButton.whileHeld(new MoveLimitedSubsystem(Robot.picker, 0.7));
		climbRopeButton.whenPressed(new MoveLimitedSubsystem(Robot.climber, 0.7));
	}

	// sets all commands connected to navigatorXbox
	private void initXboxNavigator(){
		dropGearXbox.whenPressed(new MoveLimitedSubsystem(Robot.gearDropper, 0.7));
		raiseBallBlockerXbox.whileHeld(new MoveLimitedSubsystem(Robot.ballBlocker, 0.7));
		lowerBallBlockerXbox.whileHeld(new MoveLimitedSubsystem(Robot.ballBlocker, -0.7));
		shootFuelXbox.whileHeld(new MoveLimitedSubsystem(Robot.shooter, 1));
		pickFuelXbox.whileHeld(new MoveLimitedSubsystem(Robot.picker, 0.7));
		climbRopeXbox.whenPressed(new MoveLimitedSubsystem(Robot.climber, 0.7));
	}
	
	//receives input, returns the adjusted input for better sensitivity
	private double adjustInput(double input) {
		return input * Math.abs(input);
	}
	
	//returns the adjusted value of the turning joystick's Y
	public double getRotateY(){
		return adjustInput(driverLeft.getY());
	}
	
	//returns the adjusted value of the turning joystick's X
	public double getRotateX(){
		return adjustInput(driverLeft.getX());
	}
		
	//returns the adjusted value of the driving joystick's Y
	public double getForwardY(){
		return adjustInput(driverRight.getY());
	}
	
	//returns the adjusted value of the driving joystick's X
	public double getForwardX(){
		return adjustInput(driverRight.getX());
	}
}