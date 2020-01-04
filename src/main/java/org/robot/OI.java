package org.robot;

//import org.firstcapital.robot.commands.elevator.*;
//import org.firstcapital.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj.Joystick;

public class OI {
    public static final int A = 1;
    public static final int B = 2;
    public static final int X = 3;
    public static final int Y = 4;
    
    public static final int LB = 5;
    public static final int RB = 6;
    
    public static final int BACK = 7;
    public static final int START = 8;
    
    public static final int LT = 2;
    public static final int RT = 3;
    
	public static final int LS = 9;
    public static final int RS = 10;
    
    public static final int RIGHT = 90;
	public static final int UP = 0;
	public static final int LEFT = 270;
    public static final int DOWN = 180;
	
	public static Joystick driver = new Joystick(0);
    public static Joystick operator = new Joystick(1);
    
    public static POV resetDrive = new POV(driver, DOWN);
    public static POV climb747 = new POV(driver, UP);
    public static POV manualMode = new POV(operator, DOWN);
    public static POV rearIntake = new POV(operator, LEFT);
    public static POV startingPosition = new POV(operator, UP);
    public static POV spitFloor = new POV(operator, RIGHT);
    public static Button lampToggle = new Button(driver, START);
    public static Button compressorToggle = new Button(driver, BACK);
    
    public static Button tBoneToggle = new Button(driver, START);

    public static Button cargoShipToggle = new Button(driver, B);
	
	//public static Button autoSelectUp = new Button(driver, B);
    //public static Button autoSelectDown = new Button(driver, A);
    
    public static Button pieceToggle = new Button(operator, START);
    public static Button intakeToggle = new Button(operator, RB);
    public static Button popoToggle = new Button(operator, LS);
    public static Button penetratorToggle = new Button(driver, LB);

	public static void init() {
        //new AxisButton(driver, B, 0.5).whenPressed(new Shift(true));
        //new AxisButton(driver, X, 0.5).whenPressed(new Shift(false));

        //new JoystickButton(driver, A).whenPressed(new DriveProfiledDistance(5*12.0, 0, 24, 12));
        //new JoystickButton(driver, A).whenPressed(new TurnTo(90));
        //new JoystickButton(operator, Y).whenPressed(new MoveElevator(5000));
        //new JoystickButton(operator, X).whenPressed(new MoveElevator(3000));
        //new JoystickButton(operator, Y).whenPressed(new MoveWrist(-3400));
        //new JoystickButton(operator, A).whenPressed(new MoveWrist(-1000));
        //new JoystickButton(operator, X).whenPressed(new MoveWrist(0));
        //new JoystickButton(operator, A).whenPressed(new MoveArm(0));
        //new JoystickButton(operator, Y).whenPressed(new MoveArm(-1200));
        //new JoystickButton(operator, B).whenPressed(new ManualElevator());
	}
}