import java.util.*;

public class Intersection {

    private ArrayList<Street> incomingStreets = new ArrayList<>();
    private ArrayList<Street> outcomingStreets = new ArrayList<>();
    private HashMap<Street, Integer> schedule = new HashMap<>();


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
        schedule.put(street, time);
    }

    public void step() {
        int totalTime = 0;
        for (Integer time: schedule.values()) {
            totalTime += time;
        }
        if (totalTime != 0) {
            int tempTime = Main.currentTime % totalTime;

            int temp = 0;
            for (Street street : schedule.keySet()) {
                temp += schedule.get(street);
                if (tempTime < temp) {
                    street.greenLight(1);
                    break;
                }
            }
        }

    }


    public void randomSchedule() {
        for (Street street: incomingStreets) {
            Random random = new Random();
            setGLTime(street, random.nextInt(5));
        }
    }


    public String getSchedule() {
        ArrayList<String> streetSchedules = new ArrayList<>();
        for (Street street: schedule.keySet()) {
            if (schedule.get(street) != 0) {
                streetSchedules.add(street.getName() + " " + schedule.get(street));
            }
        }
        if (streetSchedules.isEmpty()) {
            return "";
        } else {
            String returnString = "" + streetSchedules.size();
            for (String scheduling: streetSchedules) {
                returnString = returnString + "/" + scheduling;
            }
            return returnString;
        }
    }


}
