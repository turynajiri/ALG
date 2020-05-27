package UI;

import Netflix.userData;
import Netflix.MovieData;
import Netflix.MovieInterface;
import Netflix.User;
import Netflix.UserInterface;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import utils.Exceptions;

/**
 *
 * @author Jiri.Turyna
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        UserInterface ui = new userData();
        String name = null;

        Exceptions ex = new Exceptions();

        int choice1;
        int choice2;
        boolean check = false;

        do {
            System.out.println(getMenu1());
            choice1 = sc.nextInt();
            switch (choice1) {
                // Log in
                case 1:

                    System.out.println("\n Username:");
                    String username = sc.next();
                    System.out.println("\n Password: ");
                    String password = sc.next();

                    if (ui.check(username, password)) {

                        check = true;
                        name = username;
                        ui.login(username);
                        break;
                    }
                    username = null;
                    password = null;
                    break;

                // Register new user    
                case 2:
                    ui.loadUserData();

                    System.out.println("\n Username:");
                    String newUsername = sc.next();
                    System.out.println("\n Password: \n");
                    String newPassword = sc.next();

                    if (!ui.checkIfExists(newUsername)) {
                        ui.createNewUser(newUsername, newPassword);
                        System.out.println("\n User created, try loging in! \n");

                    } else {
                        System.out.println("\n User with this name already exists. Try again \n");
                    }

                    continue;

                case 0:
                    break;
                default:
                    break;
            }
            break;
        } while (choice1 != 0 || check);

        if (check) {

            MovieInterface mi = new MovieData();
            int movie;

            // normal or premium user
            if ((ui.getStatusByUsername(name).equals("n") || ui.getStatusByUsername(name).equals("p"))) {
                do {
                    System.out.println(getMenu2());
                    choice2 = sc.nextInt();

                    switch (choice2) {
                        // Watch a movie
                        case 1:
                            if (ui.getStatusByUsername(name).equals("n")) {
                                mi.empty();
                                mi.load("n");
                                System.out.println(mi.printListofMovies());
                                System.out.println("\n Which movie would you like to watch?? \n");
                                movie = sc.nextInt();
                                System.out.println(ui.watchMovie(mi.getMovieByID(movie)));

                            }
                            if (ui.getStatusByUsername(name).equals("p")) {
                                mi.empty();
                                mi.load("p");
                                System.out.println(mi.printListofMovies());
                                System.out.println("\n Which movie would you like to watch?? \n");
                                movie = sc.nextInt();
                                System.out.println(ui.watchMovie(mi.getMovieByID(movie)));

                            }
                            break;

                        // Change password   
                        case 2:
                            System.out.println("\n Your current password: \n");
                            String oldPass = ui.getPasswordByUsername(name);
                            String userOldPass = sc.next();
                            if (oldPass.equals(userOldPass)) {
                                System.out.println("\n New password: \n");
                                String newPass = sc.next();
                                ui.changePassword(name, newPass);
                                System.out.println("\n Password changed \n");
                            }
                            ui.empty();
                            ui.loadUserData();

                            break;
                        // Manage subscription
                        case 3:
                            if (ui.getStatusByUsername(name).equals("n")) {
                                ui.improveStatus(name);
                                System.out.println("\n Your status was upgraded to Premium User \n");
                            } else {
                                System.out.println("\n You are already a Premium user \n");
                            }
                            ui.empty();
                            ui.loadUserData();
                            break;

                        //Filter Movies
                        case 4:
                            String genre;
                            double rating;
                            int year;
                            int choice3 = -1;

                            do {
                                System.out.println(getMenu4());

                                choice3 = sc.nextInt();

                                switch (choice3) {
                                    // Filter by genre
                                    case 1:
                                        if (ui.getStatusByUsername(name).equals("n")) {
                                            mi.empty();
                                            mi.load("n");

                                            System.out.println("\n Insert genre to filter by (Comedy) \n");
                                            genre = sc.next();
                                            System.out.println(mi.listByGenre(genre));

                                        } else {
                                            mi.empty();
                                            mi.load("p");

                                            System.out.println("\n Insert genre to filrer by (Comedy, Romantic, Drama, Sci-fi) \n");
                                            genre = sc.next();
                                            System.out.println(mi.listByGenre(genre));
                                        }
                                        break;

                                    // Filter by rating
                                    case 2:
                                        if (ui.getStatusByUsername(name).equals("n")) {
                                            mi.empty();
                                            mi.load("n");

                                            System.out.println("\n Insert rating to print List of movies with equal or higher rating \n");
                                            rating = sc.nextDouble();
                                            System.out.println(mi.listByRating(rating));

                                        } else {
                                            mi.empty();
                                            mi.load("p");

                                            System.out.println("\n Insert rating to print List of movies with equal or higher rating \n");
                                            rating = sc.nextDouble();
                                            System.out.println(mi.listByRating(rating));

                                        }
                                        break;

                                    // Filer by year of release
                                    case 3:
                                        if (ui.getStatusByUsername(name).equals("n")) {
                                            mi.empty();
                                            mi.load("n");

                                            System.out.println("\n Insert year to print List of movies that were released in that year \n");

                                            year = sc.nextInt();
                                            System.out.println(mi.listByYear(year));

                                        } else {
                                            mi.empty();
                                            mi.load("p");

                                            System.out.println("\n Insert year to print List of movies that were released in that year \n");

                                            year = sc.nextInt();
                                            System.out.println(mi.listByYear(year));

                                        }
                                        break;

                                    case 0:
                                        break;
                                    default:
                                        break;
                                }
                            } while (choice3 != 0);
                            break;
                        case 0:
                            break;
                        default:
                            break;
                    }
                } while (choice2 != 0);

            }

            // admin account
            if (ui.getStatusByUsername(name).equals("a")) {
                do {
                    System.out.println(getMenu3());
                    choice2 = sc.nextInt();
                    switch (choice2) {
                        case 1:
                            break;
                        case 0:
                            break;
                        default:
                            break;
                    }
                } while (choice2 != 0);

            }
        }
    }

    private static String getMenu1() {
        System.out.println("\n Welcome to Netflix! \n");
        return "1: Log in to your existing account \n"
                + "2: Create a new Account \n"
                + "0: Exit App";
    }

    private static String getMenu2() {
        System.out.println("\n Welcome back! \n");
        return "1: Watch a movie \n"
                + "2: Change password \n"
                + "3: Manage subscription \n"
                + "4: Filter movies \n"
                + "0: Exit App";
    }

    private static String getMenu3() {
        System.out.println("\n Welcome Admin user \n");
        return "1: not implemented"
                + "0: Exit App";
    }

    private static String getMenu4() {
        return "1: Filter by genre \n"
                + "2: Filter by rating \n"
                + "3: Filter by year of release \n"
                + "0: Go back";
    }

}
