import javax.swing.*;

public class R6Randomizer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::createGUI);
        OperatorParser.parseOperators();
        InputReader.readInputFromTerminal();
    }
}