import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Intersection {

    private ArrayList<Street> incomingStreets = new ArrayList<>();
    private ArrayList<Street> outcomingStreets = new ArrayList<>();
    private HashMap<Street, Integer> time


    public ArrayList<Street> getIncomingStreets() {
        return incomingStreets;
    }

    public void setIncomingStreet(Street incomingStreet) {
        incomingStreets.add(incomingStreet);
    }

    public ArrayList<Street> getOutcomingStreets() {
        return outcomingStreets;
    }

    public void setOutcomingStreet(Street outcomingStreet) {
        outcomingStreets.add(outcomingStreet);
    }

    public int getNbTrafficLights()
    {
        return incomingStreets.size();
    }

    public void setGLTime(Street street, int time) {

    }

}
