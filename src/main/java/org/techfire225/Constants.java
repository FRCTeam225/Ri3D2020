package org.techfire225;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Constants {

    public static boolean isCompbot = true;
    private static Constants instance = null;

    public static Constants getConstants() {
        return new Constants();
    }
        
    public double TriggerDeadband = 0.1;
    public double DriveEncoder_TickConversion = (1.0/256.0)*(1.0/3.0)*(24.0/60.0)*(6.0*Math.PI);

    public double wheelNonLinearity = 0.5;
    public double turnGain = 0.3;
    public double turnVelocityMultiplierGain = 1;
       
        //DRIVE PROFILE CONSTANTS
        public double Drive_kP = 0.1;
        public double Drive_kF = 0.01;
        public double Drive_TurnHold_kP = 0.005;
        public double Drive_Turn_kP = 0;
        public double Drive_Turn_kD = 0;
        public double Drive_OkayError = 2;
        public double Drive_Turn_OkayError = 2;
        public double Drive_clamp = 0.3;
        public double ticksPerInchDrive = 0.5504;
        public double driveToPointGain = 0.03;
        public double lowGearRampDistance = 20.0;
        public double highGearRampUpDistance = 50.0;
        public double highGearRampDownDistance = 100.0;
        public double wheelDifference = 26.25;


        
        //FLOOR INTAKE SPEEDS
        public double Floor_intake_spd = -1.0;
        public double Floor_maintain_spd = -0.1;



        //ELEVATOR PROFILE CONSTANTS
        public double ElevatorMaxVelocity = 500;//15000
        public double ElevatorMaxAccel = 400;
        public double Elevator_kP = 0.09;
        public double Elevator_kD = 0;
        public double Elevator_kF = 0.001;
        public double Elevator_kGravity = 0.025;

        //ELEVATOR SETPOINTS
        public double Elevator_bottom = 0;
        public double Elevator_down = Elevator_bottom + 1;
        public double Elevator_one = Elevator_bottom + 5;
        public double Elevator_one_back = Elevator_bottom + 3;
        public double Elevator_one_H = Elevator_bottom + 10;
        public double Elevator_mid = Elevator_bottom + 32;
        public double Elevator_mid_back = Elevator_bottom + 27;
        public double Elevator_cargo_two = Elevator_bottom + 19;
        public double Elevator_top_three = Elevator_bottom + 36;
        public double Elevator_top = Elevator_bottom + 42;
        public double Elevator_top_boost = Elevator_bottom + 44;
        public double Elevator_hatch_grab = Elevator_bottom + 26.5;
        public double Elevator_cargo_grab = Elevator_bottom + 14;

        

        //REAR ELEVATOR PROFILE CONSTANTS
        public double Rear_elevatorMaxVelocity = 300;//15000
        public double Rear_elevatorMaxAccel = 150;
        public double Rear_elevator_kP = 0.09;
        public double Rear_elevator_kD = 0;
        public double Rear_elevator_kF = 0.001;
        public double Rear_elevator_kGravity = 0.0;
   
        //REAR ELEVATOR SETPOINTS
        public double Rear_elevator_start = 0;
        public double Rear_elevator_down_level_2 = Rear_elevator_start - 24;
        public double Rear_elevator_down_level_3 = Rear_elevator_start - 39;
        public double Rear_elevator_ground_cargo = Rear_elevator_start - 6;
        public double Rear_elevator_stabilize = Rear_elevator_start - 14;
        public double Rear_elevator_transfer_cargo = Rear_elevator_start - 1;
        public double Rear_elevator_tbone = Rear_elevator_start - 13;



        //WRIST PROFILE CONSTANTS
        public double WristMaxVelocity = 3.5; /////// 3.0
        public double WristMaxAccel = 2.0; //////// 1.0
        public double Wrist_kP = 11; // 4.6
        public double Wrist_kF = 1; // 4.2
        public double Wrist_kGravity = 0.02;
        public double Wrist_ticks_to_deg = 0.002423;
        public double Wrist_zero_offset = 0.4047;

        //WRIST SETPOINTS
        public double Wrist_full_bend_up = 0.116;
        public double Wrist_front_intake_C = Wrist_full_bend_up + 0.43; //.45
        public double Wrist_level = Wrist_full_bend_up + 0.315; //0.342
        public double Wrist_front_three_C = Wrist_full_bend_up + 0.39; //0.418
        public double Wrist_front_three_H = Wrist_full_bend_up + 0.262; // 0.325
        public double Wrist_back_one_C = Wrist_full_bend_up + 0.4;
        public double Wrist_back_two_C = Wrist_full_bend_up + 0.3; // 0.27
        public double Wrist_back_three_C = Wrist_full_bend_up + 0.34; // 0.34
        public double Wrist_front_bent = Wrist_full_bend_up + 0.29; // 0.165
        public double Wrist_hatch_ground = Wrist_full_bend_up + 0.109; // 0.119
        public double Wrist_hatch_place = Wrist_full_bend_up + 0.08; // 0.1
        public double Wrist_hatch_place_back_two = Wrist_full_bend_up + 0.11;
        public double Wrist_hatch_place_two = Wrist_full_bend_up + 0.11; // 0.13
        public double Wrist_back_transition = Wrist_full_bend_up + 0.585; // 0.54
        public double Wrist_intake_from_back = Wrist_full_bend_up + 0.585;// 0.4
        public double Wrist_cargo_ship = Wrist_full_bend_up + 0.573;// 0.4
        public double Wrist_cargo_ship_back = Wrist_full_bend_up + 0.1711;
        public double Wrist_auto_place = Wrist_full_bend_up + 0.15; // 0.325
        public double Wrist_defend_two_c = Wrist_full_bend_up + 0.543;
        public double Wrist_747 = Wrist_full_bend_up + .406;
   


        //ARM PROFILE CONSTANTS
        public double ArmMaxVelocity = 0.5; ////////// 0.3
        public double ArmMaxAccel = 0.4; /////////// 0.2
        public double ArmMaxAccelBack = 0.07; /////////// 0.2
        public double Arm_kP = 15; // 3.0
        public double Arm_kD = 0;
        public double Arm_kF = 2; // 5.0
        public double Arm_kGravity = 0.03;
        public double Arm_ticks_to_deg = 9.422e-4;
        public double Arm_zero_offset = 0.4001;
        public double Near_mod = 0.05;

        //ARM SETPOINTS
        public double Arm_against_bar = 0.1024;
        public double Arm_transition_threshold = 0.5;
        public double Arm_ground_front = Arm_against_bar + 0.2175;
        public double Arm_ground = Arm_ground_front - 0.025; //0.025
        public double Arm_start = Arm_ground_front - 0.0792; 
        public double Arm_back_thresh = Arm_ground_front - 0.120;
        public double Arm_level_one_front = Arm_ground_front - 0.11;
        public double Arm_level_one_front_H = Arm_ground_front - 0.0035;
        public double Arm_level_two_front_H = Arm_ground_front - 0.0175;
        public double Arm_level_two_back_H = Arm_ground_front - 0.1589;
        public double Arm_level_one_back = Arm_ground_front - 0.1;
        public double Arm_level_two_front = Arm_ground_front - 0.0360;            
        public double Arm_level_two_back = Arm_ground_front - 0.1499;
        public double Arm_intake_from_back = Arm_ground_front + 0.0470;
        public double Arm_three_front = Arm_ground_front - 0.088;
        public double Arm_three_front_H = Arm_ground_front - 0.078;            
        public double Arm_three_back = Arm_ground_front - 0.1309;
        public double Arm_back = Arm_ground_front - 0.2269;
        public double Arm_hatch_ground = Arm_ground_front + 0.080;
        public double Arm_defend_two_c = Arm_ground_front - 0.104;



        // VISION MEMBERS
        public double robot_center_on_screen = 65;
        public double x1_offset = 52.5; // less for more right, more for more left
        public double degrees_per_unit = 0.75031;
        public double turnToLineGain = 1.0/6.0;
   
}
