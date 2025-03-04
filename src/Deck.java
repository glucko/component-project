import javax.smartcardio.Card;

/**
 * {@code DeckKernel} enhanced with secondary methods.
 */
public interface Deck extends DeckKernel {

    /**
     * Removes a specified card from {@code this}.
     *
     * @param c
     *            The card to be removed from {@code this}.
     * @updates this
     * @requires p in this
     * @ensures LENGTH(#this) = LENGTH(this) - 1
     */
    void removeCard(Card c);

    /**
     * Sorts the cards in {@code this} by rank.
     *
     * @updates this
     *
     * @ensures this = SORT_BY_RANK(#this)
     */
    void rankSort();

    /**
     * Sorts the cards in {@code this} by suit.
     *
     * @updates this
     *
     * @ensures this = SORT_BY_SUIT(#this)
     */
    void suitSort();

    /**
     * Initializes {@code this} from an array representation.
     *
     * @param arr
     *            The array {@code this} will be set from.
     *
     * @replaces this
     * @ensures ELEMENTS(arr) = ELEMENTS(this)
     */
    void setFromArray(Card[] arr);

    /**
     * Converts {@code this} to an array of cards.
     *
     * @return An array containing all cards in {@code this}.
     *
     * @ensures s = TO_ARRAY(this)
     */
    Card[] convertToArray();

}
