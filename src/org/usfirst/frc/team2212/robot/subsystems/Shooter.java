package org.usfirst.frc.team2212.robot.subsystems;

import java.util.function.Supplier;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.PigeonImu.CalibrationMode;
import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.LimitedSubsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 *
 */
public class Shooter extends LimitedSubsystem {

	private CANTalon motor;

	public Shooter(CANTalon motor) {
		this.motor = motor;
		motor.changeControlMode(TalonControlMode.Voltage);
	}

	@Override
	public boolean isMin() {
		return false;
	}

	@Override
	public boolean isMax() {
		return false;
	}

	@Override
	public PIDSource getPIDSource() {
		return null;
	}

	@Override
	protected void move(double voltage) {
		motor.set(voltage);
	}

	@Override
	public void stop() {
		motor.set(0);
	}
}
