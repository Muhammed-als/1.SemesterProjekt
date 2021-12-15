package Domain;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Room {
    private final String description;
    private final HashMap<String, Room> exits;
    ArrayList<Item> items = new ArrayList<Item>();

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }



    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return description + ".\n" + getExitString();
    }


    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }


    //Inserts item into room Array


    public void setItem(Item name){
        items.add(name);
    }




    //Removes item from RoomArray
    public void removeItem(Item itemName){
        items.remove(itemName);
    }

    public ArrayList<Item> getItems() {
        return new ArrayList<>(items);
    }

    public Item getItem(String itemName){
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void getItemsInRoom(){

        System.out.println("Items in the room: ");
        getRoomItems();
    }




    //Displays items located in room
    public void getRoomItems(){
        String outPut;
        for(Item element : items){
            if(element != null){
                outPut = element.toString();
                System.out.println(" - " + outPut);
            }
        }
        System.out.println();
    }
}

