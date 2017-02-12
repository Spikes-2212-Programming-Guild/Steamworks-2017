package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.subsystems.Shooter;

import com.spikes2212.dashboard.ConstantHandler;

public class ShootLiniar extends ShootBySpeed {

	public static final Supplier<Double> generalConstant = ConstantHandler.addConstantDouble("ShootLiniar-generalConstant", 0.7);

	public ShootLiniar(Shooter shooter, Supplier<Double> distance, double KP, double KI, double KD) {
		super(shooter, () -> calculateSpeedByDistance(distance.get()), KP, KI, KD);
	}

	public static double calculateSpeedByDistance(double distance) {
		double speed = distance * generalConstant.get();
		return speed;
	}
}
