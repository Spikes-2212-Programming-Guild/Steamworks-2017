
package org.usfirst.frc.team2212.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import org.usfirst.frc.team2212.robot.subsystems.BallBlocker;
import org.usfirst.frc.team2212.robot.subsystems.Climber;
import org.usfirst.frc.team2212.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2212.robot.subsystems.Feeder;
import org.usfirst.frc.team2212.robot.subsystems.GearDropper;
import org.usfirst.frc.team2212.robot.subsystems.Picker;
import org.usfirst.frc.team2212.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import com.ctre.CANTalon;
import com.spikes2212.utils.CamerasHandler;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drivetrain drivetrain;
	public static GearDropper gearDropper;
	public static BallBlocker ballBlocker;
	public static Climber climber;
	public static Feeder feeder;
	public static Picker picker;
	public static Shooter shooter;
	public static CamerasHandler camerasHandler;

	Command autonomousCommand;
	SendableChooser chooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
    	// TODO change ports plz
    	drivetrain = new Drivetrain(new VictorSP(0), new VictorSP(0), new Encoder(0, 0), new Encoder(0,0));
    	gearDropper = new GearDropper(new VictorSP(0), new DigitalInput(0), new DigitalInput(0));
    	ballBlocker = new BallBlocker(new VictorSP(0), new DigitalInput(0), new DigitalInput(0));
    	climber = new Climber(new CANTalon(0));
    	feeder = new Feeder(new VictorSP(0));
    	picker = new Picker(new VictorSP(0));
    	shooter = new Shooter(new CANTalon(0), new Encoder(0,0));
    	//TODO change size and ports
		camerasHandler = new CamerasHandler(300, 300, 0,1);
    	oi = new OI();
		chooser = new SendableChooser();
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}