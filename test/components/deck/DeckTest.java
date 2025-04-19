package components.deck;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for {@code Deck}'s constructor and methods.
 *
 * @author Benjamin Gluck
 *
 */
public abstract class DeckTest {
    /**
     * Invokes the {@code Deck} constructor for the implementation under test
     * and returns the result.
     *
     * @return the new statement
     * @ensures constructor = compose((BLOCK, ?, ?), <>)
     */
    protected abstract Deck constructorTest();

    /**
     * Creates a card from a string input formatted as "RANK_SUIT".
     *
     * @param input
     *            the string representation of the card
     * @return a {@code DeckKernel.Card} instance with the specified rank and
     *         suit
     */
    private DeckKernel.Card createCard(String input) {
        String[] parts = input.split("_");
        DeckKernel.Rank rank = DeckKernel.Rank.valueOf(parts[0]);
        DeckKernel.Suit suit = DeckKernel.Suit.valueOf(parts[1]);
        return new DeckKernel.Card(suit, rank);
    }

    /**
     * Creates a new deck and populates it with the specified cards.
     *
     * @param cards
     *            the cards to add to the deck, formatted as "RANK_SUIT"
     * @return a new {@code Deck} instance with the specified cards
     */

    private Deck createDeck(String... cards) {
        Deck deck = this.constructorTest();
        for (String card : cards) {
            deck.addCard(this.createCard(card));
        }
        return deck;
    }

    /*
     * ======================================================================
     * Standard Tests
     * ======================================================================
     */

    /*
     * clear Tests --------------------------------------------------------
     */

    @Test
    public void testClear1() {
        Deck n = this.constructorTest();
        Deck nCopy = this.constructorTest();

        n.clear();

        assertEquals(nCopy, n);
    }

    @Test
    public void testClear2() {
        Deck n = this.createDeck("ONE_CLUBS");
        Deck nCopy = this.constructorTest();

        n.clear();

        assertEquals(nCopy, n);
    }

    /*
     * newInstance Test ------------------------------------------------------
     */

    @Test
    public void testNewInstance() {
        Deck n = this.constructorTest();

        assertEquals(n, n.newInstance());
    }

    /*
     * transferFrom Tests ------------------------------------------------------
     */

    @Test
    public void testTransferFrom1() {
        Deck n = this.constructorTest();
        Deck nCopy = this.constructorTest();

        nCopy.transferFrom(n);

        assertEquals(nCopy, n);
    }

    @Test
    public void testTransferFrom2() {
        Deck n = this.createDeck("ONE_CLUBS");
        Deck nCopy = this.createDeck("ONE_CLUBS");

        Deck test = this.constructorTest();

        test.transferFrom(n);

        assertEquals(nCopy, test);
    }

    @Test
    public void testTransferFrom3() {
        Deck n = this.createDeck("THREE_CLUBS", "TWO_HEARTS", "FOUR_CLUBS",
                "ONE_CLUBS");
        Deck nCopy = this.createDeck("THREE_CLUBS", "TWO_HEARTS", "FOUR_CLUBS",
                "ONE_CLUBS");

        Deck test = this.constructorTest();

        test.transferFrom(n);

        assertEquals(nCopy, test);
    }

    /*
     * ======================================================================
     * Kernel Tests
     * ======================================================================
     */

    /*
     * addCard Tests --------------------------------------------------------
     */

    @Test
    public void testAddCard1() {
        Deck n = this.constructorTest();
        Deck nCopy = this.constructorTest();

        DeckKernel.Card card = new DeckKernel.Card(DeckKernel.Suit.CLUBS,
                DeckKernel.Rank.ONE);

        n.addCard(card);
        nCopy.addCard(card);

        assertEquals(1, n.length());
        assertEquals(nCopy, n);
    }

    @Test
    public void testAddCard2() {
        Deck n = this.constructorTest();
        Deck nCopy = this.constructorTest();

        DeckKernel.Card card = new DeckKernel.Card(DeckKernel.Suit.CLUBS,
                DeckKernel.Rank.ONE);

        n.addCard(card);
        n.addCard(card);

        nCopy.addCard(card);
        nCopy.addCard(card);

        assertEquals(2, n.length());
        assertEquals(nCopy, n);
    }

    /*
     * removeTopCard Tests
     * --------------------------------------------------------
     */

    @Test
    public void testRemoveTopCard1() {
        Deck test = this.createDeck("ONE_CLUBS");
        Deck expected = this.createDeck();

        DeckKernel.Card expectedCard = this.createCard("ONE_CLUBS");
        DeckKernel.Card testCard = test.removeTopCard();

        assertEquals(expected, test);
        assertEquals(expectedCard, testCard);
    }

    @Test
    public void testRemoveTopCard2() {
        Deck test = this.createDeck("ONE_CLUBS", "TWO_HEARTS");
        Deck expected = this.createDeck("ONE_CLUBS");

        DeckKernel.Card expectedCard = this.createCard("TWO_HEARTS");
        DeckKernel.Card testCard = test.removeTopCard();

        assertEquals(expected, test);
        assertEquals(expectedCard, testCard);
    }

