package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.subsystems.Shooter;

import com.spikes2212.dashboard.ConstantHandler;

public class ShootByDistance extends ShootBySpeed {
	public static final double SHOOTING_ANGLE = 20; // FIXME find the real number
	public static final double BOILER_HEIGHT = 2.74; // meter FIXME find the real number
	public static final double WHEEL_DIAMETER = 4; // inches FIXME find the real number
	public static final int ENCODER_TICKS = 1440; // FIXME find the real number

	public ShootByDistance(Shooter shooter, Supplier<Double> distance, double KP, double KI, double KD) {
		super(shooter, () -> calculateSpeedByDistance(distance.get()), KP, KI, KD);
	}

	private static double calculateSpeedByDistance(double distance) {
		/*
		 * Physics: Vx = speed*cos(shootingAngle) Vy = speed*sin(shootingAngle)
		 * 
		 * distance = t*Vx=t*speed*cos(shootingAngle)
		 * t=distance/(speed*cos(shootingAngle)) boilerHeight =
		 * t*Vy-4.9t^2=t*speed*sin(shootingAngle)-4.9t^2=
		 * distance*tan(shootingAngle)-5d^2/(speed*cos(shootingAngle))^2
		 * 
		 * 5d^2/(speed*cos(shootingAngle))^2=distance*tan(shootingAngle)-
		 * boilerHeight
		 * 
		 * speed^2=5*distance^2/(cos(shootingAngle)^2*(distance*tan(
		 * shootingAngle)- boilerHeight))
		 * 
		 * speed=root(5*distance^2/(cos(shootingAngle)^2*(distance*tan(
		 * shootingAngle )- boilerHeight)))
		 */
		double angleInRadians = Math.toRadians(SHOOTING_ANGLE);
		double meterToTicks = ENCODER_TICKS / (0.0254 * Math.PI * WHEEL_DIAMETER);
		
		double speedInMeter = Math.sqrt(4.9 * Math.pow(distance / (Math.cos(angleInRadians)), 2)
				/ (distance * Math.tan(angleInRadians) - BOILER_HEIGHT));
		return speedInMeter * meterToTicks;
	}

}
