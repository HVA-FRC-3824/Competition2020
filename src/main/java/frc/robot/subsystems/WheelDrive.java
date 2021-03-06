package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class WheelDrive extends SubsystemBase
{
    private TalonFX m_speedMotor;
    private TalonFX m_angleMotor;
    private PIDController m_pidController;

    public WheelDrive(int m_angleMotor, int m_speedMotor, int encoder)
    {
        this.m_angleMotor = new TalonFX(m_angleMotor);
        this.m_speedMotor = new TalonFX(m_speedMotor);
        // m_pidController = new PIDController(1, 0, 0, new AnalogInput(encoder), this.m_angleMotor);

        // m_pidController.setOutputRange(-1, 1);
        // m_pidController.setContinuous();
        // m_pidController.enable();
    }

    public void drive(double speed, double angle)
    {
        
    }
}