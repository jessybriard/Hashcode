import java.util.LinkedList;
import java.util.Queue;

public class Street {

    private int startInt;
    private int endInt;
    private String name;
    private int time;
    private Queue<Car> waitingCars = new LinkedList<>();
    private boolean green;

    public Street(String startInt, String endInt, String name, String time) {
        this.green = false;
        this.startInt = Integer.parseInt(startInt);
        this.endInt = Integer.parseInt(endInt);
        this.name = name;
        this.time = Integer.parseInt(time);
    }

    public int getStartInt() {
        return startInt;
    }

    public int getEndInt() {
        return endInt;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public void addCar(Car car) {
        waitingCars.add(car);
    }

    public void greenLight(int time) {
        for (int i=0; i<time; i++) {
            if (! waitingCars.isEmpty()) {
                Car car = waitingCars.remove();
                car.crossIntersection();
            }
        }
    }

    public int getPosition(Car car) {
        Object[] cars = waitingCars.toArray();
        for (int i=0; i<cars.length; i++) {
            if (cars[i] == car) {
                return i+1;
            }
        }
        return 0;
    }

    public int getIntersection()
    {
        return endInt;
    }

}
