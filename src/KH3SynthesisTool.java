import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class KH3SynthesisTool {
    public static String[] materialTypes = {"Blazing", "Frost", "Lightning", "Lucid", "Pulsing", "Writhing", "Betwixt", "Twilight", "Sinister", "Soothing", "Mythril", "Wellspring", "Hungry"};
    private static final String[] materialStones = {"Shard", "Stone", "Gem", "Crystal"};
    private static final String[] specialMaterials = {"Evanescent Crystal", "Illusory Crystal", "Fluorite", "Damascus", "Adamantite", "Electrum", "Orichalcum", "Orichalcum+"};
    private static final int[][] materialsNeeded = new int[materialTypes.length + specialMaterials.length][];
    private static int [][] editableMaterialsNeeded = new int[materialTypes.length + specialMaterials.length][];

    public static void main(String[] args) throws Exception {

        File materialList = null;
        File synthesisRecipes = null;
        File userSynth = null;
        BufferedReader reader = null;
        for (int i = 0; i < materialTypes.length; i++)
            materialsNeeded[i] = new int[4];

        for (int i = materialTypes.length; i < materialTypes.length + specialMaterials.length; i++)
            materialsNeeded[i] = new int[1];

        //Dual arrays are in order of Shard, Stone, Gem, and Crystal
        {   //Blazing
            materialsNeeded[0][0] = 24;
            materialsNeeded[0][1] = 26;
            materialsNeeded[0][2] = 9;
            materialsNeeded[0][3] = 6;
            //Frost
            materialsNeeded[1][0] = 16;
            materialsNeeded[1][1] = 23;
            materialsNeeded[1][2] = 10;
            materialsNeeded[1][3] = 8;
            //Lightning
            materialsNeeded[2][0] = 24;
            materialsNeeded[2][1] = 34;
            materialsNeeded[2][2] = 11;
            materialsNeeded[2][3] = 6;
            //Lucid
            materialsNeeded[3][0] = 28;
            materialsNeeded[3][1] = 22;
            materialsNeeded[3][2] = 14;
            materialsNeeded[3][3] = 7;
            //Pulsing
            materialsNeeded[4][0] = 19;
            materialsNeeded[4][1] = 18;
            materialsNeeded[4][2] = 18;
            materialsNeeded[4][3] = 9;
            //Writhing
            materialsNeeded[5][0] = 17;
            materialsNeeded[5][1] = 29;
            materialsNeeded[5][2] = 13;
            materialsNeeded[5][3] = 5;
            //Betwixt
            materialsNeeded[6][0] = 4;
            materialsNeeded[6][1] = 2;
            materialsNeeded[6][2] = 5;
            materialsNeeded[6][3] = 2;
            //Twilight
            materialsNeeded[7][0] = 4;
            materialsNeeded[7][1] = 2;
            materialsNeeded[7][2] = 9;
            materialsNeeded[7][3] = 3;
            //Sinister
            materialsNeeded[8][0] = 13;
            materialsNeeded[8][1] = 11;
            materialsNeeded[8][2] = 8;
            materialsNeeded[8][3] = 4;
            //Soothing
            materialsNeeded[9][0] = 19;
            materialsNeeded[9][1] = 16;
            materialsNeeded[9][2] = 7;
            materialsNeeded[9][3] = 5;
            //Mythril
            materialsNeeded[10][0] = 26;
            materialsNeeded[10][1] = 34;
            materialsNeeded[10][2] = 28;
            materialsNeeded[10][3] = 28;
            //Wellspring
            materialsNeeded[11][0] = 12;
            materialsNeeded[11][1] = 9;
            materialsNeeded[11][2] = 22;
            materialsNeeded[11][3] = 17;
            //Hungry
            materialsNeeded[12][0] = 12;
            materialsNeeded[12][1] = 9;
            materialsNeeded[12][2] = 12;
            materialsNeeded[12][3] = 9;
            //Evanescent
            materialsNeeded[13][0] = 2;
            //Illusory
            materialsNeeded[14][0] = 2;
            //Fluorite
            materialsNeeded[15][0] = 2;
            //Damascus
            materialsNeeded[16][0] = 8;
            //Adamantite
            materialsNeeded[17][0] = 1;
            //Electrum
            materialsNeeded[18][0] = 1;
            //Orichalcum
            materialsNeeded[19][0] = 15;
            //Orichalcum+
            materialsNeeded[20][0] = 7;
        }
        JOptionPane.showMessageDialog(null, "We would suggest putting this program in a folder of its own, so we can make the text files needed. If you haven't done so, close the program and do so now.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
        try {
            materialList = new File("KH3SynthesisToolFiles\\KH3Materials.txt");
            reader = new BufferedReader(new FileReader(materialList));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Oops, we couldn't find the material list for you. Give us one second while we make that for you.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);

            try {
                File myObj = new File("KH3SynthesisToolFiles\\KH3Materials.txt");
                if (myObj.createNewFile()) {
                    JOptionPane.showMessageDialog(null, "File created: " + myObj.getName(), "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "File already exists.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
                }
                materialList = new File("KH3SynthesisToolFiles\\KH3Materials.txt");
                reader = new BufferedReader(new FileReader(materialList));
            } catch (IOException f) {
                JOptionPane.showMessageDialog(null, "An error occurred.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
            }
            createMaterialList(materialList, reader);

        }
        try {
            synthesisRecipes = new File("KH3SynthesisToolFiles\\KH3Recipes.txt");
            reader = new BufferedReader(new FileReader(synthesisRecipes));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Oops, we couldn't find the synthesis recipes. Give us one second while we make that for you.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);

            try {
                File myObj = new File("KH3SynthesisToolFiles\\KH3Recipes.txt");
                if (myObj.createNewFile()) {
                    JOptionPane.showMessageDialog(null, "File created: " + myObj.getName(), "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "File already exists.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
                }
                synthesisRecipes = new File("KH3SynthesisToolFiles\\KH3Recipes.txt");
                reader = new BufferedReader(new FileReader(synthesisRecipes));
            } catch (IOException f) {
                JOptionPane.showMessageDialog(null, "An error occurred.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
            }
            createSynthesisList(synthesisRecipes, reader);

        }
        try {
            userSynth = new File("KH3SynthesisToolFiles\\KH3SynthesizedItems.txt");
            reader = new BufferedReader(new FileReader(userSynth));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Oops, we couldn't find your synthesized items. Give us one second while we set that up for you.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);

            try {
                File myObj = new File("KH3SynthesisToolFiles\\KH3SynthesizedItems.txt");
                if (myObj.createNewFile()) {
                    JOptionPane.showMessageDialog(null, "File created: " + myObj.getName(), "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "File already exists.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
                }
                userSynth = new File("KH3SynthesisToolFiles\\KH3SynthesizedItems.txt");
                reader = new BufferedReader(new FileReader(userSynth));
            } catch (IOException f) {
                JOptionPane.showMessageDialog(null, "An error occurred.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
            }
            createUserSynthList(userSynth, reader);
        }

        int newUser = JOptionPane.showConfirmDialog(null, "Welcome to the KH3 Synthesis Tool.\nAre your materials already set up? (If your file was just made, please hit no)", "KH3 Synthesis Tool", JOptionPane.YES_NO_CANCEL_OPTION);
        if (newUser == 1) {
            JOptionPane.showMessageDialog(null, "Awesome! Let's go down the list and find out how many of each material you have.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
            for (String materialType : materialTypes) {
                for (String materialStone : materialStones) {
                    String matAmount = null;
                    int numberAmount = 0;
                    boolean verify = true;
                    while (verify) {
                        try {
                            matAmount = JOptionPane.showInputDialog(null, materialType + ":\n\nHow many " + materialStone + "s do you have? (If you have none, just hit Enter or OK)", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
                            if (matAmount == null || matAmount.isEmpty()) {
                                matAmount = "0";
                            }
                            numberAmount = Integer.parseInt(matAmount);
                            verify = false;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Please input a valid number, and no decimals please.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                    modifyFile(materialList, findInFile(materialList, materialType + " " + materialStone), materialType + " " + materialStone + " x" + numberAmount);
                }
            }

            for (String specialMaterial : specialMaterials) {
                boolean verify = true;
                String matAmount = null;
                int numberAmount = 0;
                while (verify) {
                    try {
                        if (specialMaterial.equalsIgnoreCase("Damascus"))
                            matAmount = JOptionPane.showInputDialog(null, "How many " + specialMaterial + "es do you have? (If you have none, just hit Enter or OK)", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
                        else
                            matAmount = JOptionPane.showInputDialog(null, "How many " + specialMaterial + "s do you have? (If you have none, just hit Enter or OK)", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
                        if (matAmount == null || matAmount.isEmpty()) {
                            break;
                        }
                        numberAmount = Integer.parseInt(matAmount);
                        verify = false;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Please input a valid number, and no decimals please.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                if (matAmount != null) {
                    modifyFile(materialList, findInFile(materialList, specialMaterial), specialMaterial + " x" + numberAmount);
                }
            }

        }
        if (newUser == 2) {
            System.exit(0);
        }
        updateNeededMaterials(userSynth, synthesisRecipes);
        while (true) {

            String[] options = {"Material List (Obtained)", "Material List (Needed)", "Synthesis Recipes", "Update Materials", "Update Synthesized Items", "Material Locations", "Synthesize Items", "Exit"};
            Object option = JOptionPane.showInputDialog(null, "Please select an option", "KH3 Synthesis Tool", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (option == null) {
                System.exit(0);
            }
            switch ((String) option) {
                case "Material List (Obtained)":
                    printMaterialList(materialList);
                    break;
                case "Material List (Needed)":
                    printNeededMaterials(materialList, synthesisRecipes, userSynth);
                    break;
                case "Synthesis Recipes":
                    printSynthesisRecipes(synthesisRecipes);
                    break;
                case "Update Materials":
                    updateMaterials(materialList);
                    break;
                case "Update Synthesized Items":
                    updateUserItems(userSynth, synthesisRecipes);
                    break;
                case "Material Locations":
                    materialLocations(materialList);//AKA The bane of my existence.
                    break;
                case "Synthesize Items":
                    synthesizeItems(materialList, synthesisRecipes, userSynth);
                    break;
                case "Exit":
                    System.exit(0);
            }
        }
    }

    public static String findInFile(File file, String findString) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));

            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null) {
                if (line.contains(findString)) {
                    return line;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Not found";

    }

    public static void modifyFile(File fileToBeModified, String oldString, String newString) {

        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        if (oldString.equalsIgnoreCase("Not found"))
            return;
        if (oldString.equalsIgnoreCase(newString)) {
            return;
        }
        if (oldString.contains("+"))
            oldString = oldString.replaceAll("\\+", "\\\\+");
        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent

            String newContent = oldContent.replaceAll(oldString, newString);

            //Rewriting the input text file with newContent

            writer = new FileWriter(fileToBeModified);

            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources

                reader.close();

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createMaterialList(File file, BufferedReader reader) {
        try {
            FileWriter writer = new FileWriter(file, true);
            for (String materialType : materialTypes) {
                for (String stone : materialStones) {
                    writer.write(materialType + " " + stone + " x0\n");

                }
            }
            for (String special : specialMaterials) {
                writer.write(special + " x0\n");
            }
            writer.close();
        } catch (IOException ignored) {
        }
    }

    public static void createSynthesisList(File file, BufferedReader reader) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(
                    """
                            -Mega Potion
                            Lucid Shard x3
                            Soothing Shard x3
                            Pulsing Shard x3
                            Hungry Stone x1
                            -Ether
                            Wellspring Shard x2
                            Lightning Stone x2
                            Writhing Shard x2
                            -Hi Ether
                            Wellspring Stone x3
                            Lightning Stone x3
                            Writhing Stone x3
                            Hungry Shard x1
                            -Mega Ether
                            Wellspring Stone x3
                            Lightning Stone x3
                            Writhing Stone x3
                            Hungry Gem x1
                            -Refocuser
                            Blazing Shard x3
                            Lightning Shard x3
                            Lucid Shard x3
                            Hungry Shard x1
                            -Hi Refocuser
                            Blazing Stone x3
                            Lightning Stone x3
                            Lucid Stone x3
                            Hungry Stone x1
                            -Elixir
                            Wellspring Crystal x3
                            Frost Gem x2
                            Sinister Stone x2
                            Sinister Shard x2
                            Hungry Gem x2
                            -Megalixir
                            Wellspring Crystal x3
                            Frost Crystal x2
                            Sinister Crystal x2
                            Sinister Gem x2
                            Hungry Crystal x2
                            -Strength Boost
                            Wellspring Gem x5
                            Soothing Crystal x2
                            Pulsing Crystal x2
                            Writhing Crystal x2
                            Hungry Crystal x1
                            -Magic Boost
                            Wellspring Gem x5
                            Blazing Crystal x2
                            Frost Crystal x2
                            Lightning Crystal x2
                            Hungry Crystal x1
                            -Defense Boost
                            Wellspring Gem x5
                            Lucid Crystal x2
                            Twilight Crystal x2
                            Sinister Gem x2
                            Hungry Crystal x1
                            -AP Boost
                            Wellspring Stone x3
                            Betwixt Shard x3
                            Twilight Shard x3
                            Hungry Stone x1
                            Hungry Shard x2
                            -Ultima Weapon
                            Orichalcum+ x7
                            Wellspring Crystal x2
                            Lucid Crystal x2
                            Pulsing Crystal x2
                            -Warhammer+
                            Mythril Stone x2
                            Fluorite x1
                            Pulsing Gem x1
                            Hungry Gem x1
                            -Astrolabe+
                            Mythril Crystal x2
                            Electrum x1
                            Lightning Gem x2
                            Pulsing Crystal x2
                            Hungry Stone x1
                            -Heartless Maul
                            Illusory Crystal x1
                            Damascus x2
                            Writhing Crystal x1
                            Writhing Gem x2
                            -Heartless Maul+
                            Illusory Crystal x1
                            Damascus x2
                            Writhing Crystal x1
                            Writhing Gem x2
                            Hungry Gem x2
                            -Save the Queen
                            Orichalcum x1
                            Frost Crystal x1
                            Lucid Crystal x1
                            Soothing Crystal x1
                            -Save the Queen+
                            Orichalcum x1
                            Frost Crystal x1
                            Lucid Crystal x1
                            Soothing Crystal x1
                            Hungry Crystal x1
                            -Clockwork Shield+
                            Mythril Stone x2
                            Fluorite x1
                            Lucid Gem x1
                            Hungry Shard x1
                            -Aegis Shield+
                            Mythril Gem x2
                            Adamantite x1
                            Frost Gem x2
                            Pulsing Gem x2
                            Hungry Stone x1
                            -Nobody Guard
                            Evanescent Crystal x1
                            Damascus x2
                            Betwixt Gem x2
                            Twilight Gem x2
                            -Nobody Guard+
                            Evanescent Crystal x1
                            Damascus x2
                            Betwixt Gem x2
                            Twilight Gem x2
                            Hungry Gem x2
                            -Save the King
                            Orichalcum x1
                            Blazing Crystal x1
                            Lightning Crystal x1
                            Pulsing Crystal x1
                            -Save the King+
                            Orichalcum x1
                            Blazing Crystal x1
                            Lightning Crystal x1
                            Pulsing Crystal x1
                            Hungry Crystal x1
                            -Buster Band
                            Mythril Crystal x2
                            Wellspring Crystal x1
                            Wellspring Gem x2
                            Twilight Gem x2
                            -Buster Band+
                            Mythril Crystal x2
                            Wellspring Crystal x1
                            Wellspring Gem x5
                            Twilight Gem x2
                            Hungry Gem x1
                            -Fire Bangle
                            Mythril Shard x3
                            Blazing Stone x2
                            Blazing Shard x5
                            -Fira Bangle
                            Mythril Stone x3
                            Blazing Stone x2
                            Blazing Shard x5
                            Hungry Shard x1
                            -Firaga Bangle
                            Mythril Gem x3
                            Blazing Gem x2
                            Blazing Stone x5
                            -Firaza Bangle
                            Mythril Crystal x1
                            Blazing Gem x2
                            Blazing Stone x5
                            Hungry Stone x1
                            -Fire Chain
                            Orichalcum x1
                            Blazing Stone x3
                            Sinister Stone x1
                            Sinister Shard x2
                            -Blizzard Choker
                            Mythril Shard x3
                            Frost Stone x2
                            Frost Shard x5
                            -Blizzara Choker
                            Mythril Stone x3
                            Frost Stone x5
                            Hungry Shard x1
                            -Blizzaga Choker
                            Mythril Gem x3
                            Frost Gem x2
                            Frost Stone x5
                            -Blizzaza Choker
                            Mythril Crystal x3
                            Frost Gem x2
                            Frost Stone x5
                            Hungry Stone x1
                            -Blizzard Chain
                            Orichalcum x1
                            Frost Stone x3
                            Sinister Stone x1
                            Sinister Shard x2
                            -Thunder Trinket
                            Mythril Shard x3
                            Lightning Stone x2
                            Lightning Shard x5
                            -Thundara Trinket
                            Mythril Stone x3
                            Lightning Stone x2
                            Lightning Shard x5
                            Hungry Shard x1
                            -Thundaga Trinket
                            Mythril Gem x3
                            Lightning Gem x2
                            Lightning Stone x5
                            -Thundaza Trinket
                            Mythril Crystal x3
                            Lightning Gem x2
                            Lightning Stone x5
                            Hungry Stone x1
                            -Thunder Chain
                            Orichalcum x1
                            Lightning Stone x3
                            Sinister Stone x1
                            Sinister Shard x1
                            -Shadow Anklet
                            Mythril Shard x3
                            Writhing Stone x2
                            Writhing Shard x5
                            -Dark Anklet
                            Mythril Stone x3
                            Writhing Stone x2
                            Writhing Shard x5
                            Hungry Shard x1
                            -Midnight Anklet
                            Mythril Gem x3
                            Writhing Gem x2
                            Writhing Stone x5
                            -Chaos Anklet
                            Mythril Crystal x3
                            Writhing Gem x2
                            Writhing Stone x5
                            Hungry Stone x1
                            -Dark Chain
                            Orichalcum x1
                            Writhing Stone x3
                            Sinister Stone x1
                            Sinister Shard x2
                            -Elven Bandanna
                            Mythril Shard x3
                            Soothing Stone x2
                            Soothing Shard x5
                            -Divine Bandanna
                            Mythril Gem x3
                            Soothing Gem x2
                            Soothing Stone x5
                            -Aqua Chaplet
                            Orichalcum x1
                            Soothing Stone x3
                            Sinister Stone x1
                            Sinister Shard x2
                            -Wind Fan
                            Mythril Shard x3
                            Lucid Stone x2
                            Lucid Shard x5
                            -Storm Fan
                            Mythril Gem x3
                            Lucid Gem x2
                            Lucid Stone x5
                            -Aero Armlet
                            Orichalcum x1
                            Lucid Stone x3
                            Sinister Stone x1
                            Sinister Shard x2
                            -Acrisius
                            Mythril Crystal x3
                            Blazing Crystal x1
                            Frost Crystal x1
                            Lightning Crystal x1
                            -Acrisius+
                            Mythril Crystal x3
                            Blazing Crystal x1
                            Frost Crystal x1
                            Lightning Crystal x1
                            Hungry Gem x1
                            -Cosmic Chain
                            Orichalcum x1
                            Lucid Crystal x1
                            Soothing Crystal x1
                            Writhing Crystal x1
                            Hungry Crystal x1
                            -Petite Ribbon
                            Orichalcum x2
                            Sinister Crystal x1
                            Betwixt Crystal x1
                            Hungry Crystal x1
                            Hungry Shard x3
                            -Firefighter Rossette
                            Mythril Stone x2
                            Wellspring Shard x2
                            Blazing Shard x6
                            -Umbrella Rosette
                            Mythril Stone x2
                            Wellspring Shard x2
                            Soothing Shard x6
                            -Mask Rosette
                            Mythril Stone x2
                            Wellspring Shard x2
                            Lucid Shard x6
                            -Snowman Rosette
                            Mythril Stone x2
                            Wellspring Shard x2
                            Frost Shard x6
                            -Insulator Rosette
                            Mythril Stone x2
                            Wellspring Shard x2
                            Lightning Shard x6
                            -Ability Ring+
                            Mythril Shard x2
                            Lucid Shard x6
                            -Technician's Ring+
                            Mythril Stone x2
                            Lucid Stone x6
                            -Skill Ring +
                            Mythril Gem x2
                            Lucid Gem x6
                            -Cosmic Ring
                            Mythril Crystal x2
                            Lucid Gem x3
                            Pulsing Gem x3
                            Hungry Gem x2
                            -Phantom Ring
                            Mythril Crystal x2
                            Pulsing Gem x1
                            Pulsing Stone x3
                            -Orichalcum Ring
                            Orichalcum x1
                            Pulsing Crystal x1
                            Pulsing Gem x3
                            -Sorcerer's Ring
                            Mythril Crystal x2
                            Sinister Gem x1
                            Sinister Stone x3
                            -Wisdom Ring
                            Orichalcum x1
                            Sinister Crystal x1
                            Sinister Gem x3
                            -Soldier's Earring
                            Mythril Stone x2
                            Pulsing Stone x3
                            Pulsing Shard x6
                            -Fencer's Earring
                            Mythril Shard x3
                            Pulsing Gem x3
                            Pulsing Stone x6
                            -Mage's Earring
                            Mythril Stone x2
                            Blazing Stone x3
                            Lightning Stone x3
                            Soothing Stone x3
                            -Slayer's Earring
                            Mythril Gem x3
                            Blazing Gem x3
                            Lightning Gem x3
                            Soothing Gem x3
                            -Moon Amulet
                            Mythril Stone x2
                            Pulsing Stone x3
                            Writhing Stone x3
                            -Star Charm
                            Mythril Gem x3
                            Pulsing Gem x3
                            Writhing Gem x3
                            -Draw Ring
                            Mythril Shard x3
                            Pulsing Shard x5
                            Betwixt Stone x1
                            Twilight Stone x1
                            -Blazing Crystal
                            Wellspring Crystal x1
                            Blazing Gem x2
                            Blazing Stone x3
                            Blazing Shard x5
                            -Frost Crystal
                            Wellspring Crystal x1
                            Frost Gem x2
                            Frost Stone x3
                            Frost Shard x5
                            -Lightning Crystal
                            Wellspring Crystal x1
                            Lightning Gem x2
                            Lightning Stone x3
                            Lightning Shard x5
                            -Lucid Crystal
                            Wellspring Crystal x1
                            Lucid Gem x2
                            Lucid Stone x3
                            Lucid Shard x5
                            -Pulsing Crystal
                            Wellspring Crystal x1
                            Pulsing Gem x2
                            Pulsing Stone x3
                            Pulsing Shard x5
                            -Writhing Crystal
                            Wellspring Crystal x1
                            Writhing Gem x2
                            Writhing Stone x3
                            Writhing Shard x5
                            -Mythril Shard
                            Betwixt Shard x1
                            Twilight Shard x1
                            -Mythril Stone
                            Betwixt Stone x1
                            Twilight Stone x1
                            -Mythril Gem
                            Betwixt Gem x1
                            Twilight Gem x1
                            -Mythril Crystal
                            Betwixt Crystal x1
                            Twilight Crystal x1
                            -Soothing Crystal
                            Wellspring Crystal x1
                            Soothing Gem x2
                            Soothing Stone x3
                            Soothing Shard x5
                            """
            );
            writer.close();
        } catch (IOException ignored) {
        }
    }

    public static void createUserSynthList(File file, BufferedReader reader) {
        String synth;
        {
            synth = """
                    Mega Potion
                    Ether
                    Hi Ether
                    Mega Ether
                    Refocuser
                    Hi Refocuser
                    Elixir
                    Megalixir
                    Strength Boost
                    Magic Boost
                    Defense Boost
                    AP Boost
                    Ultima Weapon
                    Warhammer+
                    Astrolabe+
                    Heartless Maul
                    Heartless Maul+
                    Save the Queen
                    Save the Queen+
                    Clockwork Shield+
                    Aegis Shield+
                    Nobody Guard
                    Nobody Guard+
                    Save the King
                    Save the King+
                    Buster Band
                    Buster Band+
                    Fire Bangle
                    Fira Bangle
                    Firaga Bangle
                    Firaza Bangle
                    Fire Chain
                    Blizzard Choker
                    Blizzara Choker
                    Blizzaga Choker
                    Blizzaza Choker
                    Blizzard Chain
                    Thunder Trinket
                    Thundara Trinket
                    Thundaga Trinket
                    Thundaza Trinket
                    Thunder Chain
                    Shadow Anklet
                    Dark Anklet
                    Midnight Anklet
                    Chaos Anklet
                    Dark Chain
                    Elven Bandanna
                    Divine Bandanna
                    Aqua Chaplet
                    Wind Fan
                    Storm Fan
                    Aero Armlet
                    Acrisius
                    Acrisius+
                    Cosmic Chain
                    Petite Ribbon
                    Firefighter Rossette
                    Umbrella Rosette
                    Mask Rosette
                    Snowman Rosette
                    Insulator Rosette
                    Ability Ring+
                    Technician's Ring+
                    Skill Ring+
                    Cosmic Ring
                    Phantom Ring
                    Orichalcum Ring
                    Sorcerer's Ring
                    Wisdom Ring
                    Soldier's Earring
                    Fencer's Earring
                    Mage's Earring
                    Slayer's Earring
                    Moon Amulet
                    Star Charm
                    Draw Ring
                    Blazing Crystal
                    Frost Crystal
                    Lightning Crystal
                    Lucid Crystal
                    Pulsing Crystal
                    Writhing Crystal
                    Mythril Shard
                    Mythril Stone
                    Mythril Gem
                    Mythril Crystal
                    Soothing Crystal
                    """;
        }
        String[] synthesisItems = synth.split("\n");
        try {
            FileWriter writer = new FileWriter(file, true);
            for (String item : synthesisItems) {
                writer.write(item + ",false\n");
            }
            writer.close();
        } catch (IOException ignored) {
        }
    }

    public static void printMaterialList(File materialList) {
        int i = 0;
        while (i > -1 && i < materialTypes.length + 1) {
            StringBuilder message = new StringBuilder();
            if (i < materialTypes.length) {
                message.append(materialTypes[i]).append(":\n\n");
                message.append(findInFile(materialList, materialTypes[i] + " " + materialStones[0])).append('\n');
                message.append(findInFile(materialList, materialTypes[i] + " " + materialStones[1])).append('\n');
                message.append(findInFile(materialList, materialTypes[i] + " " + materialStones[2])).append('\n');
                message.append(findInFile(materialList, materialTypes[i] + " " + materialStones[3])).append('\n');
            } else {
                message.append("Special Materials").append(":\n\n");
                for (String specialMaterial : specialMaterials)
                    message.append(findInFile(materialList, specialMaterial)).append('\n');
            }
            int input = JOptionPane.showConfirmDialog(null, message + "\n(If you want to go to next, hit No. To go back, hit Yes)", "KH3 Synthesis Tool: User Material Amounts", JOptionPane.YES_NO_CANCEL_OPTION);
            if (input == 0) {
                i--;
            }
            if (input == 1) {
                i++;
            }
            if (input == 2) {
                break;
            }
        }
    }

    public static void printNeededMaterials(File materialList, File synthesisList, File userSynthesis) {
        String[][] neededMaterialList = new String[materialTypes.length + specialMaterials.length][];
        for (int i = 0; i < materialTypes.length; i++)
            neededMaterialList[i] = new String[4];
        for (int i = materialTypes.length; i < materialTypes.length + specialMaterials.length; i++)
            neededMaterialList[i] = new String[1];
        int i = 0;
        int j;
        for (String material : materialTypes) {
            j = 0;
            for (String stone : materialStones) {
                int userMaterialAmount = readMaterialAmount(materialList, material + " " + stone);
                int[] materialIndex = findMaterialInList(material, stone);
                int neededMaterialAmount = editableMaterialsNeeded[materialIndex[0]][materialIndex[1]];
                neededMaterialList[i][j] = (neededMaterialAmount - userMaterialAmount) + "";
                j++;

            }
            i++;
        }
        for (String material : specialMaterials) {
            int userMaterialAmount = readMaterialAmount(materialList, material);
            int[] materialIndex = findMaterialInList(material);
            int neededMaterialAmount = editableMaterialsNeeded[materialIndex[0]][0];
            neededMaterialList[i][0] = neededMaterialAmount + "";
            i++;
        }
        i = 0;
        while (i > -1 && i < materialTypes.length + 1) {
            StringBuilder message = new StringBuilder();
            if (i < materialTypes.length) {
                message.append(materialTypes[i]).append(":\n\n");
                message.append(materialTypes[i] + " " + materialStones[0] + ": ").append(Integer.parseInt(neededMaterialList[i][0]) == 0 ? "DONE" : Integer.parseInt(neededMaterialList[i][0]) < 0 ? "OVER" : neededMaterialList[i][0]).append('\n');
                message.append(materialTypes[i] + " " + materialStones[1] + ": ").append(Integer.parseInt(neededMaterialList[i][1]) == 0 ? "DONE" : Integer.parseInt(neededMaterialList[i][1]) < 0 ? "OVER" : neededMaterialList[i][1]).append('\n');
                message.append(materialTypes[i] + " " + materialStones[2] + ": ").append(Integer.parseInt(neededMaterialList[i][2]) == 0 ? "DONE" : Integer.parseInt(neededMaterialList[i][2]) < 0 ? "OVER" : neededMaterialList[i][2]).append('\n');
                message.append(materialTypes[i] + " " + materialStones[3] + ": ").append(Integer.parseInt(neededMaterialList[i][3]) == 0 ? "DONE" : Integer.parseInt(neededMaterialList[i][3]) < 0 ? "OVER" : neededMaterialList[i][3]).append('\n');
            } else {
                message.append("Special Materials").append(":\n\n");
                for (j = 0; j < specialMaterials.length; j++) {
                    String specialMaterial = specialMaterials[j];
                    message.append(specialMaterial + ": ").append(Integer.parseInt(neededMaterialList[j][0]) == 0 ? "DONE" : Integer.parseInt(neededMaterialList[j][0]) < 0 ? "OVER": neededMaterialList[j][0]).append('\n');
                }
            }
            int input = JOptionPane.showConfirmDialog(null, message + "\n(If you want to go to next, hit No. To go back, hit Yes)", "KH3 Synthesis Tool: Needed Materials", JOptionPane.YES_NO_CANCEL_OPTION);

            if (input == 0) {
                i--;
            }
            if (input == 1) {
                i++;
            }
            if (input == 2) {
                break;
            }
        }

    }

    public static void updateMaterials(File materialList) {
        String matName = "";
        String listEntry = "Not Found";
        while (listEntry.equalsIgnoreCase("Not Found")) {
            matName = JOptionPane.showInputDialog(null, "Please input the name of your material. (In the format \"Name Type\", unless it is a special material)", "KH3 Synthesis Tool: Updating Materials", JOptionPane.PLAIN_MESSAGE);
            listEntry = findInFile(materialList, matName);
            if (listEntry.equalsIgnoreCase("Not Found")) {
                JOptionPane.showMessageDialog(null, "Your material was not found.\nPlease make sure you capitalized the first letter of each word and put a space in between.", "KH3 Synthesis Tool: Updating Materials", JOptionPane.PLAIN_MESSAGE);
            }
        }
        int newAmount = 0;
        boolean verify = true;
        while (verify) {
            try {
                newAmount = Integer.parseInt(JOptionPane.showInputDialog(null, "Now input how many you have.", "KH3 Synthesis Tool: Updating Materials", JOptionPane.PLAIN_MESSAGE));
                verify = false;
            } catch (NumberFormatException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Please make sure you input a single whole number with no other spaces.");
            }

        }
        String newString = matName + " x" + newAmount;
        modifyFile(materialList, findInFile(materialList, matName), newString);
    }

    public static void updateUserItems(File userSynthList, File generalSynthList) throws InterruptedException, IOException {

        JFrame frame = new JFrame("KH3 Synthesis Tool: User's Synthesized Items");

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(0, 0, 100, 50);
        ActionListener actionListener = ActionEvent -> frame.setVisible(false);
        closeButton.addActionListener(actionListener);
        ArrayList<JCheckBox> checkboxes = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(userSynthList));
        } catch (FileNotFoundException ignored) {
        }
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 8));
        for (int i = 0; i < 88; i++) {
            try {
                assert reader != null;
                String line = reader.readLine();
                JCheckBox checkbox = new JCheckBox(line.split(",")[0], Boolean.parseBoolean(line.split(",")[1]));
                checkboxes.add(checkbox);
                panel.add(checkboxes.get(i));
            } catch (IOException ignored) {
            }

        }
        frame.add(panel);
        frame.add(closeButton, BorderLayout.SOUTH);
        frame.pack();
        frame.setSize(1300, 700);
        frame.setVisible(true);

        while (frame.isVisible()) {
            Thread.sleep(500);
        }
        frame.dispose();
        for (JCheckBox checkBox : checkboxes) {
            modifyFile(userSynthList, findInFile(userSynthList, checkBox.getText()), checkBox.getText() + "," + checkBox.isSelected());
        }
        updateNeededMaterials(userSynthList,generalSynthList);
    }

    public static int readMaterialAmount(File file, String materialName) {
        String[] material = findInFile(file, materialName).split(" ");
        boolean specialMaterial = material.length != 3 || Arrays.stream(specialMaterials).anyMatch(Predicate.isEqual(material));
        String[] amount = specialMaterial ? material[1].split("x") : material[2].split("x");
        return Integer.parseInt(amount[1]);
    }

    public static int[] findMaterialInList(String materialName, String materialType) {
        for (int i = 0; i < materialTypes.length; i++) {
            for (int j = 0; j < materialStones.length; j++) {
                if (materialTypes[i].equalsIgnoreCase(materialName))
                    if (materialStones[j].equalsIgnoreCase(materialType))
                        return new int[]{i, j};

            }

        }
        return new int[]{-1};
    }

    public static int[] findMaterialInList(String materialName) {
        for (int i = 0; i < specialMaterials.length; i++) {
            if (specialMaterials[i].equalsIgnoreCase(materialName)) {
                return new int[]{i + materialTypes.length, 0};
            }

        }
        return new int[]{-1};
    }
    public static String findRecipe(File recipes, String itemToFind){
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(recipes));
            String line = reader.readLine();
            while(line != null)
            {
                if(line.contains('-' + itemToFind) || line.equalsIgnoreCase('-' + itemToFind))
                {
                    String recipe = line + '\n';
                    line = reader.readLine();
                    while(line != null && line.charAt(0) != '-')
                    {
                        recipe += line + '\n';
                        line = reader.readLine();
                    }
                    return recipe;
                }


                line = reader.readLine();
            }
        } catch (IOException ignored){}
        return "Not Found";
    }

    public static void printSynthesisRecipes(File synthesisRecipes) {
        String itemToFind = JOptionPane.showInputDialog(null, "Please input which recipe you'd like to view.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE);
        if(itemToFind == null)
        {
            return;
        }
        String recipe = findRecipe(synthesisRecipes, itemToFind);
        if(recipe.equalsIgnoreCase("Not Found"))
        {
            JOptionPane.showMessageDialog(null, "Your recipe was not found. Please try again.", "KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE );
            return;
        }
        JOptionPane.showMessageDialog(null, "Here is your recipe:\n" + recipe,"KH3 Synthesis Tool", JOptionPane.PLAIN_MESSAGE );
    }

    public static void materialLocations(File materialList) {
        boolean continueSearching = true;
        while (continueSearching) {
            String lookingForMaterial = JOptionPane.showInputDialog(null, "Please input the material you are looking for.", "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
            if(lookingForMaterial == null)
                return;
            while (findInFile(materialList, lookingForMaterial).equalsIgnoreCase("Not Found")) {
                lookingForMaterial = JOptionPane.showInputDialog(null, "Please input a vaild Synthesis Material with proper capitalization and spacing.", "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
            }
            continueSearching = switch (lookingForMaterial) {
                case "Blazing Shard" -> {
                    JOptionPane.showMessageDialog(null, """
                            Your best bet is to get these in Battlegate 1 in Olympus. This Battlegate can be found smack-dab in the middle of the Courtyard. However, this
                            should be one of the materials you don't worry about, seeing how plentiful the enemies that drop them are.
                            
                            TIP: They drop most frequently (at 12%) from Flame Cores, which you can find in Olympus and Monstropolis.
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Blazing Stone", "Blazing Gem" -> {
                    JOptionPane.showMessageDialog(null, """
                            While you can find these in Battlegate 13 from Vermillion Sambas, your best chance of getting these is to go to either Monstropolis or Olympus and
                            take the Flowmotion rails in either the Corridors or the Door Vault.
                            
                            TIP: This is also a good way to get Soothing Stones and Gems from Marine Rumbas and Lightning Stones and Gems from Gold Beats.
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Blazing Crystal" -> {
                    JOptionPane.showMessageDialog(null, """
                            While High Soldiers are the only way to get the item to drop, there is absolutely another, more optimal way to get Blazing Crystals.
                            Synthesize it. If you don't believe me, Blazing Crystals are a 4% drop. High Soldiers also drop Wellspring Crystals. At 12%.
                            That are used to synthesize other crystals. Just saying.
                            
                            TIP: High Soldiers are found in Battlegate 12 in San Fransokyo.
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;

                }
                case "Frost Shard" -> {
                    JOptionPane.showMessageDialog(null, """
                            You can find every Frost material in Arendelle, shards specifically can be found by killing Winterhorns.
                            
                            TIP: These are the only Frost materials that can't be found from killing the Frost Serpents.
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Frost Stone", "Frost Gem" -> {
                    JOptionPane.showMessageDialog(null, """
                            You can find every Frost material in Arendelle, Stones and Gems specifically can be found from breaking Frost Serpent parts.
                            To find Frost Serpents, go to the Mountain Ridge save-point, and enter the cave by Elsa. From there, go down the cliff until you get
                            to the big clearing after a drop (passing a set of Helmed Bodies) and you should see 2 Frost Serpents pop up.
                            
                            
                            TIP: Despite what it may seem, you want to take your Lucky Strikes OFF when fighting Frost Serpents, for the chance to get Hungry Gems by hitting the tails.
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Frost Crystal" -> {
                    JOptionPane.showMessageDialog(null, """
                            You can find every Frost material in Arendelle, Crystal specifically are found by killing Frost Serpents. To find Frost Serpents.
                            go to the Mountain Ridge save-point, and enter the cave by Elsa. From there, go down the cliff until you get to the big clearing
                            after a drop (passing a set of Helmed Bodies) and you should see 2 Frost Serpents pop up. You can also synthesize these.
                            
                            TIP: Take off your Lucky Strikes when fighting the Serpents to have a chance at getting Hungry Gems.
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Lightning Shard" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Lightning Stone", "Lightning Gems" -> {
                        JOptionPane.showMessageDialog(null, """
                            While you can find these in Battlegate 13 from Gold Beats, your best shot is to ride the Flowmotion rails in Monstropolis'
                            Door Vault and shoot them down.
                            
                            TIP: This is also a good way to find Soothing Stones and Gems from Marine Rumbas and Blaze Stones and Gems from
                            Vermillion Sambas.
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Lightning Crystals" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Lucid Shard" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Lucid Stone" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Lucid Crystal" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Pulsing Shard" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Pulsing Stone" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Pulsing Crystal" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Writhing Shard" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Writhing Stone" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Writhing Crystal" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Betwixt Shard" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Betwixt Stone" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Betwixt Crystal" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }case "Twilight Shard" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Twilight Stone" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Twilight Crystal" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Sinister Shards", "Sinister Stones", "Sinister Gems", "Sinister Crystals" -> {
                    JOptionPane.showMessageDialog(null, """
                            Sinister materials are ONLY found from killing Unversed. If you're done with Monstropolis, you can go from one end of the Power Plant to the other
                            to farm these.
                                        
                            TIP: If you are still doing the story of Monstropolis, you can abuse the Scare meter sections to farm for however many of these materials you want.
                            """, "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Soothing Shard" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Soothing Stone", "Soothing Gem" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Soothing Crystal" -> {
                    JOptionPane.showMessageDialog(null, """
                            
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;

                }
                case "Mythril Shards", "Mythril Stones" -> {
                    JOptionPane.showMessageDialog(null, """
                            Mythril Shards and Stones can be synthesized, and unlike Gems or Crystals, you should synthesize these. The materials for these are extremely common,
                            and it's very likely that you have at least 20 or 30 of them lying around. If you're really out of luck, you can find them in any Gummi space by
                            shooting the blue rocks.
                                                       
                            TIP: Go look at Twilight and Betwixt Shards/Stones, they're much more common and much easier to get in endgame.
                            """, "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Mythril Gems" -> {

                    JOptionPane.showMessageDialog(null, """
                            So, while you CAN synthesize these... it is HIGHLY. HIGHLY. NOT. RECOMMENDED.
                            Just shoot rocks. In The Eclipse if you can, Misty Streams has them too. DO NOT. SYNTH. This goes for Mythril Crystals.
                               
                            TIP: You can get these and Mythril Crystals while farming for Orichalcums in the Eclipse if you REALLY need to.
                            """, "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Mythril Crystal" -> {
                    JOptionPane.showMessageDialog(null, """
                            So, while you CAN synthesize these... it is HIGHLY. HIGHLY. NOT. RECOMMENDED.
                            Just shoot rocks. In The Eclipse if you can, Misty Streams has them too. DO NOT. SYNTH. This goes for Mythril Gems too.
                               
                            TIP: You can get these and Mythril Gems while farming for Orichalcums in the Eclipse if you REALLY need to.
                            """, "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Wellspring Shard" -> {
                    JOptionPane.showMessageDialog(null, """
                            The fastest way to get these is to go to Isla Verdemontana in the Carribean and grab them from the dozens of Powerwilds you'll find
                            on the island.
                            
                            TIP: A bunch of enemies drop this throughout the game, so while you shouldn't need to farm, the option is there if you want it.
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Wellspring Stone" -> {
                    JOptionPane.showMessageDialog(null, """
                            Best way to find these is to go to Battlegate 7, in the Hills of the Kingdom of Corona. 
                            ""","KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Wellspring Gem", "Wellspring Crystal" -> {
                    JOptionPane.showMessageDialog(null, """
                            The best way to get Wellspring gems and crystals is to do the San Fransokyo battlegate. Warp to the North
                            District and climb up one of the buildings and start making your way to the building with blue stripes.
                            You'll pass by it on your way across the rooftops, and if you really can't find it, look it up.
                            Not my fault you can't find it, I'm a text program.
                                                       
                            TIP: If you haven't, make Ultima Weapon ASAP and use the Ultima Shotlock to speed things along.
                            """, "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Hungry Gems" -> {
                    JOptionPane.showMessageDialog(null, """
                            Unfortunately, Hungry Gems can only be found in Arendelle through Frost Serpents. If you warp to the Mountain Ridge and go into the cave
                            with the ice walls next to the palace, you'll find the Ice Serpents after a drop off (past the first set of Helmed Bodies). Take off your
                            Lucky Strikes, aim for the tail and use Fire magic for the best chance of getting a Hungry Gem. If you're at the endgame and still don't
                            have enough... good frickin' luck.
                                                    
                            TIP: The Helmed Bodies also drop Writhing Gems and Pulsing/Wellspring Crystals, so you can multi-task by casting Thunder to kill them on your way too and from.
                            """, "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Evanescent Crystal" -> {
                    JOptionPane.showMessageDialog(null, """
                            Evanescent Crystals are found in Battlegates 3 and 9. These gates are found in Twilight Town (3) and
                            in Arendelle (9). While Evanescent Crystals can also drop from Berserkers, you only need 2 and the first clears
                            of the Battlegates are guaranteed.
                                                       
                            TIP: Please. For the love of the Master and all that is light. Do not make the same shield twice.
                            """, "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Illusory Crystal" -> {
                    JOptionPane.showMessageDialog(null, """
                            Illusory Crystals are found in Battlegates 6 and 8. These gates are found in Kingdom of Corona (6) and
                            in Monstropolis (9). While Illusory Crystals can also drop from Demon Towers, you only need 2 and the first clears
                            of the Battlegates are guaranteed.
                                                       
                            TIP: Please. For the love of the Master and all that is light. Do not make the same staff twice.
                            """, "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Fluorite" -> {
                    JOptionPane.showMessageDialog(null, """
                            While these are normally obtained by shooting the blue rocks in Gummi spaces, you can find plenty in the Treasure Spheres in Starlight Way.
                                                       
                            TIP: Do not farm for these. You only need two. Please.
                            """, "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Damascus" -> {
                    JOptionPane.showMessageDialog(null, """
                            These can be obtained from chests. Or Treasure Spheres. Please. Don't farm them. It's not worth it. You don't need that many.
                                                       
                            TIP: FINE. They can be obtained from space rocks in Misty Streams. Happy?
                            """, "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);


                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Adamantite" -> {
                    JOptionPane.showMessageDialog(null, """
                            Dude. You literally only need 1. Why are you looking for these.
                                                       
                            TIP: You can find it in chests, Treasure Spheres in The Eclipse, or the blue rocks. Like the other ones.
                            """, "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Orichalcum" -> {
                    JOptionPane.showMessageDialog(null, """
                            If you really need to farm these, look up the chests that have Orichalcum and get every type of Synthesis materials.
                            If you're 100% sure you need to farm these, go to The Eclipse and start shooting rocks. That's it. Just shoot and pray.
                                                       
                            TIP: Level up your Gummi so that you can use mini-ships. It makes the farming go a little faster.
                            """, "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                case "Orichalcum+" -> {
                    JOptionPane.showMessageDialog(null, """
                            You are acoustic. Like actually stupid.
                                                       
                            jk here are all the locations
                            1. A random ass chest in the Carribean. (The chest will be in Exile Island, near the center of your map.)
                            2. As a Prize from the Moogle Postbox. If you don't want to deal with buying stuff from da Moogles, just save before you cash in your postcards.
                            3. 80 Lucky Emblems.
                            4. Reward for getting the good score for all 7 Flans.
                            5. Get all 10 treasures in Frozen Slider. Watch a video on this one. Trust.
                            6. Beat all of the encounters in The Eclipse. (Go around the thing in the center, there's 4 around the center and 1 at the bottom that unlock the final boss)
                            7. Keyblade Graveyard portal, it'll be in the Final World.
                                                       
                            TIP: If you're going for 100% or Platinum, you'll get most of these on the way to that. The only ones you should struggle with are 2, 5, and 6. Godspeed.
                            """, "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE);
                    yield JOptionPane.showConfirmDialog(null, "Would you like to keep searching?", "KH3 Synthesis Tool: Material Locations", JOptionPane.YES_NO_OPTION) == 0;
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "Please input a full material name, with proper capitalization. (If you are looking for Orichalcum+, write it like this.)", "KH3 Synthesis Tool: Material Locations", JOptionPane.PLAIN_MESSAGE );
                    yield true;
                }
            };
        }
    }
    public static void updateNeededMaterials(File userSynth, File synthRecipes) throws IOException, NullPointerException{
        for(int i = 0; i < editableMaterialsNeeded.length; i++)
        {
            editableMaterialsNeeded[i] = materialsNeeded[i].clone();
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(userSynth));
        } catch (FileNotFoundException ignored) {}
        String line = reader.readLine();
        while(line != null)
        {
            if(line.split(",")[1].equalsIgnoreCase("true")) {
                String recipe = findRecipe(synthRecipes, line.split(",")[0]);
                String[] materials = recipe.split("\n");
                String[][] usableMaterials = new String[materials.length][];
                for (int i = 1; i < materials.length; i++)
                {
                    usableMaterials[i] = materials[i].split(" ");
                }
                for(String[] array : usableMaterials)
                {
                    if(array == null)
                    {
                        continue;
                    }
                    int[] indexes;
                    int materialAmount;


                    if(array.length == 3)
                    {
                        materialAmount = Integer.parseInt(array[2].split("x")[1]);
                        if(Arrays.stream(materialTypes).anyMatch(Predicate.isEqual(array[0])))
                        {
                            indexes = findMaterialInList(array[0], array[1]);
                        }
                        else
                        {
                            indexes = findMaterialInList(array[0] + " " + array[1]);
                        }
                        editableMaterialsNeeded[indexes[0]][indexes[1]] -= materialAmount;


                    }
                    else if(array.length == 2)
                    {
                        materialAmount = Integer.parseInt(array[1].split("x")[1]);
                        indexes = findMaterialInList(array[0]);
                        editableMaterialsNeeded[indexes[0]][indexes[1]] -= materialAmount;
                    }

                }

            }

            line = reader.readLine();
        }
    }
    public static void synthesizeItems(File materialList, File synthList, File userSynth) throws InterruptedException{
        JFrame frame = new JFrame("KH3 Synthesis Tool: User's Synthesized Items");

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(0, 0, 100, 50);
        ActionListener actionListener = ActionEvent -> {
            frame.setVisible(false);
        };
        closeButton.addActionListener(actionListener);
        ArrayList<JButton> buttons = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(userSynth));
        } catch (FileNotFoundException ignored) {
        }
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 8));
        for (int i = 0; i < 88; i++) {
            try {
                assert reader != null;
                String line = reader.readLine();
                JButton button = new JButton(line.split(",")[0]);
                button.setEnabled(!Boolean.parseBoolean(line.split(",")[1]));
                ActionListener actionListener1 = ActionEvent -> {
                    String[] recipeList = findRecipe(synthList, button.getText()).split("\n");
                    String recipe = "\n";
                    boolean canSynthesize = true;
                    String missing = "\n";
                    for(int j = 1; j < recipeList.length; j++) {
                        recipe += recipeList[j];
                        String[] array = recipeList[j].split(" ");

                        if(array.length == 3)
                        {
                            int materialNeeded = Integer.parseInt(array[2].split("x")[1]);
                            int materialAmount = readMaterialAmount(materialList, array[0] + " " + array[1]);
                            if(materialNeeded <= materialAmount)
                                recipe += "  " + findInFile(materialList, array[0] + " " + array[1]);
                            else {
                                missing += array[0] + " " + array[1] + " x" + (materialNeeded - materialAmount) + "\n";
                                canSynthesize = false;
                            }
                        }
                        else
                        {
                            int materialNeeded = Integer.parseInt(array[1].split("x")[1]);
                            int materialAmount = readMaterialAmount(materialList, array[0]);
                            if(materialNeeded <= materialAmount)
                                recipe += "           " + findInFile(materialList, array[0]);
                            else {
                                missing += array[0] + " x" + (materialNeeded - materialAmount) + "\n";
                                canSynthesize = false;
                            }
                        }
                        recipe += "\n";
                    }
                    int confirm = -1;
                    if (canSynthesize)
                        confirm = JOptionPane.showConfirmDialog(null, "Are you sure you would like to synthesize this item?\nThis will use:          You have:" + recipe , "KH3 Synthesis Tool: Synthesize Items", JOptionPane.YES_NO_OPTION);
                    else
                        JOptionPane.showMessageDialog(null, "You cannot synthesize this item. \nYou are missing:" + missing, "KH3 Synthesis Tool: Synthesize Items", JOptionPane.PLAIN_MESSAGE);
                    if (confirm == 0) {
                        modifyFile(userSynth, findInFile(userSynth, button.getText()), button.getText() + ",true");
                        button.setEnabled(false);
                        for(int j = 1; j < recipeList.length; j++)
                        {
                            String[] array = recipeList[j].split(" ");
                            if(array.length == 3)
                            {
                                int materialNeeded = Integer.parseInt(array[2].split("x")[1]);
                                int materialAmount = readMaterialAmount(materialList, array[0] + " " + array[1]);
                                modifyFile(materialList, findInFile(materialList, array[0] + " " + array[1]), array[0] + " " + array[1] + " x" + (materialAmount - materialNeeded));
                            }
                            else
                            {
                                int materialNeeded = Integer.parseInt(array[1].split("x")[1]);
                                int materialAmount = readMaterialAmount(materialList, array[0]);
                                modifyFile(materialList, findInFile(materialList, array[0]), array[0] + " x" + (materialAmount - materialNeeded));
                            }
                        }
                    }
                    try {
                        updateNeededMaterials(userSynth, synthList);
                    } catch (IOException ignored) {
                        button.setEnabled(true);
                    }
                    if(button.isEnabled() && canSynthesize && confirm == 0)
                        JOptionPane.showMessageDialog(null, "Something went wrong, please try again.", "KH3 Synthesis Tool: Synthesize Items", JOptionPane.PLAIN_MESSAGE);
                    else if(canSynthesize)
                        JOptionPane.showMessageDialog(null, "Item successfully synthesized!", "KH3 Synthesis Tool: Synthesize Items", JOptionPane.PLAIN_MESSAGE);
                };
                button.addActionListener(actionListener1);
                buttons.add(button);
                panel.add(buttons.get(i));
            } catch (IOException ignored) {}

        }
        frame.add(panel);
        frame.add(closeButton, BorderLayout.SOUTH);
        frame.pack();
        frame.setSize(1300, 700);
        frame.setVisible(true);

        while (frame.isVisible()) {
            Thread.sleep(500);
        }
        frame.dispose();
    }

}



