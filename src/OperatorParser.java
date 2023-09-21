import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class OperatorParser {
    /**
     * The HashMap that contains all parsed Operator objects from operatordata.txt.
     * The key for each Operator object is its name. Example: The Operator object with the name 'Sledge' can be found by querying
     * the HashMap for 'Sledge'.
     */
    private static final HashMap<String,Operator> operatorHashMap = new HashMap<>();

    /**
     * Parses the file operatordata.txt and populates the {@link #operatorHashMap} with Operator objects.
     * The Operator objects are stored in the HashMap with their names as the keys.
     * @see OperatorParser#getOperator(String)
     */
    public static void parseOperators() {
        InputStream inputStream = R6Randomizer.class.getResourceAsStream("/operatordata.txt");
        Scanner fileScanna = new Scanner(inputStream);

        //        File file = new File("resources/operatordata.txt");
        //        Scanner fileScanna = new Scanner(file);

        String line;
        while (fileScanna.hasNextLine()) {
            ArrayList<String[]> primaries = new ArrayList<>();
            ArrayList<String[]> secondaries = new ArrayList<>();
            ArrayList<String[]> gadgets = new ArrayList<>();

            line = fileScanna.nextLine();

            if (Objects.equals(line, "START")) {
                line = fileScanna.nextLine();
                String name = line;
                line = fileScanna.nextLine();

                while (!Objects.equals(line, "Secondaries")) {
                    line = fileScanna.nextLine();
                    if (!Objects.equals(line, "Secondaries")) primaries.add(line.split(", "));
                }

                while (!Objects.equals(line, "Gadgets")) {
                    line = fileScanna.nextLine();
                    if (!Objects.equals(line, "Gadgets")) secondaries.add(line.split(", "));
                }

                while (!Objects.equals(line, "END")) {
                    line = fileScanna.nextLine();
                    if (!Objects.equals(line, "END")) gadgets.add(line.split(", "));
                }

                Operator newOP = new Operator(name, primaries, secondaries, gadgets);
                operatorHashMap.put(name, newOP);
            }
        }
        fileScanna.close();
    }

    /**
     * Gives the Operator object whose name matches the input.
     * @param input The name of the operator to look for within {@link #operatorHashMap}.
     * @return The operator object that input maps to.
     * @throws InvalidParameterException If an operator with input as its name does not exist in {@link #operatorHashMap}.
     */
    public static Operator getOperator(String input) throws InvalidParameterException {
        Operator operator = operatorHashMap.get(input);
        if (operator == null) throw new InvalidParameterException("Operator not found for name: " + input);
        return operator;
    }
}