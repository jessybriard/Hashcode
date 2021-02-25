import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public int currentTime = 0;
    public int simulationDuration = 0;

    HashMap

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
        }

        for(Intersection inter : Intersection.intersections.values()){
            System.out.println("Intersection id: " + inter.getId());
            System.out.println("Incoming Streets: ");
            for(Street str : inter.getIncomingStreets()){
                System.out.println(str.getName() + " ");
            }
            System.out.println("Outgoing Streets: ");
            for(Street str : inter.getOutgoingStreets()){
                System.out.println(str.getName() + " ");
            }
        }

        /*for (Street street: streets.values()) {
            int fromInt = street.getStartInt();
            int endInt = street.getEndInt();
            intersections.get(fromInt).setOutcomingStreet(street);
            intersections.get(endInt).setIncomingStreet(street);
        }*/

        ArrayList<Car> cars = new ArrayList<>();
        for (int i=nbStreets+1; i<=(nbStreets+nbCars); i++) {
            String data = inputData.get(i);
            String pathString = data.substring((data.split(" ")[0]).length()+1);
            Car newCar = new Car();
            for (String streetString: pathString.split(" ")) {
                newCar.addStreet(streets.get(streetString));
            }
            cars.add(newCar);
            //System.out.println(data.substring((data.split(" ")[0]).length()+1));
        }

        for (Car car: cars) {
            Street initStreet = car.getStreet();
            initStreet.addCar(car);
        }

        // SIMULATION
        while (currentTime<simDuration) {


            currentTime++;
        }

    }

    public void step()
    {
        for(Intersection inter : Intersection.intersections.values()){
            int chosenIndex = 0;
            ArrayList<Car> lineup = new ArrayList<>();
            for(Street str : inter.getIncomingStreets()){
                lineup.add(str.checkNextCar());
            }
            if(!lineup.isEmpty() && lineup.size() > 1){
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
    }

}

