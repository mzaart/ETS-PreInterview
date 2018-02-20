package console;

import console.commands.CommandsManager;
import deck.CardDeck;
import org.jetbrains.annotations.NotNull;

/**
 * This class handles interacting with the user.
 */
public class ConsoleApp {

    private CardDeck deck;
    private CommandsManager cmdManager;

    /**
     * Creates a ConsoleApp
     *
     * @param cmdManager The commands manager responsible for fetching the commands
     *
     * @throws IllegalArgumentException If the commands manager is null
     */
    public ConsoleApp(CommandsManager cmdManager) {
        if (cmdManager == null) {
            throw new IllegalArgumentException("CommandsManager can not be null.");
        }
        this.deck = new CardDeck();
        this.cmdManager = cmdManager;
    }

    /**
     * Runs the app
     */
    public Void run() {
        greetUser();

        CommandsManager.Command cmd;
        do {
            cmd = cmdManager.nextCommand();

            switch (cmd) {
                case SHUFFLE:
                    deck.shuffle();
                    System.out.println("The deck has been shuffled");
                    break;
                case DRAW:
                    if (deck.isEmpty()) {
                        System.out.println("The deck is empty. Type 'Restart\' to recreate the deck.");
                    } else {
                        System.out.printf("You have selected: %s\n", deck.dealOneCard());
                    }
                    break;
                case RESTART:
                    deck = new CardDeck();
                    System.out.println("A new deck has been created.");
                    break;
                case INVALID:
                    System.out.println("Please enter a valid command.");
                    break;
            }
        } while (!cmd.equals(CommandsManager.Command.EXIT));

        return null;
    }

    /**
     * Greets the user and provides game instructions
     */
    private void greetUser() {
        System.out.println("Hello");
        System.out.println("Type: ");
        System.out.println("\'Shuffle\': to shuffle the cards.");
        System.out.println("\'Draw\' to draw a card.");
        System.out.println("\'Restart\': to recreate the deck.");
    }
}
