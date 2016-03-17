import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


class Game {
  private final int PLAYER_INITIAL_CHIPS = 100;
  private ArrayList<Player> players;
  public Deck deck;
  public int pot;
  public Pile tablecards;
  public int currentBet;


  public Game(){
    Player rixing = new Player("Rixing", PLAYER_INITIAL_CHIPS);
    Player greg = new Player("Greg", PLAYER_INITIAL_CHIPS);
    Player elvin = new Player("Elvin", PLAYER_INITIAL_CHIPS);
    Player terrance = new Player("Terrance", PLAYER_INITIAL_CHIPS);
    players = new ArrayList<>();
    players.add(rixing);
    players.add(greg);
    players.add(elvin);
    players.add(terrance);
  }

  public ArrayList<Player> getPlayers(){
    return players;
  }

  public void startGame(){
    deck = new Deck();
    tablecards = new Pile(5);
    deck.shuffle();
    ante();
  }

  public void createTableCards(){
    for (int i = 0; i < 5; i++){
      tablecards.add(deck.deal());
    }

  }


  public Player choseWinner() {
    ArrayList<HRankStatus>  rans =  filterTil1();
    HRankStatus plyer = filterHighCard(rans);
    Player winner = plyer.winner;
    plyer.winner = null;
    winner.winRound(pot);
    return winner;
  }



  private ArrayList<HRankStatus> filterTil1(){
    ArrayList<HRankStatus> ranks = new ArrayList<>();
    for (Player p : players){
      PokerAnalyzer analyzer1 = new PokerAnalyzer(tablecards);
      HRankStatus winnerRank = analyzer1.analyzeHand(p.getHand());
      winnerRank.winner = p;
      ranks.add(winnerRank);
    }
    Collections.sort(ranks);
      Collections.sort(ranks,Collections.reverseOrder());
    ArrayList<HRankStatus> possibleWinners = new ArrayList<>();
    HRankStatus curr = ranks.get(0);
    for (int i = 1; i < ranks.size(); i++){
      HRankStatus foo = ranks.get(i);
     if (curr.compareTo(foo) == 0) {
       possibleWinners.add(foo);
     }else if (curr.compareTo(foo)== -1){
       curr = foo;
       possibleWinners.clear();
     }
    }
      possibleWinners.add(curr);
    return possibleWinners;

  }


  private HRankStatus filterHighCard(ArrayList<HRankStatus> ranks){
    if(ranks.size() <= 1){
      return ranks.get(0);
    }else{
      Collections.sort(ranks,new RankComparator());
      Collections.sort(ranks, new RankComparator().reversed());
      HRankStatus one =  ranks.get(0);
      HRankStatus two =  ranks.get(1);
      if (one.compareTo(two) == 0){
       Card c1 =  one.winner.getHand().getHighestCard();
        Card c2 = two.winner.getHand().getHighestCard();
        if (c1.compareTo(c2) == 0){
            if (c1.getSuit().compareTo(c2.getSuit()) > 0){
              return one;
            }else{
              return two;
            }
        }else if (c1.compareTo(c2) == 1){
          return one;
        }else{
          return two;
        }
      }else if (one.compareTo(two) == -1) {
        return two;
      }else {
        return one;
      }
    }
  }


  public void reset(){
    System.out.println("Game reset");
    deck = new Deck();
    tablecards.clear();
    for (Player p : players){
      p.reset();
    }
    pot = 0;
  }

  public void ante(){
      currentBet = 2;
    for (Player p: players){
      pot += p.bet(currentBet);
    }
  }

  public void distributeCards(){
    for (Player p: players){
      Card c = deck.deal();
      p.addCardtoHand(c);
    }
  }


  public void display(){
    Card head = tablecards.getEntry(1);
    System.out.print("\t");
    while (head != null){
      System.out.print(head + "\t");
      head = head.getNext();
    }
    System.out.print("\n");
    for (Player p : players){
      PokerAnalyzer pa = new PokerAnalyzer(tablecards);
      pa.analyzeHand(p.getHand()).display();
      System.out.print(p.getName() + " : $" + p.countChips() + "\n");
      p.hand.display();

    }
  }


  public static void main(String[] args){
    Game game = new Game();
    System.out.println();
    game.startGame();
    game.distributeCards();
    game.distributeCards();
    game.createTableCards();
    game.choseWinner();
    game.display();
    game.reset();
  }
}
