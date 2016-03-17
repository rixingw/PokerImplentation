import javafx.beans.binding.StringBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by wu on 3/16/16.
 */
public class PlayerNode extends Parent {
    Player player;
    Rectangle rect;
    Text score;
    int direcion;

    public PlayerNode(Player player, double x, double y, int direct){
        this.player = player;
        initComponents(x,y, player.getName(), player.countChips());
        this.direcion = direct;
    }


    public void actionDidPerform(){
        score.setText("$" + Integer.toString(player.countChips()));
    }

    Color winnerColor = Color.FIREBRICK;
    Color loserColor = Color.DARKGREY;

    public void setWinnerMode(boolean winner){
        if(winner){
            rect.setStroke(winnerColor);
        }else{
            rect.setStroke(loserColor);
        }
    }


    private void initComponents(double x, double y, String name, int num){
        setLayoutX(x);
        setLayoutY(y);
        rect = new Rectangle(120, 120);
        rect.setArcWidth(30);
        rect.setArcHeight(30);
        rect.setStyle("-fx-stroke-width:5");
        rect.setStroke(loserColor);
        rect.setFill(Color.BLANCHEDALMOND);
        Text textNode = new Text(name);
        textNode.setFont(new Font("Times New Roman", 30));
        textNode.setFill(Color.BLACK);
        StackPane layout = new StackPane(rect);
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        score = new Text("$" + Integer.toString(num));
        score.setFont(new Font("Times New Roman", 40));
        vbox.getChildren().addAll(textNode, score);
        layout.getChildren().addAll(vbox);
        getChildren().add(layout);
    }




}
