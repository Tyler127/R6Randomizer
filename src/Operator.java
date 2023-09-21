import java.util.ArrayList;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class Operator {
    private final String name;

    /**
     * Contains a String[] for each primary gun available to the Operator.
     * Each String[] contains the gun name and the ranges for what attachments are available for that gun.
     * The attachment ranges appear in the order: Optics -> Barrel -> Grips -> Laser
     */
    private final ArrayList<String[]> primaryBlueprints;

    /**
     * Contains a String[] for each secondary gun available to the Operator.
     * Each String[] contains the gun name and the ranges for what attachments are available for that gun.
     * The attachment ranges appear in the order: Optics -> Barrel -> Grips -> Laser
     */
    private final ArrayList<String[]> secondaryBlueprints;

    /**
     * Contains a singular String[] that contains the available gadgets for the Operator.
     */
    private final ArrayList<String[]> gadgets;

    private ArrayList<String> primaryRandomized;
    private ArrayList<String> secondaryRandomized;
    private String gadgetRandomized;

    private final Random random = new Random();

    public Operator(String name, ArrayList<String[]> primaryBlueprints, ArrayList<String[]> secondaryBlueprints, ArrayList<String[]> gadgets) {
        this.name = name;
        this.primaryBlueprints = primaryBlueprints;
        this.secondaryBlueprints = secondaryBlueprints;
        this.gadgets = gadgets;
    }

    /**
     * Prints to the terminal a random loadout for the Operator.
     */
    public void printRandomLoadout() {
        makeRandomLoadout();
        System.out.println(primaryRandomized.get(0));
        System.out.println("    " + primaryRandomized.get(1));
        System.out.println("    " + primaryRandomized.get(2));
        System.out.println("    " + primaryRandomized.get(3));
        System.out.println("    " + primaryRandomized.get(4));

        System.out.println(secondaryRandomized.get(0));
        System.out.println("    " + secondaryRandomized.get(1));
        System.out.println("    " + secondaryRandomized.get(2));
        System.out.println("    " + secondaryRandomized.get(3));
        System.out.println("    " + secondaryRandomized.get(4));

        System.out.println(gadgetRandomized);
    }

    /**
     *
     * @return
     */
    public String getRandomLoadoutString() {
        this.makeRandomLoadout();
        StringBuilder finalString = new StringBuilder();
        finalString.append("<html>");

        for (int i=0; i<5; i++) {
            if (i == 0) {
                finalString.append(primaryRandomized.get(i));
                finalString.append("<ul>");
            } else {
                finalString.append("<li>");
                finalString.append(primaryRandomized.get(i));
                finalString.append("</li>");
            }
            finalString.append("<br/>");
        }
        finalString.append("</ul>");

        for (int i=0; i<5; i++) {
            if (i == 0) {
                finalString.append(secondaryRandomized.get(i));
                finalString.append("<ul>");
            } else {
                finalString.append("<li>");
                finalString.append(secondaryRandomized.get(i));
                finalString.append("</li>");
            }
            finalString.append("<br/>");
        }
        finalString.append("</ul>");

        finalString.append(gadgetRandomized);
        finalString.append("</html>");
        return finalString.toString();
    }

    /**
     * Creates a new loadout for the Operator by populating {@link #primaryRandomized},
     * {@link #secondaryRandomized}, and {@link #gadgetRandomized} with randomly selected guns, attachments, and gadget.
     */
    private void makeRandomLoadout() {
        // Lists containing the possible attachments for the guns
        String[] opticsList = {"Iron Sights", "Red Dot A", "Red Dot B", "Red Dot C",
                "Holo A", "Holo B", "Holo C", "Holo D", "Reflex A", "Reflex B",
                "Reflex C", "1.5x", "2.0x", "2.5x A", "2.5x B", "3.0x"};
        String[] barrelList = {"None", "Suppressor", "Muzzle Brake", "Flash Hider" , "Compensator" , "Extended Barrel"};
        String[] gripsList = {"None", "Vertical Grip", "Angled Grip"};
        String[] laserList = {"No Laser", "Laser"};

        // Initialize new ArrayLists for the random loadout
        primaryRandomized = new ArrayList<>(5);
        secondaryRandomized = new ArrayList<>(5);
        for (int i=0; i<5; i++) this.primaryRandomized.add(null);
        for (int i=0; i<5; i++) this.secondaryRandomized.add(null);

        // primaryBlueprint is a String[] containing the gun name and attachment indices. A random one is selected from all
        // the primary guns available to the operator the method is being run on. Same is true for each other equipment.
        String[] primaryBlueprint = primaryBlueprints.get(random.nextInt(primaryBlueprints.size()));
        String[] secondaryBlueprint = secondaryBlueprints.get(random.nextInt(secondaryBlueprints.size()));

        // Indexes into the attachment lists above using a random integer with a maximum value
        // provided by the chosen gun blueprint's max value for that attachment.
        primaryRandomized.set(0, primaryBlueprint[0]); // adds gun to the Operator's randomized loadout array
        primaryRandomized.set(1, opticsList[random.nextInt(parseInt(primaryBlueprint[1]))]); // optics
        primaryRandomized.set(2, barrelList[random.nextInt(parseInt(primaryBlueprint[2]))]); // barrel
        primaryRandomized.set(3, gripsList[random.nextInt(parseInt(primaryBlueprint[3]))]); // grips
        primaryRandomized.set(4, laserList[random.nextInt(parseInt(primaryBlueprint[4]))]); // laser

        secondaryRandomized.set(0, secondaryBlueprint[0]); // gun
        secondaryRandomized.set(1, opticsList[random.nextInt(parseInt(secondaryBlueprint[1]))]); // optics
        secondaryRandomized.set(2, barrelList[random.nextInt(parseInt(secondaryBlueprint[2]))]); // barrel
        secondaryRandomized.set(3, gripsList[random.nextInt(parseInt(secondaryBlueprint[3]))]); // grips
        secondaryRandomized.set(4, laserList[random.nextInt(parseInt(secondaryBlueprint[4]))]); // laser

        // Select a random gadget from the list of gadgets the operator has
        String[] gadgetsList = gadgets.get(0);
        gadgetRandomized = gadgetsList[random.nextInt(gadgetsList.length)];
    }

    public String getName() {
        return name;
    }
}