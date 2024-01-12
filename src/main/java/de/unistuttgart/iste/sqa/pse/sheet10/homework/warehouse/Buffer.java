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

    // TODO add data structure for exercise 1f here.

	/*@
	  @ requires bufferQueue != null;
	  @*/

    /**
     * This constructor creates a new bufferQueue.
     */
// TODO add documentation here
    public Buffer() {
        bufferQueue = new LinkedList<>();
        // TODO initialize data structure for exercise 1f here.
    }

	/*@
	  @ requires bufferQueue != null;
	  @ ensures an item has been added to the queue or an exception has been thrown;
	 */

    /**
     * This method adds a new item to the bufferQueue.
     *
     * @param stationeryItem the stationeryItem which is added to the bufferQueue
     * @throws IllegalArgumentException if the item is null;
     */
    // TODO add documentation here
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
    // TODO add documentation here
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
        // TODO implement exercise 1g here.
    }
}
