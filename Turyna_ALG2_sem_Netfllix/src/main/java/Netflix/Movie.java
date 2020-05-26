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
    private int id;

    public Movie(String name, String gener, int releasedYear, double rating, int id) {
        this.name = name;
        this.gener = gener;
        this.releasedYear = releasedYear;
        this.rating = rating;
        this.id = id;
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
    
    public int getid(){
        return id;
    }

    @Override
    public String toString() {
        return id + "." + name + ", " + gener + ", " + releasedYear + ", " + rating + "%";
    }
    
}
