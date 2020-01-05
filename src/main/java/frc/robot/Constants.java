package frc.robot;

public class Constants {
    private Constants() {}

    private static Constants instance = new Constants();

    public static Constants getConstants() {
        return instance;
    }

    public static double indexer_speed = 0.8;
    public static double feeder_speed = 0.8;
}