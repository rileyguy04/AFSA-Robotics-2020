package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
    private static OI instance;
    public static OI getInstance(){
        if (instance == null){
            instance = new OI();
        }
        return instance;
    }

    public Joystick rightStick;
    public Joystick leftStick;

    public OI(){
        leftStick = new Joystick(0);
        rightStick = new Joystick(1);
    }
    private boolean shooterPressed = false;
    public boolean shooterButtonPressed(){
        if(rightStick.getTriggerPressed()){
            shooterPressed = !shooterPressed;
        }
        SmartDashboard.putBoolean("OI Shooter Pressed", shooterPressed);
        return shooterPressed;
    }
    public double shooterSpeed(){
        return ((rightStick.getThrottle()+1)/2);
    }
    public double liftSpeed(){
        return SmartDashboard.getNumber("Lift Speed", -0.2);
    }
    public double intakeSpeed(){
        return SmartDashboard.getNumber("Intake Speed", -0.2);
    }
    public double dartSpeed(){
        return SmartDashboard.getNumber("Dart Speed", 0.1);
    }
    public double minSignal(){
        return SmartDashboard.getNumber("Min Signal", 0.1);
    }
    private boolean inYouSuck = false;
    public boolean inYouSuck(){
        if(rightStick.getRawButton(12)){
            inYouSuck = !inYouSuck;
        }
        return inYouSuck;
    }
    public boolean liftUpButtonHeld(){
        return rightStick.getRawButton(6);
    }
    public boolean liftDownButtonHeld(){
        return rightStick.getRawButton(4);
    }
    private boolean rejectBallOn = false;
    public boolean rejectBallOn(){
        if(rightStick.getRawButtonPressed(3)){
            rejectBallOn = !rejectBallOn;
        }
        return rejectBallOn;
    }
    public boolean dartUpButtonHeld(){
        return leftStick.getRawButton(6);
    }
    public boolean dartDownButtonHeld(){
        return leftStick.getRawButton(4);
    }
    public boolean cameraSwich(){
        return rightStick.getRawButtonPressed(2);
    }
    public double climbDeploySpeed(){
        return SmartDashboard.getNumber("Climb Speed", 0.5);
    }
    public double climbSpeed(){
        return SmartDashboard.getNumber("Climb Speed", -1);
    }
    public boolean climbButtonHeld(){
        return rightStick.getRawButtonPressed(9);
    }
    public boolean deployClimbButtonHeld(){
        return rightStick.getRawButtonPressed(11);
    }
}