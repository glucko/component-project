import components.deck.Deck;
import components.deck.Deck1;

/**
 * Customized JUnit test fixture for {@code Deck1}.
 */
public class Deck1Test extends DeckTest {

    @Override
    protected final Deck constructorTest() {
        return new Deck1();
    }

}
