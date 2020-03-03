package tfar.itemsstayintheenchantingtable.mixin;

import net.minecraft.container.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.itemsstayintheenchantingtable.EnchantingTableBlockEntityAccessor;
import tfar.itemsstayintheenchantingtable.inventory.slot.EnchantingSlot;
import tfar.itemsstayintheenchantingtable.inventory.slot.LapizSlot;

import javax.annotation.Nullable;

@Mixin(EnchantingTableContainer.class)
abstract class EnchantingTableContainerMixin extends Container {

	@Mutable @Shadow @Final private Inventory inventory;

	@Shadow @Final private BlockContext context;

	@Inject(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/container/BlockContext;)V",
					at = @At("RETURN"))
	private void changeInv(int syncId, PlayerInventory playerInventory, BlockContext blockContext, CallbackInfo ci){
		this.inventory = context.run(World::getBlockEntity)
						.filter(EnchantingTableBlockEntityAccessor.class::isInstance).map(blockEntity ->
										((EnchantingTableBlockEntityAccessor) blockEntity).getInventory()).orElse(new BasicInventory(2));
		Slot slot = new EnchantingSlot(inventory, 0, 15, 47);
		slot.id = 0;
		slot.setStack(ItemStack.EMPTY);
		slotList.set(0,slot);

		Slot slot1 = new LapizSlot(inventory,1, 35, 47);
		slot1.id = 1;
		slot1.setStack(ItemStack.EMPTY);
		slotList.set(1,slot1);
	}

	/**
	 * @author no
	 */
	@Overwrite
	public void close(PlayerEntity player) {
		super.close(player);
	}

	protected EnchantingTableContainerMixin(@Nullable ContainerType<?> type, int syncId) {
		super(type, syncId);
	}
}
