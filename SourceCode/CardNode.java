import javafx.scene.image.Image;

/**
 * Created by wu on 3/16/16.
 */
public class CardNode extends Image{

    public CardNode(Card card){
        super("images/Cards/" + card + ".png");
    }

}
