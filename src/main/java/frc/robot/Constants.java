package frc.robot;

public class Constants {
    private Constants() {}

    private static Constants instance = new Constants();

    public static Constants getConstants() {
        return instance;
    }

    public double indexer_speed = 1.0;
    public double feeder_speed = 1.0;

    public double shooterP = 0;
    public double shooterI = 0;
    public double shooterD = 0;
    public double shooterF = 0;

    public double debugShooterSet = -3000;

    public double colorwheel_fast = 0.4;
    public double colorwheel_slow = 0.3;

    public byte panelsToSpin = 26;
}