
public class Card implements Comparable<Card>{
  private Card next;
  private Card prev;
  private Value value;
  private Suit suit;

  public Card getNext()               {return next;}

  public void setNext(Card card)      {this.next = card;}

  public Card getPrev()               {return prev;}

  public void setPrev(Card card)      {this.prev = card;}

  public Suit getSuit()               {return this.suit;}

  public Value getValue()             {return this.value;}

  public void setSuit(Suit suit)      {this.suit = suit;}

  public void setValue(Value value)      {this.value = value;}

  public boolean isHighCard() { return  this.value.isAce();}

  public Card(String valueStr, String suitStr){
        Value v = Value.getValue(valueStr);
        Suit s = Suit.getSuit(suitStr);
        this.setCard(v, s);
  }
  public Card(Value value, Suit suit){
        this.setCard(value, suit);
  }
  public void setCard(Value value, Suit suit){
        this.value = value;
        this.suit = suit;
  }

  private double computeCardOrdinal(Card card){
          double valueOrdinal = (double)card.getValue().ordinal();
      return valueOrdinal;
  }

  public int compareTo(Card otherCard){
        double cardOrdinal = computeCardOrdinal(this);
        double ocardOrdinal = computeCardOrdinal(otherCard);
        if (cardOrdinal == ocardOrdinal)
            return 0;
        else if (cardOrdinal > ocardOrdinal)
            return 1;
        else
            return -1;
  }


    @Override
    public String toString(){
      return this.value.toString() + this.suit.toString();
    }

  public static void main(String[] args){
    Card card1 = new Card("Ace","Hearts");
    Card card2 = new Card("Ace", "Hearts");
    Card card3 = new Card("Two", "Hearts");
    Card card4 = new Card("Two", "Diamonds");
    assert card1.compareTo(card2) == 0 : "Failed";
    assert card2.compareTo(card3) == -1 : "Failed";
    assert card3.compareTo(card4) == 1 : "Failed";
  }
}
