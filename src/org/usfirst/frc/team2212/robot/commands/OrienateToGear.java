package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.utils.RunnableCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2212.robot.ImageProcessingConstants;

/**
 *
 */
public class OrienateToGear extends CommandGroup {
    public static final Supplier<Double> CAMERA_ID = ConstantHandler.addConstantDouble("OrienateToGear-CAMERA_ID", 0);
    public static final Supplier<Double> KP = ConstantHandler.addConstantDouble("OrienateToGear-KP", 0);
    public static final Supplier<Double> KI = ConstantHandler.addConstantDouble("OrienateToGear-KI", 0);
    public static final Supplier<Double> KD = ConstantHandler.addConstantDouble("OrienateToGear-KD", 0);
    public static final Supplier<Double> TOLERANCE = ConstantHandler.addConstantDouble("OrienateToGear-TOLERANCE", 0);

    public OrienateToGear(Supplier<Double> turningSpeed) {
        addSequential(new RunnableCommand(() -> ImageProcessingConstants.NETWORK_TABLE.putNumber("currentCamera", CAMERA_ID.get())));
        addSequential(new OrientToTwoTargets(turningSpeed, KP.get(), KI.get(), KD.get(), TOLERANCE.get()));
    }

}
