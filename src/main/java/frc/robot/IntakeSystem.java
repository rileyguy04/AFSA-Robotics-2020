package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class IntakeSystem {
    private static CANSparkMax intakeMotor;
    private static CANSparkMax liftMotor;
    /*public IntakeSystem(CANSparkMax intakeMotor,CANSparkMax liftMotor){
        this.intakeMotor = intakeMotor;
        this.liftMotor = liftMotor;
    }*/
    public IntakeSystem(RobotMap.MotorControllerID intakeMotorID, RobotMap.MotorControllerID liftMotorID){
        intakeMotor = new CANSparkMax(intakeMotorID.getID(),MotorType.kBrushless);
        intakeMotor.setSmartCurrentLimit(30);
        liftMotor = new CANSparkMax(liftMotorID.getID(),MotorType.kBrushless);
        liftMotor.setSmartCurrentLimit(30);
    }
    public void stop(){
        intakeMotor.stopMotor();
        liftMotor.stopMotor();
    }
    public void intakeBall(){
        intakeMotor.set(OI.getInstance().intakeSpeed());
        liftMotor.set(OI.getInstance().liftSpeed());
    }
    public void outputBall(){
        intakeMotor.set(-OI.getInstance().intakeSpeed());
        liftMotor.set(-OI.getInstance().liftSpeed());
    }
    public void rejectBall(){
        intakeMotor.set(-OI.getInstance().intakeSpeed());
    }
}