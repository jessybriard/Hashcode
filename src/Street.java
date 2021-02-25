public class Street {

    int startInt;
    int endInt;
    String name;
    int time;

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
}
