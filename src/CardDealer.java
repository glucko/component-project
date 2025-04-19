import components.deck.Deck;
import components.deck.Deck1;
import components.deck.DeckKernel;

public class CardDealer {

    // This shows the potential of Deck to be used as a card dealer. In this example,
    // it is used to easily deal of hands of a certain number without needing to
    // worry about shuffling, running out of cards, or counting out cards

    public static Deck dealCards(Deck deck, int n) {
        Deck hand = new Deck1();
        deck.shuffle();
        for (int i = 0; i < n; i++) {
            hand.addCard(deck.removeTopCard());
        }
        return hand;
    }

    /**
     * Creates a card from a string input formatted as "RANK_SUIT".
     *
     * @param input
     *            the string representation of the card
     * @return a {@code DeckKernel.Card} instance with the specified rank and
     *         suit
     */
    public static DeckKernel.Card createCard(String input) {
        String[] parts = input.split("_");
        DeckKernel.Rank rank = DeckKernel.Rank.valueOf(parts[0].toUpperCase());
        DeckKernel.Suit suit = DeckKernel.Suit.valueOf(parts[1].toUpperCase());
        return new DeckKernel.Card(suit, rank);
    }

    /**
     * Creates a new deck and populates it with the specified cards.
     *
     * @param cards
     *            the cards to add to the deck, formatted as "RANK_SUIT"
     * @return a new {@code Deck} instance with the specified cards
     */

    public static Deck createDeck(String... cards) {
        Deck deck = new Deck1();
        for (String card : cards) {
            deck.addCard(createCard(card));
        }
        return deck;
    }

    public static void main(String[] args) {
        Deck deck = createDeck("ONE_CLUBS", "TWO_DIAMONDS", "THREE_HEARTS",
                "FOUR_SPADES", "FIVE_CLUBS", "SIX_DIAMONDS", "SEVEN_HEARTS",
                "EIGHT_SPADES", "NINE_CLUBS", "TEN_DIAMONDS", "JACK_HEARTS",
                "QUEEN_SPADES", "KING_CLUBS", "ACE_DIAMONDS");

        System.out.println("Deck 1: " + dealCards(deck, 3).toString());
        System.out.println("Deck 2: " + dealCards(deck, 3).toString());
        System.out.println("Deck 3: " + dealCards(deck, 3).toString());
    }
}
