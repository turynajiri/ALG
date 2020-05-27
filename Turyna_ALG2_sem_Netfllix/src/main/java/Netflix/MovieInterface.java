package Netflix;

import java.io.FileNotFoundException;

/**
 *
 * @author Jiri.Turyna
 */
public interface MovieInterface {
    public String listByGenre(String genre) throws IllegalArgumentException;
    public String listByYear(int year) throws IllegalArgumentException;
    public String listByRating(double rating) throws IllegalArgumentException;
    public String printListofMovies();
    public void load(String status) throws FileNotFoundException;

    public String getMovieByID(int movie);

    public void empty();
}
