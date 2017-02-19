package com.spikes2212.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class ImageProcessingConstants {
	public static final int CAMERA_WIDTH = 640;
	public static final int CAMERA_HEIGHT = 360;
	public static final double CAMERA_VIEW_ANGLE_Y = 32.0;
	public static final double CAMERA_VIEW_ANGLE_X = 58.75;
	public static final double BOILER_CAMERA_HEIGHT_FROM_FLOOR_IN = 19.5;

	public static final Supplier<Double> BOILER_CAMERA_ANGLE_TO_FLOOR = ConstantHandler
			.addConstantDouble("CAMERA_ANGLE_TO_FLOOR", 20);

	public static final NetworkTable NETWORK_TABLE = NetworkTable.getTable("ImageProcessing");

	public static final double HIGH_REFLECTIVE_HEIGHT_IN = 84;
	public static final double LOW_REFLECTIVE_HEIGHT_IN = 78;

	// calculates the center of the big reflective
	public static final Supplier<Double> BIG_OBJECT_CENTER = () -> ((NETWORK_TABLE.getNumber("x0", 0)
			+ 0.5 * NETWORK_TABLE.getNumber("width0", 0)) / CAMERA_WIDTH - 0.5);
	// calculates the center of the small reflective
	public static final Supplier<Double> SMALL_OBJECT_CENTER = () -> ((NETWORK_TABLE.getNumber("x1", 0)
			+ 0.5 * NETWORK_TABLE.getNumber("width1", 0)) / CAMERA_WIDTH - 0.5);
	// calculates the center of the two reflectives
	public static Supplier<Double> TWO_OBJECTS_CENTER = () -> (BIG_OBJECT_CENTER.get() + SMALL_OBJECT_CENTER.get()) / 2;

	// calculates the height of the center of the high reflective from the
	// bottom of the camera's field of view
	private static final Supplier<Double> HIGH_REFLECTIVE_HEIGHT_PX = () -> CAMERA_HEIGHT
			- NETWORK_TABLE.getNumber("y0", 0.0) - 0.5 * NETWORK_TABLE.getNumber("height0", 0);
	// calculates the height of the center of the low reflective from the bottom
	// of the camera's field of view
	private static final Supplier<Double> LOW_REFLECTIVE_HEIGHT_PX = () -> CAMERA_HEIGHT
			- NETWORK_TABLE.getNumber("y1", 0.0) - 0.5 * NETWORK_TABLE.getNumber("height1", 0);

	// calculates the camera's view angle to the center of the high reflective
	// from the floor
	private static final Supplier<Double> cameraViewAngleToHighBoiler = () -> (CAMERA_VIEW_ANGLE_Y / CAMERA_HEIGHT)
			* HIGH_REFLECTIVE_HEIGHT_PX.get() + BOILER_CAMERA_ANGLE_TO_FLOOR.get();
	// calculates the camera's view angle to the center of the low reflective
	// from the floor
	private static final Supplier<Double> cameraViewAngleToLowBoiler = () -> (CAMERA_VIEW_ANGLE_Y / CAMERA_HEIGHT)
			* LOW_REFLECTIVE_HEIGHT_PX.get() + BOILER_CAMERA_ANGLE_TO_FLOOR.get();

	// calculates the distance from the boiler according to the high contour
	private static final Supplier<Double> distanceHigh = () -> (HIGH_REFLECTIVE_HEIGHT_IN
			- BOILER_CAMERA_HEIGHT_FROM_FLOOR_IN) / Math.tan(Math.toRadians(cameraViewAngleToHighBoiler.get()));
	// calculates the distance from the boiler according to the low contour
	private static final Supplier<Double> distanceLow = () -> (LOW_REFLECTIVE_HEIGHT_IN
			- BOILER_CAMERA_HEIGHT_FROM_FLOOR_IN) / Math.tan(Math.toRadians(cameraViewAngleToLowBoiler.get()));
	// calculates the distance from the boiler
	public static final Supplier<Double> distanceToBoiler = () -> (distanceHigh.get() + distanceLow.get()) / 2;

}
