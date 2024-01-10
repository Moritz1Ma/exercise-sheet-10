package de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse;

import de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse.items.StationeryItem;
import java.util.Optional;

/**
 * Represents a warehouse that can hold a fixed number of items.
 * The number of holdable items is defined by the capacity of the storage rack.
 *
 * @author your name
 */
public final class StorageRack {
	// @ private instance invariant capacity > 0;
	// @ private instance invariant numberOfItems >= 0;
	// @ private instance invariant numberOfItems <= capacity;

	private final int capacity;
	private int numberOfItems;
	// TODO: Add data structures for exercises 1a and 1c here.

	/*@
	@ requires capacity > 0;
	@ ensures this.capacity == capacity;
	@ ensures numberOfItems == 0;
	@ TODO add missing pre- and postconditions here or in the JavaDoc.
	@*/
	/**
	 * Creates a new storage rack with the given capacity.
	 *
	 * @param capacity capacity of the storage rack.
	 *
	 * @throws IllegalArgumentException If capacity is less than 1.
	 */
	public StorageRack(final int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("A warehouse must have a minimum capacity of 1.");
		}
		this.capacity = capacity;
		numberOfItems = 0;
		// TODO initialize data structures for exercises 1a and 1c here.
	}

	// TODO add documentation here.
	public void addItem(final StationeryItem stationeryItem) {
		// TODO implement exercises 1b and 1d here.
	}

	// TODO add documentation here.
	public void removeItem(final int compartmentNumber) {
		// TODO implement exercises 1b and 1d here.
	}

	// TODO add documentation here.
	public /*@ pure @*/ Optional<StationeryItem> getItem(final int compartmentNumber) {
		// TODO implement exercise 1b here.
		return Optional.empty(); // TODO delete this line if necessary.
	}

	// TODO add documentation here.
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
