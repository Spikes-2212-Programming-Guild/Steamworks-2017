package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.utils.RunnableCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2212.robot.ImageProcessingConstants;

/**
 *
 */
public class OrientToBoiler extends CommandGroup {
	public static final Supplier<Double> BOILER_CAMERA_ID = ConstantHandler
			.addConstantDouble("OrientToBoiler-CAMERA_ID", 0);

	public OrientToBoiler(Supplier<Double> turningSpeed) {
		addSequential(new RunnableCommand(
				() -> ImageProcessingConstants.NETWORK_TABLE.putNumber("currentCamera", BOILER_CAMERA_ID.get())));
		addSequential(new OrientToTwoTargets(turningSpeed, OrientToTwoTargets.KP.get(), OrientToTwoTargets.KI.get(),
				OrientToTwoTargets.KD.get(), OrientToTwoTargets.TOLERANCE.get()));
	}
}
