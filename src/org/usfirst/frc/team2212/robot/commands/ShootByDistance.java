package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.subsystems.Shooter;

import com.spikes2212.dashboard.ConstantHandler;

public class ShootByDistance extends ShootBySpeed {
	public static final double SHOOTING_ANGLE = 20; // FIXME find the real number
	public static final double BOILER_HEIGHT = 2.74; // meter FIXME find the real number
	public static final double GRAVITY = 386.1; // in/s^2

	public ShootByDistance(Shooter shooter, Supplier<Double> distance, double KP, double KI, double KD) {
		super(shooter, () -> calculateSpeedByDistance(distance.get()), KP, KI, KD);
	}

	private static double calculateSpeedByDistance(double distance) {
		/*
		 * Physics: Vx = speed*cos(shootingAngle) Vy = speed*sin(shootingAngle)
		 * 
		 * distance = t*Vx=t*speed*cos(shootingAngle)
		 * t=distance/(speed*cos(shootingAngle)) boilerHeight =
		 * t*Vy-g/2*t^2=t*speed*sin(shootingAngle)-g/2*t^2=
		 * distance*tan(shootingAngle)-g/2*d^2/(speed*cos(shootingAngle))^2
		 * 
		 * g/2*d^2/(speed*cos(shootingAngle))^2=distance*tan(shootingAngle)-
		 * boilerHeight
		 * 
		 * speed^2=g/2*distance^2/(cos(shootingAngle)^2*(distance*tan(
		 * shootingAngle)- boilerHeight))
		 * 
		 * speed=root(g/2*distance^2/(cos(shootingAngle)^2*(distance*tan(
		 * shootingAngle )- boilerHeight)))
		 */
		double angleInRadians = Math.toRadians(SHOOTING_ANGLE);
		
		double speed = Math.sqrt(GRAVITY/2 * Math.pow(distance / (Math.cos(angleInRadians)), 2)
				/ (distance * Math.tan(angleInRadians) - BOILER_HEIGHT));
		return speed;
	}

}
