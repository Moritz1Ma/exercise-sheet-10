package de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse;

import de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse.items.StationeryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * Represents a warehouse that can hold a fixed number of items.
 * <p>
 * The number of holdable items is defined by the capacity of the storage rack.
 * <p>
 *
 * @author Moritz Mairle, Quentin Hadar, Nora Jasharaj
 */
public final class StorageRack {
    // @ private instance invariant capacity > 0;
    // @ private instance invariant numberOfItems >= 0;
    // @ private instance invariant numberOfItems <= capacity;

    private final int capacity;
    private int numberOfItems;
    public ArrayList<Optional<StationeryItem>> storageRack;
    public HashMap<Identifier, Integer> identifierToNumber; // TODO identifierToNumber --> identifiers besser?


    // TODO: Add data structures for exercises 1a and 1c here.
	/*@
	@ requires capacity > 0;
	@ ensures numberOfItems == 0;
	@ ensures StorageRack is a new ArrayList with optionally filled items;
	@ ensures Identifier is a new HashMap;
	TODO add missing pre- and postconditions here or in the JavaDoc.
	@*/

    /**
     * Creates a new storage rack with the given capacity.
     *
     * @param capacity capacity of the storage rack;
     * @throws IllegalArgumentException If capacity is less than 1;
     */
    public StorageRack(final int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("A warehouse must have a minimum capacity of 1.");
        }
        this.capacity = capacity;
        this.numberOfItems = 0;
        this.storageRack = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            this.storageRack.add(Optional.empty());
        }
        this.identifierToNumber = new HashMap<>(capacity);
    }

    // TODO add documentation here.
	/*@
	@ requires capacity > 0;
	@ requires StorageRack != null;
	@ requires identifier !=0;
	@ ensures stationaryItem is added to StorageRack for the lowest index that is empty;
	@ ensures identifier of the stationeryItem is being given its index number from the StorageRack;
	@*/

    /**
     * This method adds a new StationeryItem to the StorageRack.
     *
     * @param stationeryItem the item desired to be added;
     */
    public void addItem(final StationeryItem stationeryItem) {
        for (int i = 0; i < capacity; i++) {
            if (storageRack.get(i).isEmpty()) {
                storageRack.set(i, Optional.of(stationeryItem));
                identifierToNumber.put(stationeryItem.getIdentifier(), i); //TODO mit Quentin drüber schauen --> i + 1
                this.numberOfItems++;
                break;
            }
        }
        // TODO implement exercises 1b and 1d here.
    }
    // TODO add documentation here.
	/*@
	@ requires compartmentNumber ! <0;
	@ requires StorageRack != null;
	@ requires identifier != null;
	@ ensures the item is removed from its position;
	@ ensures the identifier´s number of its index is removed;
	 */

    /**
     * This method removes an item for desired compartmentNumber.
     *
     * @param compartmentNumber number of the compartment;
     */
    public void removeItem(final int compartmentNumber) {
       if(!storageRack.get(compartmentNumber - 1).equals(Optional.empty())){
           Identifier id = getItem(compartmentNumber - 1).get().getIdentifier();
           identifierToNumber.remove(id);
           storageRack.set(compartmentNumber - 1, Optional.empty());
           this.numberOfItems--;
       }
        // TODO implement exercises 1b and 1d here.
    }

    // TODO add documentation here.
	/*@
	@ requires compartmentNumber !<0;
	@ requires StorageRack != null;
	@ ensures the compartmentNumber of the element is returned;
	 */

    /**
     * This method returns the Item in a compartment.
     *
     * @param compartmentNumber The number of the compartment of which the item is looked for
     * @return Optional of the StationeryItem in the compartment
     * @ throws IllegalArgumentException if the compartmentNumber does not exist in the StorageRack.
     */
    public /*@ pure @*/ Optional<StationeryItem> getItem(final int compartmentNumber) {
        if (compartmentNumber > 0) {
            if (storageRack.get(compartmentNumber - 1).isPresent()) {
                return storageRack.get(compartmentNumber - 1);
            } else {
                return Optional.empty();
            }
        } else {
            throw new IllegalArgumentException("This compartment number is invalid");
        }
        // TODO implement exercise 1b here.
    }

    // TODO add documentation here.
		/*@
		  @ requires identifier != null;
		  @ ensures compartmentNumber of wanted identifier is being returned.
		 */

    /**
     * This method returns the compartmentNumber of a wanted StationeryItem from the StorageRack by its identifier.
     *
     * @param identifier, the identifier of the StationeryItem.
     * @return compartment number of the identifier, if existent. Otherwise, returns null.
     * @throws IllegalArgumentException if identifier doesnt exist.
     */
    public /*@ pure @*/ Optional<Integer> getCompartmentNumberOf(final Identifier identifier) {
        if (identifierToNumber.containsKey(identifier)) {
            int compartmentNumber = (int) identifierToNumber.get(identifier);
            return Optional.of(compartmentNumber);
        } else {
            throw new IllegalArgumentException("This identifier is nonexistent.");
        }
        // TODO implement exercise 1d here
    }

	/*@
	@ ensures \result == capacity;
	@*/

    /**
     * @return The capacity of this warehouse in items.
     */
    public /*@ pure @*/ int getCapacity() {
        return capacity;
    }

	/*@
	@ ensures \result == numberOfItems;
	@*/

    /**
     * @return The number of items in this warehouse.
     */
    public /*@ pure @*/ int getNumberOfItems() {
        return this.numberOfItems;
    }
}
