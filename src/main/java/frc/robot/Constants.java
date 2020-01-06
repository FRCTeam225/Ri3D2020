package frc.robot;

public class Constants {
    private Constants() {}

    private static Constants instance = new Constants();

    public static Constants getConstants() {
        return instance;
    }

    public double indexer_speed = 0.3;
    public double feeder_speed = 0.3;

    public double shooterP = 0;
    public double shooterI = 0;
    public double shooterD = 0;
    public double shooterF = 0;

    public double debugShooterSet = -3000;
}