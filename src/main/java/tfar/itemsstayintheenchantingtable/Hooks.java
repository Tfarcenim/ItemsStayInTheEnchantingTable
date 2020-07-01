package tfar.itemsstayintheenchantingtable;

import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.inventory.Inventory;

public class Hooks {

	public static void load(EnchantingTableBlockEntity blockEntity, Inventory inv) {
		((EnchantingTableBlockEntityAccessor)blockEntity).loadToInventory(inv);
	}

	public static void save(EnchantingTableBlockEntity blockEntity, Inventory inv) {
		((EnchantingTableBlockEntityAccessor)blockEntity).saveToBlockEntity(inv);
	}

}
