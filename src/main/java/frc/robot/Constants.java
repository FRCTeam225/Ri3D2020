package frc.robot;

public class Constants {
    private Constants() {}

    private static Constants instance = new Constants();

    public static Constants getConstants() {
        return instance;
    }

    public double indexer_speed = 0.8;
    public double feeder_speed = 1.0;
    public double intake_speed = 1.0;

    public double shooterP = 0.0011;
    public double shooterI = 0;
    public double shooterD = 4;
    public double shooterF = 0.00017;

    public double visionP = -0.04;
    public double visionLimit = 0.5;

    public double debugShooterSet = -4000;
}