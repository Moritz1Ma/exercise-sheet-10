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


    // TODO add documentation here.
	/*@
	  @ ensures itemStorageRack != null;
	  @ ensures itemStorageRack.get(capacity) == 75;
	  @*/

    /**
     * This constructor creates a new StorageRack with a capacity of a maximum of 75 items.
     */
    public Company() {
        orderBuffer = new Buffer();
        this.itemStorageRack = new StorageRack(75);
        this.returningCustomers = new HashSet<>();
        // TODO: implement exercises 1i here.
    }

	/*@
	  @ requires itemStorageRack != null;
	  @ ensures a stationeryIte is being added;
	  @
	 */

    /**
     * This method adds a new stationeryItem to itemStorageRack. If the Rack is full, nothing happens.
     *
     * @param stationeryItem;
     */
    // TODO add documentation here.
    public void storeInStorageRack(final StationeryItem stationeryItem) {
        if (itemStorageRack.getNumberOfItems() < itemStorageRack.getCapacity()) {
            itemStorageRack.addItem(stationeryItem);
        }
    }

    // TODO add documentation here.
    /*@

     */
    public void processIncomingOrder(final Identifier identifier, final Customer customer) {
        if (itemStorageRack.getCompartmentNumberOf(identifier).equals(Optional.empty())
                || itemStorageRack.getCompartmentNumberOf(identifier).isEmpty()) {
            return;
        }
        int compartmentNumber = itemStorageRack.getCompartmentNumberOf(identifier).get();
        if (itemStorageRack.getItem(compartmentNumber).isEmpty()){
            return;
        }
        StationeryItem order = itemStorageRack.getItem(compartmentNumber).get();


        orderBuffer.bufferItem(order);
        if(!returningCustomers.contains(customer)){
            orderBuffer.bufferItem(getBonusItem());
            returningCustomers.add(customer);
        }
        itemStorageRack.removeItem(compartmentNumber);
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

        switch ((new Random().nextInt(3))) {
            case 1:
                return new Compass(new Identifier(), "A marketing bonus item.");
            case 2:
                return new Ruler(new Identifier(), "A marketing bonus item.");
            default:
                return new Pen(new Identifier(), "A marketing bonus item.");
        }
    }

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
