package tfar.itemsstayintheenchantingtable.mixin;

import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.container.Container;
import net.minecraft.container.EnchantingTableContainer;
import net.minecraft.container.NameableContainerProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.itemsstayintheenchantingtable.EnchantingTableBlockEntityAccessor;
import tfar.itemsstayintheenchantingtable.SerializableBasicInventory;

import javax.annotation.Nullable;

@Mixin(EnchantingTableBlockEntity.class)
public class EnchantingTableBlockEntityMixin implements EnchantingTableBlockEntityAccessor {

	private SerializableBasicInventory inventory = new SerializableBasicInventory(2);

	@Inject(at = @At("RETURN"), method = "toTag")
	private void save(CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
		cir.getReturnValue().put("inventory",inventory.serialize());
	}

	@Inject(at = @At("RETURN"), method = "fromTag")
	private void load(CompoundTag tag, CallbackInfo ci) {
		inventory.deserialize((CompoundTag) tag.get("inventory"));
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}
}
