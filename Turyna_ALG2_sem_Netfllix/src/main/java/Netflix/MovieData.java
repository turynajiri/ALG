package Netflix;

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
public class MovieData {

    private static Exceptions ex = new Exceptions();
    private static ArrayList<Movie> normalMovieData = new ArrayList<>();
    private static ArrayList<Movie> premiumMovieData = new ArrayList<>();

    public String listByGener(String gener) {
        StringBuilder sb = new StringBuilder();

        for (Movie m : normalMovieData) {
            if (m.getGener().equals(gener)) {
                sb.append(m.getName() + "\n");
            }
        }

        for (Movie m : premiumMovieData) {
            if (m.getGener().equals(gener)) {
                sb.append(m.getName() + "\n");
            }
        }

        return sb.toString();
    }

    public String listByYear(int year) {

        StringBuilder sb = new StringBuilder();

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

        return sb.toString();
    }

    public String listByRating(double rating) {
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

    public static void load(String status) throws FileNotFoundException {

        File f;
        Scanner sc;

        String name;
        int releasedYear;
        String gener;
        double rating;

        if (status == "n") {
            try {
                f = new File("data/NormalMovies.txt");
                sc = new Scanner(f);
                while (sc.hasNext()) {
                    gener = sc.next();
                    releasedYear = sc.nextInt();
                    rating = sc.nextDouble();
                    name = sc.nextLine();

                    Movie m = new Movie(name, gener, releasedYear, rating);
                    normalMovieData.add(m);
                }

            } catch (Exception e) {
                ex.FileNotFound();
            }

        } else if (status == "p") {
            try {
                f = new File("data/PremiumMovies.txt");
                sc = new Scanner(f);
                while (sc.hasNext()) {
                    gener = sc.next();
                    releasedYear = sc.nextInt();
                    rating = sc.nextDouble();
                    name = sc.nextLine();

                    Movie m = new Movie(name, gener, releasedYear, rating);
                    premiumMovieData.add(m);
                }

            } catch (Exception e) {
                ex.FileNotFound();
            }

        } else {
            ex.badUserArgument();
        }
    }

    @Override
    public String toString() {
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

    public static void main(String[] args) throws FileNotFoundException {
        MovieData data = new MovieData();
        data.load("n");
        System.out.println(data.listByRating(82));
    }
}
