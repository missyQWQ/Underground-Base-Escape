// YichunZhang k19012458
/**
 * This class is part of the "Underground Base Escape" application. 
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling, David J. Barnes and Yichun Zhang
 * @version 2016.02.29
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "help", "commandInstruction", "lookaround", "currentWeight", 
        "myItems", "checkRemainTime", "back", "quit", "go", "check",
        "pickup", "throw", "kill", "open"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }    

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out.
     * Print format: everyline prints maximum 5 commands
     */
    public void showAll() 
    {
        int idx = 0;
        for(String command: validCommands) {
            System.out.print(command + " | ");
            idx++;
            if(idx % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
    
    /**
     * Print out the detailed commands instruction
     */
    public void showCommandInstruction()
    {
        System.out.println("*********************************");
        System.out.println("[one-word commands]"); 
        System.out.println("lookaround: return items, exits and " 
                         + "characters of current room. ");
        System.out.println("currentWeight: return total weight of "
                         + "items the player carries with. ");
        System.out.println("myItems: return items the player "
                         + "carries with. ");
        System.out.println("checkRemainTime: return the remaining time. ");
        System.out.println("back: back to last room you've been in");
        System.out.println("quit: quit the game");
        System.out.println("*********************************");
        System.out.println("[two-word commands]");
        System.out.println("go: followed by direction. Go to the "
                         + "room in this direction");
        System.out.println("check: followed by item name. Return "
                         + "detailed information of this item. ");
        System.out.println("pickup: followed by item name. Pick "
                         + "up the item. ");
        System.out.println("throw: followed by item name. Throw "
                         + "away the item. ");        
        System.out.println("kill: followed by character name. Kill "
                         + "this character if it can be killed. ");
        System.out.println("*********************************");
        System.out.println("[three-word commands]");
        System.out.println("open: followed by room name and item name. "
                         + "Try to open the room by using this item. ");
        System.out.println("*********************************");
    }
}