package Interface;


import Domain.Item;

public interface PlayerInterface {
    void pickUp(Item item);
    void dropItem(Item item);
    void printInventory();
}
