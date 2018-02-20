import console.ConsoleApp;
import console.commands.CommandsManagerStdIn;

/**
 * This class acts as an entry point to the application
 */
public class App {

    /**
     * This method runs the application
     *
     * @param args This parameter is not used and any values in it will be ignored.
     */
    public static void main(String[] args) {
        try {
            ConsoleApp app = new ConsoleApp(new CommandsManagerStdIn());
            app.run();
        } catch (Exception e) {
            System.out.println("An error has occurred.");
        }
    }
}
