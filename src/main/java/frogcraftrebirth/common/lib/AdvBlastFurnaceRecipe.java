/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common.lib;

import frogcraftrebirth.api.recipes.IAdvBlastFurnaceRecipe;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class AdvBlastFurnaceRecipe implements IAdvBlastFurnaceRecipe {

	private final IFrogRecipeInput primaryInput;
	private final IFrogRecipeInput secondaryInput;
	private final FluidStack fluidInput;
	private final ItemStack output;
	private final ItemStack byproduct;
	private final Fluid shieldGas;
	private final int time;
	private final int heatConsumption;

	public AdvBlastFurnaceRecipe(IFrogRecipeInput inputPrimary, IFrogRecipeInput inputSecondary, FluidStack inputFluid, ItemStack outputPrimary, ItemStack outputSecondary, Fluid shield, int time, int heatConsumption) {
		this.primaryInput = inputPrimary;
		this.secondaryInput = inputSecondary;
		this.fluidInput = inputFluid;
		this.output = outputPrimary;
		this.byproduct = outputSecondary;
		this.shieldGas = shield;
		this.time = time;
		this.heatConsumption = heatConsumption;
	}

	@Override
	public IFrogRecipeInput getInput() {
		return primaryInput;
	}

	@Override
	public IFrogRecipeInput getInputSecondary() {
		return secondaryInput;
	}

	@Override
	public FluidStack getInputFluid() {
		return fluidInput;
	}

	@Override
	public ItemStack getOutput() {
		return output;
	}

	@Override
	public ItemStack getByproduct() {
		return byproduct;
	}

	@Override
	public Fluid getShieldGas() {
		return shieldGas;
	}

	@Override
	public int getTime() {
		return time;
	}

	@Override
	public int getHeatConsumption() {
		return heatConsumption;
	}

}
