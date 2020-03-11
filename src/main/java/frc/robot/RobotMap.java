package frc.robot;

public class RobotMap{
    enum MotorControllerID{
        pink(1),
        orange(2),
        yellow(3),
        blue(4),
        green(5),
        purple(6),
        white(7),
        grey(8),
        climbController1(10),
        climbController2(11);
        private final int ID;
        private MotorControllerID(int ID){
            this.ID = ID;
        }
        public int getID(){
            return ID;
        }
    }
}