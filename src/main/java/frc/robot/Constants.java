package frc.robot;

public class Constants {
    private Constants() {}

    private static Constants instance = new Constants();

    public static Constants getConstants() {
        return instance;
    }

    public static double indexer_speed = 0.8;
    public static double feeder_speed = 0.8;

    // Shooter constants
    public static double shooter_kP = 0.00005;
    public static double shooter_kI = 0.000001;
    public static double shooter_kD = 0;
    public static double shooter_kIz = 0;
    public static double shooter_kFF = 0;
    public static double shooter_kMaxOutput = 1;
    public static double shooter_kMinOutput = -1;
    public static double shooter_maxRPM = 5700;
}