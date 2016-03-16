
import java.util.Random;


public class Pile<T extends Card> implements ListInterface<T>{

  private Card topCard = null;
  private Card bottomCard = null;
  private final int maxcapacity;
  private int nCard = 0;
  /** Task: Pile Constrcutor
   * set head = null
   * set tail = null
   * */
   public Pile(int maxcap){
     maxcapacity = maxcap;
   }
  /** Task: Adds a new entry to the end of the list.
   *        Entries currently in the list are unaffected.
   *        The list's size is increased by 1.
   *  @param newEntry  the object to be added as a new entry
   *  @return true if the addition is successful, or false if the list
   *          is full */

  public boolean add(T newEntry){
        if (isFull()){
          return false;
        }else{
          if (topCard == null && bottomCard == null){
                topCard = newEntry;
          }else if (bottomCard == null){
                bottomCard = newEntry;
                topCard.setNext(bottomCard);
                bottomCard.setPrev(topCard);
          }else{
                Card temp = bottomCard;
                bottomCard = newEntry;
                bottomCard.setPrev(temp);
                temp.setNext(bottomCard);
          }
          nCard++;
          return true;
        }
  }

  /** Task: Adds a new entry at a specified position within the list.
   *        Entries originally at and above the specified position
   *        are at the next higher position within the list.
   *        The list's size is increased by 1.
   *  @param newPosition  an integer that specifies the desired
   *                      position of the new entry
   *  @param newEntry     the object to be added as a new entry
   *  @return true if the addition is successful, or
   *          false if either the list is full, newPosition < 1, or
   *          newPosition > getLength()+1 */

  public boolean add(int newPosition, T newEntry){
      if(isFull() || newPosition < 1 || newPosition > getLength()+1){
        return false;
      }else if(newPosition == getLength()){
          return add(newEntry);
      }else if (newPosition == 1){
          Card temp = topCard;
          topCard = newEntry;
          topCard.setNext(temp);
          temp.setPrev(topCard);
          nCard++;
          return true;
      }else{
            int currPos = 1;
            Card card = topCard;
            while(currPos != newPosition){
              card = card.getNext();
              currPos++;
            }
            Card prev = card.getPrev();
            prev.setNext(newEntry);
            prev.getNext().setPrev(prev);
            prev.getNext().setNext(card);
            card.setPrev(prev.getNext());
            nCard++;
            return true;
    }
  }

  public boolean addAll(Card... args){
    boolean success = false;
    for (Card c: args){
        if(add((T)c)){
          success = true;
        }else{
          break;
        }
    }
    return success;
  }


  /** Task: Removes the entry at a given position from the list.
   *        Entries originally at positions higher than the given
   *        position are at the next lower position within the list,
   *        and the list's size is decreased by 1.
   *  @param givenPosition  an integer that indicates the position of
   *                        the entry to be removed
   *  @return a reference to the removed entry or null, if either
   *          the list was empty, givenPosition < 1, or
   *          givenPosition > getLength() */
  public T remove(int givenPosition){
    Card toRemove;

    if (isEmpty() || givenPosition < 1 || givenPosition > getLength()){
      return null;
    }else if (givenPosition == 1 && nCard == 1){
      toRemove = (topCard == null)? bottomCard : topCard;
      topCard = null;
      bottomCard = null;
    }
    else if (givenPosition == 1){
      toRemove = topCard;
      topCard = topCard.getNext();
      topCard.setPrev(null);
    }else if (givenPosition == nCard){
      toRemove = bottomCard;
      bottomCard = bottomCard.getPrev();
      bottomCard.setNext(null);
    }else{
      int index = 1;
      toRemove = topCard;
      while(index != givenPosition){
        toRemove = toRemove.getNext();
        index++;
      }
      Card next = toRemove.getNext();
      Card prev = toRemove.getPrev();
      prev.setNext(next);
      next.setPrev(prev);
    }
    nCard--;
    toRemove.setNext(null);
    toRemove.setPrev(null);
    return (T)toRemove;

  }


  /** Task: Removes all entries from the list. */
  public void clear(){
    topCard = null;
    bottomCard = null;
    nCard = 0;
  }

  /** Task: Replaces the entry at a given position in the list.
   *  @param givenPosition  an integer that indicates the position of the
   *                        entry to be replaced
   *  @param newEntry  the object that will replace the entry at the
   *                   position givenPosition
   *  @return true if the replacement occurs, or false if either the
   *          list is empty, givenPosition < 1, or
   *          givenPosition > getLength() */
  public boolean replace(int givenPosition, T newEntry){
     if (isEmpty() || givenPosition < 1 || givenPosition > getLength()){
       return false;
     }
     else{
       int index = 1;
       Card curr = topCard;
       while(index != givenPosition){
         curr = curr.getNext();
         index++;
       }
       // In case newEntry is already in the list -> copying its properties
       Card replacement = new Card(newEntry.getValue(), newEntry.getSuit());
       swap(curr, replacement);
     }
     return true;
  }

