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

/**
 *
 * @author Jiri.Turyna
 */
public class User {

    Exceptions ex = new Exceptions();

    private String status;
    private String username;
    private int Id;
    private String password;

    public User(String status, String username, int Id, String password) {
        this.status = status;
        this.username = username;
        this.Id = Id;
        this.password = password;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {

    }

    public String getStatus() {
        return status;
    }

    public String getStatusByUsername(String username) throws FileNotFoundException {
        String status;
        userData us = new userData();
        us.loadUserData();
        status = us.loadStatusOfUser(username);
        return status;
    }
    
    public String getPassByUsername(String username) throws FileNotFoundException {
        String password;
        userData us = new userData();
        us.loadUserData();
        password = us.loadSPasswordOfUser(username);
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return Id;
    }

    public String getPassword() {
        return password;
    }

    public void login(String username) {
    }

    public String watchMovie(String movie) {
        return "Watching a movie " + movie;
    }

    public void createNewUser(String username, String password) throws IOException {
        String status = "n";

        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/Accounts.txt"));
            File f = new File("data/Accounts.txt");
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
    
    public void changePassword (String username, String password){
        
    }

    @Override
    public String toString() {
        return "User{" + ", status=" + status + ", username=" + username + ", Id=" + Id + ", password=" + password + '}';
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        User u = new User("n", "Karel", 1, "asd");
    }
}
