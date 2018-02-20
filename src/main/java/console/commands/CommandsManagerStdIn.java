package console.commands;

import console.commands.CommandsManager;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * Handles fetching user commands from standard input
 */
public class CommandsManagerStdIn extends CommandsManager {

    private Scanner scanner;

    /**
     * Creates a new instance of this class
     */
    public CommandsManagerStdIn() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Fetches next user command from standard input
     *
     * @return The next command from standard input
     */
    @Override
    @NotNull
    public Command nextCommand() {
        try {
            String cmd = scanner.nextLine();
            for (Command command : Command.values()) {
                if (command.name().equals(cmd.toUpperCase())) {
                    return command;
                }
            }
        } catch (Exception ignored) {
        }

        return Command.INVALID;
    }
}
