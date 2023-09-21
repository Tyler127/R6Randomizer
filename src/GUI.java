import javax.swing.*;
import java.awt.*;

public class GUI {
    static JFrame frame;
    static JTextField inputField;
    static JButton button;
    static JLabel resultLabel;

    /**
     * Creates the program GUI.
     */
    public static void createGUI() {
        // Set up the frame
        frame = new JFrame("R6Randomizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(450,475));
        frame.setLayout(new FlowLayout());

        // Create GUI elements
        inputField = new JTextField(20);
        frame.add(inputField);
        button = new JButton("Get Random Loadout");
        frame.add(button);
        resultLabel = new JLabel();
        frame.add(resultLabel);

        // When the button is pressed finds a random loadout for the input
        button.addActionListener(e -> {
            setTextToRandomLoadout(resultLabel);
            inputField.setText("");
        });

        // When 'Enter' is pressed finds a random loadout for the input
        inputField.addActionListener(e -> {
            setTextToRandomLoadout(resultLabel);
            inputField.setText("");
        });

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Changes the text of a JLabel to a random loadout for the Operator name in {@link #inputField}.
     *
     * @param label the JLabel that will have its text changed to the random loadout.
     */
    private static void setTextToRandomLoadout(JLabel label) {
        String userInput = inputField.getText();
        label.setText(InputReader.readInputFromGUI(userInput));
    }
}
