package Domain;

import Data.FileBackend;

import java.util.Random;

public class Game {
    boolean finished = false;
    boolean winCondition = false;
    public Room b1, b2, b3, b4, b5, b6, b7, b8, b9;
    Player player = new Player();
    private final Parser parser;
    private Room currentRoom;
    Random random = new Random();

    public Game() {
        createRooms();
        createItems();
        parser = new Parser();
    }

    public void createItems() {

        FileBackend fileBackend = new FileBackend("items.txt");

        Room[] rooms = {b1,b2,b3,b4,b5,b6,b8,b9};
        String loadItem;


        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j <= this.random.nextInt(20); j++) {

                loadItem = fileBackend.load();

                if(!fileBackend.ListIsEmpty()){

                    rooms[i].setItem(new Item(loadItem));
                }

                else{
                    System.out.println(fileBackend.load());
                    return;
                }
            }

            //System.out.println(i + " " + rooms[i].getItems());
        }
    }

    public void createRooms() {
        b1 = new Room("You are in the North-West corner of the beach");
        b2 = new Room("You are in the North side of the beach");
        b3 = new Room("You are in the North-East corner of the beach");
        b4 = new Room("You are in the West side of the beach");
        b5 = new Room("You are in the middle of the beach");
        b6 = new Room("You are in the East side of the beach");
        b7 = new Room("You are in the South-West corner of the beach \n \nyou spot a trashcan");
        b8 = new Room("You are in the South side of the beach");
        b9 = new Room("You are in the South-East corner of the beach");

        /*
        s10 = new Room("You are at the shore");
        s11 = new Room("You are at the shore");
        s12 = new Room("You are at the shore");

        // TODO Hvad er meningen med these rooms?
        w1 = new Room("Water. Water everywhere... Something is not quite right.");
        w2 = new Room("Water. Water everywhere... Something is not quite right.");
        w3 = new Room("Water. Water everywhere... Something is not quite right.");

         */

        b1.setExit("south", b4);
        b1.setExit("east", b2);


        b2.setExit("west",b1);
        b2.setExit("south",b5);
        b2.setExit("east",b3);

        b3.setExit("west", b2);
        b3.setExit("south", b6);

        b4.setExit("north", b1);
        b4.setExit("east", b5);
        b4.setExit("south", b7);

        b5.setExit("north", b2);
        b5.setExit("west", b4);
        b5.setExit("east", b6);
        b5.setExit("south", b8);

        b6.setExit("north", b3);
        b6.setExit("south", b9);
        b6.setExit("west", b5);

        b7.setExit("north", b4);
        b7.setExit("east", b8);
       // b7.setExit("south", s10);

        b8.setExit("north", b5);
        b8.setExit("west", b7);
        b8.setExit("east", b9);
       // b8.setExit("south", s11);

        b9.setExit("north", b6);
        b9.setExit("west", b8);
       // b9.setExit("south", s12);

        /*
        s10.setExit("north", b7);
        s10.setExit("east", s11);

        s11.setExit("north", b8);
        s11.setExit("west", s10);
        s11.setExit("east", s12);

        s12.setExit("north", b9);
        s12.setExit("west", s11);

         */

        currentRoom = b5;


    }

    public void play() {

        printWelcome();


        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if (winCondition){
                finished = true;
                System.out.println("You've completed the game!");
            }

        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the world of ocean pollution!");
        System.out.println("World of ocean pollution is a game about ending big corporations pollution of the seas.");
        System.out.println("Your main goal is to clean up the beach and stop the polluters before they can pollute.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = winConditionChecker();

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.INVENTORY) {
            player.printInventory();
        } else if (commandWord == CommandWord.GET) {
            pickUpItem(command);
        } else if (commandWord == CommandWord.DROP) {
            dropAnItem(command);
        }
        else if (commandWord == commandWord.TALK){
            talk();
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost, alone and confused");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Your mission is to save the waters, head back now!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            nextRoom.getItemsInRoom();
        }
    }


    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room newRoom){
        this.currentRoom = newRoom;

    }

    private void pickUpItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Pick up what exactly??");
            return;

        }

        String item = command.getSecondWord();
        Item newItem = currentRoom.getItem(item);

        if (newItem == null) {
            System.out.println("That item is not here.");

            // Player kan holde 3 items, hvis han prøver at holde mere får han besked om fuldt inventory
        } else {
            if (player.getInventory().size() <= 2) {
                player.pickUp(newItem);
                currentRoom.removeItem(newItem);
                System.out.println("You picked up " + newItem);
            } else {
                System.out.println("Inventory is full! Go drop some trash in the bin.");
            }
        }
    }

    private void dropAnItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Drop what exactly?");
            return;
        }

        String item = command.getSecondWord();

        Item newItem = player.checkInventory(item);


        if (newItem == null) {
            System.out.println("You do not have that item in your inventory.");
        } else {

            player.dropItem(newItem);
            currentRoom.setItem(newItem);
            System.out.println("You dropped " + newItem);
        }
        winConditionChecker();
    }

    public void talk(){
        if (currentRoom == b1){
            System.out.println("The kid says:");
            System.out.println("Please leave the thrash in the thrashcan, it would help out a lot");
        }
        else {
            System.out.println("Hello??");
        }
    }
    public boolean winConditionChecker(){
        if (b1.getItems().size() >= 6){

            return winCondition = true;
        }
        return winCondition = false;
    }


    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}