  /** Task: Retrieves the entry at a given position in the list.
   *  @param givenPosition  an integer that indicates the position of
   *                        the desired entry
   *  @return a reference to the indicated entry or null, if either
   *          the list is empty, givenPosition < 1, or
   *          givenPosition > getLength() */
  public T getEntry(int givenPosition){
      if (isEmpty() || givenPosition < 1 || givenPosition > getLength()){
        return null;
      }else{
        Card curr = topCard;
        int index = 1;
        while (index != givenPosition){
          curr = curr.getNext();
          index++;
        }
        return (T)curr;
      }
  }

  /** Task: Sees whether the list contains a given entry.
   *  @param anEntry  the object that is the desired entry
   *  @return true if the list contains anEntry, or false if not */
  public boolean contains(T anEntry){
    boolean hasEntry = false;
    Card curr = topCard;
    while(curr != null){
      if (curr.compareTo(anEntry) == 0){
        hasEntry = true; break;
      }
      curr = curr.getNext();
    }
    return hasEntry;
  }

  /** Task: Gets the length of the list.
   *  @return the integer number of entries currently in the list */
  public int getLength(){
    return nCard;
  }

  /** Task: Sees whether the list is empty.
   *  @return true if the list is empty, or false if not */
  public boolean isEmpty(){
      return nCard == 0;
  }

  /** Task: Sees whether the list is full.
   *  @return true if the list is full, or false if not */
  public boolean isFull(){
    return nCard == maxcapacity;
  }

  /** Task: Displays all entries that are in the list, one per line,
   *        in the order in which they occur in the list. */
  public void display(){
    System.out.println(this);
  }


  /** Task: Printable
   * @return string representation of this class */
  public String toString(){
    String str = "";
    Card curr = topCard;
    while(curr != null){
       str += ( curr + "\n");
       curr = curr.getNext();
    }
    return str;
  }

  /** Task: Shuffle all entries that are in the list
            in a randomized order*/
  public void shuffle(){
    Random rand = new Random();
    Card curr = topCard;
    while(curr != null){
      int randNum = rand.nextInt(getLength()) + 1;
      Card randomCard = (Card)getEntry(randNum);
      swap(randomCard, curr);
      curr = curr.getNext();
    }
  }

  /** Task: Sord all cards in ascending and descending order
   * @param asc sort in ascending order if true else sort in descending order
   */
  public void sort(boolean asc){
    int direction = asc? 1: -1;
    Card curr = topCard;
    while(curr != null){
      Card next = curr.getNext();
        while(next != null){
          if (curr.compareTo(next) == direction)
              swap(curr, next);
          next = next.getNext();
        }
      curr = curr.getNext();
    }
}


  /** Task: Add all elements from a pile to current pile
   *  @param pile the pile to add
   *  */
  public void join(Pile pile){
  if (maxcapacity < (pile.getLength() + nCard)) {
      System.out.println(" Capacity is too small , operation aborted!");
  } else{
      Card toLink = (T)pile.getEntry(1);
      while(toLink != null){
        // Card next = toLink.getNext();
        // toLink.setNext(null);
        // toLink.setPrev(null);
        //  add((T)toLink);
        // toLink = next;
        Card temp = new Card(toLink.getValue(), toLink.getSuit());
        add((T)temp);
        toLink = toLink.getNext();
      }
    }
  }

  /** Task: Find the highest card in the pile
   * @return the higest card value
   */
  public Card getHighestCard(){
    Card highestCard = null;
    Card curr = topCard;
    while (curr != null){
      if (curr.isHighCard()){
        highestCard = curr;
      }
      curr = curr.getNext();
    }
    if (highestCard == null){
      sort(false);
      highestCard = getEntry(1);
    }
    return highestCard;
  }


  /** Task: swap the properties of two cards Only the value and suit has altered
   * @param card1 the first card to swap
   * @param card2 the second card to swap
   */
  private void swap(Card card1, Card card2){
    Value vcard1 = card1.getValue();
    Suit scard1 = card1.getSuit();
    Value vcard2 = card2.getValue();
    Suit scard2 = card2.getSuit();
    card1.setCard(vcard2, scard2);
    card2.setCard(vcard1, scard1);
  }


  public Pile groupBySuit(Suit suit){
    Pile<Card> group = new Pile<Card>(getLength());
    Card curr = topCard;
    while (curr != null){
      if (curr.getSuit() == suit){
        Card copy = new Card(curr.getValue(), curr.getSuit());
        group.add(copy);
      }
      curr = curr.getNext();
    }
    return group;
  }

  public Pile groupByValue(Value value){
    Pile<Card> group = new Pile<Card>(4);
    Card curr = topCard;
    while (curr != null){
      if (curr.getValue() == value){
        Card copy = new Card(curr.getValue(), curr.getSuit());
        group.add(copy);
      }
      curr = curr.getNext();
    }
    return group;
  }

  public Pile toUniquePile(){
    Pile<Card> seq = new Pile<Card>(getLength());
    sort(true);
    Card curr = topCard;
    while (curr != null){
      if (!seq.contains(curr)){
        Card copy = new Card(curr.getValue(), curr.getSuit());
        seq.add((T)copy);
      }
      curr = curr.getNext();
    }
    return seq;
  }


}
