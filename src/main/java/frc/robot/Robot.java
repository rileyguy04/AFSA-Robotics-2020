/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private ShooterSystem shooterSystem;
  private IntakeSystem intakeSystem;
  private ClimbSystem climbSystem;
  private DriveTrain driveTrain;
  private static UsbCamera usbCam0;
  private static UsbCamera usbCam1;
  private static VideoSink server;
  private static double leftAutoSpeed;
  private static double rightAutoSpeed;
  private static double leftAutoTarget;
  private static double rightAutoTarget;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    shooterSystem = new ShooterSystem(RobotMap.MotorControllerID.orange,RobotMap.MotorControllerID.pink);
    intakeSystem = new IntakeSystem(RobotMap.MotorControllerID.purple,RobotMap.MotorControllerID.blue);
    driveTrain = new DriveTrain(RobotMap.MotorControllerID.white, RobotMap.MotorControllerID.green, RobotMap.MotorControllerID.grey, RobotMap.MotorControllerID.yellow);
    climbSystem = new ClimbSystem(RobotMap.MotorControllerID.climbController1, RobotMap.MotorControllerID.climbController2);
    usbCam0 = CameraServer.getInstance().startAutomaticCapture(0);
    usbCam1 = CameraServer.getInstance().startAutomaticCapture(1);
    server = CameraServer.getInstance().getServer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    shooterSystem.rpm();
    shooterSystem.stickSpeed();
    shooterSystem.shooterTemp();
    shooterSystem.displayFaults();
    shooterSystem.shooterCurrent();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    leftAutoTarget = driveTrain.getLeftPosition()+50;
    rightAutoTarget = driveTrain.getRightPosition()+50;
    leftAutoSpeed = .5;
    rightAutoSpeed = .5;
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  @Override
  public void teleopPeriodic() {
    //Controll Intake System
    if(OI.getInstance().rejectBallOn()){
      intakeSystem.rejectBall();
    }else if(OI.getInstance().liftUpButtonHeld()){
      intakeSystem.intakeBall();
    } else if(OI.getInstance().liftDownButtonHeld()){
      intakeSystem.outputBall();
    }else{
      intakeSystem.stop();
    }

    //Controll Shooter System
    if(OI.getInstance().shooterButtonPressed()){
      shooterSystem.shooterON();
    }else{
      shooterSystem.shooterStop();
    }
    if(OI.getInstance().dartUpButtonHeld()){
      shooterSystem.dartUp();
    }else if(OI.getInstance().dartDownButtonHeld()){
      shooterSystem.dartDown();
    }else{
      shooterSystem.dartStop();
    }

    //Controll Drive System with deadzone
    if(Math.abs(OI.getInstance().leftStick.getY()) > OI.getInstance().minSignal() && Math.abs(OI.getInstance().rightStick.getY()) > OI.getInstance().minSignal()){
      driveTrain.bothDrive();
    } else if (Math.abs(OI.getInstance().leftStick.getY()) < OI.getInstance().minSignal()){
      driveTrain.rightDriveMove();
    } else if(Math.abs(OI.getInstance().rightStick.getY()) < OI.getInstance().minSignal()){
      driveTrain.leftDriveMove();
    } else {
      driveTrain.stop();
    }

    //Controll Camera
    if(OI.getInstance().cameraSwich()){
      if(server.getSource().equals(usbCam0)){
        server.setSource(usbCam1);
      } else if(server.getSource().equals(usbCam1)){
        server.setSource(usbCam0);
      } else {
        System.out.println("Camera Server Source Error");
      }
    }

    //Controll Climb System
    if(OI.getInstance().deployClimbButtonHeld()){
      climbSystem.deployClimb();
    }else if(OI.getInstance().climbButtonHeld()){
      climbSystem.climb();
    }else{
      climbSystem.stopClimb();
    }
  }
  @Override
  public void testPeriodic(){
    
  }
}