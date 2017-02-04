package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.subsystems.Shooter;
import org.usfirst.frc.team2212.robot.utils.PIDOutputForSpeed;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootBySpeed extends Command {

	private double KP;
	private double KI;
	private double KD;
	private double speed;
	private Supplier<Double> wantedSpeed;
	private PIDController movmentControl;
	private Shooter shooter;

	/**
	 * Sets the Proportional coefficient of the PID loop of this command.
	 *
	 * @param P
	 *            the new Proportional coefficient.
	 * @see PIDController#setPID(double, double, double)
	 */
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

	public ShootBySpeed(Shooter shooter, Supplier<Double> wantedSpeed, double KP, double KI, double KD) {
		this.shooter = shooter;
		this.wantedSpeed = wantedSpeed;
		this.KD = KD;
		this.KI = KI;
		this.KP = KP;
		speed = 0;
	}
	
	public ShootBySpeed(Shooter shooter, double wantedSpeed, double KP, double KI, double KD) {
		this(shooter, ()->wantedSpeed, KP, KI, KD);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		movmentControl = new PIDController(KP, KI, KD, shooter.getPIDSource(), (output) -> {
			speed -= 0.01 * output;
			shooter.tryMove(speed);
		});
		movmentControl.setAbsoluteTolerance(0);
		movmentControl.setSetpoint(this.wantedSpeed.get());
		movmentControl.setOutputRange(-1, 1);
		movmentControl.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		movmentControl.disable();
		shooter.tryMove(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
