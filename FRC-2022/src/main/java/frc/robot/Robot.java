// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  private Module backRight = new Module (Map.rotateBR, Map.driveBR, Map.encoderBR);
  private Module backLeft = new Module (Map.rotateBL, Map.driveBL, Map.encoderBL);
  private Module frontRight = new Module (Map.rotateFR, Map.driveFR, Map.encoderFR);
  private Module frontLeft = new Module (Map.rotateFL, Map.driveFL, Map.encoderFL);

  private Swerve swerve = new Swerve(backRight, backLeft, frontRight, frontLeft);

  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {
    Autonomous.SmartDashPrintData();
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {

    swerve.init();
  }


  @Override
  public void teleopPeriodic() {

    swerve.drive(Map.driver.getX(), Map.driver.getY(), Map.driver.getTwist());
    swerve.realignToField(Map.zeroGyro);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {

    swerve.disabled();
  }

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
