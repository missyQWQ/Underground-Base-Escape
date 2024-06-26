    // YichunZhang k19012458
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.HashMap;
    import java.util.Random;
    /** 
     *  This class is the main class of the "Underground Base Escape" application. 
     *  
     *  This main class creates and initialises all the others: it creates all
     *  rooms, items, characters, creates the parser and starts the game. It also 
     *  evaluates and executes the commands that the parser returns.
     * 
     *  @author  Michael Kölling, David J. Barnes and Yichun Zhang
     *  @version 2016.02.29
     */
    
    public class Game 
    {
        private Parser parser;
        private Room currentRoom; 
        // ArrayList of all the items, rooms and characters
        private ArrayList<Item> itemList;
        private ArrayList<Room> roomList;
        private ArrayList<Character> characterList;
        // items need for opening corresponding locked room
        private HashMap<String, Room> itemForLockedRoom;
        // maximum total weight of items player can carry with
        private final int WEIGHTLIMIT;
        // time limit player need to pass game within
        private int countdown;
        // password for opening the exit
        private String exitPwd;
        // last direction player has gone
        private String lastDirection;
    
        /**
         * Create the game and initialise its internal map.
         */
        public Game() 
        {
            parser = new Parser();
            itemList = new ArrayList<>();
            roomList = new ArrayList<>();
            characterList = new ArrayList<>();
            itemForLockedRoom = new HashMap<>(); 
            WEIGHTLIMIT = 160;
            countdown = 15;
            exitPwd = "1216";
            lastDirection = null;
            
            initialiseGame(); 
        }

    /**
     * Create all the rooms, items and characters.
     * Link rooms with their corresponding exits, items, characters together.
     */
    private void initialiseGame()
    {
        // ------------ Rooms Initialization ------------
        
        Room lab, storageRoom, restroom, lobby, 
             office, baseExit, magicTransRoom;

        // create the rooms
        lab = new Room("laboratory", null, false);
        storageRoom = new Room("storageRoom", 
                      "The door seems can be opened by a key.", true);
        restroom = new Room("restroom", null, false);
        lobby = new Room("lobby", null, false);
        office = new Room("office", null, false);
        baseExit = new Room("baseExit",
                   "There is a T9 keyboard above the doorknob requiring " +
                   "for a 4-digit password.", true);
        magicTransRoom = new Room("magicTransRoom", 
                         "It's designed by scientists of East Union " +
                         "in 2045.\nUse energy stone to activate the " +
                         "room,\nand it can transport you to a random " +
                         "room except locked rooms in this underground " +
                         "base.",true);

        // link rooms with their corresponding exits
        lab.setExit("east", magicTransRoom);
        lab.setExit("west", storageRoom);
        lab.setExit("north", lobby);
        storageRoom.setExit("east", lab);
        restroom.setExit("east", lobby);
        lobby.setExit("east", baseExit);
        lobby.setExit("south", lab);
        lobby.setExit("west", restroom);
        lobby.setExit("north", office);
        office.setExit("south", lobby);

        // initialise a list of all the items
        roomList.add(lab);
        roomList.add(storageRoom);
        roomList.add(restroom);
        roomList.add(lobby);
        roomList.add(office);
        roomList.add(baseExit);
        roomList.add(magicTransRoom);

        // start game from laboratory
        currentRoom = lab;  

        
        // ------------ Items Initialization ------------
        
        Item key, energyStone, laserGun, TinaDiary1, TinaDiary2, 
             necklace, expDoc, testTube, videotape, videoPlayer;

        // create the items
        key = new Item(
              "Key", 
              "A strange looking key with blood drops.\n" 
            + "(Can be picked up, weight: 30g) ", 
              30, true, false);
                     
        energyStone = new Item("EnergyStone", 
              "A special stone contains powerful energy.\n"
            + "(Can be picked up, weight: 100g) ", 
              100, true, true);
                             
        laserGun = new Item(
              "LaserGun", 
              "A gun engraved with a sentence: 'Kill the world. ---- Dr. J'\n"
            + "(Can be picked up, weight: 150g) ", 
              150, true, false);
                          
        TinaDiary1 = new Item(
              "TinaDiary1", 
              "4/Feb/2069 \n"
            + "It’s the first day Peter come here.  I'm so excited as he was "
            + "not the strongest guy among all\nthe soldiers but did be "
            + "selected by Dr. Josh. How honoured! Doctor even agreed to let "
            + "me do\nthe injection! I even reset the password of the exit "
            + "to our wedding anniversary. I’m sure\nPeter will be surprised "
            + "when we leave here together after he finishing the experiment.\n"
            + " 9/Feb/2069 \n"
            + "It has been 5 days since Peter got injection. He is obviously "
            + "stronger than ever, but always\nfeel sleepy. Dr. Josh said "
            + "it’s normal, as his body was auto-repairing. Hope this period "
            + "will\nnot last too long, as I can’t wait to go home with Peter "
            + "to see our lovely baby. I miss her a lot.\n"
            + "15/Feb/2069 \n"
            + "Things get different. His conscious hours become fewer and "
            + "fewer. I’m so worried but Dr.\nJosh insisted it’s ok. ", 
              0, false, true);
        
        TinaDiary2 = new Item(
              "TinaDiary2", 
              "27/Feb/2069 \n"
            + "That’s a nightmare. Peter suddenly woke up from a coma and "
            + "killed all the people out of\ncontrol. He must remember me as "
            + "he didn’t hurt me at all. Dr. Josh escaped, locked the exit\n"
            + "and started the explosion program. Fortunately, I found how "
            + "to save Peter in Josh’s office.\nHe will be fine, although "
            + "I may have no change to see him and our baby again. I’m not "
            + "sure\nwhat monster I will become, but I’m afraid I may hurt "
            + "him. This is why I locked myself in this\nroom... Anyway, "
            + "Peter, I love you. ", 
              0, false, false);
              
        necklace = new Item(
              "Necklace", 
              "A small wedding photo with “16/Dec/2064” written on it is "
            + "embedded in the necklace. You\nfind the woman is the zombie "
            + "you just killed and the man you feel so familiar but can’t\n"
            + "remember who is he. ", 
              0, false, false);
              
        expDoc = new Item(
              "ExperimentDocuments", 
              "2.1.3: Possible reasons for being crazy:  \n"
            + "Genes disorder may occur. \n"
            + "4.2.2: Solutions to genes disorder: \n"
            + "1) Inject the drug to a normal person \n"
            + "2) Find the difference of genes between the newly injected "
            + "one and the old one\n"
            + "3) Use the equipment... inject the previous one with the "
            + "missing part...\n"
            + "4) In case of... the liquid in the test tube may help you... "
            + "use the key under the documents\n"
            + "   to get it in storage room...\n"
            + "(ps. item 'Key' is Visible now)", 
              0, false, true);
              
        testTube = new Item(
              "TestTube", 
              "An empty test tube seems has been used. \n"
            + "There is a specification on the tube 'Inject this within 10 "
            + "minutes after being infected can\nmake you stay awake "
            + "temporarily'", 
              0, false, true);
              
        videotape = new Item(
              "Videotape", 
              "A videotape seems can be played by a device.\n"
            + "(Can be picked up, weight: 50g) ", 
              50, true, true);
              
        videoPlayer = new Item(
            "VideoPlayerDevice", 
            "A video player that can play videotape. ", 
            0, false, true);

        // link items with their corresponding rooms
        lab.setItem(testTube);
        storageRoom.setItem(necklace);
        storageRoom.setItem(TinaDiary2);
        restroom.setItem(TinaDiary1);
        restroom.setItem(videotape);
        lobby.setItem(videoPlayer);
        lobby.setItem(laserGun);
        lobby.setItem(energyStone);
        office.setItem(key);
        office.setItem(expDoc);

        // initialise a list of all the items
        itemList.add(key);
        itemList.add(energyStone);
        itemList.add(laserGun);
        itemList.add(TinaDiary1);
        itemList.add(TinaDiary2);
        itemList.add(necklace);
        itemList.add(expDoc);
        itemList.add(testTube);
        itemList.add(videotape);
        itemList.add(videoPlayer);

        // initialise items need for opening corresponding locked room
        itemForLockedRoom.put("Key", storageRoom);
        itemForLockedRoom.put("EnergyStone", magicTransRoom);
        itemForLockedRoom.put("1216", baseExit);

        
        // ------------ Characters Initialization ------------
        
        Character zombie, robot;
        
        // create the characters
        zombie = new Character("Zombie", "P...Peter...", 
                                false, true, storageRoom);
        robot = new Character("AIRobot", "Hi, I’m Robot X. " +
                              "Welcome to 'Underground Base Escape'", 
                              true, false, office);

        // link characters with their corresponding exits
        characterList.add(zombie);
        characterList.add(robot);
    }

    /**
     *  Main play routine. Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("You wake up.");
        System.out.println("Your mind goes totally blank.");
        System.out.println("Who are you? Where is it? Why are you here?");
        System.out.println("Suddenly, you notice a screen in front of you :");
        System.out.println("---- Underground Base Explosion Countdown: " +
                           "12h ---- ");
        System.out.println("(Notice: each move from current room to " +
                           "another room will reduce 1 hour)");                   
        System.out.println();
        System.out.println("Type 'help' if you need help.");
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("commandInstruction")) {
            printCommandInstr();
        }
        else if (commandWord.equals("go")) {
            wantToQuit = goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("lookaround")) {
            lookAround(currentRoom);
        }
        else if (commandWord.equals("checkRemainTime")) {
            getRemainTime();
        }
        else if (commandWord.equals("currentWeight")) {
            showWeight();
        }
        else if (commandWord.equals("myItems")) {
            showCarriedItems();
        }
        else if (commandWord.equals("back")) {
            backLastRoom();
        }
        else if (commandWord.equals("pickup")) {
            pickUp(command);
        }
        else if (commandWord.equals("throw")) {
            throwItem(command);
        }
        else if (commandWord.equals("check")) {
            showItemDescription(command);
        }
        else if (commandWord.equals("kill")) {
            killCharacter(command);
        }
        else if (commandWord.equals("open")) {
            openLockedRoom(command);
        }

        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:
    
    // --------------- one-word commands ---------------

    /**
     * "help" was entered.
     * print out some help information.
     */
    private void printHelp() 
    {
        System.out.println("Your command words are:");
        parser.showCommands();
        System.out.println();
        System.out.println("Type 'commandInstruction' to find more help "
                         + "with these Instructions.");
        System.out.println();
    }

    /**
     * "commandInstruction" was entered.
     * Print out detailed command instructions.
     */
    private void printCommandInstr()
    {
        parser.showCmdInstr();
    }

    /** 
     * "quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true; 
        }
    }
    
    /** 
     * "lookaround" was entered. 
     * Print out items, exits and characters information.
     */
    private void lookAround(Room roomName)
    {
        System.out.println("You look around the room and find some "
                         + "items and exits.");
        System.out.println(currentRoom.getInfo());
        System.out.println(getCharactersInfo());
    }
    
    /** 
     * "checkRemainTime" was entered. 
     * Print out the remaining time.
     */
    private void getRemainTime()
    {
        System.out.println("You have " + countdown + " hours left.");
        System.out.println("Notice: each move from current room to" +
            "another room will reduce 1 hour ");
    }
    
    /** 
     * "currentWeight" was entered. 
     * Print out total weight of items player carry with.
     */
    private void showWeight()
    {
        System.out.println("Your current weight is " + getTotalWeight());
        System.out.println("Your weight limit is " + WEIGHTLIMIT);
    }
    
    /** 
     * "myItems" was entered. 
     * Print out all the items player carry with.
     */
    private void showCarriedItems()
    {
        String currentItems = "You have these items carried with you: ";
        for(Iterator<Item> it = itemList.iterator(); it.hasNext(); ) {
            Item item = it.next();
            if(item.getIsCarriedWith()) {
                currentItems += item.getName() + " | ";
            }
        }
        System.out.println(currentItems);
    }
    
    /** 
     * "back" was entered. 
     * Take player back to the last room he has been in
     */
    private void backLastRoom()
    {
        if(lastDirection == null) {
            System.out.println("No room to back. You haven't moved yet.");
            return;
        }
        // create an array of the four directions
        String[] directions = {"east", "south", "west", "north"};
        int length = directions.length;
        int index = -1;
        for(int i = 0; i < length; i++) {
            if(directions[i].equals(lastDirection)) {
                if(i < length/2) {
                    index = i + 2;
                    break;
                }
                else {
                    index = i - 2;
                    break;
                }
            }
        }        
        goRoom(new Command("go", directions[index], null));
    }

    // --------------- two-word commands ---------------
    
    /** 
     * "go" was entered.
     * go + direction
     * Try to in to one direction
     * @param command The command to be processed.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean goRoom(Command command) 
    {
        if(!isTwoWord(command)) {
            return false;
        }

        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);
        if(nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if(isLocked(nextRoom)) {
            System.out.println(currentRoom.getDescriptionLocked(nextRoom));
        }
        else {
            currentRoom = nextRoom;
            // each move reduce 1 hour
            countdown -= 1; 
            // each move make the auto-moved character move once
            characterMove("AIRobot"); 
            // record last direction for "back" command
            lastDirection = direction;
            if(isMagicRoom(currentRoom))
            {
                System.out.println("You entered the magic transporter room.");
                System.out.println("Start transporting......");
                goRandomRoom();
            }
            if(isPassGame()) {
                System.out.println("Congratulations! You have passed the game.");
                return true;
            }
            if(isFailGame()) {
                System.out.println("Unfortunately, time is up.\n"
                                 + "The underground base explode.\n"
                                 + "You lose the game.");
                return true;
            }
            System.out.println(currentRoom.getDescriptionUnlocked());
        }
        return false;
    }

    /** 
     * "pickup" was entered.
     * pickup + itemName
     * Try to pick up a item
     * @param command The command to be processed.
     */
    private void pickUp(Command command)
    {
        if(!isTwoWord(command)) {
            return;
        }
        String itemName = command.getSecondWord();
        if(currentRoom.pickUp(itemName, getTotalWeight(), WEIGHTLIMIT)) {
            System.out.println("You have successfully picked up " 
                             + itemName + ".");
        }
        else {
            System.out.println(itemName + " cannot be picked up." + 
                " (unmovalbe, unavailable or overweight)");
        }
    }

    /** 
     * "throw" was entered.
     * throw + itemName
     * Try to throw away a item
     * @param command The command to be processed.
     */
    private void throwItem(Command command)
    {
        if(!isTwoWord(command)) {
            return;
        }
        String itemName = command.getSecondWord();
        Item item = itemname2item(itemName);
        if(item != null && item.throwAwayItem()) {
            System.out.println("You have successfully thrown away " 
                             + itemName + ".");
        }
        else {
            System.out.println("Failed. You don't have " + itemName 
                             + " with you.");
        }
    }

    /** 
     * "check" was entered.
     * check + itemName
     * Get detailed information of this item
     * @param command The command to be processed.
     */
    private void showItemDescription(Command command)
    {
        if(!isTwoWord(command)) {
            return;
        }
        boolean isExist = false;
        String itemName = command.getSecondWord();
        Item item = itemname2item(itemName);
        if(item != null) {
            System.out.println(item.getDescription());
            if(itemName.equals("ExperimentDocuments")) {
                setItemVisible(itemname2item("Key"));
            }
            if(itemName.equals("VideoPlayerDevice") && getIsCarried("Videotape")) {
                playVideo();
                setItemVisible(itemname2item("LaserGun"));
            }
        }
        else {
            System.out.println("Failed. " + itemName + " not exist.");
        }
    }

    /** 
     * "kill" was entered.
     * kill + characterName
     * Kill the character that can be killed
     * @param command The command to be processed.
     */
    private void killCharacter(Command command)
    {
        if(!isTwoWord(command)) {
            return;
        }
        String characterName = command.getSecondWord();
        if(characterName.equals("Zombie")) {
            if(charctname2charct(characterName).getCanBeKilled())
            {
                charctname2charct(characterName).changeCanBeKilled();
                setItemVisible(itemname2item("TinaDiary2"));
                setItemVisible(itemname2item("Necklace"));
                System.out.println("You killed it using your laser gun.");
                System.out.println("You seemed to find something on its body.");
                charctname2charct(characterName).changeDescription("(silence...It seems died.)");
            }
            else
            {
                System.out.println("You have killed it. How can you kill it twice.");
            }

        }
        else {
            System.out.println(characterName + " cannot be killed, or does not exist.");
        }
    }

    // --------------- three-word commands ---------------
    
    /** 
     * "open" was entered.
     * open + roomName + itemName
     * Try to open the locked room by using this item
     * @param command The command to be processed.
     */        
    private void openLockedRoom(Command command)
    {
        if(!isThreeWord(command)) {
            return;
        }        
        String lockedRoomName = command.getSecondWord();
        String item = command.getThirdWord();
        if(getIsNeighbour(lockedRoomName)) {
            if(getIsCarried(item) || item.equals(exitPwd)) {
                Room lockedRoom = itemForLockedRoom.get(item);
                if(lockedRoom != null && lockedRoom.getRoomName().equals(lockedRoomName)){
                    lockedRoom.setUnlocked();
                    System.out.println(lockedRoomName + " has been opened by " +
                        item + " successfully.");
                }
                else {
                    System.out.println(item + " cannot open " + lockedRoomName);
                }
            }
            else {
                System.out.println("Open failed.");
            }
        }
        else {
            System.out.println("You are far from " + lockedRoomName + " now, or " +
                lockedRoomName + " not exist.");
        }
    }

    // --------------- other functions ---------------    
    
    /** 
     * Whether the command is two words
     * @param command The command to be processed.
     * @return true, if this command is two words, false otherwise.
     */
    private boolean isTwoWord(Command command)
    {
        boolean isTwo = true;
        if(!command.hasSecondWord()) {           
            System.out.println(command.getCommandWord() + " what?");
            isTwo = false;
        }
        if(command.hasThirdWord()) {
            System.out.println("Only one can be " 
                             + command.getCommandWord() + " at a time.");
            isTwo = false;
        }
        return isTwo;
    }
    
    /** 
     * Whether the command is three words
     * @param command The command to be processed.
     * @return true, if this command is three words, false otherwise.
     */
    private boolean isThreeWord(Command command)
    {
        boolean isThree = true;
        if(!command.hasSecondWord() || !command.hasThirdWord()) {
            System.out.println(command.getCommandWord() + 
                              " what by using what?");
            isThree = false;
        }
        return isThree;
    }
    
    /** 
     * Convert item name to item
     * @param itemName Name of the item, which is a String
     * @return item, which is a Item. If not exist, return null
     */
    private Item itemname2item(String itemName) 
    {
        for(Iterator<Item> it = itemList.iterator(); it.hasNext(); ) {
            Item item = it.next();
            if(item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    /** 
     * Convert room name to room
     * @param roomName Name of the room, which is a String
     * @return room, which is a Room. If not exist, return null
     */
    private Room roomname2room(String roomName)
    {
        for(Iterator<Room> it = roomList.iterator(); it.hasNext(); ) {
            Room room = it.next();
            if(room.getRoomName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    /** 
     * Convert character name to character
     * @param charctname Name of the character, which is a String
     * @return character, which is a Character. If not exist, return null
     */
    private Character charctname2charct(String charctname)
    {
        for(Iterator<Character> it = characterList.iterator(); it.hasNext(); ) {
            Character character = it.next();
            if(character.getName().equals(charctname)) {
                return character;
            }
        }
        return null;
    }

    /** 
     * Whether the item is carried by player
     * @param itemName Item name
     * @return true, if this item is carried by player, false otherwise.
     */    
    private boolean getIsCarried(String itemName) 
    {
        boolean isCarried = false;
        Item item = itemname2item(itemName);
        if(item != null && item.getIsCarriedWith()) {
            isCarried = !isCarried;
        }
        return isCarried;
    }

    /** 
     * Whether the room is neighbour of current room.
     * @param roomName Name of the room
     * @return true, if the room is neighbour of current room, false otherwise.
     */
    private boolean getIsNeighbour(String roomName)
    {
        ArrayList<String> neighbours = currentRoom.getNeighbourRooms();
        
        boolean isNeighbour = false;
        for(Iterator<String> it = neighbours.iterator(); it.hasNext(); ) {
            String neighbour = it.next();
            if(neighbour.equals(roomName)) {
                isNeighbour = !isNeighbour;
            }
        }
        return isNeighbour;
    }
   
    /** 
     * @return true, if the player pass the game, false otherwise.
     */
    private boolean isPassGame()
    {
        return isBaseExit(currentRoom);
    }

    /** 
     * @return true, if the player fail the game, false otherwise.
     */
    private boolean isFailGame()
    {
        boolean isFail = false;
        if(countdown <= 0) {
            isFail = !isFail;
        }
        return isFail;
    }

    /** 
     * Whether this room is a magic transporter room.
     * @param roomName Name of the room
     * @return true, if this room is a magic transporter room, false otherwise.
     */
    private boolean isMagicRoom(Room roomName)
    {
        return roomName.getRoomName().equals("magicTransRoom");
    }

    /** 
     * Whether this room is locked.
     * @param roomName Name of the room
     * @return true, if this room is locked, false otherwise.
     */
    private boolean isLocked(Room roomName)
    {
        return roomName.getIsLocked();
    }

    /** 
     * Whether this room is the base exit.
     * @param roomName Name of the room
     * @return true, if this room is the base exit, false otherwise.
     */
    private boolean isBaseExit(Room roomName)
    {
        return roomName.getRoomName().equals("baseExit");
    }
    
    /** 
     * @return totalWeight Total weight of items that are carried with
     */        
    private int getTotalWeight() 
    {
        int totalWeight = 0;
        for(Iterator<Item> it = itemList.iterator(); it.hasNext(); ) {
            Item item = it.next();
            if(item.getIsCarriedWith()) {
                totalWeight += item.getWeight();
            }
        }
        return totalWeight;
    }
    
    /** 
     * Make this item to be visible
     * @param itemName Item name
     */ 
    private void setItemVisible(Item itemName)
    {
        if(!itemName.getIsCarriedWith()) {
            itemName.setVisible();
        }
    }

    /** 
     * Print out content of the video
     */
    private void playVideo()
    {
        System.out.println(
              "You're inserting your videotape into the device."
            + "Loding......"
            + "Hi, I’m Dr. J.\n"
            + "This is my BrightFuture Plan.\n"
            + "My young sister died in the Third World War because\n"
            + "of the nuclear radiation. I had been trying to find a way "
            + "to resurrect her, and finally I got it.\nPeter is the one "
            + "who’s critical genes segments are similar to my sister. So, "
            + "I chose him for the experiment.\nIf successful, human would "
            + "no longer be afraid of nuclear radiation and I would be the "
            + "king\nof the new world! \nNow, my friend, pick up the gun "
            + "under the device, kill that monster, come and follow me.\n "
            + "Let’s build a new world together!\n"
            + "(ps. item 'LaserGun' is Visible now)");
    }

    /** 
     * Transport to a random room except locked rooms and magic room itself 
     */
    private void goRandomRoom()
    {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(roomList.size()); 
        Room randomRoom = roomList.get(index);
        currentRoom = randomRoom;
        while(isLocked(currentRoom) || isMagicRoom(currentRoom)) {
            randomGenerator = new Random();
            index = randomGenerator.nextInt(roomList.size()); 
            randomRoom = roomList.get(index);
            currentRoom = randomRoom;
        }
    }

    /** 
     * The character goes to a random room which is linked to his current room
     * Character moves around by itself
     * @param characterName Name of the character
     */
    private void characterMove(String characterName)
    {
        Room position = generateRandomNeighborRoom(characterName);
        while(position == null || getIsUnaccessible(position)) {
            position = generateRandomNeighborRoom(characterName);
        }
        charctname2charct(characterName).move(position);
    }

    /** 
     * Generate a random room which is linked to the character's current room
     * @param characterName Name of the character
     * @return position The room that is linked to character's current room
     */ 
    private Room generateRandomNeighborRoom(String characterName)
    {
        String[] directions = {"east", "south", "west", "north"};
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(directions.length); 
        String direction = directions[index];
        Character character = charctname2charct(characterName);
        Room position = character.getCurrentPosition().getExit(direction);
        return position;
    }

    /** 
     * When the room exist, whether it is accessible:
     * whether the room is locked, is base exit, or is magic room
     * @param position Name of the room
     * @return true, if unaccessible, false otherwise
     */
    private boolean getIsUnaccessible(Room position)
    {
        boolean isUnaccessible = false;
        if(position != null) {
            isUnaccessible = isLocked(position) || isBaseExit(position) 
            || isMagicRoom(position);
        }
        return isUnaccessible;
    }

    /**
     * @return A description of the character in current room.
     */
    private String getCharactersInfo()
    {
        for(Iterator<Character> it = characterList.iterator(); it.hasNext(); ) 
        {
            Character character = it.next();
            String characterPosition = character.getCurrentPosition().getRoomName();
            String myPosition = currentRoom.getRoomName();
            if(characterPosition.equals(myPosition)) {
                return character.getInfo();
            }
        }
        return "No characters in this room. \n";
    }
}