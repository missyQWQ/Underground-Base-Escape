// YichunZhang k19012458
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Class Room - a room in an adventure game.
 * This class is part of the "Underground Base Escape" application. 
 *
 * A "Room" represents one location in the scenery of the game. It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling, David J. Barnes and Yichun Zhang
 * @version 2016.02.29
 */

public class Room 
{
    private String name;
    private String description;
    private boolean isLocked;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;              // stores items of this room.
         
    /**
     * Create a room with its "name" and whether it is locked
     * @param name The room's name.
     * @param description The room's description
     * @param isLocked Whether the room is locked
     */
    public Room(String name, String description, boolean isLocked) 
    {
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Define an item from this room.
     * @param itemName The name of the item.
     */
    public void setItem(Item itemName)
    {
        items.add(itemName);
    }

    /**
     * @return A long description of the unlocked room
     */
    public String getDescriptionUnlocked()
    {
        return "You are in a " + name;
    }
    
    /**
     * @return A long description of the locked room
     */
    public String getDescriptionLocked(Room lockedRoomName)
    {
        return "Door locked.\nYou are in front of a " + lockedRoomName.getRoomName() +
               "\n" + lockedRoomName.getdescription();
    }
    
    /**
     * @return Name of the room
     */
    public String getRoomName()
    {
        return name;
    }
    
    /**
     * @return Description of the room
     */
    public String getdescription()
    {
        return description;
    }
    
    /** 
     * @return true, if the room is locked, false otherwise
     */
    public boolean getIsLocked()
    {
        return isLocked;
    }
    
    /** 
     * Define the room is inlocked
     */
    public void setUnlocked()
    {
        isLocked = false;
    }
    
    /**
     * Combine two strings describing the room's exits and items, for example
     * "Exits: north | west | 
     *  Items: Key | EnergyStone |"
     * @return Details of the room's exits and items.
     */
    public String getInfo()
    {
        return getItemString() + "\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north | west | ".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += exit + " | ";
        }
        return returnString;
    }
    
    /**
     * Return a string describing the room's items, for example
     * "Items: Key | EnergyStone | ".
     * @return Details of the room's items.
     */
    private String getItemString()
    {
        String itemString = "Items: ";
        for(Iterator<Item> it = items.iterator(); it.hasNext(); ) {
            Item item = it.next();
            if(item.getIsVisible()) {
                itemString += item.getName() + " | ";
            }
        }
        return itemString;
    }
    
    /** 
     * Get names of all neighbour rooms of the current room
     * @return neighbours, an ArrayList of all the neighbours' names
     */
    public ArrayList<String> getNeighbourRooms()
    {
        ArrayList<String> neighbours = new ArrayList<>();
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            neighbours.add(getExit(exit).getRoomName());
        }
        return neighbours;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
     
    /**
     * Return whether the item can be picked up (is overweight)
     * @param itemName The name of the item
     * @param totalWeight Total weight of items player carry with
     * @param weightLimit Maximum total weight of items player can carry with
     * @return true, if the item is overweight, false otherwise
     */
    public boolean pickUp(String itemName, int totalWeight, int weightLimit)
    {
        for(Iterator<Item> it = items.iterator(); it.hasNext(); ) {
            Item item = it.next();
            if(item.getName().equals(itemName)) {
                boolean isOverweight = weightLimit < (totalWeight 
                                                     + item.getWeight());
                if(!isOverweight) {
                    return item.pickUpItem();
                }
            }
        }
        return false;
    }
}