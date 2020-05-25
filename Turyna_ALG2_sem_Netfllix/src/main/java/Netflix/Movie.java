package Netflix;

/**
 *
 * @author Jiri.Turyna
 */
public class Movie {
    private String name;
    private String gener;
    private int releasedYear;
    private double rating;

    public Movie(String name, String gener, int releasedYear, double rating) {
        this.name = name;
        this.gener = gener;
        this.releasedYear = releasedYear;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getGener() {
        return gener;
    }

    public int getReleasedYear() {
        return releasedYear;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie: " + "name=" + name + ", gener=" + gener + ", releasedYear=" + releasedYear + ", rating=" + rating + '}';
    }
    
}
