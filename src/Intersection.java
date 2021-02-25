import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Intersection {

    public static HashMap<Integer, Intersection> intersections = new HashMap<>();
    private ArrayList<Street> schedule = new ArrayList<>();
    private int id;

    private Set<Street> incomingStreets = new HashSet<>();
    private Set<Street> outgoingStreets = new HashSet<>();

    public Intersection(int id){
        this.id = id;
    }

    public Set<Street> getIncomingStreets() {
        return incomingStreets;
    }

    public void setIncomingStreet(Street incomingStreet) {
        incomingStreets.add(incomingStreet);
    }

    public Set<Street> getOutgoingStreets() {
        return outgoingStreets;
    }

    public void setOutgoingStreet(Street outcomingStreet) {
        outgoingStreets.add(outcomingStreet);
    }

    public int getId()
    {
        return id;
    }

    public void setGreenFor(Street str){
        if(outgoingStreets.contains(str)){
            str.setGreen(true);
            schedule.add(str);
        }
    }

}