    @Test
    public void testRemoveTopCard3() {
        Deck test = this.createDeck("ONE_CLUBS", "TWO_HEARTS", "THREE_CLUBS",
                "FOUR_CLUBS");
        Deck expected = this.createDeck("ONE_CLUBS", "TWO_HEARTS",
                "THREE_CLUBS");

        DeckKernel.Card expectedCard = this.createCard("FOUR_CLUBS");
        DeckKernel.Card testCard = test.removeTopCard();

        assertEquals(expected, test);
        assertEquals(expectedCard, testCard);
    }

    /*
     * shuffle Tests --------------------------------------------------------
     */

    @Test
    public void testShuffle1() {
        Deck test = this.createDeck("ONE_CLUBS");
        Deck expected = this.createDeck("ONE_CLUBS");

        test.shuffle();
        assertEquals(expected, test);
    }

    @Test
    public void testShuffle2() {
        Deck test = this.createDeck("ONE_CLUBS", "ONE_CLUBS", "ONE_CLUBS");
        Deck expected = this.createDeck("ONE_CLUBS", "ONE_CLUBS", "ONE_CLUBS");

        test.shuffle();
        assertEquals(expected, test);
    }

    /*
     * length Tests --------------------------------------------------------
     */

    @Test
    public void testLength1() {
        Deck test = this.createDeck();
        Deck expected = this.createDeck();

        assertEquals(0, test.length());
        assertEquals(expected, test);
    }

    @Test
    public void testLength2() {
        Deck test = this.createDeck("ONE_CLUBS");
        Deck expected = this.createDeck("ONE_CLUBS");

        assertEquals(1, test.length());
        assertEquals(expected, test);
    }

    @Test
    public void testLength3() {
        Deck test = this.createDeck("ONE_CLUBS", "ONE_CLUBS", "ONE_CLUBS");
        Deck expected = this.createDeck("ONE_CLUBS", "ONE_CLUBS", "ONE_CLUBS");

        assertEquals(3, test.length());
        assertEquals(expected, test);
    }

    /*
     * cardExists Tests --------------------------------------------------------
     */

    @Test
    public void testCardExists1() {
        Deck test = this.createDeck();
        Deck expected = this.createDeck();

        DeckKernel.Card card = new DeckKernel.Card(DeckKernel.Suit.CLUBS,
                DeckKernel.Rank.ONE);

        assertEquals(false, test.cardExists(card));
        assertEquals(expected, test);
    }

    @Test
    public void testCardExists2() {
        Deck test = this.createDeck("ONE_CLUBS");
        Deck expected = this.createDeck("ONE_CLUBS");

        DeckKernel.Card card = new DeckKernel.Card(DeckKernel.Suit.CLUBS,
                DeckKernel.Rank.ONE);

        assertEquals(true, test.cardExists(card));
        assertEquals(expected, test);
    }

    @Test
    public void testCardExists3() {
        String[] cards = { "TWO_HEARTS", "THREE_DIAMONDS", "FOUR_SPADES",
                "FIVE_CLUBS", "SIX_HEARTS", "SEVEN_DIAMONDS", "EIGHT_SPADES",
                "NINE_CLUBS", "TEN_HEARTS", "JACK_DIAMONDS", "QUEEN_SPADES",
                "KING_CLUBS", "ACE_HEARTS" };

        Deck test = this.createDeck(cards);
        Deck expected = this.createDeck(cards);

        DeckKernel.Card card = new DeckKernel.Card(DeckKernel.Suit.SPADES,
                DeckKernel.Rank.QUEEN);

        assertEquals(true, test.cardExists(card));
        assertEquals(expected, test);
    }

    /*
     * ======================================================================
     * Secondary Tests
     * ======================================================================
     */

    /*
     * removeCard Tests --------------------------------------------------------
     */

    @Test
    public void testRemoveCard1() {
        Deck test = this.createDeck("ONE_CLUBS");
        Deck expected = this.createDeck();

        test.removeCard(this.createCard("ONE_CLUBS"));
        assertEquals(expected, test);
    }

    @Test
    public void testRemoveCard2() {
        Deck test = this.createDeck("ONE_CLUBS", "TWO_HEARTS");
        Deck expected = this.createDeck("ONE_CLUBS");

        test.removeCard(this.createCard("TWO_HEARTS"));
        assertEquals(expected, test);
    }

    @Test
    public void testRemoveCard3() {
        Deck test = this.createDeck("ONE_CLUBS", "TWO_HEARTS", "THREE_CLUBS",
                "FOUR_CLUBS");
        Deck expected = this.createDeck("ONE_CLUBS", "THREE_CLUBS",
                "FOUR_CLUBS");

        test.removeCard(this.createCard("TWO_HEARTS"));
        assertEquals(expected, test);
    }

