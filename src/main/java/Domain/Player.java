package Domain;

import Interface.PlayerInterface;

import java.util.ArrayList;

public class Player implements PlayerInterface {

    private ArrayList<Item> inventory = new ArrayList<Item>();

    Player() {
    }

    public ArrayList<Item> getInventory() {
        return new ArrayList<>(inventory);
    }


    //Drop items
    public Item checkInventory(String itemName) {

        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }


    @Override
    public void pickUp(Item item) {
        inventory.add(item);
    }

    @Override
    public void dropItem(Item item) {
        inventory.remove(item);
    }

    @Override
    public void printInventory () {
        String output = "";
        for (Item item : inventory) {
            output += item.getName() + " ";
        }
        System.out.println("Your inventory contains: ");
        System.out.println(output);
    }
}

