package App;

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
import java.time.LocalDateTime;
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

    public void writeToFile(String username, String action) {
        LocalDateTime now = LocalDateTime.now();

        File f = new File("data/UserActions.txt");
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(f, true));
            writer.write("\n" + username + " " + action + " " + now);
            writer.close();
        } catch (IOException ex) {
            System.out.println("IOException");
        }

    }

    public void login(String username) {
        String action = "Logged in";
        writeToFile(username, action);

    }

    public boolean check(String username, String password) throws FileNotFoundException {
        try {
            // true if matched
            boolean check = true;
            loadUserData();
            for (User u : data) {
                if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                    return true;
                }
            }
            ex.loginError(username);

        } catch (IOException e) {
            System.out.println("IOException");
        }
        return false;

    }

    public void createNewUser(String username, String password) throws IOException {
        String status = "n";
        String action = "New user created";

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

            writeToFile(username, action);
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

        f = new File("data/Accounts.txt");
        if (f == null) {
            try {
                ex.FileNotFound(f.toString());
            } catch (IOException ex) {
                System.out.println("IOException");
            }
        }
        sc = new Scanner(f);
        while (sc.hasNext()) {
            id = sc.nextInt();
            username = sc.next();
            status = sc.next();
            password = sc.next();
            User u = new User(status, username, id, password);
            data.add(u);
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

        String action = "Password changed";
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
            writeToFile(username, action);
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

        String action = "Status improved";
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

            writeToFile(username, action);
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

    public String watchMovie(String movie, String username) {
        
        String action = "Watching a movie" + movie;
        writeToFile(username, action);
        return "You are watching movie: " + movie;
    }

    @Override
    public void empty() {
        data.clear();
    }
}
