package de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse;

import de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse.items.StationeryItem;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Represents a buffer for temporary storage of items.
 *
 * @author Moritz Mairle, Quentin Hadar, Nora Jasharaj.
 */
public final class Buffer {

    private final Queue<StationeryItem> bufferQueue;


	/*@
	  @ requires bufferQueue !=0;
	  @*/

    /**
     * This constructor creates a new bufferQueue.
     */
    public Buffer() {
        bufferQueue = new LinkedList<>();
    }

	/*@
	  @ requires bufferQueue !== null;
	  @ ensures an item has been added to the queue or an exception has been thrown;
	 */

    /**
     * This method adds a new item to the bufferQueue.
     *
     * @param stationeryItem;
     * @throws IllegalArgumentException if the item is null;
     */
    public void bufferItem(final StationeryItem stationeryItem) {
        if (stationeryItem == null) {
            throw new IllegalArgumentException("Item must not be null");
        }
        bufferQueue.add(stationeryItem);
    }

	/*@
	  @ requires bufferQueue !== null;
	  @ ensures an item has been released or an exception has been thrown;
	 */

    /**
     * This method removes and returns an item from the bufferQueue.
     *
     * @return item at the bottom of the queue, if existent.
     * @throws NoSuchElementException if the bufferQueue is empty;
     */
    public StationeryItem releaseItem() {
        StationeryItem item = bufferQueue.poll();
        if (item == null) {
            throw new NoSuchElementException("There are no items in this buffer. There is nothing to be released!");
        } else {
            return item;
        }
    }

	/*@
	  @ requires bufferQueue !== null;
	  @ ensures returns true/false if it contains elements or not.
	 */

    /**
     * This boolean checks whether the bufferQueue is empty or not.
     *
     * @return true if it contains no elements, false if not.
     */
    // TODO add documentation here
    public /*@ pure @*/ boolean isEmpty() {
        return bufferQueue.isEmpty();
    }
}
