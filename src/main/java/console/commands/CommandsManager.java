package console.commands;

import org.jetbrains.annotations.NotNull;

/**
 * This class abstracts fetching commands from the user
 */
public abstract class CommandsManager {

    /**
     * Contains supported commands
     */
    public enum Command {
        /**
         * Shuffles the deck of cards
         */
        SHUFFLE,

        /**
         * Draws one card from the deck
         */
        DRAW,

        /**
         * Recreates a deck
         */
        RESTART,

        /**
         * Closes and exits the application
         */
        EXIT,

        /**
         * Represents an invalid command
         */
        INVALID
    }

    /**
     * Fetches the next command from the user
     *
     * @return The command entered by the user
     */
    @NotNull
    public abstract Command nextCommand();
}
