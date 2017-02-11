package org.usfirst.frc.team2212.robot.subsystems;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.utils.DoubleSpeedcontroller;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends TankDrivetrain {
	public static final Supplier<Double> leftAcceleration = ConstantHandler
			.addConstantDouble("Drivetrain-leftAcceleration", 0.01);
	public static final Supplier<Double> rightAcceleration = ConstantHandler
			.addConstantDouble("Drivetrain-rightAcceleration", 0.01);

	public static final Supplier<Double> leftKP = ConstantHandler.addConstantDouble("Drivetrain-leftKP", 1);
	public static final Supplier<Double> leftKI = ConstantHandler.addConstantDouble("Drivetrain-leftKI", 1);
	public static final Supplier<Double> leftKD = ConstantHandler.addConstantDouble("Drivetrain-leftKD", 1);
	public static final Supplier<Double> rightKP = ConstantHandler.addConstantDouble("Drivetrain-rightKP", 1);
	public static final Supplier<Double> rightKI = ConstantHandler.addConstantDouble("Drivetrain-rightKI", 1);
	public static final Supplier<Double> rightKD = ConstantHandler.addConstantDouble("Drivetrain-rightKD", 1);

	public static final double DISTANCE_PER_PULSE = 1;
	public static final double MAX_SPEED = 1; // FIXME get the real number
	private SpeedController leftSpeedcontroller;
	private SpeedController rightSpeedcontroller;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private PIDController leftMovmentControl;
	private PIDController rightMovmentControl;

	public Drivetrain(SpeedController leftSpeedcontroller, SpeedController rightSpeedcontroller, Encoder leftEncoder,
			Encoder rightEncoder) {
		leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		leftEncoder.setPIDSourceType(PIDSourceType.kRate);
		rightEncoder.setPIDSourceType(PIDSourceType.kRate);
		this.leftSpeedcontroller = leftSpeedcontroller;
		this.rightSpeedcontroller = rightSpeedcontroller;
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;

		leftMovmentControl = new PIDController(leftKP.get(), leftKI.get(), leftKD.get(), leftEncoder,
				(double output) -> leftSpeedcontroller
						.set(leftSpeedcontroller.get() + output * leftAcceleration.get()));
		rightMovmentControl = new PIDController(rightKP.get(), rightKI.get(), rightKD.get(), rightEncoder,
				(double output) -> rightSpeedcontroller
				.set(rightSpeedcontroller.get() + output * rightAcceleration.get()));

		leftMovmentControl.setAbsoluteTolerance(0);
		leftMovmentControl.setOutputRange(-1, 1);
		rightMovmentControl.setAbsoluteTolerance(0);
		rightMovmentControl.setOutputRange(-1, 1);
	}

	@Override
	public void setLeft(double speedLeft) {
		leftMovmentControl.setSetpoint(MAX_SPEED * speedLeft);
		if (!leftMovmentControl.isEnabled())
			leftMovmentControl.enable();
	}

	@Override
	public void setRight(double speedRight) {
		rightMovmentControl.setSetpoint(MAX_SPEED * speedRight);
		if (!rightMovmentControl.isEnabled())
			rightMovmentControl.enable();
	}

	@Override
	public PIDSource getLeftPIDSource() {
		return leftEncoder;
	}

	@Override
	public PIDSource getRightPIDSource() {
		return rightEncoder;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
