import org.omg.PortableServer.POA;

import java.util.ArrayList;
import java.util.Collections;

public class PokerAnalyzer{
  private final Pile openCards;

  public PokerAnalyzer(Pile cards){
    openCards = cards;
  }



  public HRankStatus analyzeHand(Hand hand){
    HRankStatus result;

      result = isRoyalFlush(hand);
      if (result.conditionMet()){
        return result;
      }

      result = isStraightFlush(hand);
      if (result.conditionMet()){
        return result;
      }

      result = isFourOfAKind(hand);
      if (result.conditionMet()){
        return result;
      }

      result = isFullhouse(hand);
      if (result.conditionMet()){
        return result;
      }

      result = isFlush(hand);
      if (result.conditionMet()){
        return result;
      }

      result = isStraight(hand);
      if (result.conditionMet()){
        return result;
      }

      result = isThreeOfAKind(hand);
      if (result.conditionMet()){
        return result;
      }

      result = isTwoPair(hand);
      if (result.conditionMet()){
        return result;
      }
      result = isOnePair(hand);
      if (result.conditionMet()){
        return result;
      }

      result = isHighCard(hand);
      return result;
}



  public HRankStatus isHighCard(Hand hand){
        Card hCard = hand.getHighestCard();
        HRankStatus hr = HRankStatus.HIGHCARD;
        hr.setHRank(hCard, true);
        return hr;
  }

  public HRankStatus isOnePair(Hand hand){
    Pile allCards = combinePiles(hand);
    boolean met = false;
    Card hCard = allCards.getHighestCard();
    ArrayList<Pile> groups = getValueGroups(allCards);
    if (groups.size() == 1){
        Pile p = groups.get(0);
        met = true;
//        Card temp = hCard;
        hCard = p.getEntry(1);
        hCard.setPrev(isHighCard(hand).getHighestCard());
//        met = (p.getLength() == 2);
//        if (met){
//          Card temp = hCard;
//          hCard = p.getHighestCard();
//          hCard.setPrev(temp);
//        }
    }
    HRankStatus hr = HRankStatus.ONE_PAIR;
    hr.setHRank(hCard, met);
    return hr;
  }

  // Might not work in case Two players has the same pair
  public HRankStatus isTwoPair(Hand hand){
    Pile allCards = combinePiles(hand);
    boolean met = false;
    Card hCard = allCards.getHighestCard();
    ArrayList<Pile> groups = getValueGroups(allCards);
    if (groups.size() == 2 || groups.size() == 3){
//        Pile p = groups.get(groups.size()-1);
//        if (p.getLength() == 2){
//          met = true;
//          hCard = p.getHighestCard();
//          if (groups.size() >= 2){
//             Pile sec = groups.get(groups.size()-2);
//             Card p1 = sec.getHighestCard();
//             Card p1copy=  new Card(p1.getValue(), p1.getSuit());
//             Card hcopy = new Card(hCard.getValue(), hCard.getSuit());
//             hcopy.setPrev(p1copy);
//             hCard = hcopy;
//          }
//        }

        met = true;
        hCard = groups.get(0).getEntry(1);
        Card temp = hCard;
//        for (Pile p : groups){
//            temp.setPrev(p.getEntry(1));
//            temp = temp.getPrev();
//        }

        for (int i = groups.size()-1; i >= 0; i--){
            Pile p = groups.get(i);
            temp.setPrev(p.getEntry(1));
            temp = temp.getPrev();
        }
        temp.setPrev(isHighCard(hand).getHighestCard());
    }
    HRankStatus hr = HRankStatus.TWO_PAIR;
    hr.setHRank(hCard, met);
    return hr;
  }


  public HRankStatus isThreeOfAKind(Hand hand){
    Pile allCards = combinePiles(hand);
    boolean met = false;
    Card hCard = allCards.getHighestCard();
    ArrayList<Pile> groups = getValueGroups(allCards);
    for (Pile p : groups){
      if (p.getLength() == 3){
        met = true;
        hCard = p.getHighestCard();
        break;
      }
    }
    HRankStatus hr = HRankStatus.THREE_OF_A_KIND;
    hr.setHRank(hCard, met);
    return hr;
  }



  public HRankStatus isStraight(Hand hand){
    Pile allCards =  combinePiles(hand);
    return isSequential(allCards);
  }


  public HRankStatus isFlush(Hand hand){
        HRankStatus handrank = HRankStatus.FLUSH;
        int count = 0;
        Pile allCards = combinePiles(hand);
        Card highCard = allCards.getHighestCard();
        for(Suit suit: Suit.values()){
          Pile pile = allCards.groupBySuit(suit);
          int totalcounts = pile.getLength();
          if (totalcounts > count){
            count = totalcounts;
            highCard = pile.getHighestCard();
          }
        }
        boolean met = (count >= 5);
        handrank.setHRank(highCard, met);
        return handrank;
    }


