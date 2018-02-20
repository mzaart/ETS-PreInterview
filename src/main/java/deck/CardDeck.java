package deck;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a deck of cards
 */
public class CardDeck {

    private final static int DECK_SIZE = 52;

    private List<Card> cards;

    /**
     * Creates a new deck of 52 cards
     */
    public CardDeck() {
        cards = new ArrayList<>(DECK_SIZE);
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Deals one card from the deck.
     *
     * @return The selected card or null if the deck is empty.
     */
    @Nullable
    public Card dealOneCard() {
        if (cards.isEmpty()) {
            return null;
        }

        return cards.remove(0);
    }

    /**
     * Shuffles the cards
     */
    public void shuffle() {
        for (int i = 0; i <= cards.size()-1; i++) {
            int j = new Random().nextInt(cards.size()-1);
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    /**
     * Checks whether the deck is empty.
     *
     * @return True if the deck is empty, false otherwise.
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
