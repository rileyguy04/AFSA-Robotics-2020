package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ClimbSystem{
    private static TalonSRX climbController1;
    private static TalonSRX climbController2;

    public ClimbSystem(RobotMap.MotorControllerID climbController1ID, RobotMap.MotorControllerID climbController2ID){
        climbController1 = new TalonSRX(climbController1ID.getID());
        climbController2 = new TalonSRX(climbController2ID.getID());
    }
    public void deployClimb(){
        climbController1.set(TalonSRXControlMode.PercentOutput, OI.getInstance().climbDeploySpeed());
        climbController2.set(TalonSRXControlMode.PercentOutput, OI.getInstance().climbDeploySpeed());
    }
    public void stopClimb(){
        climbController1.set(TalonSRXControlMode.PercentOutput, 0);
        climbController2.set(TalonSRXControlMode.PercentOutput, 0);
    }
    public void climb(){
        climbController1.set(TalonSRXControlMode.PercentOutput, OI.getInstance().climbSpeed());
        climbController2.set(TalonSRXControlMode.PercentOutput, OI.getInstance().climbSpeed());
    }
}