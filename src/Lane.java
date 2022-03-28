import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Lane {

    Queue<Car> Lane = new LinkedList<>();

    public void addHumanCar() {
        Lane.add(new Car(false));
    }

    public void addSelfDrivingCar() {
        Lane.add(new Car(true));
    }

    public boolean isNextSelfDriving() {
        if (!Lane.isEmpty())
            if (Lane.peek().selfDriving) {
                return true;
            }
        return false;
    }

    public void test(){
        int count = 0;
        for (Car i: Lane){
            System.out.println(count);
            count++;
        }

    }


    public Car crossCar() {
        return Lane.remove();
    }

    public Boolean isEmpty(){
        return Lane.isEmpty();
    }


    public static void main(String[] args) {
        Lane mine = new Lane();

        mine.addSelfDrivingCar();
        mine.addSelfDrivingCar();
        mine.addHumanCar();
        mine.test();
        mine.crossCar();
        mine.test();
    }


}
