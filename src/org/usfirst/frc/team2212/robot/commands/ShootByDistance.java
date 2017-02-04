package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.subsystems.Shooter;

import com.spikes2212.dashboard.ConstantHandler;

public class ShootByDistance extends ShootBySpeed {
	static Supplier<Double> cameraAngle = ConstantHandler.addConstantDouble("cameraAngle", 20);
	static Supplier<Double> boilerHeight = ConstantHandler.addConstantDouble("boilerHeight", 250); // cm
	
	public ShootByDistance(Shooter shooter, Supplier<Double> distance, double KP, double KI, double KD) {
		super(shooter,calculateSpeedByDistance(distance),KP,KI,KD);
	}
	private static Supplier<Double> calculateSpeedByDistance(Supplier<Double> distance){
		/*
		 * Physics: Vx = speed*sin(cameraAngle) Vy = speed*cos(cameraAngle)
		 * distance = t*Vx=t*speed*sin(cameraAngle) t=d/(speed*sin(cameraAngle))
		 * boilerHeight = t*Vy-5t^2=t*speed*cos(cameraAngle)-5t^2=
		 * distance*tan(cameraAngle)-5d^2/(speed*sin(cameraAngle))^2
		 * 5d^2/(speed*sin(cameraAngle))^2=distance*tan(cameraAngle)- boilerHeight
		 * speed^2=5*distance^2/(sin(cameraAngle)^2*(distance*tan(cameraAngle)- boilerHeight))
		 * speed=root(5*distance^2/(sin(cameraAngle)^2*(distance*tan(cameraAngle)- boilerHeight)))
		 */
		return ()->Math.sqrt(5*distance.get()*distance.get()/(Math.sin(cameraAngle.get()*Math.PI/180)*(Math.sin(cameraAngle.get()*Math.PI/180)*(distance.get() * Math.tan(cameraAngle.get()*Math.PI/180)- boilerHeight.get()))));
	}

}
