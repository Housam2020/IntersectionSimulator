import java.util.ArrayList;

public class Direction {

    int numberOfLanes;
    ArrayList<Lane> lanes = new ArrayList<>();
    Lane leftTurnLane = new Lane();

    public Direction(int numberOfLanes) {   //Initalize direction with all it's lanes
        this.numberOfLanes = numberOfLanes;

        for (int i = 1; i < numberOfLanes; i++) {
            lanes.add(new Lane());
        }
    }





}
