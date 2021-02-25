import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello World!");
        Scanner input = new Scanner(new File("src/a.txt"));
        List<String> inputData = new ArrayList<>();
        while (input.hasNext()) {
            String answer = input.nextLine();
            inputData.add(answer);
        }
        System.out.println(inputData);

        String[] data1 = inputData.get(0).split(" ");
        int simDuration = Integer.parseInt(data1[0]);
        int nbIntersections = Integer.parseInt(data1[1]);
        int nbStreets = Integer.parseInt(data1[2]);
        int nbCars = Integer.parseInt(data1[3]);
        int bonusPoints = Integer.parseInt(data1[4]);

        HashMap<String, Street> streets = new HashMap<>();

        for (int i=1; i<=nbStreets; i++) {
            String[] data = inputData.get(i).split(" ");
            streets.put(data[2], new Street(data[0], data[1], data[2], data[3]));
        }

        

    }

}

