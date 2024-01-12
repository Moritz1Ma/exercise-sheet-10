package de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse;

import de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse.items.Compass;
import de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse.items.Pen;
import de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse.items.Ruler;
import de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse.items.StationeryItem;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

/**
 * Represents a company.
 * <p>
 * A company stores items and processes orders.
 *
 * @author Moritz Mairle, Quentin Hadar, Nora Jasharaj
 */
public final class Company {

    private final StorageRack itemStorageRack;
    private final Buffer orderBuffer;
    private final Set<Customer> returningCustomers;

	/*@
	  @ ensures itemStorageRack != null;
	  @ ensures itemStorageRack.getCapacity == 75;
	  @*/
    /**
     * This constructor creates a new StorageRack with a capacity of a maximum of 75 items.
     */
    public Company() {
        orderBuffer = new Buffer();
        this.itemStorageRack = new StorageRack(75);
        this.returningCustomers = new HashSet<>();
    }

	/*@
	  @ requires itemStorageRack != null;
	  @ requires stationeryItem != null;
	  @ ensures numberOfItems == \old(numberOfItems + 1)
	  @ ensures itemStorageRack.storageRack.contains(stationeryItem);
	 */
    /**
     * Adds a new stationeryItem to itemStorageRack. If the Rack is full, nothing happens.
     *
     * @param stationeryItem the Item to be added.
     */
    public void storeInStorageRack(final StationeryItem stationeryItem) {
        if (itemStorageRack.getNumberOfItems() < itemStorageRack.getCapacity()) {
            itemStorageRack.addItem(stationeryItem);
        }
    }


    /*@
      @ requires !itemStorageRack.getCompartmentNumberOf(identifier).equals(Optional.empty());
      @ requires !itemStorageRack.getCompartmentNumberOf(identifier).isEmpty());
      @ requires !itemStorageRack.getItem(compartmentNumber).isEmpty();
      @ ensures orderBuffer.bufferQueue.contains(order);
      @ ensures !itemStorageRack.storageRack.contains(order);
      @ ensures if customer is a first timer customer that he is given a bonus item
     */

    /**
     * Adds a stationeryItem to the Buffer and removes it from the storageRack
     *
     * @param identifier the identifier of the item
     * @param customer the customer who ordered the item
     */
    public void processIncomingOrder(final Identifier identifier, final Customer customer) {
        if (itemStorageRack.getCompartmentNumberOf(identifier).equals(Optional.empty())
                && itemStorageRack.getCompartmentNumberOf(identifier).isEmpty()) {
            return;
        }

        int compartmentNumber = itemStorageRack.getCompartmentNumberOf(identifier).get();
        if (itemStorageRack.getItem(compartmentNumber).isEmpty()){
            return;
        }

        StationeryItem order = itemStorageRack.getItem(compartmentNumber).get();

        orderBuffer.bufferItem(order);
        itemStorageRack.removeItem(compartmentNumber);

        if(!returningCustomers.contains(customer)){
            orderBuffer.bufferItem(getBonusItem());
            returningCustomers.add(customer);
        }
    }

	/*@
	@ ensures \result != null;
	@ ensures \result.getIdentification().getType() == ItemType.PRESENT;
	@*/

    /**
     * Generates a bonus item for marketing reasons.
     *
     * @return A marketing bonus item.
     */
    private /*@ pure @*/ StationeryItem getBonusItem() {
        return switch ((new Random().nextInt(3))) {
            case 1 -> new Compass(new Identifier(), "A marketing bonus item.");
            case 2 -> new Ruler(new Identifier(), "A marketing bonus item.");
            default -> new Pen(new Identifier(), "A marketing bonus item.");
        };
    }

    /*@
      @ requires orderBuffer != null;
      @ requires !orderBuffer.isEmpty();
      @ ensures \return != null;
     */
    /**
     * If items are waiting for packaging, takes the next available item from the buffer and return it.
     *
     * @return Optional next available item for packaging, or an empty Optional if there is none.
     */
    public Optional<StationeryItem> takeItemForPackaging() {
        if (orderBuffer.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(orderBuffer.releaseItem());
        }
    }
}
