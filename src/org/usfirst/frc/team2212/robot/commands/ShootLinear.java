package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.subsystems.Shooter;

import com.spikes2212.dashboard.ConstantHandler;

public class ShootLinear extends ShootBySpeed {

	public static final Supplier<Double> distanceToSpeedRatio = ConstantHandler.addConstantDouble("ShootLinear-distanceToSpeedRatio", 0.7);

	public ShootLinear(Shooter shooter, Supplier<Double> distance, double KP, double KI, double KD) {
		super(shooter, () -> calculateSpeedByDistance(distance.get()), KP, KI, KD);
	}

	public static double calculateSpeedByDistance(double distance) {
		double speed = distance * distanceToSpeedRatio.get();
		return speed;
	}
}
