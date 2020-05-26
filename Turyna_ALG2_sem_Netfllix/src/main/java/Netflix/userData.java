package Netflix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import utils.Exceptions;

/**
 *
 * @author Jiri.Turyna
 */
public class userData implements UserInterface {

    User u = new User();
    Exceptions ex = new Exceptions();

    private ArrayList<User> data = new ArrayList<>();

    public boolean checkIfExists(String username) {
        for (User u : data) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean check(String username, String password) throws FileNotFoundException {
        // true if matched
        boolean kappa = true;
        loadUserData();
        for (User u : data) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return ex.loginError();

    }

    public void loadUserData() throws FileNotFoundException {

        File f;
        Scanner sc;

        int id;
        String username;
        String status;
        String password;

        try {
            f = new File("data/Accounts.txt");
            sc = new Scanner(f);
            while (sc.hasNext()) {
                id = sc.nextInt();
                username = sc.next();
                status = sc.next();
                password = sc.next();
                User u = new User(status, username, id, password);
                data.add(u);
            }
        } catch (Exception e) {
            ex.FileNotFound();
        }
    }

    public String printListOfUsers() {
        StringBuilder sb = new StringBuilder();
        for (User u : data) {
            sb.append(u);
            sb.append("\n");
        }
        return sb.toString();
    }

    public String loadStatusOfUser(String username) {
        StringBuilder sb = new StringBuilder();
        for (User u : data) {
            if (u.getUsername().equals(username)) {
                String tmp = u.getStatus();
                sb.append(tmp);
            }
        }
        return sb.toString();
    }

    public String loadSPasswordOfUser(String username) {
        StringBuilder sb = new StringBuilder();
        for (User u : data) {
            if (u.getUsername().equals(username)) {
                String tmp = u.getPassword();
                sb.append(tmp);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        userData data = new userData();
        data.loadUserData();

        System.out.println(data.loadStatusOfUser("Karel"));
    }
}
