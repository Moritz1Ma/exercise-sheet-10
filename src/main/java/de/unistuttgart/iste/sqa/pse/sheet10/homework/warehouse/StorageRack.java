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
    private final ArrayList<Optional<StationeryItem>> storageRack;
    private final HashMap<Identifier, Integer> identifierMap;


	/*@
	@ requires capacity > 0;
	@ ensures numberOfItems == 0;
	@ ensures StorageRack is a new ArrayList with initial capacity set as this.capacity, which is filled with
	          empty Optionals;
	@ ensures identifierMap is a new HashMap with the same initial capacity as storageRack;
	@*/
    /**
     * Creates a new storage rack with the given capacity.
     *
     * @param capacity capacity of the storage rack;
     * @throws IllegalArgumentException If capacity is less than 1;
     */
    public StorageRack(final int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("A warehouse must have a minimum capacity of 1");
        }
        this.capacity = capacity;
        this.numberOfItems = 0;
        this.storageRack = new ArrayList<>(capacity);
        /*@
          @ loop_invariant added Optional.empty() i times
          @ decreasing capacity - i
         */
        for (int i = 0; i < capacity; i++) {
            this.storageRack.add(Optional.empty());
        }
        this.identifierMap = new HashMap<>(capacity);
        checkInvariants();
    }

	/*@
	@ requires capacity > 0;
	@ requires stationerItem != null;
	@ requires numberOfItems < capacity;
	@ requires storageRack != null;
    @ requires identifier != null;
	@ requires numberOfItems < capacity;
	@ ensures stationaryItem is added to StorageRack for the lowest index that is empty;
	@ ensures identifier of the stationeryItem is being given the compartmentNumber of the storageRack;
	@*/
    /**
     * Adds a new StationeryItem to the storageRack;
     *
     * @param stationeryItem the Item to be added;
     * @throws IllegalArgumentException If the stationery item is null;
     */
    public void addItem(final StationeryItem stationeryItem) {
        if (numberOfItems >= capacity) {
            return;
        }
        if (stationeryItem == null) {
            throw new IllegalArgumentException("The stationery item must not be null");
        }
        /*@
          @ loop_invariant loop checked i slots;
          @ decreasing indexOfFirstEmptySlot - i;
         */
        for (int i = 0; i < capacity; i++) {
            if (storageRack.get(i).isEmpty()) {
                storageRack.set(i, Optional.of(stationeryItem));
                identifierMap.put(stationeryItem.getIdentifier(), ++i);
                this.numberOfItems++;
                break;
            }
        }
    }

	/*@
	@ requires compartmentNumber >= 0;
	@ requires storageRack != null;
	@ ensures the item is removed from its position;
	@ ensures the identifiers entry is removed from the identifierMap;
	 */
    /**
     * This method removes an item from the specified compartmentNumber.
     *
     * @param compartmentNumber number of the compartment;
     */
    public void removeItem(final int compartmentNumber) {
        if (getItem(compartmentNumber).isPresent()
                && !storageRack.get(compartmentNumber - 1).equals(Optional.empty())) {
            Identifier id = getItem(compartmentNumber).get().getIdentifier();
            identifierMap.remove(id);
            storageRack.set(compartmentNumber - 1, Optional.empty());
            this.numberOfItems--;
        }
    }

	/*@
	@ requires 0 < compartmentNumber <= capacity;
	@ requires storageRack != null;
	@ ensures the compartmentNumber of the element is returned;
	 */
    /**
     * This method returns the Item in a compartment.
     *
     * @param compartmentNumber The number of the compartment of which the item is looked for
     * @return Optional of the StationeryItem in the compartment
     * @throws IllegalArgumentException if the compartmentNumber does not exist in the StorageRack.
     */
    public /*@ pure @*/ Optional<StationeryItem> getItem(final int compartmentNumber) {
        if (0 < compartmentNumber && compartmentNumber <= capacity) {
            if (!storageRack.get(compartmentNumber - 1).equals(Optional.empty())) {
                return storageRack.get(compartmentNumber - 1);
            } else {
                return Optional.empty();
            }
        }
        throw new IllegalArgumentException("This compartment number is invalid");
    }

		/*@
		  @ requires identifier != null;
		  @ ensures compartmentNumber of wanted identifier is being returned.
		 */
    /**
     * This method returns the compartmentNumber of a wanted StationeryItem
     * from the StorageRack by its identifier.
     *
     * @param identifier the identifier of the StationeryItem.
     * @return compartment number of the identifier, if existent. Otherwise, returns null.
     */
    public /*@ pure @*/ Optional<Integer> getCompartmentNumberOf(final Identifier identifier) {
        if (identifierMap.containsKey(identifier)) {
            int compartmentNumber = identifierMap.get(identifier);
            return Optional.of(compartmentNumber);
        }
        return Optional.empty();
    }

	/*@
	@ ensures \result == capacity;
	@*/
    /**
     * @return The capacity of this warehouse in items.
     */
    public /*@ pure @*/ int getCapacity() {
        return this.capacity;
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

    /**
     * A check if the invariants of the class are fulfilled
     */
    private void checkInvariants() {
        assert capacity > 0 : "Capacity should be greater than 0";
        assert numberOfItems >= 0 : "Number of items should be non-negative";
        assert numberOfItems <= capacity : "Number of items should be less than or equal to capacity";
    }

}
