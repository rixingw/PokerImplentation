

 public enum HRankStatus{
  HIGHCARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT,
  FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH;

  private Card tieBreaker;
  private boolean matched;

  public void setHRank(Card hCard, boolean matched){
    this.tieBreaker = hCard;
    this.matched = matched;
  }

  public Card getHighestCard(){
    return tieBreaker;
  }

  public boolean conditionMet(){
    return matched;
  }

  public void display(){
    System.out.println("\nHandStatus ");
    System.out.println("\tis" + this + " : " + matched);
    System.out.println("\tTie Breaker : " + tieBreaker);
    if (this == TWO_PAIR){
    System.out.println("\t2ndTie Breaker: " + tieBreaker.getPrev());
    }
  }
}
