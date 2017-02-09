package org.usfirst.frc.team2212.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class ImageProcessingConstants {
	public static NetworkTable NETWORK_TABLE = NetworkTable.getTable("ImageProcessing");
	public static final Supplier<Integer> CAMERA_WIDTH = ConstantHandler.addConstantInt("CAMERA_WIDTH", 640);
	public static final Supplier<Double> BIG_OBJECT_CENTER = () -> ((NETWORK_TABLE.getNumber("x0", 0)
			+ 0.5 * NETWORK_TABLE.getNumber("width0", 0)) / CAMERA_WIDTH.get() - 0.5);
	public static final Supplier<Double> SMALL_OBJECT_CENTER = () -> ((NETWORK_TABLE.getNumber("x1", 0)
			+ 0.5 * NETWORK_TABLE.getNumber("width1", 0)) / CAMERA_WIDTH.get() - 0.5);
	public static Supplier<Double> TWO_OBJECTS_CENTER = () -> (BIG_OBJECT_CENTER.get() + SMALL_OBJECT_CENTER.get()) / 2;
	public static PIDSource leftSource = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public double pidGet() {
			return TWO_OBJECTS_CENTER.get();
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
			return TWO_OBJECTS_CENTER.get();
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};

}
