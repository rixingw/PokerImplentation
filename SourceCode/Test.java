
class Test{
  public static void main(String[] args){
      Pile<Card> cards = new Pile<Card>(10);
      Card ace = new Card("Ace", "Spades");
      Card two = new Card("Two", "Diamonds");
      Card three = new Card("Three", "Hearts");
      Card four = new Card("Four", "Clubs");
      Card five = new Card("Five", "Spades");
      Card six = new Card("Six", "Diamonds");
      Card seven = new Card("Seven", "Hearts");
      Card eight = new Card("Eight", "Clubs");
      Card nine = new Card("Nine", "Spades");
      Card ten = new Card("Ten", "Diamonds");
      Card jack = new Card("Jack", "Clubs");
      Card king = new Card("King", "Clubs");

      test("getLength",cards.getLength()==0, true);
      test("add Card ", cards.add(ace), true);
      test("add Card ", cards.add(two), true);
      test("add Card ", cards.add(three), true);
      test("add Card ", cards.add(four), true);
      test("add Card ", cards.add(five), true);
      test("add Card ", cards.add(six), true);
      test("add Card ", cards.add(seven), true);
      test("add Card ", cards.add(eight), true);
      test("add Card ", cards.add(nine), true);
      test("add Card ", cards.add(ten), true);
      test("add Card ", cards.add(jack), false);
      test("getLength",cards.getLength()==10, true);
      test("isFull   ", cards.isFull(), true);
      test("remove@1 ", cards.remove(1) == null, false);
      test("remove@0 ", cards.remove(0) == null, true);
      test("remove10 ", cards.remove(10)== null, true);
      test("remove   ", cards.getLength() == 9, true);

      test("display  ", true, true);
      cards.display();

      boolean success = cards.add(1, ace);
      test("add ace@1",success==true, true);
      cards.display();

      Card replace = new Card("Ace", "Spades");
      success = cards.replace(10, replace);
      test("replaceTen",success== true, true);
      cards.display();

      success = cards.replace(1, eight);
      test("replaceOne",success== true, true);
      cards.display();

      success = cards.replace(5, nine);
      test("replaceFive",success== true, true);
      cards.display();

      Card highcard = cards.getHighestCard();
      test("High Card ", true, true);
      System.out.println("-> " + highcard);
      cards.display();

      System.out.println("Test Shuffle: N/A");
      cards.shuffle();
      cards.display();


      cards.sort(true);
      System.out.println("Test Sorting: Asc");
      cards.display();

      cards.sort(false);
      System.out.println("Test Sorting: Dsc");
      cards.display();

      Pile pile1 = new Pile(5);
      Card a = new Card("Ace", "Spades");
      Card b = new Card("Two", "Spades");
      Card c = new Card("Three", "Spades");
      pile1.addAll(a,b,c);

      test("\nPile 1", true, true);
      pile1.display();
      System.out.println("   +   \n ");
      Card d = new Card("Four", "Spades");
      Card e = new Card("Five", "Spades");

      Pile pile2 = new Pile(2);
      pile2.add(d);
      pile2.add(e);
      test("Pile 2", true, true);
      pile2.display();
      pile1.join(pile2);
      test("   =  ", true, true);
      pile1.display();

      System.out.println("All Cards:");
      cards.display();

      test("Group by ♧", true, true);
      Pile clubs = cards.groupBySuit(Suit.CLUBS);
      clubs.display();

      test("Group by ♤", true, true);
      Pile spades = cards.groupBySuit(Suit.SPADES);
      spades.display();

      test("Group by ♡", true, true);
      Pile hearts = cards.groupBySuit(Suit.HEARTS);
      hearts.display();


      test("Group by ♢", true, true);
      Pile diamonds = cards.groupBySuit(Suit.DIAMONDS);
      diamonds.display();



      Card c1 = new Card("Six", "Diamonds");
      Card c2 = new Card("Six", "Hearts");
      Card c3 = new Card("Eight", "Clubs");
      Card c4 = new Card("Nine", "Spades");
      Card c5 = new Card("Ten", "Diamonds");
      Card c6 = new Card("Jack", "Clubs");
      Card c7 = new Card("King", "Clubs");

      Pile groupOfValues = new Pile(7);
      groupOfValues.addAll(c1,c2,c3,c4,c5,c6,c7);
      groupOfValues.display();

      Pile sixes = groupOfValues.groupByValue(Value.SIX);
      test("group of 6s", sixes.getLength() == 2, true);
      sixes.display();


  }

  public static void test(String name, boolean a, boolean b){
    if (a == b){
      System.out.println(name + ": Passed!");
    }else{
      System.out.println(name + ": Failed!");
    }
  }
}
