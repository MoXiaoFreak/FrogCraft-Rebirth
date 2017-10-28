package frogcraftrebirth.common.lib.util;

import java.util.*;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public final class ItemUtil {
	
	private static final Random RAND = new Random();

	public static final Predicate<ItemStack> EMPTY_PREDICATE = ItemStack::isEmpty;

	public static final Predicate<ItemStack> NON_EMPTY_PREDICATE = stack -> !stack.isEmpty();

	/**
	 * Identical to {@link InventoryHelper#dropInventoryItems}, except this one is designed for {@link IItemHandler}.
	 * @param worldIn The world that block is in
	 * @param pos The position of block
	 * @param inv An array of IItemHandler implementation. Note: all IItemHandler here are assumed to start index from zero.
	 */
	public static void dropInventroyItems(World worldIn, BlockPos pos, IItemHandler... inv) {
		for (IItemHandler invSingle : inv) {
			final int slots = invSingle.getSlots();
			for (int index = 0; index < slots; index++) {
				ItemStack stack = invSingle.getStackInSlot(index);
				if (!stack.isEmpty())
					dropItemStackAsEntityInsanely(worldIn, pos, stack);
			}
		}
	}
	
	/**
	 * Identical to {@link InventoryHelper#spawnItemStack} in terms of functionality,
	 * except that this one has insane implementation.
	 * @param worldIn The world that item stack will drop.
	 * @param pos The position reference.
	 * @param toDrop The item stack to drop.
	 */
	public static void dropItemStackAsEntityInsanely(World worldIn, BlockPos pos, @Nonnull ItemStack toDrop) {
		EntityItem entityItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), toDrop);
		entityItem.motionX = RAND.nextGaussian() * 0.05D;
		entityItem.motionY = RAND.nextGaussian() * 0.05D + 0.2D;
		entityItem.motionZ = RAND.nextGaussian() * 0.05D;
		worldIn.spawnEntity(entityItem);
	}

}
