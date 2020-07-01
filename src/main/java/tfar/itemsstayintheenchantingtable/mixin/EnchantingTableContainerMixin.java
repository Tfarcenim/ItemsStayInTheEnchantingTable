package tfar.itemsstayintheenchantingtable.mixin;

import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.client.network.packet.GuiSlotUpdateS2CPacket;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import net.minecraft.container.EnchantingTableContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.itemsstayintheenchantingtable.Hooks;

import javax.annotation.Nullable;

@Mixin(EnchantingTableContainer.class)
abstract class EnchantingTableContainerMixin extends Container {

	@Shadow @Final private BlockContext context;

	@Shadow @Final private Inventory inventory;

	@Shadow public abstract void onContentChanged(Inventory inventory);

	private boolean loading = false;

	private EnchantingTableBlockEntity blockEntity;

	@Inject(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/container/BlockContext;)V",
					at = @At("RETURN"))
	private void loadInv(int syncId, PlayerInventory playerInventory, BlockContext blockContext, CallbackInfo ci){
		blockContext.run((world, pos) -> {
			blockEntity = (EnchantingTableBlockEntity)world.getBlockEntity(pos);
			loading = true;
			Hooks.load(blockEntity, inventory);
			loading = false;
			((ServerPlayerEntity)playerInventory.player).networkHandler.sendPacket(
							new GuiSlotUpdateS2CPacket(syncId,0,new ItemStack(Items.DIAMOND)));
		});
	}

	@Inject(method = "onContentChanged",at = @At("HEAD"))
	private void saveData(Inventory inventoryIn, CallbackInfo ci){
		context.run(((world, blockPos) -> {
			if (!loading)
			Hooks.save(blockEntity,inventory);//don't save contents until they're fully deserialized
		}));
	}

	@Inject(method = "close",at = @At("HEAD"),cancellable = true)
	private void keep(PlayerEntity player, CallbackInfo ci){
		context.run((world,pos) -> {
			ItemStack stack = inventory.removeInvStack(0);
			Inventory inventory = new BasicInventory(stack);
			dropInventory(player,world,inventory);
		});
		ci.cancel();
	}

	protected EnchantingTableContainerMixin(@Nullable ContainerType<?> type, int syncId) {
		super(type, syncId);
	}
}
