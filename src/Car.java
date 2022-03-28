public class Car {

    boolean selfDriving = false;
    int timeToCross;   //seconds, can represent speed eg. 5 seconds = 60 km/h
    int reactionTime;
    int speedUpTime;
    int stopTime;

    String test = "hi";

    public Car(boolean selfDriving) {
        if (selfDriving) {
            selfDriving = true;
            timeToCross = 3;
            reactionTime = 0;
            speedUpTime = 2;
            stopTime = 3;
        }
        else {
            timeToCross = 3;
            reactionTime = 3;
            speedUpTime = 2;
            stopTime = 3;
        }
    }

}
