import java.util.ArrayList;
import java.util.Collections;

/**
 * {@code Deck} represented as a {@link java.util.ArrayList java.util.ArrayList}
 * with implementations of primary methods.
 *
 * @convention Every element of $this.rep is a Card.
 * @correspondence this = $this.rep, where first element of this.rep is bottom
 *                 of deck and last element of this.rep is top of deck.
 */
public final class Deck1 extends DeckSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private ArrayList<Card> rep;

    @Override
    public Deck newInstance() {
        return new Deck1();
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new ArrayList<Card>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Deck1() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public void transferFrom(Deck source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Deck1 : ""
                + "Violation of: source is of dynamic type Deck1";

        Deck1 localSource = (Deck1) source;
        this.rep = localSource.rep;
        localSource.clear();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public void addCard(Card c) {
        this.rep.add(c);
    }

    @Override
    public Card removeTopCard() {
        return this.rep.remove(this.rep.size());
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.rep);
    }

    @Override
    public int length() {
        return this.rep.size();
    }

    @Override
    public boolean cardExists(Card c) {
        return this.rep.contains(c);
    }
}
