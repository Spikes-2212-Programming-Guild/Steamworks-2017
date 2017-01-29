package org.usfirst.frc.team2212.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class OrienteToGear extends Command{
	
	private String tableName; 
	private NetworkTable networkTable;
	
	private Supplier<Double> speed;
	private boolean isUpdated;
	public OrienteToGear(Supplier<Double> speed){
		networkTable = NetworkTable.getTable(tableName);
		this.speed = speed;
		requires(org.usfirst.frc.team2212.robot.Robot.drivetrain);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}

	@Override
	protected void execute() {
		org.usfirst.frc.team2212.robot.Robot.drivetrain.tankDrive(speed.get(), -speed.get());
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		super.end();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		super.interrupted();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
		return isUpdated;
	}

}
