package utils;

/**
 *
 * @author Jiri.Turyna
 */
public class Exceptions {    

    public Exceptions() {
    }
    
    public boolean loginError(){
        System.out.println("\n" + "Error!" + "\n" + "Credentials do not match. Try again");
        return false;
    }

    public void FileNotFound() {
        System.out.println("File not found");
    }

    public void badUserArgument() {
        System.out.println("Wrong user argument");
    }

    public void MovieNotFound() {
        System.out.println("Movie not found");
    }

    public void GenreNotFound() {
        System.out.println("Genre not found");
    }

    public void IOException() {
        System.out.println("IOException");
    }

    public void FileNotDeleted() {
        System.out.println("File not deleted");
    }

    public void wrongRating() {
        System.out.println("Rating has to be from 0-100");
    }

    public void wrongYear() {
        System.out.println("No movies found for this year");
    }

    public void wrongArgument() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
