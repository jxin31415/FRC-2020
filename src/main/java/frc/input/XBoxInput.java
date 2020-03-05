package frc.input;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * The input from the XBox remote
 */
public class XBoxInput extends InputMethod {
  private XboxController controller;
  private final double JOYSTICK_DEAD_ZONE = 0.075;
  private boolean shouldShoot;

  public XBoxInput() {
    // the joystick is registered as port #0
    controller = new XboxController(0);
    shouldShoot = false;
  }

  @Override
  public double leftSidePower() {
    double forward = controller.getY(Hand.kLeft);
    if(Math.abs(forward) < JOYSTICK_DEAD_ZONE)
      return 0;
    return forward;
  }

  @Override
  public double rightSidePower() {
    double forward = controller.getY(Hand.kRight);
    if(Math.abs(forward) < JOYSTICK_DEAD_ZONE)
      return 0;
    return forward;
  }

  @Override
  public int shouldIntake(){
    if(controller.getBumper(Hand.kLeft))
      return -1;
    return (controller.getTriggerAxis(Hand.kLeft) > 0.75 || controller.getTriggerAxis(Hand.kRight) > 0.75) ? 1 : 0;
  }

  @Override
  public int shouldIndex(){
    if(controller.getBumper(Hand.kRight))
      return -1;
    return (controller.getXButton()) ? 1 : 0;
  }

  @Override
  public int shouldSpinConveyer(){
    if(controller.getBumper(Hand.kRight))
      return -1;
    return (controller.getYButton()) ? 1 : 0;
  }

  @Override
  public boolean shouldShoot(){
    if(controller.getAButton()){
      shouldShoot = true;
    }
    if(controller.getBButton()){
      shouldShoot = false;
    }
    return shouldShoot;
  }
}