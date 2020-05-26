package Netflix;

import java.io.FileNotFoundException;

/**
 *
 * @author Jiri.Turyna
 */
public interface UserInterface {
    public boolean check(String username, String password) throws FileNotFoundException;
    public void loadUserData() throws FileNotFoundException;
    public boolean checkIfExists(String username);
}
