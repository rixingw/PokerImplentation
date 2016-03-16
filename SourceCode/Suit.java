

public enum Suit{
  SPADES,HEARTS, DIAMONDS,CLUBS;

  public String toString(){
    char symbol;
    switch(this){
      case SPADES:    symbol = (char)'\u2664'; break;
      case HEARTS:    symbol = (char)'\u2661'; break;
      case DIAMONDS:  symbol = (char)'\u2662'; break;
      default:        symbol = (char)'\u2667'; break;
    }
    return Character.toString(symbol).toLowerCase();
  }

  public static Suit getSuit(String suit){
      return Suit.valueOf(suit.toUpperCase());
  }
}
