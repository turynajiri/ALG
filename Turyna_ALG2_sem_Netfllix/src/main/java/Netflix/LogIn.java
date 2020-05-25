package Netflix;

/**
 *
 * @author Jiri.Turyna
 */
public class LogIn {

    private String password;
    private String username;

    public LogIn(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public boolean check(String password, String username) {
        // true if matched
        return password.equals("ahoj") && username.equals("pepa");

    }
}
