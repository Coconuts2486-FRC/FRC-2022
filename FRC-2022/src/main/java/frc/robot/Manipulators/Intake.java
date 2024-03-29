package frc.robot.Manipulators;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ColorSensor;
import frc.robot.RobotMap;

public class Intake {
    public static SendableChooser allianceChooser = new SendableChooser<>();

    // intake components
    private TalonFX intakeMain;
    private TalonSRX lowerMortarIntake;
    private TalonSRX upperMortarIntake;
    private Solenoid lift;
    private static boolean pistonactive = false;
    private static boolean sensorActive = false;


    // intake constructor
    public Intake(int intakeMain, int lowarMortarIntake, int upperMortarIntake, int lift){

        this.intakeMain = new TalonFX(intakeMain);
        this.lowerMortarIntake = new TalonSRX(lowarMortarIntake);
        this.upperMortarIntake = new TalonSRX(upperMortarIntake);
        this.lift = new Solenoid(PneumaticsModuleType.CTREPCM, lift);
    }

    // initializes intake components
    public void init(){
        intakeMain.setNeutralMode(NeutralMode.Brake);
        lowerMortarIntake.setNeutralMode(NeutralMode.Brake);
        upperMortarIntake.setNeutralMode(NeutralMode.Brake);
        intakeMain.configOpenloopRamp(0);
        lowerMortarIntake.configOpenloopRamp(0.1);
        upperMortarIntake.configOpenloopRamp(0.1);
        lift.set(false);
        RobotMap.intakeTimer = (float) Timer.getFPGATimestamp();
    }

    // init intake in auto
    public void autoInit(){

        intakeMain.setNeutralMode(NeutralMode.Brake);
        lowerMortarIntake.setNeutralMode(NeutralMode.Brake);
        upperMortarIntake.setNeutralMode(NeutralMode.Brake);
        intakeMain.configOpenloopRamp(0);
        lowerMortarIntake.configOpenloopRamp(0);
        upperMortarIntake.configOpenloopRamp(0);
        lift.set(false);
        RobotMap.intakeTimer = (float) Timer.getFPGATimestamp();
    }

    public void autoDropArm(){

        lift.set(true);
    }

    // allows us to select our alliance so that the intake rejects the opposing alliance balls
    public static void chooser() {

        // returns the opposite alliance because that's the ball we want to spit out
        allianceChooser.setDefaultOption("Red", "Blue");
        allianceChooser.addOption("Blue", "Red");
        SmartDashboard.putData("Alliance", allianceChooser);

    }

    public void armControl(boolean liftArm){

        // intake arm control
        if (liftArm){
            if (!pistonactive){
                lift.set(true);
                pistonactive = true;
            } else{
 
                lift.set(false);
                pistonactive = false;
            }
        }
    }

    // intake control
    public void run(boolean intake, boolean mortarIntake, boolean outtake){

        // main intake control; rejects opposing alliance balls
        if (Timer.getFPGATimestamp() - RobotMap.outtakingTimer < 0.5) {
            lift.set(false);
            intakeMain.set(ControlMode.PercentOutput, -0.9);
        } else if (intake || mortarIntake){

            intakeMain.set(ControlMode.PercentOutput, 1);
        } else {
            intakeMain.set(ControlMode.PercentOutput, 0);
            RobotMap.outtaking = false;
        }

       /* if (ColorSensor.DetectedColor().equals(allianceChooser.getSelected()) && !RobotMap.outtaking) {

            RobotMap.outtaking = true;
            RobotMap.outtakingTimer = Timer.getFPGATimestamp();
        }
*/     
        // outtake
        if (outtake){

            intakeMain.set(ControlMode.PercentOutput, -1);
        }

        // secondary intake control

        /* || RobotMap.mortar.getVelocity() > ((RobotMap.mortar.calculateVelocity(LimeLight.getY()) + RobotMap.mortar.adjustVelocity()) - RobotMap.threshold) && RobotMap.operator.getRawButton(RobotMap.override) == false*/
        if (mortarIntake){

            lowerMortarIntake.set(ControlMode.PercentOutput, 1);
            upperMortarIntake.set(ControlMode.PercentOutput, 1);
        } else{

            lowerMortarIntake.set(ControlMode.PercentOutput, 0);
            upperMortarIntake.set(ControlMode.PercentOutput, 0);
        }

        // change threshhold
        /*if (RobotMap.intakeTimer - Timer.getFPGATimestamp() > 90) {
            RobotMap.threshold = 125;
        }*/
    }
}   
