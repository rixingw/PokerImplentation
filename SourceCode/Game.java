import java.util.ArrayList;

class Game {
  private final int PLAYER_INITIAL_CHIPS = 100;
  public ArrayList<Player> players;
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

  public void startGame(){
    deck = new Deck();
    tablecards = new Pile(5);
    ante();
    distributeCards();
    distributeCards();
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

  

  public String toString(){
    String temp = "";
    for (Player p : players){
      temp += p + "\n";
      temp += p.hand;
      temp += "\n";
    }
    return temp;
  }



  public static void main(String[] args){
    Game game = new Game();
    game.startGame();
    System.out.println(game);
  }
}
