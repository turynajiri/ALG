package elevengame;

/**
 * Represents the table with cards to play and a deck
 * @author jiri.turyna
 */
public class Board implements BoardInterface{
    private Card[] cards;
    private Deck deck;
    
    public Board(){
        deck = new Deck(DataStore.loadSymbols(), DataStore.loadValues(), DataStore.loadNPoints());
        cards = new Card[nCards()];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = deck.deal();
            
        }
        //deal cards
    }
    
    @Override
    public String gameName() {
        return "Hra jedenactka";
    }
    
    @Override
    public int nCards() {
        return cards.length;
    }

    @Override
    public int getDeckSize() {
        return deck.getDeckSize();
    }
    
    @Override
    public String getCardDescriptionAt(int index){
        if(cards[index] == null){
            return " ";
        }
        return cards[index].getSymbol() + "-" + cards[index].getValue();
    }

    @Override
    public boolean anotherPlayIsPossible() {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public boolean playAndReplace(int[] iSelectedCards) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isWon() {
        return deck.isEmpty() ? true : false;
    }
}
