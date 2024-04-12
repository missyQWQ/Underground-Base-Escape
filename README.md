# Underground Base Escape
This is a text-based adventure game. You wake up in a laboratory and lose all your memory. You walk through the rooms, check and pick up items to achieve specific tasks, such as opening a locked door, killing a monster, playing a video etc.  Gradually, you will find the truth of your memory loss and get touched by the sorrowful love story. However, if you wander for too long, and don’t win the game within the limited time, the underground base will explode and you will lose the game.

## Introduction
This is the code part of a university project from King's College London 2019/20 4CCS1PPA Programming and Applications, and please note this project is run on BuleJ. Please contact me if you're interested in the whole spec and the written report.
- Supervisors: Michael Kölling, David J. Barnes
- Author: [Yichun Zhang](https://github.com/missyQWQ)

To start:
To play this game, create an instance of this class and call "play" method.

To win:
To win this game, the player needs to escape from the underground base within 15 hours (number of location changes).

## Structure
![Structure](/code-structure.png)

## Walkthrough
```
help -> commandInstruction -> lookaround -> check TestTube -> go north -> lookaround ->
check VideoPlayerDevice -> check EnergyStone -> go north -> lookaround -> check
ExperimentDocuments -> lookaround -> pickup Key -> back -> go south -> go west -> open
storageRoom Key -> go west -> lookaround -> go east -> go north -> go west -> lookaround
-> check TinaDiary1 -> check Videotape -> pickup Videotape -> go east -> lookaround ->
check VideoPlayerDevice -> lookaround -> pickup LaserGun -> currentWeight -> myItems ->
throw Key -> throw Videotape -> pickup LaserGun -> go south -> go west -> kill Zombie ->
lookaround -> check TinaDiary2 -> check Necklace -> go east -> go north ->
checkRemainTime -> go east -> open baseExit 1216 -> go east
```

## Game Settings
### Rooms
1. Magic Transporter Room
```
A magic transporter room, designed by scientists of East Union in 2045.
Use 2 energy stones to activate the room and it can transport you together with your items less than 200g to one of the other rooms except the locked room in the underground base.
Are you sure to transport? (type ‘yes’ or ‘no’)
```
2. Laboratory
```
You wake up.
Your mind goes totally blank.
Who are you? Where is it? why are you here?
Suddenly, you notice a screen in front of you
Underground Base Explosion Countdown: 12h
(pay attention: 1. Each move from current room to another room will reduce 1 hour 2. Use
command “checkRemainTime” to check the remaining hours 3. Use command “help” to get
some help)
(help -> use command ‘lookAround’ to check the room)
You notice some items on the ground: xxxxxxxx
You see a xxx standing in the room
```
3. Storage Room
```
It’s a Storage Room, but the door locked. It seems can be opened by a key.
You enter the Storage Room.
(lookAround)
You notice some items on the ground: xxxxxxxx
You see a xxx standing in the room
```
4. Restroom
```
You enter the Restroom.
(lookAround)
You notice some items on the ground: xxxxxxxx
You see a xxx standing in the room
```
5. Lobby
```
You enter the Lobby.
(lookAround)
You notice some items on the ground: xxxxxxxx
You see a xxx standing in the room
```
6. Office
```
You enter the Office.
(lookAround)
You notice some items on the ground: xxxxxxxx
You see a xxx standing in the room
```
7. Exit
```
It’s the base exit, but the electronic door is locked.
There is a T9 keyboard above the doorknob requiring for a 4-digit password.
Please enter the password, or type ‘####’ to leave.
```

### Characters
1. A monster
```
Unmovable. In the storage room. P…Peter…P…Peter…
```
2. An AI Robot
```
Movable. Start from office, randomly choose which door to go next.

Hi, I’m Robot X. You can exchange energy stones with me for equipment. 2 energy stones for
a Body Armor, and 3 energy stones for a Laser Gun. You want a : (type ‘none’ to leave)
```

### Items (appear once)
1. Key
```
A strange looking key with blood drops. (Can be picked up, weight: 30g)
```
2. Energy Stone
```
A special stone contains powerful energy. (Can be picked up, weight: 100g)
```
3. Laser Gun
```
A gun engraved with a sentence: “Kill the world. ---- Dr. J” (Can be picked up, weight: 150g)
```
4. Tina’s Diary (1)
```
4/Feb/2069
It’s the first day Peter come here. I’m so excited as he was not the strongest guy among all
the soldiers but did be selected by Dr. Josh. How honoured! Doctor even agreed to let me do
the injection! I even reset the password of the exit to our wedding anniversary. I’m sure
Peter will be surprised when we leave here together after he finishing the experiment.

9/Feb/2069
It has been 5 days since Peter got injection. He is obviously stronger than ever, but always
feel sleepy. Dr. Josh said it’s normal, as his body was auto-repairing. Hope this period will
not last too long, as I can’t wait to go home with Peter to see our lovely baby. I miss her a
lot.

15/Feb/2069
Things get different. His conscious hours become fewer and fewer. I’m so worried but Dr.
Josh insisted it’s ok.
```
5. Tina’s Diary (2)
```
27/Feb/2069
That’s a nightmare. Peter suddenly woke up from a coma and killed all the people out of
control. He must remember me as he didn’t hurt me at all. Dr. Josh escaped, locked the exit
and started the explosion program. Fortunately, I found how to save Peter in Josh’s office.
He will be fine, although I may have no change to see him and our baby again. I’m not sure
what monster I will become, but I’m afraid I may hurt him. This is why I locked myself in this
room. Anyway, Peter, I love you.
```
6. Necklace
```
A small wedding photo with “16/Dec/2064” written on it is embedded in the necklace. You
find the woman is the zombie you just killed and the man you feel so familiar but can’t
remember who is he.
```
7. Experiment Documents
```
2.1.3: Possible reasons for being crazy:
Genes disorder may occur.
4.2.2: Solutions to genes disorder:
1) Inject the drug to a normal person
2) Find the difference of genes between the newly injected one and the old one
3) Use the equipment……. inject the previous one with the missing part……
4) In case of…… the liquid in the test tube may help you…… use the key under the documents
to get it in storage room……
```
8. Test Tube
```
An empty test tube seems has been used. And there is a specification on the tube “Inject this
within 10 minutes after being infected can make you stay awake temporarily”
```
9. Videotape
```
A videotape seems can be played by a device. (Can be picked up, weight: 50g)
```
10. Video Player Device
```
A video player that can play videotape. Do you want to play it? (type “yes” or “no”)
If(yes)
If(videotape.carriedWith())
I’m Dr. J. This is my BrightFuture Plan. My young sister died in the Third World War because
of the nuclear radiation. I had been trying to find a way to resurrect her, and finally I got it.
Peter is the one who’s critical genes segments are similar to my sister. So, I chose him for the
experiment. If successful, human would no longer be afraid of nuclear radiation and I would
be the king of the new world! now, my friend, pick up the gun under the device, kill that
monster, come and follow me. Let’s build a new world together!
You get a item: Laser Gun.
```

### Commands
- check itemName(inTheRoom)
```
Followed by a specific item name. Return detailed information about the item.
```
- lookAround
```
Return items, exits and characters of the current room.
```
- currentWeight
```
Return the total weight of items the player carries with them.
```
- checkRemainTime
```
Return the remaining time.
```
- pickup itemName(canBePickUp)
```
Followed by a specific item name. Pick up that item.
```
- throw itemName(carriedWith)
```
Followed by a specific item name. Throw away that item.
```
- kill charName
```
Followed by a character's name. Kill this character if it can be killed.
```
- open
```
Followed by a room name and an item name. Try to open the room by using that item.
```
- MyItems
```
Return items the player carries with them.
```
- commandInstruction
```
Return the detailed command instructions.
```
- back
```
Take the player to the previous room they were in.
```
