package deck;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a single playing card.
 */
public class Card {

    public enum Suit {SPADE, CLUB, HEART, DIAMOND};
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    private Suit suit;
    private Rank rank;

    /**
     * Create a new rank
     *
     * @param suit The card's suit
     * @param rank The card's rank
     *
     * @throws IllegalArgumentException If suit or rank is null.
     */
    public Card(Suit suit, Rank rank) {
        if (suit == null) {
            throw new IllegalArgumentException("Suit can not be null");
        }
        this.suit = suit;

        if (rank == null) {
            throw new IllegalArgumentException("Rank can not be null");
        }
        this.rank = rank;
    }

    /**
     * Returns the card's suit
     * @return The card's suit
     */
    @NotNull
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the card's rank
     *
     * @return The card's rank
     */
    @NotNull
    public Rank getRank() {
        return rank;
    }

    /**
     * Checks if two cards are equal
     * @param other The other card to check
     * @return True if other is Card and has the same suit and rank, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Card)) {
            return false;
        }

        Card c = (Card) other;
        return suit == c.suit && rank == c.rank;
    }

    /**
     * Returns a string representation of the card
     * @return String representation of the card
     */
    @Override
    @NotNull
    public String toString() {
        return String.format("%s of %s", rank, suit);
    }
}
