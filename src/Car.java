import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Car {

    private Queue<Street> path = new LinkedList<>();

    public Car() {}

    public Street getStreet()
    {
        return path.element();
    }

    public void addStreet(Street street) {
        path.add(street);
    }

    public void crossIntersection()
    {
        path.remove();
        if (! path.isEmpty()) {
            Street newStreet = path.element();
            newStreet.addCar(this);
        } else {
            // CAR has finished path, calculate score
        }
    }

    public int getPathLength()
    {
        return path.size();

    }

}
