package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutonomousThreeBall extends SequentialCommandGroup
{
  public AutonomousThreeBall(int direction, double duration)
  {
    addCommands(
        new InstantCommand(() -> RobotContainer.m_limelight.turnOnLED()),
        new InstantCommand(() -> RobotContainer.m_launcher.setPreset(Constants.LAUNCHER_AUTO_INIT_TOP_RPM, Constants.LAUNCHER_AUTO_INIT_BOTTOM_RPM, Constants.LAUNCHER_AUTO_INIT_ANGLE)),
        new WaitCommand(3.0),
        new InstantCommand(() -> RobotContainer.m_chamber.setElevatorPosition(50000)),
        new WaitCommand(6.0),
        new InstantCommand(() -> RobotContainer.m_launcher.setTopWheelPower(0.0)),
        new InstantCommand(() -> RobotContainer.m_launcher.setBottomWheelPower(0.0)),
        new InstantCommand(() -> RobotContainer.m_launcher.setPivotPower(0.0)),
        new InstantCommand(() -> RobotContainer.m_limelight.turnOffLED()),
        new InstantCommand(() -> RobotContainer.m_chassis.autoDrive(direction * 0.25, 0.0)),
        new WaitCommand(duration),
        new InstantCommand(() -> RobotContainer.m_chassis.autoDrive(0.0, 0.0))
    );
  }
}