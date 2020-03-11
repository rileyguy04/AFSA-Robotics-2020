package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterSystem {
    private static CANSparkMax shooterMotor;
    private static CANSparkMax dartMotor;
    /*public ShooterSystem(CANSparkMax shooterMotor,CANSparkMax dratMotor){
        this.shooterMotor = shooterMotor;
        this.dartMotor = dartMotor;
    }*/
    public ShooterSystem(RobotMap.MotorControllerID shooterMotorID, RobotMap.MotorControllerID dartMotorID){
        shooterMotor = new CANSparkMax(shooterMotorID.getID(),MotorType.kBrushless);
        shooterMotor.setSmartCurrentLimit(40);
        dartMotor = new CANSparkMax(dartMotorID.getID(),MotorType.kBrushless);
        shooterMotor.setSmartCurrentLimit(20);
    }
    public void shooterStop(){
        shooterMotor.stopMotor();
        SmartDashboard.putNumber("Shooter Expected Speed", 0);
    }
    public void shooterON(){
        shooterMotor.set(OI.getInstance().shooterSpeed());
        SmartDashboard.putNumber("Shooter Expected Speed", OI.getInstance().shooterSpeed());
    }
    public void dartStop(){
        dartMotor.stopMotor();
    }
    public void dartUp(){
        dartMotor.set(OI.getInstance().dartSpeed());
    }
    public void dartDown(){
        dartMotor.set(-OI.getInstance().dartSpeed());
    }
    public void rpm(){
        SmartDashboard.putNumber("RPM: ", shooterMotor.getEncoder().getVelocity());
    }
    public void stickSpeed(){
        SmartDashboard.putNumber("Shooter Speed Output", OI.getInstance().shooterSpeed());
    }
    public void shooterTemp(){
        SmartDashboard.putNumber("Shooter Temp", shooterMotor.getMotorTemperature());
    }
    public void shooterCurrent(){
        SmartDashboard.putNumber("Shooter Current", shooterMotor.getOutputCurrent());
    }
    public void displayFaults(){
        SmartDashboard.putNumber("Shooter Faults", shooterMotor.getFaults());
        SmartDashboard.putNumber("Dart Faults", dartMotor.getFaults());
    }
}