package Netflix;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import utils.Exceptions;

/**
 *
 * @author Jiri.Turyna
 */
public class MovieData implements MovieInterface {

    private Exceptions ex = new Exceptions();
    private ArrayList<Movie> normalMovieData = new ArrayList<>();
    private ArrayList<Movie> premiumMovieData = new ArrayList<>();

    public String listByGenre(String genre) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();

        for (Movie m : normalMovieData) {
            if (m.getGenre().equals(genre)) {
                sb.append(m.getName() + "\n");
            }
        }

        for (Movie m : premiumMovieData) {
            if (m.getGenre().equals(genre)) {
                sb.append(m.getName() + "\n");
            }
        }

        return sb.toString();
    }

    public String listByYear(int year) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();

        try {

            for (Movie m : normalMovieData) {

                ArrayList<Movie> sortedData = normalMovieData;
                if (m.getReleasedYear() == year) {
                    sb.append(m.getName() + " " + m.getReleasedYear() + "\n");
                }
            }

            for (Movie m : premiumMovieData) {
                ArrayList<Movie> sortedData = premiumMovieData;
                Collections.sort(sortedData, Collections.reverseOrder(Comparator.comparing(Movie::getRating)));
                if (m.getReleasedYear() == year) {
                    sb.append(m.getName() + " " + m.getReleasedYear() + "\n");
                }
            }
        } catch (Exception e) {
            ex.wrongYear();
        }
        return sb.toString();
    }

    public String listByRating(double rating) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        for (Movie m : normalMovieData) {
            if (m.getRating() >= rating) {
                sb.append(m.getName() + " " + m.getRating() + "\n");
            }
        }

        for (Movie m : premiumMovieData) {
            if (m.getRating() >= rating) {
                sb.append(m.getName() + " " + m.getRating() + "\n");
            }
        }
        return sb.toString();
    }

    public void load(String status) throws FileNotFoundException {

        File f;
        Scanner sc;

        String name;
        int releasedYear;
        String genre;
        double rating;

        if (status == "n") {
            try {
                f = new File("data/NormalMovies.txt");
                sc = new Scanner(f);
                int i = 0;
                while (sc.hasNext()) {
                    genre = sc.next();
                    releasedYear = sc.nextInt();
                    rating = sc.nextDouble();
                    name = sc.nextLine();

                    Movie m = new Movie(name, genre, releasedYear, rating, i);
                    i++;
                    normalMovieData.add(m);
                }

            } catch (Exception e) {
                ex.FileNotFound();
            }

        } else if (status == "p") {
            try {
                f = new File("data/PremiumMovies.txt");
                sc = new Scanner(f);
                int i = 0;

                while (sc.hasNext()) {
                    genre = sc.next();
                    releasedYear = sc.nextInt();
                    rating = sc.nextDouble();
                    name = sc.nextLine();

                    Movie m = new Movie(name, genre, releasedYear, rating, i);
                    i++;
                    premiumMovieData.add(m);
                }

            } catch (Exception e) {
                ex.FileNotFound();
            }

        } else {
            ex.badUserArgument();
        }

    }

    public String printListofMovies() {
        StringBuilder sb = new StringBuilder();
        for (Movie m : normalMovieData) {
            sb.append(m);
            sb.append("\n");
        }
        for (Movie m : premiumMovieData) {
            sb.append(m);
            sb.append("\n");
        }

        return sb.toString();
    }

    public String getMovieByID(int id) {
        StringBuilder sb = new StringBuilder();
        for (Movie m : normalMovieData) {
            if (m.getid() == id) {
                sb.append(m.getName());
            }
        }
        for (Movie m : premiumMovieData) {
            if (m.getid() == id) {
                sb.append(m.getName());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        MovieData data = new MovieData();
        data.load("n");
        System.out.println(data.getMovieByID(1));
    }

    @Override
    public void empty() {
        normalMovieData.clear();
        premiumMovieData.clear();
    }

}
