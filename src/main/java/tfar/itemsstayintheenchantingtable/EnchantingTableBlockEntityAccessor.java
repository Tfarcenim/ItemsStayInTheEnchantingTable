package tfar.itemsstayintheenchantingtable;

import net.minecraft.inventory.Inventory;

public interface EnchantingTableBlockEntityAccessor {
	Inventory getInventory();
	void saveToBlockEntity(Inventory inv);

	void loadToInventory(Inventory inv);
}
