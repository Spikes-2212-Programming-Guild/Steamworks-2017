package org.usfirst.frc.team2212.robot.subsystems;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.LimitedSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;


/**
 *
 */
public class BallBlocker extends LimitedSubsystem {
	public static final Supplier<Double> UP_SPEED = ConstantHandler.addConstantDouble("BallBlocker-UP_SPEED", 0.5);
	public static final Supplier<Double> DOWN_SPEED = ConstantHandler.addConstantDouble("BallBlocker-DOWN_SPEED", -0.5);

	private SpeedController motor;
	private DigitalInput up;
	private DigitalInput down;

	public BallBlocker(SpeedController motor, DigitalInput down, DigitalInput up) {
		this.up = up;
		this.down = down;
		this.motor = motor;
	}

	@Override
	public boolean isMin() {
		return !down.get();
	}

	@Override
	public boolean isMax() {
		return !up.get();
	}

	@Override
	public PIDSource getPIDSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void move(double speed) {
		motor.set(speed);
	}
}

