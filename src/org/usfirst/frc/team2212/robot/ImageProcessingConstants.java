package org.usfirst.frc.team2212.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class ImageProcessingConstants {
	public static final double CAMERA_WIDTH = 640.0;
	public static final double CAMERA_HEIGHT = 360.0;
	public static final double CAMERA_ANGLE_Y = 32.0;
	public static final double CAMERA_ANGLE_X = 58.75;
	public static final double CAMERA_HEIGHT_FROM_FLOOR_IN = 11.5;

	public static final Supplier<Double> CAMERA_ANGLE_TO_FLOOR = ConstantHandler
			.addConstantDouble("CAMERA_ANGLE_TO_FLOOR", 20);

	public static final NetworkTable NETWORK_TABLE = NetworkTable.getTable("ImageProcessing");

	public static final double HIGH_REFLECTIVE_HEIGHT_IN = 84;
	public static final double LOW_REFLECTIVE_HEIGHT_IN = 78;

	public static final Supplier<Double> BIG_OBJECT_CENTER = () -> ((NETWORK_TABLE.getNumber("x0", 0)
			+ 0.5 * NETWORK_TABLE.getNumber("width0", 0)) / CAMERA_WIDTH - 0.5);
	public static final Supplier<Double> SMALL_OBJECT_CENTER = () -> ((NETWORK_TABLE.getNumber("x1", 0)
			+ 0.5 * NETWORK_TABLE.getNumber("width1", 0)) / CAMERA_WIDTH - 0.5);
	public static Supplier<Double> TWO_OBJECTS_CENTER = () -> (BIG_OBJECT_CENTER.get() + SMALL_OBJECT_CENTER.get()) / 2;

	private static final Supplier<Double> LOW_REFLECTIVE_HEIGHT_PX = () -> CAMERA_HEIGHT
			- NETWORK_TABLE.getNumber("y1", 0.0) - 0.5 * NETWORK_TABLE.getNumber("height1", 0);

	private static final Supplier<Double> HIGH_REFLECTIVE_HEIGHT_PX = () -> CAMERA_HEIGHT
			- NETWORK_TABLE.getNumber("y0", 0.0) - 0.5 * NETWORK_TABLE.getNumber("height0", 0);

	private static final Supplier<Double> cameraViewAngleToHighBoiler = () -> (CAMERA_ANGLE_Y / CAMERA_HEIGHT)
			* HIGH_REFLECTIVE_HEIGHT_PX.get() + CAMERA_ANGLE_TO_FLOOR.get();
	private static final Supplier<Double> cameraViewAngleToLowBoiler = () -> (CAMERA_ANGLE_Y / CAMERA_HEIGHT)
			* LOW_REFLECTIVE_HEIGHT_PX.get() + CAMERA_ANGLE_TO_FLOOR.get();

	private static final Supplier<Double> distanceHigh = () -> (HIGH_REFLECTIVE_HEIGHT_IN
			- CAMERA_HEIGHT_FROM_FLOOR_IN) / Math.tan(Math.toRadians(cameraViewAngleToHighBoiler.get()));
	private static final Supplier<Double> distanceLow = () -> (LOW_REFLECTIVE_HEIGHT_IN
			- CAMERA_HEIGHT_FROM_FLOOR_IN)/ Math.tan(Math.toRadians(cameraViewAngleToLowBoiler.get()));
	public static final Supplier<Double> distanceToBoiler = () -> (distanceHigh.get() + distanceLow.get()) / 2;

}