    /*
     * rankSort Tests --------------------------------------------------------
     */
    @Test
    public void testRankSort1() {
        Deck test = this.createDeck("ONE_CLUBS");
        Deck expected = this.createDeck("ONE_CLUBS");

        test.rankSort();
        assertEquals(expected, test);
    }

    @Test
    public void testRankSort2() {
        Deck test = this.createDeck("THREE_CLUBS", "TWO_HEARTS", "FOUR_CLUBS",
                "ONE_CLUBS");
        Deck expected = this.createDeck("ONE_CLUBS", "TWO_HEARTS",
                "THREE_CLUBS", "FOUR_CLUBS");

        test.rankSort();
        assertEquals(expected, test);
    }

    /*
     * suitSort Tests --------------------------------------------------------
     */
    @Test
    public void testSuitSort1() {
        Deck test = this.createDeck("ONE_CLUBS");
        Deck expected = this.createDeck("ONE_CLUBS");

        test.suitSort();
        assertEquals(expected, test);
    }

    @Test
    public void testSuitSort2() {
        Deck test = this.createDeck("THREE_CLUBS", "TWO_HEARTS", "THREE_CLUBS",
                "THREE_CLUBS");
        Deck expected = this.createDeck("TWO_HEARTS", "THREE_CLUBS",
                "THREE_CLUBS", "THREE_CLUBS");

        test.suitSort();
        assertEquals(expected, test);
    }

    /*
     * setFromArray Tests ----------------------------------------------------
     */

    @Test
    public void testSetFromArray1() {
        Deck test = this.constructorTest();
        Deck expected = this.constructorTest();

        test.setFromArray(new DeckKernel.Card[0]);

        assertEquals(expected, test);
    }

    @Test
    public void testSetFromArray2() {
        String[] strCards = { "ONE_CLUBS" };
        DeckKernel.Card[] cards = new DeckKernel.Card[strCards.length];

        for (int i = 0; i < strCards.length; i++) {
            cards[i] = this.createCard(strCards[i]);
        }

        Deck test = this.constructorTest();
        Deck expected = this.createDeck(strCards);

        test.setFromArray(cards);
        assertEquals(expected, test);
    }

    @Test
    public void testSetFromArray3() {
        String[] strCards = { "THREE_CLUBS", "TWO_HEARTS", "FOUR_CLUBS",
                "ONE_CLUBS" };
        DeckKernel.Card[] cards = new DeckKernel.Card[strCards.length];

        for (int i = 0; i < strCards.length; i++) {
            cards[i] = this.createCard(strCards[i]);
        }

        Deck test = this.constructorTest();
        Deck expected = this.createDeck(strCards);

        test.setFromArray(cards);
        assertEquals(expected, test);
    }

    /*
     * convertToArray Tests ----------------------------------------------------
     */

    @Test
    public void testConvertToArray1() {
        Deck test = this.constructorTest();
        DeckKernel.Card[] expected = new DeckKernel.Card[0];

        DeckKernel.Card[] testArr = test.convertToArray();
        assertArrayEquals(expected, testArr);
    }

    @Test
    public void testConvertToArray2() {
        String[] strCards = { "ONE_CLUBS" };
        DeckKernel.Card[] expected = new DeckKernel.Card[strCards.length];

        for (int i = 0; i < strCards.length; i++) {
            expected[i] = this.createCard(strCards[i]);
        }

        Deck test = this.createDeck(strCards);
        DeckKernel.Card[] testArr = test.convertToArray();
        assertArrayEquals(expected, testArr);
    }

    @Test
    public void testConvertToArray3() {
        String[] strCards = { "THREE_CLUBS", "TWO_HEARTS", "FOUR_CLUBS",
                "ONE_CLUBS" };
        DeckKernel.Card[] expected = new DeckKernel.Card[strCards.length];

        for (int i = 0; i < strCards.length; i++) {
            expected[i] = this.createCard(strCards[i]);
        }

        Deck test = this.createDeck(strCards);
        DeckKernel.Card[] testArr = test.convertToArray();
        assertArrayEquals(expected, testArr);
    }

    /*
     * toString Tests --------------------------------------------------------
     */

    @Test
    public void testToString1() {
        Deck test = this.constructorTest();
        String expected = "";

        String testStr = test.toString();
        assertEquals(expected, testStr);
    }

    @Test
    public void testToString2() {
        String[] strCards = { "ONE_CLUBS" };

        String expected = "";
        for (String i : strCards) {
            expected += this.createCard(i).toString() + "\n";
        }

        Deck test = this.createDeck(strCards);
        assertEquals(expected, test.toString());
    }

    @Test
    public void testToString3() {
        String[] strCards = { "THREE_CLUBS", "TWO_HEARTS", "FOUR_CLUBS",
                "ONE_CLUBS" };

        String expected = "";
        for (String i : strCards) {
            expected += this.createCard(i).toString() + "\n";
        }

        Deck test = this.createDeck(strCards);
        assertEquals(expected, test.toString());
    }
}
