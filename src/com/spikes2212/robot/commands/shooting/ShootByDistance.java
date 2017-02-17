package com.spikes2212.robot.commands.shooting;

import java.util.function.Supplier;

public class ShootByDistance extends ShootBySpeed {

	public static final double SHOOTING_ANGLE = 80; // FIXME find the real
													// number
	public static final double SHOOTING_ANGLE_IN_RADIANS = Math.toRadians(SHOOTING_ANGLE);
	public static final double BOILER_HEIGHT = 97; // 8 ft. 1 in.
	public static final double GRAVITY = 386.1; // in/s^2

	public ShootByDistance(Supplier<Double> distance) {
		super(() -> calculateSpeedByDistance(distance.get()));
	}

	private static double calculateSpeedByDistance(double distance) {
		/*
		 * Physics: Vx = speed*cos(shootingAngle) Vy = speed*sin(shootingAngle)
		 * 
		 * distance = t*Vx=t*speed*cos(shootingAngle)
		 * t=distance/(speed*cos(shootingAngle)) boilerHeight =
		 * t*Vy-0.5*g*t^2=t*speed*sin(shootingAngle)-0.5*g*t^2=
		 * distance*tan(shootingAngle)-0.5*g*d^2/(speed*cos(shootingAngle))^2
		 * 
		 * 0.5*g*d^2/(speed*cos(shootingAngle))^2=distance*tan(shootingAngle)-
		 * boilerHeight
		 * 
		 * speed^2=0.5*g*distance^2/(cos(shootingAngle)^2*(distance*tan(
		 * shootingAngle)- boilerHeight))
		 * 
		 * speed=root(0.5*g*distance^2/(cos(shootingAngle)^2*(distance*tan(
		 * shootingAngle )- boilerHeight)))
		 */

		double speed = Math.sqrt(GRAVITY / 2 * Math.pow(distance / (Math.cos(SHOOTING_ANGLE_IN_RADIANS)), 2)
				/ (distance * Math.tan(SHOOTING_ANGLE_IN_RADIANS) - BOILER_HEIGHT));
		return speed;
	}

}
