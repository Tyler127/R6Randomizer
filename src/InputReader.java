import java.security.InvalidParameterException;
import java.util.Scanner;

public class InputReader {

    /**
     * Reads an input Operator name and creates a random loadout for that Operator.
     * @param input The name of the Operator for which a random loadout is to be generated.
     * @return The random loadout string for the given Operator.
     *          If the input is invalid returns "Invalid Operator Name!".
     * @see Operator#getRandomLoadoutString()
     */
    public static String readInputFromGUI(String input) {
        try {
            Operator selectedOperator = OperatorParser.getOperator(input);
            return selectedOperator.getRandomLoadoutString();
        } catch (InvalidParameterException e) {
           return "Invalid Operator Name!";
        }
    }

    /**
     * Prompts the user for an Operator name in the terminal. Attempts to find the Operator object and if it cannot
     * will continue to prompt the user until a valid Operator name is entered. Then, a random loadout for the selected
     * Operator is printed to the terminal.
     */
    public static void readInputFromTerminal() {
        Scanner inputScanna = new Scanner(System.in);
        System.out.println("Type operator name:");
        String input = inputScanna.nextLine();

        boolean validInput = false;
        while (!validInput) {
            try {
                Operator selectedOperator = OperatorParser.getOperator(input);
                System.out.println("Randomized " + selectedOperator.getName() + " Loadout:");
                selectedOperator.printRandomLoadout();
                validInput = true;
            } catch (InvalidParameterException e) {
                System.out.println("Invalid Operator Name!");
                System.out.println("Type operator name:");
                input = inputScanna.nextLine();
            }
        }
        inputScanna.close();
    }
}