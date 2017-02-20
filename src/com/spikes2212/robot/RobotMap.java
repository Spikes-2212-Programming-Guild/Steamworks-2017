package com.spikes2212.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public interface USB {
	}

	public interface CAN {
		public static final int DRIVE_LEFT_1 = 6;
		public static final int DRIVE_LEFT_2 = 3;
		public static final int DRIVE_RIGHT_1 = 5;
		public static final int DRIVE_RIGHT_2 = 1;
		public static final int CLIMBER = 2;
		public static final int SHOOTER = 4;
	}

	public interface PWM {
		public static final int FEEDER = 3;
		public static final int BLENDER = 2;// part of the feeder subsystem
		public static final int GEAR_DROPPER = 1;
		public static final int PICKER = 0;
		public static final int BALL_BLOCKER = 4;
	}

	public interface DIO {
		public static final int DRIVE_LEFT_ENCODER_A = 6;
		public static final int DRIVE_LEFT_ENCODER_B = 7;
		public static final int BALL_BLOCKER_UP = 3;
		public static final int BALL_BLOCKER_DOWN = 2;
		public static final int GEAR_DROPPER_OPEN = 0;
		public static final int GEAR_DROPPER_CLOSE = 1;
		public static final int SHOOTER_ENCODER_A = 4;
		public static final int SHOOTER_ENCODER_B = 5;
		public static final int DRIVE_RIGHT_ENCODER_A = 8;
		public static final int DRIVE_RIGHT_ENCODER_B = 9;
	}

	public interface AnalogInput {
	}
}
