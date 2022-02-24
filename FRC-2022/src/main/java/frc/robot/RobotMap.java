package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Drivetrain.Module;
import frc.robot.Drivetrain.Swerve;
import frc.robot.Manipulators.Intake;
import frc.robot.Manipulators.Mortar;

public class RobotMap {

    // drivetrain motors
   public static int rotateBL = 8;
   public static int rotateBR = 2;
   public static int rotateFL = 6;
   public static int rotateFR = 4;
   public static int driveBL = 7;
   public static int driveBR = 1;
   public static int driveFL = 5;
   public static int driveFR = 3;

   // mortar motors
   public static int mortarLeft = 40;
   public static int mortarRight = 41;
   public static TalonFX mortarVelocity = new TalonFX(40);

   // intake motors
   public static int intakeMain = 10;
   public static int lowerMortarIntake = 0;
   public static int upperMortarIntake = 11;

   // intake pistion
   public static int lift = 15;

   // controllers
   public static Joystick driver = new Joystick(0); 
   public static int intakeLift = 1;
   public static int track = 2;
   public static int zeroGyro = 11;
   public static int cutSpeed = 12;

   public static XboxController operator = new XboxController(1);
   public static int led = 1;
   public static int scoreLow = 6;


   // encoders
   public static int encoderFL = 22;
   public static int encoderBR = 20;
   public static int encoderFR = 21;
   public static int encoderBL = 23;

   // gyro
   public static TalonSRX gyroTalon = new TalonSRX(0);
   public static PigeonIMU gyro = new PigeonIMU(gyroTalon);

   // modules
   public static Module backRight = new Module (rotateBR, driveBR, encoderBR);
   public static Module backLeft = new Module (rotateBL, driveBL, encoderBL);
   public static Module frontRight = new Module (rotateFR, driveFR, encoderFR);
   public static Module frontLeft = new Module (rotateFL, driveFL, encoderFL);
 
   // swerve drive
   public static Swerve swerve = new Swerve(backRight, backLeft, frontRight, frontLeft);

   // intake
   public static Intake intake = new Intake(intakeMain, lowerMortarIntake, upperMortarIntake, lift);

   // mortar

   public static Mortar mortar = new Mortar(mortarLeft, mortarRight);

   // Pnuematics
   public static Compressor compressor = new Compressor(1, PneumaticsModuleType.REVPH);
   
}
