/**
 * Created by wu on 3/17/16.
 */
import java.util.Comparator;

public class RankComparator implements Comparator<HRankStatus> {
    @Override
    public int compare(HRankStatus o1, HRankStatus o2) {

        Card o1c = o1.getHighestCard();
        Card o2c = o2.getHighestCard();
        if (o1c.compareTo(o2c) == 0){
            while(o1c.getPrev() != null && o2c.getPrev() != null){
                if (o1c.compareTo(o2c) == 0) {
                    o1c = o1c.getPrev();
                    o2c = o2c.getPrev();
                }else{
                    break;
                }
            }
        }
        return o1c.compareTo(o2c);
     }
}