  public HRankStatus isFullhouse(Hand hand){
    Pile allCards = combinePiles(hand);
    boolean met = false;
    Card hCard = allCards.getHighestCard();
    ArrayList<Pile> groups = getValueGroups(allCards);
    boolean triple = false;
    boolean pair = false;
    for  (Pile p : groups){
      if (p.getLength() == 2){
        pair = true;
      }
      if (p.getLength() == 3){
        triple = true;
        hCard = p.getHighestCard();
      }
    }
    met = (triple && pair);
    HRankStatus hr = HRankStatus.FULL_HOUSE;
    hr.setHRank(hCard, met);
    return hr;
  }

  public HRankStatus isFourOfAKind(Hand hand){
    Pile allCards = combinePiles(hand);
    boolean met = false;
    Card hCard = allCards.getHighestCard();
    ArrayList<Pile> groups = getValueGroups(allCards);
    if (groups.size() >= 1){
        Pile p = groups.get(0);
        if (groups.size() == 2){
          Pile b = groups.get(1) ;
          if (b.getLength() == 4){
            p = b;
          }
        }
        met = (p.getLength() == 4);
        if (met){
          hCard = p.getHighestCard();
        }
    }else{
      met = false;
    }

    HRankStatus hr = HRankStatus.FOUR_OF_A_KIND;
    hr.setHRank(hCard, met);
    return hr;
  }



  public HRankStatus isStraightFlush(Hand hand){
    Pile cards = combinePiles(hand);
    boolean met = false;
    Card hCard =cards.getHighestCard();
    HRankStatus flush = isFlush(hand);
    if (flush.conditionMet()){
      Suit suit = flush.getHighestCard().getSuit();
      Pile sameGroup = cards.groupBySuit(suit);
      HRankStatus seq = isSequential(sameGroup);
      if (seq.conditionMet()){
          met = true;
          hCard = seq.getHighestCard();
      }
    }

    HRankStatus hr = HRankStatus.STRAIGHT_FLUSH;
    hr.setHRank(hCard, met);
    return hr;
  }

  public HRankStatus isRoyalFlush(Hand hand){
    Pile cards = combinePiles(hand);
    HRankStatus strflush = isStraightFlush(hand);
    boolean met = (strflush.conditionMet() && (numberOfRoyalCards(cards) >= 5));
    HRankStatus hr = HRankStatus.ROYAL_FLUSH;
    Card hCard = strflush.getHighestCard();
    hr.setHRank(hCard, met);
    return hr;
  }


  private HRankStatus isSequential(Pile allCards){
    Pile uniqueCards  =  allCards.toUniquePile();
    boolean met = false;
    Card hCard = uniqueCards.getHighestCard();
        if (uniqueCards.getLength() >= 5){
            if (numberOfRoyalCards(uniqueCards) >= 5){
              hCard = uniqueCards.getEntry(1);
              met = true;
            }else{
              uniqueCards.sort(true);
              Card curr = uniqueCards.getEntry(1);
              int matched = 0;
              Card temp = null;
              while (curr != null){
                Card next = curr.getNext();
                if (next != null){
                  if (curr.getValue().nextValue() == next.getValue()){
                    matched++;
                    temp = next;
                  }else{
                    if (matched >= 4){
                      met = true;
                      break;
                    }else{
                      matched = 0;
                    }
                  }
                }else{
                  if (matched >= 4){
                    met = true;
                  }
                }
                curr = curr.getNext();
            }
            if (met == true){
              hCard = temp;
            }
        }
      }
    HRankStatus hs = HRankStatus.STRAIGHT;
    hs.setHRank(hCard,met);
    return hs;
  }


  private int numberOfRoyalCards(Pile pile){
    int count = 0;
    Card curr =  pile.getEntry(1);
    while (curr != null){
      if (curr.getValue().isRoyalRank()){
        count++;
      }
      curr = curr.getNext();
    }
    return count;
  }



  private ArrayList<Pile> getValueGroups(Pile cards){
    ArrayList<Pile> groups = new ArrayList<Pile>();
    for (Value value: Value.values()){
      Pile vgroup = cards.groupByValue(value);
      if (vgroup.getLength() >= 2){
        groups.add(vgroup);
      }
    }
    return groups;
  }


  private Pile combinePiles(Hand hand){
    Pile pile = new Pile(openCards.getLength() + hand.getLength());
    pile.join(openCards);
    pile.join(hand);
    return pile;
  }



  public static void main(String[] args){
    Card c1 = new Card("Ace", "Diamonds");
    Card c2 = new Card("Three", "Diamonds");
    Card c3 = new Card("Four", "Diamonds");
    Card c4 = new Card("Two", "Diamondss");
    Card c5 = new Card("Five", "Diamonds");

    Card c6 = new Card("Six", "Diamonds");
    Card c7 = new Card("Six", "Hearts");

    Pile gBase = new Pile(5);
    gBase.addAll(c1,c2,c3,c4,c5);
    Hand gHand = new Hand();
    gHand.addAll(c6,c7);

    PokerAnalyzer pk1 = new PokerAnalyzer(gBase);
    HRankStatus stra = pk1.analyzeHand(gHand);
    stra.display();

  }
}
