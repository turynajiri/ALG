package UI;

import Netflix.userData;
import Netflix.MovieData;
import Netflix.MovieInterface;
import Netflix.User;
import Netflix.UserInterface;
import java.io.File;
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
                    System.out.println("Password: ");
                    String password = sc.next();
                    userData l = new userData();

                    if (l.check(username, password)) {
                        User u = new User(username);

                        check = true;
                        name = username;
                        u.login(username);
                        break;
                    } else {
                        continue;
                    }

                // Register new user    
                case 2:
                    ui.loadUserData();

                    System.out.println("Username:");
                    String newUsername = sc.next();
                    System.out.println("Password: ");
                    String newPassword = sc.next();

                    if (!ui.checkIfExists(newUsername)) {
                        User u = new User();
                        u.createNewUser(newUsername, newPassword);
                        System.out.println("\n User created, try loging in!");

                    } else {
                        System.out.println("\n User with this name already exists. Try again");
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

            User u = new User(name);
            MovieInterface mi = new MovieData();
            int movie;

            // normal or premium user
            if ((u.getStatusByUsername(name).equals("n") || u.getStatusByUsername(name).equals("p"))) {
                do {
                    System.out.println(getMenu2());
                    choice2 = sc.nextInt();

                    switch (choice2) {
                        // Watch a movie
                        case 1:
                            if (u.getStatusByUsername(name).equals("n")) {
                                mi.load("n");
                                System.out.println(mi.printListofMovies());
                                System.out.println("Which movie would you like to watch??");
                                movie = sc.nextInt();
                                System.out.println(u.watchMovie(mi.getMovieByID(movie)));

                            }
                            if (u.getStatusByUsername(name).equals("p")) {
                                mi.load("p");
                                System.out.println(mi.printListofMovies());
                                System.out.println("Which movie would you like to watch??");
                                movie = sc.nextInt();
                                System.out.println(u.watchMovie(mi.getMovieByID(movie)));

                            }
                            break;

                        // Change password   
                        case 2:
                            int choice3 = 0;
                            boolean check2 = false;
                            do {
                                choice3 = sc.nextInt();
                                System.out.println(getMenu4());
                                switch (choice3) {
                                    case 1:
                                        System.out.println("\n Zadejte stávající heslo");
                                        String oldPass = u.getPassByUsername(name);
                                        String userOldPass = sc.next();
                                        if (oldPass.equals(userOldPass)) {
                                            System.out.println("\n Zadejte nové heslo");
                                            String newPass = sc.next();
                                            u.changePassword(name, newPass);
                                        }

                                    //Cancel
                                    case 0:
                                        break;
                                    default:
                                        break;
                                }

                            } while (choice3 != 0 || check2);
                            break;

                        // Manage subscription
                        case 3:
                            break;
                        case 0:
                            break;
                        default:
                            break;
                    }
                } while (choice2 != 0);

            }

            // admin account
            if (u.getStatusByUsername(name).equals("a")) {
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
                + "0: Exit App";
    }

    private static String getMenu3() {
        System.out.println("\n Welcome Admin user \n");
        return "1: not implemented"
                + "0: Exit App";
    }

    private static boolean getMenu4() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
