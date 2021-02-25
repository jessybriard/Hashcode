import java.util.HashSet;
import java.util.Set;

public class Intersection {

    private Set<Street> incomingStreets = new HashSet<>();
    private Set<Street> outcomingStreets = new HashSet<>();


    public Set<Street> getIncomingStreets() {
        return incomingStreets;
    }

    public void setIncomingStreet(Street incomingStreet) {
        incomingStreets.add(incomingStreet);
    }

    public Set<Street> getOutcomingStreets() {
        return outcomingStreets;
    }

    public void setOutcomingStreet(Street outcomingStreet) {
        outcomingStreets.add(outcomingStreet);
    }

}
