import components.deck.Deck;
import components.deck.Deck1;
import components.deck.DeckKernel;

public class CardFilter {

    // This shows the potential of Deck to be used as a card filter. This means
    // that certain cards can easily be removed from a deck while keeping the
    // rest of the deck intact.

    public static void filterCards(Deck deck, Deck filter) {
        for (DeckKernel.Card card : filter.convertToArray()) {
            while (deck.cardExists(card)) {
                deck.removeCard(card);
            }
        }
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

        Deck filter = createDeck("ONE_CLUBS", "TWO_DIAMONDS");

        System.out.println("Deck before filter: " + deck.toString());
        filterCards(deck, filter);
        System.out.println("Deck After filter: " + deck.toString());
    }
}
