package org.usfirst.frc.team2212.robot.commands.shooting;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

public class ShootLinear extends ShootBySpeed {

	public static final Supplier<Double> distanceToSpeedRatio = ConstantHandler
			.addConstantDouble("ShootLinear-distanceToSpeedRatio", 0.7);

	public ShootLinear(Supplier<Double> distance) {
		super(() -> calculateSpeedByDistance(distance.get()));
	}

	public static double calculateSpeedByDistance(double distance) {
		double speed = distance * distanceToSpeedRatio.get();
		return speed;
	}
}
