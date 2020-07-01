package tfar.itemsstayintheenchantingtable;

import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.EnchantingTableBlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;

public class ClientHooks {
	public static void render(EnchantingTableBlockEntityRenderer enchantingTableBlockEntityRenderer, EnchantingTableBlockEntity enchantingTableBlockEntity, float delta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider,
														int i, int j){
		Inventory inventory = ((EnchantingTableBlockEntityAccessor)enchantingTableBlockEntity).getInventory();
		ItemStack display = inventory.getInvStack(0);
		if (!display.isEmpty()){
			matrixStack.push();
			drawItemStack(display, matrixStack,i, vertexConsumerProvider,  delta);
			matrixStack.pop();
		}
	}

	public static void drawItemStack(ItemStack stack, MatrixStack matrices, int int_1, VertexConsumerProvider vertexConsumerProvider, float deltaTime) {
		matrices.translate(.5,1.5,.5);
		double ticks = ( Util.getMeasuringTimeNano()/20f + deltaTime) * .5;
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float)(ticks * 2.5)));
		matrices.scale(0.25F, 0.25F, 0.25F);
		MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.FIXED, int_1, OverlayTexture.DEFAULT_UV, matrices, vertexConsumerProvider);
	}
}
