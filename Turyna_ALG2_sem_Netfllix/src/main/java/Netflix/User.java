package Netflix;

/**
 *
 * @author Jiri.Turyna
 */
public class User {
    private boolean check = false;
    
    public boolean login() {
        
        System.out.println("You are logged in!");
        return check = true;
    }

    public String getStatus() {
        return null;
    }

    public void watchAMovie(int movie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
