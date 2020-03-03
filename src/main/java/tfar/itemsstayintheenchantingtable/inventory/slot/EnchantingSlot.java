package tfar.itemsstayintheenchantingtable.inventory.slot;

import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;

public class EnchantingSlot extends Slot {
	public EnchantingSlot(Inventory inventory, int invSlot, int xPosition, int yPosition) {
		super(inventory, invSlot, xPosition, yPosition);
	}

	@Override
	public int getMaxStackAmount() {
		return 1;
	}
}
