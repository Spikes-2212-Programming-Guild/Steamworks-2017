package org.usfirst.frc.team2212.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class ImageProssecingConstants {
	public static final Supplier<String> TABLE_NAME = ConstantHandler.addConstantString("OrienteToTwoObjects-tableName",
			"ImageProcessing");
	public static final Supplier<Integer> CAMERA_WIDTH = () -> 0;
	public static final NetworkTable NETWORK_TABLE = NetworkTable.getTable(TABLE_NAME.get());
	public static Supplier<Double> GEARS_SET_POINT = () -> ((ImageProssecingConstants.NETWORK_TABLE.getNumber("x0", 0)
			+ 0.5 * ImageProssecingConstants.NETWORK_TABLE.getNumber("width0", 0)
			+ (ImageProssecingConstants.NETWORK_TABLE.getNumber("x1", 0)
					+ 0.5 * ImageProssecingConstants.NETWORK_TABLE.getNumber("width1", 0))
					/ (2 * ImageProssecingConstants.CAMERA_WIDTH.get()))
			- 0.5);
	public static Supplier<Double> tolerance = ConstantHandler.addConstantDouble("Tolerance", 0);
	public static PIDSource leftSource = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public double pidGet() {
			return -GEARS_SET_POINT.get();
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};
	public static PIDSource rightSource = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public double pidGet() {
			return GEARS_SET_POINT.get();
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};
}
