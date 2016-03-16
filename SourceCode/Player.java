

class Player{
  public int chips;
  public String name;
  public Hand hand;
  private boolean folded = false;

  public Player(String name, int chips){
    this.name = name;
    this.chips = chips;
    hand = new Hand();
  }
  public int bet(int amount){
    if (this.chips - amount >= 0){
      this.chips -= amount;
      return amount;
    }else{
      this.chips -= amount;
      return this.chips;
    }
  }

  public void winRound(int amount){
    this.chips += amount;
  }

  public void foldRound(boolean fd){
    this.folded = fd;
  }

  public boolean isFolded(){
    return this.folded;
  }

  public void addCardtoHand(Card card){
      this.hand.add(card);
  }

  @Override
  public String toString(){
    return this.name + ": $" + Integer.toString(chips);
  }

}
