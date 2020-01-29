package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase
{
  private WPI_TalonSRX m_stringPuller;
  private WPI_TalonSRX m_reel;

  private DoubleSolenoid m_PTO;

  private Servo m_ratchetLeft;
  private Servo m_ratchetRight;

  /**
   * Displays current desired position of reel. 
   * Used for jogging up and down feature and displaying on SmartDashboard for operator.
   */
  private int reelCurrentPosition = 0;
  
  public Climber()
  {
    m_stringPuller = new WPI_TalonSRX(Constants.CLIMBER_STRING_PULLER_ID);
    RobotContainer.configureTalonSRX(m_stringPuller, true, FeedbackDevice.CTRE_MagEncoder_Relative, false, false, Constants.CLIMBER_STRING_PULLER_F, 
                            Constants.CLIMBER_STRING_PULLER_P, Constants.CLIMBER_STRING_PULLER_I, Constants.CLIMBER_STRING_PULLER_D, 
                            Constants.CLIMBER_STRING_PULLER_CRUISEVELOCITY, Constants.CLIMBER_STRING_PULLER_ACCELERATION);
    m_reel = new WPI_TalonSRX(Constants.CLIMBER_REEL_ID);
    RobotContainer.configureTalonSRX(m_stringPuller, true, FeedbackDevice.CTRE_MagEncoder_Relative, false, false, Constants.CLIMBER_REEL_F, 
                            Constants.CLIMBER_REEL_P, Constants.CLIMBER_REEL_I, Constants.CLIMBER_REEL_D, 
                            Constants.CLIMBER_REEL_CRUISEVELOCITY, Constants.CLIMBER_REEL_ACCELERATION);

    m_PTO = new DoubleSolenoid(Constants.CLIMBER_PTO_PORT_A, Constants.CLIMBER_PTO_PORT_B);

    m_ratchetLeft = new Servo(Constants.CLIMBER_RATCHET_LEFT_PORT);
    m_ratchetRight = new Servo(Constants.CLIMBER_RATCHET_RIGHT_PORT);
    
  }

  /**
   * Sets the desired position of the reel.
   * @param position will be used as the setpoint for the PID controller
   */
  public void setPosition(int position)
  {
    /* Verify desired position is within the minimum and maximum reel range. */
    if (position < Constants.CLIMBER_REEL_MIN_POSITION)
      position = Constants.CLIMBER_REEL_MIN_POSITION;
    else if (position > Constants.CLIMBER_REEL_MAX_POSITION)
      position = Constants.CLIMBER_REEL_MAX_POSITION;

    /* Update current desired position variable for other features like jogging up and down. */
    this.reelCurrentPosition = position;

    /**
     * Convert desired position to encoder ticks for PID setpoint.
     * To do this, use pheonix software to get several points with x = position and y = encoder ticks.
     * Compile points into a table and use linear regression (y=mx+b) to find an equation to convert position (x) to encoder ticks (y).
     */
    int setpoint = position; // TODO: Find equation and convert position to setpoint.

    m_reel.set(ControlMode.MotionMagic, setpoint);
  }

  /**
   * Returns the current reel position.
   * Used for jogging the reel position up and down by taking the current position and adding the jog magnitude.
   */
  public int getCurrentPosition()
  {
    return this.reelCurrentPosition;
  }
 
  /**
   * This method will be called once per scheduler run.
   */
  @Override
  public void periodic()
  {
  }

  /**
   * Methods for Robot.java to get TalonFX/TalonSRX objects to pass to the SetPIDValues command to configure PIDs via SmartDashboard.
   * @return TalonFX/TalonSRX object to be configured.
   */
  public WPI_TalonSRX getStringPullerTalonSRX()
  {
      return m_stringPuller;
  }
}
  