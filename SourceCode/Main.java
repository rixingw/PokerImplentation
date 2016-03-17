import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Created by wu on 3/14/16.
 */

public class Main extends Application{

    public static void main(String[] args){launch(args);}
    Stage window;
    GraphicsContext gc;
    Scene scene;
    Group root;
    Game game;
    ArrayList<PlayerNode> players;
    ArrayList<CardNode> cardnodes;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        game = new Game();
        players = new ArrayList<>();
        loadGameUI();
        loadPlayersUI();
    }


    private void loadGameUI(){
        window.setResizable(false);
        window.setTitle("Texas Holdem Poker");
        root = new Group();
        scene = new Scene(root);
        Canvas canvas = new Canvas(1000, 600);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        Image bgImg = new Image("images/background.jpg");
        gc.drawImage(bgImg,0,0);
        cardnodes = new ArrayList<>();
        window.setScene(scene);
        window.show();
        scene.setOnMouseClicked(e ->{
                startGame();
        });
    }
//
    private Player findWinner(){
       Player winner =  game.choseWinner();
        for (PlayerNode p: players){
            p.actionDidPerform();
            if (p.player == winner){
                p.setWinnerMode(true);
            }else{
                p.setWinnerMode(false);
            }
        }
        return winner;
    }


    private void clearScreen(){
        root.getChildren().removeAll(cardnodes);
    }


    private void startGame(){
        game.startGame();
        sendCards(1);
        sendCards(2);
        showCards();
        Player p = findWinner();
        game.display();
        game.reset();
        clearScreen();
    }

    private void sendCards(int pos){
        game.distributeCards();
        for (PlayerNode p : players){
            Hand h = p.player.getHand();
            Card fCard = h.getEntry(pos);
            cardtoPlayer(fCard,p,pos-1);
        }
    }

    private void showCards(){
        game.createTableCards();
        double x = 300;
        double y = 100;
        Pile tCards = game.tablecards;
        Card head = tCards.getEntry(1);
        int index = 0;
        while (head != null){
            CardNode cn = new CardNode(head);
            double dx = x + cn.getWidth() * index * 1.2;
            gc.drawImage(cn,dx,y);
            cardnodes.add(cn);
            index++;
            head = head.getNext();
        }
    }




    private void cardtoPlayer(Card card, PlayerNode playerNode, int position){
        double x = playerNode.getLayoutX();
        double y = playerNode.getLayoutY();
        double direction = playerNode.direcion;
        CardNode cardnode = new CardNode(card);
        if (direction == 0){
            x += (cardnode.getWidth() * 1.1 * position) + 10;
            y -= (cardnode.getHeight() + 10);
        }else if (direction > 0){
            x += (cardnode.getWidth() * position * 1.1) + 130;
            y += 20;
        }else{
            x -= (cardnode.getWidth() * position * 1.1) + 50;
            y += 20;
        }
        gc.drawImage(cardnode,x,y);
        cardnodes.add(cardnode);
    }


    /**
     *
     *
     * (0,230)                                  (880,230)
     *
     *
     ********(310,480)**********(690,480)************/
    private void loadPlayersUI(){
        Player p1 = game.getPlayers().get(0);
        addPlayer(p1,0,180, 1);
        Player p2 = game.getPlayers().get(1);
        addPlayer(p2,250,480, 0);
        Player p3 = game.getPlayers().get(2);
        addPlayer(p3,630,480, 0);
        Player p4 = game.getPlayers().get(3);
        addPlayer(p4,880,180, -1);

    }

    private void addPlayer(Player player, double x, double y, int direction){
        PlayerNode pn = new PlayerNode(player, x, y, direction);
        players.add(pn);
        root.getChildren().add(pn);
    }

}
