package Hurricanes;

/**
 *
 * @author Jiri.Turyna
 */
public class Hurricane {

    private int year;
    private int month;
    private int pressure;
    private int speed;
    private String name;

    public Hurricane(int year, int month, int pressure, int speed, String name) {
        this.year = year;
        this.month = month;
        this.pressure = pressure;
        this.speed = speed;
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getPressure() {
        return pressure;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public int knotsToKM() {
        return 0;
    }

    public int getSaffirSimpson(int speed) {
        return 0;
    }
}
