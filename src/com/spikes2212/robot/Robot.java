
package com.spikes2212.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

import com.spikes2212.dashboard.DashBoardController;
import com.spikes2212.dashboard.DashBoardController;
import com.spikes2212.robot.commands.autonomous.MoveStraightAndDropGearAuto;
import com.spikes2212.robot.commands.autonomous.MoveStraightAuto;
import com.spikes2212.robot.commands.autonomous.MoveTurnLeftAndDropGearAuto;
import com.spikes2212.robot.commands.autonomous.MoveTurnRightAndDropGearAuto;
import com.spikes2212.robot.commands.orientation.TurnToTwoTargets;
import com.spikes2212.robot.subsystems.BallBlocker;
import com.spikes2212.robot.subsystems.Climber;
import com.spikes2212.robot.subsystems.Drivetrain;
import com.spikes2212.robot.subsystems.Feeder;
import com.spikes2212.robot.subsystems.GearDropper;
import com.spikes2212.robot.subsystems.Picker;
import com.spikes2212.robot.subsystems.Shooter;
import com.spikes2212.utils.DoubleSpeedcontroller;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
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
	public static BallBlocker ballBlocker;
	public static Climber climber;
	public static Feeder feeder;
	public static GearDropper gearDropper;
	public static Picker picker;
	public static Shooter shooter;
	public static CamerasHandler camerasHandler;
	public static DashBoardController dbc = new DashBoardController();
	private SendableChooser<Command> autoChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		drivetrain = new Drivetrain(
				new DoubleSpeedcontroller(new CANTalon(RobotMap.CAN.DRIVE_LEFT_1),
						new CANTalon(RobotMap.CAN.DRIVE_LEFT_2)),
				new DoubleSpeedcontroller(new CANTalon(RobotMap.CAN.DRIVE_RIGHT_1),
						new CANTalon(RobotMap.CAN.DRIVE_RIGHT_2)),
				new Encoder(RobotMap.DIO.DRIVE_LEFT_ENCODER_A, RobotMap.DIO.DRIVE_LEFT_ENCODER_B),
				new Encoder(RobotMap.DIO.DRIVE_RIGHT_ENCODER_A, RobotMap.DIO.DRIVE_RIGHT_ENCODER_B));
		ballBlocker = new BallBlocker(new VictorSP(RobotMap.PWM.BALL_BLOCKER),
				new DigitalInput(RobotMap.DIO.BALL_BLOCKER_DOWN), new DigitalInput(RobotMap.DIO.BALL_BLOCKER_UP));
		climber = new Climber(new CANTalon(RobotMap.CAN.CLIMBER));
		feeder = new Feeder(new VictorSP(RobotMap.PWM.FEEDER), new VictorSP(RobotMap.PWM.BLENDER));
		gearDropper = new GearDropper(new VictorSP(RobotMap.PWM.GEAR_DROPPER),
				new DigitalInput(RobotMap.DIO.GEAR_DROPPER_OPEN), new DigitalInput(RobotMap.DIO.GEAR_DROPPER_CLOSE));
		picker = new Picker(new VictorSP(RobotMap.PWM.PICKER));
		shooter = new Shooter(new CANTalon(RobotMap.CAN.SHOOTER),
				new Encoder(RobotMap.DIO.SHOOTER_ENCODER_A, RobotMap.DIO.SHOOTER_ENCODER_B));
		camerasHandler = new CamerasHandler(160*2, 120*2, 0, 1);
		dbc.addDouble("Center", ImageProcessingConstants.TWO_OBJECTS_CENTER);
		dbc.addDouble("Distance", ImageProcessingConstants.distanceToBoiler);
		dbc.addBoolean("Gear Closed", gearDropper::isMin);
		oi = new OI();
		autoChooser.addDefault("Gears Straight", new MoveStraightAndDropGearAuto());
		autoChooser.addObject("Gears Left", new MoveTurnLeftAndDropGearAuto());
		autoChooser.addObject("Gears Right", new MoveTurnRightAndDropGearAuto());
		autoChooser.addObject("Straight", new MoveStraightAuto());
		SmartDashboard.putData("Auto", autoChooser);
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
		dbc.update();
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
		autoChooser.getSelected().start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		dbc.update();
	}

	public void teleopInit() {
		autoChooser.getSelected().cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		dbc.update();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}