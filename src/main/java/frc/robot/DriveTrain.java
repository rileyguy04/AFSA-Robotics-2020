package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap.MotorControllerID;

public class DriveTrain{
    private static CANSparkMax leftDrive1;
    private static CANSparkMax leftDrive2;
    private static CANSparkMax rightDrive1;
    private static CANSparkMax rightDrive2;
    private static SpeedControllerGroup leftDrive;
    private static SpeedControllerGroup rightDrive;
    private static DifferentialDrive driveTrain;
    public DriveTrain(RobotMap.MotorControllerID leftDrive1ID, RobotMap.MotorControllerID leftDrive2ID, RobotMap.MotorControllerID rightDrive1ID, RobotMap.MotorControllerID rightDrive2ID){
        leftDrive1 = new CANSparkMax(leftDrive1ID.getID(),MotorType.kBrushless);
		leftDrive1.setSmartCurrentLimit(40);
        leftDrive2 = new CANSparkMax(leftDrive2ID.getID(),MotorType.kBrushless);
		leftDrive2.setSmartCurrentLimit(40);
        rightDrive1 = new CANSparkMax(rightDrive1ID.getID(),MotorType.kBrushless);
		rightDrive1.setSmartCurrentLimit(40);
        rightDrive2 = new CANSparkMax(rightDrive2ID.getID(),MotorType.kBrushless);
		rightDrive2.setSmartCurrentLimit(40);
        leftDrive = new SpeedControllerGroup(leftDrive1, leftDrive2);
        rightDrive = new SpeedControllerGroup(rightDrive1, rightDrive2);
        driveTrain = new DifferentialDrive(leftDrive, rightDrive);
    }
    public void stop(){
        driveTrain.stopMotor();
    }
    public void rightDriveMove(){
        driveTrain.tankDrive(0, -OI.getInstance().rightStick.getY());
    }
    public void leftDriveMove(){
        driveTrain.tankDrive(-OI.getInstance().leftStick.getY(), 0);
    }
    public void bothDrive(){
        driveTrain.tankDrive(-OI.getInstance().leftStick.getY(), -OI.getInstance().rightStick.getY());
    }
    public double getLeftPosition(){
        return leftDrive1.getEncoder().getPosition();
    }
    public double getRightPosition(){
        return rightDrive1.getEncoder().getPosition();
    }
}