package tfar.itemsstayintheenchantingtable.inventory.slot;

import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class LapizSlot extends Slot {
	public LapizSlot(Inventory inventory, int invSlot, int xPosition, int yPosition) {
		super(inventory, invSlot, xPosition, yPosition);
	}

	@Override
	public boolean canInsert(ItemStack stack) {
		return stack.getItem() == Items.LAPIS_LAZULI;
	}
}
