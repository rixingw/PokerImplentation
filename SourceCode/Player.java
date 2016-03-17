import java.util.Objects;

class Player{
  private int chips;
  private String name;
  public Hand hand;
  //private boolean folded = false;

  public Player(String name, int chips){
    this.name = name;
    this.chips = chips;
    hand = new Hand();
  }
  public int bet(int amount){
    if ((this.chips - amount) >= 0){
      this.chips -= amount;
      return amount;
    }else{
      int r = this.chips;
      this.chips = 0;
      return r;
    }
  }
  public Hand getHand(){
    return hand;
  }

  public int countChips(){return this.chips;}

  public String getName(){return this.name;}

  public void winRound(int amount){
    this.chips += amount;
  }

  public void reset(){
    hand.clear();
  }


  public void addCardtoHand(Card card){
      this.hand.add(card);
  }

  @Override
  public String toString(){
    return this.name + ": $" + Integer.toString(chips);
  }

}
