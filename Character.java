// YichunZhang k19012458
/**
 * Class Character - a character in an adventure game.
 * This class is part of the "Underground Base Escape" application.
 * 
 * A "Character" represents one people or animals or monsters – anything 
 * that moves in the scenery of the game. Characters are also in rooms 
 * (like the the player and the items). Unlike items, characters can move 
 * around by themselves.
 *
 * @author Yichun Zhang
 * @version 2019/11/29
 */
public class Character
{
    private String name;
    private String description;
    private boolean isMovable;
    private boolean canBeKilled;
    private Room currentPosition;

    /**
     * Create a character.
     * @param name The character's name.
     * @param description Information about the character
     * @param isMovable Whether it can move or not
     * @param canBeKilled Whether it can die or not
     * @param currentPosition The room that the character is currently in
     */
    public Character(String name, String description, boolean isMovable, 
                     boolean canBeKilled, Room currentPosition)
    {
        this.name = name;
        this.description = description;
        this.isMovable = isMovable;
        this.canBeKilled = canBeKilled;
        this.currentPosition = currentPosition;
    }
    
    /**
     * @return Name of the character
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return Current position/room of the character
     */
    public Room getCurrentPosition()
    {
        return currentPosition;
    }
    
    /**
     * @return A long description the character
     */
    public String getInfo()
    {
        return "Character: a " + name + " is saying:\"" + description + ".\"\n";
    }
    
    /**
     * @return true, if the character can be killed, false otherwise
     */
    public boolean getCanBeKilled()
    {
        return canBeKilled;
    }
    
    /**
     * Move to next room
     * @param nextPosition Next room
     */
    public void move(Room nextPosition)
    {
        currentPosition = nextPosition;
    }
    
    /**
     * Change the description of the character
     * @param newDescription The new description that will replace the old one
     */
    public void changeDescription(String newDescription)
    {
        description = newDescription;
    }
    
    /**
     * Change whether can be killed of the character
     */
    public void changeCanBeKilled()
    {
        canBeKilled = !canBeKilled;
    }
}