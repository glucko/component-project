import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;

    private static Random RANDOM = new Random();

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

    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Card removeTopCard() {
        return this.cards.remove(0);
    }

    public Card generateRandomCard() {
        int suitIndex = Deck.RANDOM.nextInt(Deck.SUITVALS.length);
        int rankIndex = Deck.RANDOM.nextInt(Deck.RANKVALS.length);

        return new Card(Deck.SUITVALS[suitIndex], Deck.RANKVALS[rankIndex]);
    }

    public void rankSort() {
        this.cards.sort(new Card.RankComparator());
    }

    public void suitSort() {
        this.cards.sort(new Card.SuitComparator());
    }

    public String stringRep() {
        String rep = "";

        for (Card i : this.cards) {
            rep += i.toString() + ", ";
        }
        return rep;
    }

    public static void main(String[] args) {
        Deck deck = new Deck();

        for (int i = 0; i < 5; i++) {
            deck.addCard(deck.generateRandomCard());
        }

        System.out.println("Before any changes:");
        System.out.println(deck.stringRep());

        System.out.println("\nAfter removing top card:");
        System.out.println("Top card: " + deck.removeTopCard());
        System.out.println(deck.stringRep());

        System.out.println("\nSort by Suits:");
        deck.suitSort();
        System.out.println(deck.stringRep());

        System.out.println("\nSort by Rank:");
        deck.rankSort();
        System.out.println(deck.stringRep());
    }
}
