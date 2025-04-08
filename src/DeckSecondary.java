import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// Note: could not get rid of the overrides, saving auto added them.
/**
 * {@code Deck} abstract class.
 */
public abstract class DeckSecondary implements Deck {

    public static enum Suit {
        HEARTS, SPADES, CLUBS, DIAMONDS
    }

    public static enum Rank {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ACE, KING, QUEEN, JACK;
    }

    private static Suit[] SUITVALS = Suit.values();
    private static Rank[] RANKVALS = Rank.values();

    record Card(Suit suit, Rank rank) {
        static class SuitComparator implements Comparator<Card> {
            @Override
            public int compare(Card c1, Card c2) {
                return c1.suit.compareTo(c2.suit);
            }
        }

        static class RankComparator implements Comparator<Card> {
            @Override
            public int compare(Card c1, Card c2) {
                return c1.rank.compareTo(c2.rank);
            }
        }
    }

    /**
     * Removes a specified card from {@code this}.
     *
     * @param c
     *            The card to be removed from {@code this}.
     * @updates this
     * @requires p in this
     * @ensures LENGTH(#this) = LENGTH(this) - 1
     */
    @Override
    public void removeCard(Card c) {
        Card[] cardsToAdd = new Card[this.length() - 1];

        int i = 0;
        while (this.length() > 0) {
            Card top = this.removeTopCard();
            if (!top.equals(c)) {
                cardsToAdd[i] = top;
                i++;
            }
        }
        this.setFromArray(cardsToAdd);
    }

    /**
     * Sorts the cards in {@code this} by rank.
     *
     * @updates this
     *
     * @ensures this = SORT_BY_RANK(#this)
     */
    @Override
    public void rankSort() {
        List<Card> cards = Arrays.asList(this.convertToArray());

        cards.sort(new Card.RankComparator());

        this.setFromArray(cards.toArray(new Card[cards.size()]));
    }

    /**
     * Sorts the cards in {@code this} by suit.
     *
     * @updates this
     *
     * @ensures this = SORT_BY_SUIT(#this)
     */
    @Override
    public void suitSort() {
        List<Card> cards = Arrays.asList(this.convertToArray());

        cards.sort(new Card.SuitComparator());

        this.setFromArray(cards.toArray(new Card[cards.size()]));
    }

    /**
     * Initializes {@code this} from an array representation.
     *
     * @param arr
     *            The array {@code this} will be set from.
     *
     * @replaces this
     * @ensures ELEMENTS(arr) = ELEMENTS(this)
     */
    @Override
    public void setFromArray(Card[] arr) {

        while (this.length() > 0) {
            this.removeTopCard();
        }

        for (int i = 0; i < arr.length; i++) {
            this.addCard(arr[i]);
        }
    }

    /**
     * Converts {@code this} to an array of cards.
     *
     * @return An array containing all cards in {@code this}.
     *
     * @ensures s = TO_ARRAY(this)
     */
    @Override
    public Card[] convertToArray() {
        Card[] cards = new Card[this.length()];
        for (int i = 0; i < this.length(); i++) {
            cards[i] = this.removeTopCard();
        }

        return cards;
    }

    @Override
    public String toString() {
        String out = "";
        for (Card i : this.convertToArray()) {
            out += i.toString() + "\n";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        DeckSecondary other = (DeckSecondary) obj;
        return Arrays.equals(other.convertToArray(), this.convertToArray());
    }

}
