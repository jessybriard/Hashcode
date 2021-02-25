import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int currentTime = 0;

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
        int nbIntersections = Integer.parseInt(data1[1]);
        int nbStreets = Integer.parseInt(data1[2]);
        int nbCars = Integer.parseInt(data1[3]);
        int bonusPoints = Integer.parseInt(data1[4]);

        ArrayList<Intersection> intersections = new ArrayList<>();
        for (int i=0; i<nbIntersections; i++) {
            intersections.add(new Intersection());
        }

        HashMap<String, Street> streets = new HashMap<>();
        for (int i=1; i<=nbStreets; i++) {
            String[] data = inputData.get(i).split(" ");
            streets.put(data[2], new Street(data[0], data[1], data[2], data[3]));
        }

        for (Street street: streets.values()) {
            int fromInt = street.getStartInt();
            int endInt = street.getEndInt();
            intersections.get(fromInt).setOutcomingStreet(street);
            intersections.get(endInt).setIncomingStreet(street);
        }

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

}

