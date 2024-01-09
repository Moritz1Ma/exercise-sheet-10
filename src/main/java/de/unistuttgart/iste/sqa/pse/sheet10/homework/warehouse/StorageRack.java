package de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse;

import de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse.items.StationeryItem;

import java.util.*;

import static java.util.List.of;

/**
 * Represents a warehouse that can hold a fixed number of items.
 * The number of holdable items is defined by the capacity of the storage rack.
 * <p>
 * Moritz Mairle, Quentin Hadar, Nora Jasharaj
 */
public final class StorageRack {
    // @ private instance invariant capacity > 0;
    // @ private instance invariant numberOfItems >= 0;
    // @ private instance invariant numberOfItems <= capacity;

    private final int capacity;
    private int numberOfItems;
    ArrayList<Optional<StationeryItem>> StorageRack;
    Map<String, Integer> IdentifierToNumber;


    // TODO: Add data structures for exercises 1a and 1c here.

	/*@
	@ requires capacity > 0;
	@ ensures this.capacity == capacity;
	@ ensures numberOfItems == 0;
	@ ensures
	@ throws IllegalArgumentAException if capacity is <=0;
	TODO add missing pre- and postconditions here or in the JavaDoc.
	@*/

    /**
     * Creates a new storage rack with the given capacity.
     *
     * @param capacity capacity of the storage rack.
     * @throws IllegalArgumentException If capacity is less than 1.
     */
    public StorageRack(final int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("A warehouse must have a minimum capacity of 1.");
        }
        this.capacity = capacity;
        numberOfItems = 0;
        this.StorageRack = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            this.StorageRack.add(Optional.empty());
        }
        IdentifierToNumber = new HashMap<>();

        // TODO initialize data structures for exercises 1a and 1c here.
    }

    // TODO add documentation here.
	/*@
	@ requires capacity >-1
	@ requires StorageRack != null
	@ ensures stationaryItem is added to StorageRack for the lowest index that is empty
	@*/

    /**
     * This method adds a new StationeryItem to the StorageRack
     *
     * @ param stationaryItem the item in question to be added
     */
    public void addItem(final StationeryItem stationeryItem) {
        for (int i = 0; i < capacity; i++) {
            if (StorageRack.get(i).isEmpty()) {
                StorageRack.set(i, Optional.of(stationeryItem));
                return;
            }
        }

        // TODO implement exercises 1b and 1d here.
    }

    // TODO add documentation here.
	/*@
	@ requires compartmentNumber ! <0
	@ requires StorageRac != null
	@ ensures the item is removed from its position
	 */

    /**
     * This method removes an item for said compartmentNumber.
     *
     * @param compartmentNumber
     */
    public void removeItem(final int compartmentNumber) {
        StorageRack.remove(compartmentNumber);
        // TODO implement exercises 1b and 1d here.
    }

    // TODO add documentation here.
	/*@
	@ requires compartmentNumber !<0
	@ requires StorageRack != null
	@ ensures the compartmentNumber of the element is returned
	 */

    /**
     * This method returns an items compartmentNumber
     *
     * @param compartmentNumber
     * @return compartmentnumber of the element
     */
    public /*@ pure @*/ Optional<StationeryItem> getItem(final int compartmentNumber) {
        if (compartmentNumber >= -1) {
            if (!StorageRack.get(compartmentNumber).isEmpty()) {
                return StorageRack.get(compartmentNumber);
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
		  @ requires
		  @ ensures
		 */

    /**
     *
     */
    public /*@ pure @*/ Optional<Integer> getCompartmentNumberOf(final Identifier identifier) {
        // TODO implement exercise 1d here.
        return Optional.empty(); // TODO delete this line if necessary.
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

