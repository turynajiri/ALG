package Netflix;

import java.io.FileNotFoundException;

/**
 *
 * @author Jiri.Turyna
 */
public interface MovieInterface {
    public String listByGener(String gener);
    public String listByYear(int year);
    public String listByRating(double rating);
    public String printListofMovies();
    public void load(String status) throws FileNotFoundException;

    public String getMovieByID(int movie);
}
