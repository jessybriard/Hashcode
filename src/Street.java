import java.util.*;
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

        if(!Intersection.intersections.containsKey(this.startInt)){
            System.out.println("Putting the intersection with id: " + this.startInt);
            Intersection.intersections.put(this.startInt, new Intersection(this.startInt));
        }
        if(!Intersection.intersections.containsKey(this.endInt)){
            System.out.println("Putting the intersection with id: " + this.endInt);
            Intersection.intersections.put(this.endInt, new Intersection(this.endInt));
        }

        Intersection.intersections.get(this.startInt).setOutgoingStreet(this);
        Intersection.intersections.get(this.endInt).setIncomingStreet(this);
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
        if(!waitingCars.contains(car)){
            waitingCars.add(car);
        }
        if(waitingCars.size() == 1){
            moveCars();
        }
    }

    public void moveCars(){
        if(green){
            waitingCars.remove().crossIntersection();
        }
    }

    public boolean hasNextCar(){
        return !waitingCars.isEmpty();
    }

    public Car checkNextCar(){
        return waitingCars.peek();
    }

    public void greenLight(int time) {
        for (int i=0; i<time; i++) {
            if (! waitingCars.isEmpty()) {
                Car car = waitingCars.remove();
                car.crossIntersection();
            }
        }
    }

    public void setGreen(boolean gr){
        green = gr;
    }

    public boolean isGreen(){
        return green;
    }

}
