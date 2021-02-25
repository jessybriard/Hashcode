import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int points = 0;

    public static int currentTime = -1;
    public static int simulationDuration = 0;

    public static HashMap<String, Street> streetsMap = new HashMap<>();
    public static ArrayList<Car> cars = new ArrayList<>(); 

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello World!");
        Scanner input = new Scanner(new File("src/a.txt"));
        List<String> inputData = new ArrayList<>();
        while (input.hasNext()) {
            String answer = input.nextLine();
            inputData.add(answer);
        }
        System.out.println(inputData);

        String[] data1 = inputData.get(0).split(" ");
        int simDuration = Integer.parseInt(data1[0]);
        simulationDuration = simDuration;
        int nbIntersections = Integer.parseInt(data1[1]);
        int nbStreets = Integer.parseInt(data1[2]);
        int nbCars = Integer.parseInt(data1[3]);
        int bonusPoints = Integer.parseInt(data1[4]);



        HashMap<String, Street> streets = new HashMap<>();
        for (int i=1; i<=nbStreets; i++) {
            String[] data = inputData.get(i).split(" ");
            streets.put(data[2], new Street(data[0], data[1], data[2], data[3]));
            streetsMap.put(data[2], new Street(data[0], data[1], data[2], data[3]));
        }

        /*for(Intersection inter : Intersection.intersections.values()){
            System.out.println("Intersection id: " + inter.getId());
            System.out.println("Incoming Streets: ");
            for(Street str : inter.getIncomingStreets()){
                System.out.println(str.getName() + " ");
            }
            System.out.println("Outgoing Streets: ");
            for(Street str : inter.getOutgoingStreets()){
                System.out.println(str.getName() + " ");
            }
        }*/

        /*for (Street street: streets.values()) {
            int fromInt = street.getStartInt();
            int endInt = street.getEndInt();
            intersections.get(fromInt).setOutcomingStreet(street);
            intersections.get(endInt).setIncomingStreet(street);
        }*/

        
        for (int i=nbStreets+1; i<=(nbStreets+nbCars); i++) {
            String data = inputData.get(i);
            String pathString = data.substring((data.split(" ")[0]).length()+1);
            
            Queue<Street> path = new LinkedList<>();
            for (String streetString: pathString.split(" ")) {
                path.add(streets.get(streetString));
            }
            Car newCar = new Car(path);
            cars.add(newCar);
            //System.out.println(data.substring((data.split(" ")[0]).length()+1));
        }

        for (Car car: cars) {
            Street initStreet = car.getStreet();
            initStreet.addCar(car);
        }

        // SIMULATION
        while (currentTime<1000) {
            step();
        }


        System.out.println("\n\n\nTotal points: " + points);
        for (Car oneCar : cars) {
            System.out.println("Car location: " + oneCar.getStreet().getName());
        }

        for (Street str : streetsMap.values()) {
            String green = "";
            if(str.isGreen()){
                green = "green";
            }
            else{green = "red";}
            System.out.println("Street " + str.getName() + " is currently " + green);
        }
    }

    public static void step()
    {
        for(Intersection inter : Intersection.intersections.values()){

            int chosenIndex = 0;
            ArrayList<Car> lineup = new ArrayList<>();
            for(Street str : inter.getIncomingStreets()){
                if(str.hasNextCar()){
                    lineup.add(str.checkNextCar());
                }
            }
            if(!lineup.isEmpty() && lineup.size() >= 2){
                for(int i = 1; i < lineup.size(); i++){
                    if(lineup.get(i).getPathLength() < lineup.get(chosenIndex).getPathLength()){
                        chosenIndex = i;
                    }
                }
            }
            if(!lineup.isEmpty()){
                inter.setGreenFor(lineup.get(chosenIndex).getStreet());
            }
        }

        for(Street str : streetsMap.values()){
            str.moveCars();
        }

        if(currentTime > -1){
            for(Car c : cars){
                c.drive();
            }
        }

        currentTime++;
    }

    public static void arrivedCar(Car removingCar){
        points += 1000 + (simulationDuration - currentTime);
        cars.remove(removingCar);
    }

}

