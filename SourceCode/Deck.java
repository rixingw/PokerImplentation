

public class Deck extends Pile<Card>{
  public Deck(){
    super(52);
    for (Suit suit : Suit.values()){
        for (Value value: Value.values()){
           Card card = new Card(value, suit);
           this.add(card);
        }
    }
    this.shuffle();
  }
  public Card deal(){
      return remove(1);
  }

  public static void main(String[] args){
    Deck deck = new Deck();
    deck.display();
    while (deck.getLength() > 1){
      Card pop = deck.deal();
      System.out.println("\n" + pop + " " + deck.getLength()+"\n");
    }
    System.out.println("Deck size:" + deck.getLength());
  }
}
