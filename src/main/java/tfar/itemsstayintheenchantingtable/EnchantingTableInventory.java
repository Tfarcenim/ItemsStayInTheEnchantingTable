package tfar.itemsstayintheenchantingtable;

import net.minecraft.container.EnchantingTableContainer;
import net.minecraft.inventory.BasicInventory;

public class EnchantingTableInventory extends BasicInventory {
	private final EnchantingTableContainer container;

	public EnchantingTableInventory(int slots, EnchantingTableContainer container) {
		super(slots);
		this.container = container;
	}

	@Override
	public void markDirty() {
		super.markDirty();
		container.onContentChanged(this);
	}
}
