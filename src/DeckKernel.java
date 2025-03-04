import javax.smartcardio.Card; // ignore this, kept getting auto added on save

/**
 * Deck kernel componenet with primary methods.
 */
public interface DeckKernel extends Standard<Deck> {

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
