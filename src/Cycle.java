public class Cycle {

    int cycleTime;
    int timePassedSubcycle;
    int timePassedTotal = 0;
    int numberOfLanes;
    double percentCongestion; //value for congestion between 0-1
    double percentSelfDriving; //value for selfDriving between 0-1
    //double percentLeftTurn = 0.2;
    int maxCarsInLane = 4;   //Value for the maximum amount of cars that can generate at once in a lane
    int numberOfCarsPassed = 0;

    Direction[] directions = new Direction[4];  //NSEW directions
    SignalLight light = new SignalLight();




    public Cycle(int cycleTime, int numberOfLanes, double percentCongestion, double percentSelfDriving) {
        this.cycleTime = cycleTime;
        this.timePassedSubcycle = 0;
        this.percentCongestion = percentCongestion;
        this.percentSelfDriving = percentSelfDriving;

        directions[0] = new Direction(numberOfLanes);
        directions[1] = new Direction(numberOfLanes);
        directions[2] = new Direction(numberOfLanes);
        directions[3] = new Direction(numberOfLanes);

        this.numberOfLanes = numberOfLanes;     //Generating all the lanes
        for (int i = 0; i < 4; i++) {
            directions[i].lanes.add(new Lane());

        }




    }

    public void generate(Lane lane) {    //enqueues cars into lane, taking into account %congestion and %selfDriving
        int numberOfCars = (int) ((double) maxCarsInLane * (percentCongestion) );    //calculate total # cars depending on congestion + maxCars

        double random;
        for (int i = 0; i < numberOfCars; i++) {    //adds ratio of self vs human cars based on %selfDrive
            random = Math.random();
            if (percentSelfDriving >= random) {

                lane.addSelfDrivingCar();
            }
            lane.addHumanCar();
        }
    }


    public void subCycle() {    //Generates cars for each lane in each direction, then lets batch through

        Car car1;
        Car car2;
        timePassedSubcycle = 0;




        while (cycleTime >= timePassedSubcycle) {

            for (Direction currentDirection : directions) {   //Generating cars for each lane in each direction
                for (Lane currentLane : currentDirection.lanes) {
                    generate(currentLane);
                }
                generate(currentDirection.leftTurnLane);    //Also for left turn lane
            }

            for (int i = 0; i < 4; i++) {   //Itterate every direction that has green light
                if (light.lights[i] == 1) {

                    if (percentCongestion <= Math.random()) { //Represent clearing in the road for car to turn left, more congestion = less left turns
                        if (!directions[i].leftTurnLane.isEmpty()) {
                            car1 = directions[i].leftTurnLane.crossCar();
                            timePassedSubcycle += (car1.reactionTime + car1.timeToCross);
                        }
                    }

                    else {
                        for (Lane currentLane : directions[i].lanes) {

                            if (currentLane.isNextSelfDriving()) {   //If there is a self driving car at the front, let two cars cross because they can be closer together
                                if (!currentLane.isEmpty()) {
                                    car1 = currentLane.crossCar();  //cross two cars and add cross time to total
                                    timePassedSubcycle += (car1.reactionTime + car1.timeToCross);
                                    numberOfCarsPassed ++;
                                }
                                if (!currentLane.isEmpty()) {
                                    car2 = currentLane.crossCar();
                                    timePassedSubcycle +=  (car2.reactionTime + car2.timeToCross);
                                    numberOfCarsPassed ++;
                                }

                            } else {    //If car is not self driving, only let one car through
                                if (!currentLane.isEmpty()) {
                                    car1 = currentLane.crossCar();
                                    timePassedSubcycle += (car1.reactionTime + car1.timeToCross);
                                    numberOfCarsPassed++;
                                }
                            }
                        }
                    }
                }
            }


        }
        light.LightCycle();
        timePassedTotal+=timePassedSubcycle;
    }

    public int totalTime(){
        return timePassedTotal;
    }

    public int totalCarsCrossed(){
        return numberOfCarsPassed;
    }


    public static void main(String[] args) {
        Cycle test = new Cycle((60), 2, 0.5, 0.5);

        test.subCycle();
        System.out.println(test.numberOfCarsPassed);
        System.out.println(test.timePassedTotal);


        for (int i = 0; i < 10; i++) {
            test.subCycle();
        }
        System.out.println(test.numberOfCarsPassed);
        System.out.println(test.timePassedTotal);





    }


}
