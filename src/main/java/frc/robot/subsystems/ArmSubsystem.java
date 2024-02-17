// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {

  CANSparkMax armNeo;
  RelativeEncoder armEncoder;

  public ArmSubsystem() {
    armNeo = new CANSparkMax(22, MotorType.kBrushless);
    armNeo.restoreFactoryDefaults();

  //  armEncoder = armNeo.getEncoder(SparkRelativeEncoder.Type.kHallSensor,42); // Internal relative encoder
    armEncoder = armNeo.getEncoder();  // same as above for internal encoder
    
   //  armEncoder = armNeo.getEncoder(SparkRelativeEncoder.Type.kQuadrature, 4096);   Rev thru-bore encoder
    armEncoder.setPosition(0);


    armNeo.setSoftLimit(SoftLimitDirection.kForward, 31.25f);  // Arm straight up = 125:1 gearing * 1/4 of one rotation
    armNeo.setSoftLimit(SoftLimitDirection.kReverse, 0.0f);
    armNeo.enableSoftLimit(SoftLimitDirection.kForward, true); 
    armNeo.enableSoftLimit((SoftLimitDirection.kReverse), true);

  }



  @Override
  public void periodic() {
    SmartDashboard.putNumber("Encoder Position Value", armEncoder.getPosition());
  }

  public void setArmSpeed(double speed) {
      armNeo.set(speed);
  }

  public double getArmPosition() {
      return armEncoder.getPosition();
  }


}
