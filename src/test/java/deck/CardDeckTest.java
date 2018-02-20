package deck;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CardDeckTest {

    private CardDeck deck;

    @Before
    public void setUp() {
        this.deck = new CardDeck();
    }

    @Test
    public void dealOneCard() {
        for (int i = 0; i < 52; i++) {
            assertNotNull(deck.dealOneCard());
        }

        assertNull(deck.dealOneCard());
    }

    @Test
    public void shuffle() {
        boolean cardsEqual = true;

        // get cards before and after shuffling
        List<Card> before = (List<Card>) getFieldValue(deck, "cards");
        deck.shuffle();
        List<Card> after = (List<Card>) getFieldValue(deck, "cards");

        // verify
        while (!before.isEmpty()) {
            Card c1 = before.remove(0);
            Card c2 = after.remove(0);
            cardsEqual = cardsEqual && c1.equals(c2);
        }
        assertFalse(cardsEqual);
    }

    @Test
    public void isEmpty() {
        assertFalse(deck.isEmpty());

        // empty deck
        for (int i = 0; i < 52; i++) {
            deck.dealOneCard();
        }

        assertTrue(deck.isEmpty());
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