// YichunZhang k19012458
/**
 * Class Item - a item in an adventure game.
 * This class is part of the "Underground Base Escape" application.
 *
 * A "Item" represents one object in the scenery of the game. Items are in
 * rooms. 
 * 
 * @author Yichun Zhang
 * @version 2019/11/29
 */
public class Item
{
    private String name;
    private String description;
    private int weight;
    private boolean canBePicked;
    private boolean isCarriedWith;
    private boolean isVisible;

    /**
     * Create a item.
     * @param name The item's name.
     * @param description Information about the item
     * @param weight The item's weight
     * @param canBePicked Whether it can be picked up or not
     * @param isVisible Whether it can be visible or not
     */
    public Item(String name, String description, int weight, 
                boolean canBePicked, boolean isVisible)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.canBePicked = canBePicked;
        isCarriedWith = false;
        this.isVisible = isVisible;
    }
    
    /**
     * @return true, if the item is visible, false otherwise
     */
    public boolean getIsVisible()
    {
        return isVisible;
    }
    
    /**
     * @return true, if the item can be carried with, false otherwise
     */
    public boolean getIsCarriedWith()
    {
        return isCarriedWith;
    }
    
    /**
     * @return Name of the item
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return Weight of the item
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * @return description of the item
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Set the itme is visible
     */
    public void setVisible()
    {
        isVisible = true;
    }
    
    /**
     * @return true, if the item can be picked up and is visible, 
     * false otherwise
     */
    public boolean pickUpItem()
    {
        boolean isPickUp = false;
        if (canBePicked && isVisible) {
            isCarriedWith = true;
            isVisible = false;
            isPickUp = !isPickUp;
        }
        return isPickUp;
    }
    
    /**
     * @return true, if the item is carried with by the player and is not 
     * visible, false otherwise
     */
    public boolean throwAwayItem()
    {
        boolean isThrowAway = false;
        if (isCarriedWith && !isVisible) {
            isCarriedWith = false;
            isVisible = true; 
            isThrowAway = !isThrowAway;
        }
        return isThrowAway;
    }
}