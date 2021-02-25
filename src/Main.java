import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    private String dataSet = "a";
    public static int currentTime = 0;
    private List<String> inputData;
    private int simDuration;
    private int nbIntersections;
    private int nbStreets;
    private int nbCars;
    private int bonusPoints;
    private ArrayList<Intersection> intersections;
    private HashMap<String, Street> streets;
    private ArrayList<Car> cars;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Main main = new Main();
        main.run();

    }

    public void reset() throws FileNotFoundException, UnsupportedEncodingException {

        Scanner input = new Scanner(new File("src/" + dataSet + ".txt"));
        inputData = new ArrayList<>();
        while (input.hasNext()) {
            String answer = input.nextLine();
            inputData.add(answer);
        }
        System.out.println(inputData);

        String[] data1 = inputData.get(0).split(" ");
        simDuration = Integer.parseInt(data1[0]);
        nbIntersections = Integer.parseInt(data1[1]);
        nbStreets = Integer.parseInt(data1[2]);
        nbCars = Integer.parseInt(data1[3]);
        bonusPoints = Integer.parseInt(data1[4]);

        intersections = new ArrayList<>();
        for (int i = 0; i < nbIntersections; i++) {
            intersections.add(new Intersection());
        }

        streets = new HashMap<>();
        for (int i = 1; i <= nbStreets; i++) {
            String[] data = inputData.get(i).split(" ");
            streets.put(data[2], new Street(data[0], data[1], data[2], data[3]));
        }

        for (Street street : streets.values()) {
            int fromInt = street.getStartInt();
            int endInt = street.getEndInt();
            intersections.get(fromInt).setOutcomingStreet(street);
            intersections.get(endInt).setIncomingStreet(street);
        }

        cars = new ArrayList<>();
        for (int i = nbStreets + 1; i <= (nbStreets + nbCars); i++) {
            String data = inputData.get(i);
            String pathString = data.substring((data.split(" ")[0]).length() + 1);
            Car newCar = new Car();
            for (String streetString : pathString.split(" ")) {
                newCar.addStreet(streets.get(streetString));
            }
            cars.add(newCar);
            //System.out.println(data.substring((data.split(" ")[0]).length()+1));
        }

        for (Car car : cars) {
            Street initStreet = car.getStreet();
            initStreet.addCar(car);
        }

    }

    public void run() throws FileNotFoundException, UnsupportedEncodingException {

        reset();

        // SIMULATION
        /**while (currentTime<simDuration) {


            currentTime++;
        }*/


        for (int i=0; i<intersections.size(); i++) {

        }


        int minimumPath = cars.get(0).getPathLength();
        Car minPathCar = cars.get(0);
        for (Car car: cars) {
            int pathLength = car.getPathLength();
            if (pathLength<minimumPath) {
                minimumPath = pathLength;
                minPathCar = car;
            }
        }

        ArrayList<String> schedules = new ArrayList<>();
        while (minPathCar.getPathLength() > 0) {
            Street currentStreet = minPathCar.getStreet();
            int position = currentStreet.getPosition(minPathCar);
            currentStreet.greenLight(position);
            schedules.add(currentStreet.getIntersection() + " " + currentStreet.getName());
            //System.out.println(currentStreet.getIntersection() + " - " + currentStreet.getName());
        }

        writeFile(schedules);

    }

    public void writeFile(ArrayList<String> schedules) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(dataSet + "_submission.txt", "UTF-8");
        writer.println(schedules.size());
        for (String schedule: schedules) {
            String[] scheduleData = schedule.split(" ");
            writer.println(scheduleData[0]);
            writer.println("1");
            writer.println(scheduleData[1] + " 1");
        }
        writer.close();
    }

}

