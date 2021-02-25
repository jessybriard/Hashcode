import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Car {

    private Queue<String> path = new LinkedList<>();

    public Car(String data) {
        path.addAll(Arrays.asList(data.split(" ")));
    }

    public String getStreet()
    {
        return path.element();
    }

}
