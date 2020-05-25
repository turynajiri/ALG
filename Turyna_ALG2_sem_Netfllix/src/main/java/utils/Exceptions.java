package utils;

/**
 *
 * @author Jiri.Turyna
 */
public class Exceptions {
    

    public Exceptions() {
    }
    
    public String loginError(){
        return(" Error! \n"
                + "Credentials do not match. Try again");
    }

    public String FileNotFound() {
        return ("File not Found");
    }

    public String badUserArgument() {
        return ("Wrong User Argument");
    }

    public String MovieNotFound() {
        return "Movie not found";
    }

    public String GenerNotFound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
