// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.MoveArmWithXboxCmd;
import frc.robot.commands.WPIArmProfiledPIDCmd;
import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
      private final ArmSubsystem armSubsystem = new ArmSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
      armSubsystem.setDefaultCommand(new MoveArmWithXboxCmd(armSubsystem, 0));  // ??????

    configureBindings();
  }

 
  private void configureBindings() {
     m_driverController.a().onTrue(new WPIArmProfiledPIDCmd(armSubsystem, 0)); // Move rot * gearing = 4 now
     m_driverController.b().onTrue(new WPIArmProfiledPIDCmd(armSubsystem, 0.5)); 
     m_driverController.x().onTrue(new WPIArmProfiledPIDCmd(armSubsystem, 1));
//     m_driverController.y().onTrue(new WPIArmProfiledPIDCmd(armSubsystem, 2));

     m_driverController.rightBumper().whileTrue(new MoveArmWithXboxCmd(armSubsystem, 0.04));
     m_driverController.leftBumper().whileTrue(new MoveArmWithXboxCmd(armSubsystem, -0.04));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
