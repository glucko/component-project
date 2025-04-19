package components.deck;

import java.util.Comparator;

import components.standard.Standard;

/**
 * Deck kernel componenet with primary methods.
 */
public interface DeckKernel extends Standard<Deck> {

    /**
     * Represents the possible suits of a playing card.
     */
    enum Suit {
        HEARTS, SPADES, CLUBS, DIAMONDS
    }

    /**
     * Represents the possible ranks of a playing card.
     */
    enum Rank {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ACE, KING, QUEEN, JACK;
    }

    /**
     * Represents a playing card with a suit and rank.
     *
     * @param suit
     *            the suit of the card (e.g., HEARTS, SPADES, etc.)
     * @param rank
     *            the rank of the card (e.g., ONE, TWO, ACE, etc.)
     */
    record Card(Suit suit, Rank rank) {
        /**
         * Comparator for comparing cards based on their suit.
         */
        static class SuitComparator implements Comparator<Card> {
            @Override
            public int compare(Card c1, Card c2) {
                return c1.suit.compareTo(c2.suit);
            }
        }

        /**
         * Comparator for comparing cards based on their rank.
         */
        static class RankComparator implements Comparator<Card> {
            @Override
            public int compare(Card c1, Card c2) {
                return c1.rank.compareTo(c2.rank);
            }
        }
    }

    /**
     * Adds {@code c} to {@code this}.
     *
     * @param c
     *            The card to add to {@code this}.
     * @updates this
     * @ensures LENGTH(#this) = LENGTH(this) + 1
     */
    void addCard(Card c);

    /**
     * Removes and returns the top card from {@code this}.
     *
     * @return the Card that was at the top of the deck
     * @requires LENGTH(this) > 0
     * @updates this
     * @ensures LENGTH(#this) = LENGTH(this) - 1
     */
    Card removeTopCard();

    /**
     * Shuffles {@code this}, randomizing the order of cards.
     *
     * @updates this
     * @ensures LENGTH(#this) = LENGTH(this)
     */
    void shuffle();

    /**
     * Returns the length of {@code this}.
     *
     * @return The length of {@code this}
     *
     * @ensures length = LENGTH(this)
     */
    int length();

    /**
     * Checks if a specific card is present in {@code this}.
     *
     * @param c
     *            the card to check.
     * @return true if the card is in {@code this}, false otherwise.
     */
    boolean cardExists(Card c);
}
