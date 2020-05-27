package App;

import java.io.FileNotFoundException;

/**
 *
 * @author Jiri.Turyna
 */
public interface MovieInterface {
    public String listByGenre(String genre) throws IllegalArgumentException;
    public String listByYear(String status);
    public String listByRating(String rating) throws IllegalArgumentException;
    public String printListofMovies();
    public void load(String status) throws FileNotFoundException;

    public String getMovieById(int movie);
    public void playSound(String movie);
    public void empty();
}
