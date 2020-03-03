package tfar.itemsstayintheenchantingtable.inventory;

import net.minecraft.block.entity.EnchantingTableBlockEntity;
import tfar.itemsstayintheenchantingtable.SerializableBasicInventory;

public class PersistantInventory extends SerializableBasicInventory {
	private final EnchantingTableBlockEntity enchantingTableBlockEntity;

	public PersistantInventory(int slots, EnchantingTableBlockEntity enchantingTableBlockEntity) {
		super(slots);
		this.enchantingTableBlockEntity = enchantingTableBlockEntity;
	}


}
