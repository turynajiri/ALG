package cmd;

import java.io.File;

/**
 *
 * @author Jiri.Turyna
 */
public class Help extends Command{

    @Override
    public String execute(File actualDir) {
        String help = "HELP\n" 
                + String.format("%-7s %s\n", "help", "Display help")
                + String.format("%-7s %s\n", "dir", "Display list of files and folders");
        return help;
    }
    
}
