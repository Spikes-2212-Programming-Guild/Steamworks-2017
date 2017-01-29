package org.usfirst.frc.team2212.robot.subsystems;

import com.spikes2212.genericsubsystems.LimitedSubsystem;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Feeder extends LimitedSubsystem {

   SpeedController motor;
   
   public Feeder(SpeedController motor){
	   this.motor=motor;
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
	protected void move(double speed) {
		motor.set(speed);
	}
}

