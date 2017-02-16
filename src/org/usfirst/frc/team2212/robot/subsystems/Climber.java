package org.usfirst.frc.team2212.robot.subsystems;

import java.util.function.Supplier;

import com.ctre.CANTalon;
import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.LimitedSubsystem;

import edu.wpi.first.wpilibj.PIDSource;

public class Climber extends LimitedSubsystem {
    private CANTalon motor;
    public static final Supplier<Double> MAX_CURRENT = ConstantHandler.addConstantDouble("Climber-MAX_CURRENT", 20);
    public static final Supplier<Double> climbingSpeed = ConstantHandler.addConstantDouble("Climber-climbingSpeed", 0.7);

    public Climber(CANTalon motor) {
        this.motor = motor;
        this.motor.setInverted(true);
    }

    @Override
    public boolean isMin() {
        // TODO Auto-generated method sub
        return false;
    }

    @Override
    public boolean isMax() {
        return motor.getOutputCurrent() >= MAX_CURRENT.get();
    }

    @Override
    public PIDSource getPIDSource() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void move(double speed) {
        motor.set(speed);
    }
}
