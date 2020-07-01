package tfar.itemsstayintheenchantingtable.mixin;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.network.packet.BlockEntityUpdateS2CPacket;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerListener;
import net.minecraft.container.EnchantingTableContainer;
import net.minecraft.container.NameableContainerProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.itemsstayintheenchantingtable.EnchantingTableBlockEntityAccessor;
import tfar.itemsstayintheenchantingtable.SerializableBasicInventory;

import javax.annotation.Nullable;
import javax.management.monitor.CounterMonitor;

@Mixin(EnchantingTableBlockEntity.class)
public class EnchantingTableBlockEntityMixin extends BlockEntity implements EnchantingTableBlockEntityAccessor, BlockEntityClientSerializable {

	public SerializableBasicInventory inventory = new SerializableBasicInventory(2);

	@Inject(at = @At("RETURN"), method = "toTag")
	private void saveToTag(CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
		tag.put("inventory",inventory.serialize());
	}

	@Inject(at = @At("RETURN"), method = "fromTag")
	private void loadFromTag(CompoundTag tag, CallbackInfo ci) {
		CompoundTag tag1 = (CompoundTag) tag.get("inventory");
		if (tag1 != null)
			inventory.deserialize((CompoundTag) tag.get("inventory"));
	}

	public void saveToBlockEntity(Inventory inv){
			inventory.setInvStack(0,inv.getInvStack(0).copy());
			inventory.setInvStack(1,inv.getInvStack(1).copy());
			markDirty();
		}

	public void loadToInventory(Inventory inv){
		inv.setInvStack(0,inventory.getInvStack(0));
		inv.setInvStack(1,inventory.getInvStack(1));
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public void fromClientTag(CompoundTag compoundTag) {
		inventory.deserialize((CompoundTag) compoundTag.get("inventory"));
	}

	@Override
	public CompoundTag toClientTag(CompoundTag compoundTag) {
		compoundTag.put("inventory",inventory.serialize());
		return compoundTag;
	}

	@Override
	public void markDirty() {
		super.markDirty();
		sync();
	}

	public EnchantingTableBlockEntityMixin(BlockEntityType<?> type) {
		super(type);
	}

}
