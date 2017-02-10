package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.Robot;
import org.usfirst.frc.team2212.robot.subsystems.Drivetrain;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	public static final Supplier<Double> acceleration = ConstantHandler.addConstantDouble("Shooter-acceleration", 0.01);
	private Supplier<Double> rightSpeed;
	private Drivetrain drivetrain;
	private double KP;
	private double KI;
	private double KD;
	private PIDController leftMovmentControl;
	private double leftSpeed;

	public void setP(double P) {
		KP = P;
	}

	/**
	 * Gets the Proportional coefficient of the PID loop of this command.
	 *
	 * @return the current Proportional coefficient.
	 * @see PIDController#getP()
	 */
	public double getP() {
		return KP;
	}

	/**
	 * Sets the Integral coefficients of the PID loop of this command.
	 *
	 * @param I
	 *            the new Integral coefficient.
	 * @see PIDController#setPID(double, double, double)
	 */
	public void setI(double I) {
		KI = I;
	}

	/**
	 * Gets the Integral coefficient of the PID loop of this command.
	 *
	 * @return the current Integral coefficient.
	 * @see PIDController#getI()
	 */
	public double getI() {
		return KI;
	}

	/**
	 * Sets the Differential coefficient of the PID loop of this command.
	 *
	 * @param D
	 *            the new Differential coefficient.
	 * @see PIDController#setPID(double, double, double)
	 */
	public void setD(double D) {
		KD = D;
	}

	/**
	 * Gets the Differential coefficient of the PID loop of this command.
	 *
	 * @return the current Differential coefficient.
	 * @see PIDController#getD()
	 */
	public double getD() {
		return KD;
	}

	public DriveStraight(Drivetrain drivetrain, Supplier<Double> rightSpeed, double KP, double KI, double KD) {
		this.drivetrain = drivetrain;
		this.KD = KD;
		this.KI = KI;
		this.KP = KP;
		this.rightSpeed = rightSpeed;
		leftSpeed = 0;
	}

	public DriveStraight(Drivetrain drivetrain, double rightSpeed, double KP, double KI, double KD) {
		this(drivetrain, () -> rightSpeed, KP, KI, KD);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		 leftMovmentControl = new PIDController(KP, KI, KD, drivetrain.getLeftPIDSource(), (double output) -> leftSpeed+=output*acceleration.get());
		 leftMovmentControl.setAbsoluteTolerance(0);
	     leftMovmentControl.setOutputRange(-1, 1);
	     leftMovmentControl.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		leftMovmentControl.setSetpoint(drivetrain.getRightPIDSource().pidGet());
		drivetrain.setLeft(leftSpeed);
		drivetrain.setRight(rightSpeed.get());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		leftMovmentControl.disable();
		drivetrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
