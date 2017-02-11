package org.usfirst.frc.team2212.robot;

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
		public static final int DRIVE_LEFT_1 = 1;
		public static final int DRIVE_LEFT_2 = 4;
		public static final int DRIVE_RIGHT_1 = 6;
		public static final int DRIVE_RIGHT_2 = 2;
		public static final int CLIMBER = 3;
		public static final int SHOOTER = 5;
	}

	public interface PWM {
		public static final int PICKER = 0;
		public static final int GEAR_DROPPER = 1;// Cable tag: Gears
		public static final int BALL_BLOCKER = 2;
		public static final int FEEDER = 3;
		public static final int BLENDER = 4;// This is also part of the feeder
	}

	public interface DIO {
		public static final int GEAR_DROPPER_OPEN = 0;
		public static final int GEAR_DROPPER_CLOSED = 1;
		public static final int BALL_BLOCKER_OPEN = 8;
		public static final int BALL_BLOCKER_CLOSED = 9;
	}

	public interface AnalogInput {
	}
}
