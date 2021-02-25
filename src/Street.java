import java.util.LinkedList;
import java.util.Queue;

public class Street {

    private int startInt;
    private int endInt;
    private String name;
    private int time;
    private Queue<Car> waitingCars = new LinkedList<>();

    public Street(String startInt, String endInt, String name, String time) {
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
}
