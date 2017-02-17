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
		public static final int FEEDER=0;//FIXME enter real port
		public static final int GEAR_DROPPER = 1;
		public static final int PICKER = 2;
		public static final int BALL_BLOCKER = 4;
	}

	public interface DIO {
		public static final int DRIVE_LEFT_ENCODER_A = 0;
		public static final int DRIVE_LEFT_ENCODER_B = 1;
		public static final int BALL_BLOCKER_UP = 2;
		public static final int BALL_BLOCKER_DOWN = 3;
		public static final int GEAR_DROPPER_OPEN = 4;
		public static final int GEAR_DROPPER_CLOSE = 5;
		public static final int SHOOTER_ENCODER_A = 6;
		public static final int SHOOTER_ENCODER_B = 7;
		public static final int DRIVE_RIGHT_ENCODER_A = 8;
		public static final int DRIVE_RIGHT_ENCODER_B = 9;
	}

	public interface AnalogInput {
	}
}
