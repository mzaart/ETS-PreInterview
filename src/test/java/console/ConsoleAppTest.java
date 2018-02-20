package console;

import console.commands.CommandsManager;
import console.commands.MockCommandsManager;
import deck.Card;
import deck.CardDeck;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

public class ConsoleAppTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void NullConstructorParams() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("CommandsManager can not be null.");
        new ConsoleApp(null);
    }

    @Test
    public void greet() {
        String expected = "Hello\n" +
                "Type: \n" +
                "\'Shuffle\': to shuffle the cards.\n" +
                "\'Draw\' to draw a card.\n" +
                "\'Restart\': to recreate the deck.\n";

        OutputStream stream = captureOutput();
        ConsoleApp app = new ConsoleApp(new MockCommandsManager());
        app.run();

        assertEquals(expected, stream.toString());
    }

    @Test
    public void drawCommand() {
        MockCommandsManager manager = new MockCommandsManager();
        ConsoleApp app = new ConsoleApp(manager);

        // get first card
        CardDeck deck = (CardDeck) getFieldValue(app, "deck");
        List<Card> cards = (List<Card>) getFieldValue(deck, "cards");
        Card first = cards.get(0);

        // draw card
        manager.addCommand(CommandsManager.Command.DRAW);
        OutputStream stream = captureOutput();
        app.run();

        // verify
        assertEquals(String.format("You have selected: %s", first), getLastResponse(stream));
    }

    @Test
    public void shuffleCommand() {
        MockCommandsManager manager = new MockCommandsManager();
        ConsoleApp app = new ConsoleApp(manager);

        // get initial deck
        CardDeck deck1 = (CardDeck) getFieldValue(app, "deck");

        // get shuffled deck
        manager.addCommand(CommandsManager.Command.SHUFFLE);
        app.run();
        CardDeck deck2 = (CardDeck) getFieldValue(app, "deck");

        // verify deck is shuffled
        boolean cardsEqual = true;
        while (!deck1.isEmpty()) {
            Card c1 = deck1.dealOneCard();
            Card c2 = deck2.dealOneCard();
            cardsEqual = cardsEqual && c1.equals(c2);
        }
        assertFalse(cardsEqual);
    }

    @Test
    public void restartCommand() {
        MockCommandsManager manager = new MockCommandsManager();
        ConsoleApp app = new ConsoleApp(manager);

        // get card deck
        CardDeck deck = (CardDeck) getFieldValue(app, "deck");

        // empty deck
        while (!deck.isEmpty()) {
            deck.dealOneCard();
        }

        // verify recreating deck
        manager.addCommand(CommandsManager.Command.RESTART);
        app.run();
        deck = (CardDeck) getFieldValue(app, "deck");
        assertFalse(deck.isEmpty());
    }


    @Test
    public void invalidCommand() {
        MockCommandsManager manager = new MockCommandsManager(CommandsManager.Command.INVALID);
        ConsoleApp app = new ConsoleApp(manager);

        OutputStream stream = captureOutput();
        app.run();

        assertEquals("Please enter a valid command.", getLastResponse(stream));
    }

    @Test
    public void emptyDeck() {
        // populate commands
        MockCommandsManager manager = new MockCommandsManager();
        for (int i = 1; i <= 53; i++) {
            manager.addCommand(CommandsManager.Command.DRAW);
        }

        // create app
        OutputStream stream = captureOutput();
        ConsoleApp app = new ConsoleApp(manager);
        app.run();

        // verify
        assertEquals("The deck is empty. Type 'Restart\' to recreate the deck.", getLastResponse(stream));
    }

    /**
     * Captures standard output in a OutputStream
     */
    private OutputStream captureOutput() {
        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        return outputStream;
    }

    /**
     * Gets last response ouputted by the app
     */
    private String getLastResponse(OutputStream stream) {
        String[] responses = stream.toString().split("\n");
        return responses[responses.length-1];
    }

    /**
     * Gets a field value using reflection
     */
    private Object getFieldValue(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}