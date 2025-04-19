package components.deck;

import java.util.Arrays;
import java.util.List;

/**
 * {@code Deck} abstract class.
 */
public abstract class DeckSecondary implements Deck {

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCard(Card c) {
        Card[] cardsToAdd = new Card[this.length() - 1];

        int i = cardsToAdd.length - 1;
        while (this.length() > 0) {
            Card top = this.removeTopCard();
            if (!top.equals(c)) {
                cardsToAdd[i] = top;
                i--;
            }
        }
        this.setFromArray(cardsToAdd);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rankSort() {
        List<Card> cards = Arrays.asList(this.convertToArray());

        cards.sort(new Card.RankComparator());

        this.setFromArray(cards.toArray(new Card[cards.size()]));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void suitSort() {
        List<Card> cards = Arrays.asList(this.convertToArray());

        cards.sort(new Card.SuitComparator());

        this.setFromArray(cards.toArray(new Card[cards.size()]));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFromArray(Card[] arr) {
        this.clear();
        for (int i = 0; i < arr.length; i++) {
            this.addCard(arr[i]);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card[] convertToArray() {
        Card[] cards = new Card[this.length()];
        for (int i = this.length() - 1; i >= 0; i--) {
            cards[i] = this.removeTopCard();
        }

        return cards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String out = "";
        for (Card i : this.convertToArray()) {
            out += i.toString() + "\n";
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
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
