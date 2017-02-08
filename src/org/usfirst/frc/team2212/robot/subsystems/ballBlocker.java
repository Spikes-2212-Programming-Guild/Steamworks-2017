package org.usfirst.frc.team2212.robot.subsystems;

import com.spikes2212.genericsubsystems.LimitedSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ballBlocker extends LimitedSubsystem {

	private SpeedController motor;
	private DigitalInput up;
	private DigitalInput down;

	public ballBlocker(SpeedController motor, DigitalInput down, DigitalInput up) {
		this.up = up;
		this.down = down;
		this.motor = motor;
	}

	@Override
	public boolean isMin() {
		return down.get();
	}

	@Override
	public boolean isMax() {
		return up.get();
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

