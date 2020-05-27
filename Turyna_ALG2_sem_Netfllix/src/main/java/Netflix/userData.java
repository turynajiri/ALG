package Netflix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import utils.Exceptions;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Jiri.Turyna
 */
public class userData implements UserInterface {

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

    public void login(String username) {
    }

    public boolean check(String username, String password) throws FileNotFoundException {
        // true if matched
        boolean check = true;
        loadUserData();
        for (User u : data) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return ex.loginError();

    }

    public void createNewUser(String username, String password) throws IOException {
        String status = "n";

        try {
            File f = new File("data/Accounts.txt");
            BufferedReader reader = new BufferedReader(new FileReader(f));
            FileWriter fileWriter = new FileWriter(f, true);
            int lines = 0;
            while (reader.readLine() != null) {
                lines++;
            }
            reader.close();
            String str = (lines + " " + username + " " + status + " " + password + "\n");
            fileWriter.write(str);
            fileWriter.close();

        } catch (Exception e) {
            ex.IOException();
        }

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

    public String getStatusByUsername(String username) {
        StringBuilder sb = new StringBuilder();
        for (User u : data) {
            if (u.getUsername().equals(username)) {
                String tmp = u.getStatus();
                sb.append(tmp);
            }
        }
        return sb.toString();
    }

    public String getPasswordByUsername(String username) {
        StringBuilder sb = new StringBuilder();
        for (User u : data) {
            if (u.getUsername().equals(username)) {
                String tmp = u.getPassword();
                sb.append(tmp);
            }
        }
        return sb.toString();
    }

    public String getIdByUsername(String username) {
        StringBuilder sb = new StringBuilder();
        for (User u : data) {
            if (u.getUsername().equals(username)) {
                int tmp = u.getId();
                sb.append(tmp);
            }
        }
        return sb.toString();
    }

    public void changePassword(String username, String password) {
        Path inputFilePath = Paths.get("data/Accounts.txt");
        Path tempfilePath = Paths.get("data/tmp.txt");
        File inputFile = new File("data/Accounts.txt");
        File tempFile = new File("data/tmp.txt");

        List<String> lines = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            int i = 0;

            String toRemove = getIdByUsername(username);
            while ((line = reader.readLine()) != null) {
                if (line.contains(toRemove + " ")) {
                    line = (i + " " + username + " " + getStatusByUsername(username) + " " + password);
                }
                writer.write(line + "\n");
                i++;
            }

            reader.close();
            writer.close();

            System.gc();

            Files.deleteIfExists(Paths.get("data/Accounts.txt"));
            Files.copy(Paths.get("data/tmp.txt"), Paths.get("data/Accounts.txt"));
            Files.deleteIfExists(Paths.get("data/tmp.txt"));
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
    }

    public void improveStatus(String username) {
        Path inputFilePath = Paths.get("data/Accounts.txt");
        Path tempfilePath = Paths.get("data/tmp.txt");
        File inputFile = new File("data/Accounts.txt");
        File tempFile = new File("data/tmp.txt");

        List<String> lines = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            int i = 0;

            String toRemove = getIdByUsername(username);
            while ((line = reader.readLine()) != null) {
                if (line.contains(toRemove + " ")) {
                    line = (i + " " + username + " " + "p" + " " + getPasswordByUsername(username));
                }
                writer.write(line + "\n");
                i++;
            }

            reader.close();
            writer.close();

            System.gc();

            Files.deleteIfExists(Paths.get("data/Accounts.txt"));
            Files.copy(Paths.get("data/tmp.txt"), Paths.get("data/Accounts.txt"));
            Files.deleteIfExists(Paths.get("data/tmp.txt"));
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        userData data = new userData();
        data.loadUserData();
        data.improveStatus("Jirka");
    }

    public String watchMovie(String movie) {
        return "You are watching movie: " + movie;
    }

    @Override
    public void empty() {
        data.clear();
    }
}
