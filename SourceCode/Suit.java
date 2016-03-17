

public enum Suit{
  SPADES,HEARTS, DIAMONDS,CLUBS;

  public String toString(){
    char symbol;
    switch(this){
      case SPADES:    symbol = 'S'; break;
      case HEARTS:    symbol = 'H'; break;
      case DIAMONDS:  symbol = 'D'; break;
      default:        symbol = 'C'; break;
    }
    return Character.toString(symbol).toUpperCase();
  }

  public static Suit getSuit(String suit){
    return Suit.valueOf(suit.toUpperCase());
  }
}
