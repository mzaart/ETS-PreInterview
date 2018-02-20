package deck;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CardTest {

    private Card.Suit suit;
    private Card.Rank rank;
    private Card card;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        suit = Card.Suit.HEART;
        rank = Card.Rank.ACE;
        card = new Card(suit, rank);
    }

    @Test
    public void constructorMissingSuit() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Suit can not be null");
        new Card(null, rank);
    }

    @Test
    public void constructorMissingRank() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Rank can not be null");
        new Card(suit, null);
    }

    @Test
    public void constructorMissingAllParams() {
        exception.expect(IllegalArgumentException.class);
        new Card(null, null);
    }

    @Test
    public void constructorHasValidParams() {
        try {
            card = new Card(suit, rank);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void getSuit() {
        assertEquals(suit, card.getSuit());
    }

    @Test
    public void getRank() {
        assertEquals(rank, card.getRank());
    }

    @Test
    public void equalsSuccess() {
        Card other = new Card(suit, rank);
        assertTrue(card.equals(other));
    }

    @Test
    public void equalsFails() {
        assertFalse(card.equals(null));
        assertFalse(card.equals("object of another type"));
        assertFalse(card.equals(new Card(Card.Suit.CLUB, Card.Rank.JACK)));
    }

    @Test
    public void toStringTest() {
        assertEquals(card.toString(), String.format("%s of %s", rank, suit));
    }

}