package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.utils.RunnableCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2212.robot.ImageProcessingConstants;

/**
 *
 */
public class OrientToGear extends CommandGroup {
    public static final Supplier<Double> CAMERA_ID = ConstantHandler.addConstantDouble("OrientToGear-CAMERA_ID", 0);
    public static final Supplier<Double> KP = ConstantHandler.addConstantDouble("OrientToGear-KP", 0);
    public static final Supplier<Double> KI = ConstantHandler.addConstantDouble("OrientToGear-KI", 0);
    public static final Supplier<Double> KD = ConstantHandler.addConstantDouble("OrientToGear-KD", 0);
    public static final Supplier<Double> TOLERANCE = ConstantHandler.addConstantDouble("OrientToGear-TOLERANCE", 0);

    public OrientToGear(Supplier<Double> turningSpeed) {
        addSequential(new RunnableCommand(() -> ImageProcessingConstants.NETWORK_TABLE.putNumber("currentCamera", CAMERA_ID.get())));
        addSequential(new OrientToTwoTargets(turningSpeed, KP.get(), KI.get(), KD.get(), TOLERANCE.get()));
    }

}