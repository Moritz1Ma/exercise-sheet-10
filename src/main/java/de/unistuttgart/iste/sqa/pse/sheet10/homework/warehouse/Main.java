package de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse;

import de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse.items.Ruler;
import de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse.items.StationeryItem;
import de.unistuttgart.iste.sqa.pse.sheet10.homework.warehouse.items.Pen;

import java.util.Optional;

/**
 * A Simple class with a main method to experiment with the warehouse system.
 *
 * This Class will not be graded. Fell free to experiment at you leisure.
 * The {@code main} operation already contains some code. You can use it as a starting point.
 *
 */
public class Main {

	public static void main(String[] args) {
		Company mailOrderCompany = new Company();

		StationeryItem glitterPen = new Pen(new Identifier(), "Very glittery");
		StationeryItem gelPen = new Pen(new Identifier(), "With gel");
		StationeryItem ruler = new Ruler(new Identifier(), "Slightly crooked");

		Customer missLee = new Customer("Annabel Lee");
		Customer misterPoe = new Customer("Edgar Allan Poe");

		// add incoming stationary item to warehouse
		mailOrderCompany.storeInStorageRack(gelPen);
		mailOrderCompany.storeInStorageRack(glitterPen);
		mailOrderCompany.storeInStorageRack(ruler);

		// order items
		mailOrderCompany.processIncomingOrder(glitterPen.getIdentifier(), missLee);
		mailOrderCompany.processIncomingOrder(gelPen.getIdentifier(), missLee);
		mailOrderCompany.processIncomingOrder(ruler.getIdentifier(), misterPoe);

		// expected buffer content : glitterPen, a bonus item for annabel, gelPen, ruler, a bonus item for edgar.
		// As we've not yet learned about testing, we need to compare the console output with our own eyes.
		for (int i = 0; i < 5; i++) {
			Optional<StationeryItem> nextItem = mailOrderCompany.takeItemForPackaging();
			if (nextItem.isPresent()) {
				System.out.println("Handing " + nextItem.get().toString() + " over to packaging.");
			}

		}
	}
}
