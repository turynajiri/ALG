package UI;

import Netflix.LogIn;
import Netflix.MovieData;
import Netflix.User;
import java.io.File;
import java.io.FileNotFoundException;
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
    public static void main(String[] args) throws FileNotFoundException {

        User u = new User();
        Exceptions ex = new Exceptions();

        int choice1;
        int choice2;
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
                    LogIn l = new LogIn(username, password);

                    if (l.check(password, username)) {
                        u.login();
                        break;
                    } else {
                        System.out.println(ex.loginError());
                        continue;
                    }

                // Register new user    
                case 2:
                    break;

                case 0:
                    break;
                default:
                    break;
            }
            break;
        } while (choice1 != 0 || !u.login());

        // normal or premium user
        if (u.login() && (u.getStatus().equals("n") || u.getStatus().equals("p"))) {
            int movie;
            do {
                System.out.println(getMenu2());
                choice2 = sc.nextInt();
                switch (choice2) {
                    case 1:
                        if (u.getStatus().equals("n")) {
                                MovieData.load(u.getStatus());
                                System.out.println("Which movie would you like to watch??");
                                movie = sc.nextInt();
                                u.watchAMovie(movie);
                            
                        }
                        if (u.getStatus().equals("p")) {
                                MovieData.load(u.getStatus());
                                System.out.println("Which movie would you like to watch??");
                                movie = sc.nextInt();
                                u.watchAMovie(movie);
                            
                        }
                        break;
                    case 0:
                        break;
                    default:
                        break;
                }
            } while (choice2 != 0);

        }

        // admin account
        if (u.login() && u.getStatus().equals("a")) {
            do {
                System.out.println(getMenu2());
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

    private static String getMenu1() {
        System.out.println("\n Welcome to Netflix! \n");
        return "1: Log in to your existing account \n"
                + "2: Create a new one \n"
                + "0: Exit App";
    }

    private static String getMenu2() {
        System.out.println("\n Welcome back! \n");
        return "1: Watch a movie \n"
                + "2: Check account status \n"
                + "3: Manage subscription \n"
                + "4: Account options \n"
                + "0: Exit App";
    }

}
