import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Car {

    private Queue<Street> path = null;

    private Street currentStreet;
    private int driveCount;

    public Car(Queue<Street> path_in) {
        currentStreet = path_in.remove();
        currentStreet.addCar(this);
        path = path_in;
        driveCount = 0;
    }

    public void drive()
    {
        if(currentStreet == null){
            currentStreet = path.remove();
        }
        else{
            if(driveCount > 0){
                driveCount -= 1;
            }
            if(driveCount == 0){
                if(path.isEmpty()){
                    Main.arrivedCar(this);
                    return;
                }
                currentStreet.addCar(this);
            }
        }
    }

    public Street getStreet()
    {
        return currentStreet;
    }

    public void addStreet(Street street) {
        path.add(street);
    }

    public void crossIntersection()
    {
        currentStreet = path.remove();
        driveCount = currentStreet.getTime();
    }

    public int getPathLength()
    {
        return path.size();

    }

}
