/**
 * This file is a part of FrogCraftRebirth, 
 * created by U_Knowledge at 4:44:30 PM, Mar 28, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api;

import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.item.ItemStack;

public class OreStack {
	
	private final String entry;
	private final int amount;
	
	public OreStack(String entry) {
		this(entry, 1);
	}
	
	public OreStack(String entry, int amount) {
		this.entry = entry;
		this.amount = amount;
	}
	
	public String getEntry() {
		return this.entry;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public boolean equals(OreStack stack) {
		return stack.getEntry().equals(this.entry) && stack.getAmount() == this.getAmount();
	}
	
	public boolean consumable(ItemStack stack) {
		return ItemUtil.entryHasStack(stack, this.entry) && stack.stackSize >= this.amount;
	}
	
	public void consume(ItemStack stack) {
		if (this.consumable(stack))
			stack.stackSize -= this.amount;
		if (stack.stackSize <= 0)
			stack = null;
	}

}
