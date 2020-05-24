package Hurricanes;

import java.util.Scanner;

/**
 *
 * @author Jiri.Turyna
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        Interface i = new Data();
        try {
        i.load();
        } catch (Exception e) {
            System.out.println("Error in loading file");
        }
    }
    
}
