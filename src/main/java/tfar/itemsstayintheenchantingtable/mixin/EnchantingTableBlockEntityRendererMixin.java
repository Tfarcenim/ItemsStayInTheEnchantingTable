package tfar.itemsstayintheenchantingtable.mixin;

import net.minecraft.block.AnvilBlock;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.EnchantingTableBlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.itemsstayintheenchantingtable.ClientHooks;
import tfar.itemsstayintheenchantingtable.EnchantingTableBlockEntityAccessor;

@Mixin(EnchantingTableBlockEntityRenderer.class)
public abstract class EnchantingTableBlockEntityRendererMixin {

	@Shadow public abstract void render(EnchantingTableBlockEntity enchantingTableBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j);

	@Inject(method = "render",at = @At("RETURN"))
	private void renderItem(EnchantingTableBlockEntity enchantingTableBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci){
		ClientHooks.render((EnchantingTableBlockEntityRenderer)(Object)this,enchantingTableBlockEntity,f,matrixStack,vertexConsumerProvider,i,j);
	}
}
