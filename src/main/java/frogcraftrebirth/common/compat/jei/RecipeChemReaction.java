/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 2:32:11 PM, Aug 13, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import java.util.ArrayList;
import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RecipeChemReaction implements IRecipeWrapper {
	
	public static List<RecipeChemReaction> getWrappedRecipeList() {
		List<RecipeChemReaction> list = new ArrayList<RecipeChemReaction>();
		for (IAdvChemRecRecipe recipe : FrogAPI.managerACR.getRecipes()) {
			list.add(new RecipeChemReaction(recipe));
		}
		return list;
	}
	
	final IAdvChemRecRecipe recipe;
	
	public RecipeChemReaction(IAdvChemRecRecipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public List<?> getInputs() {
		return null; //Should return something
	}

	@Override
	public List<ItemStack> getOutputs() {
		return new ArrayList<ItemStack>(recipe.getOutputs());
	}

	@Override
	public List<FluidStack> getFluidInputs() {
		return null;
	}

	@Override
	public List<FluidStack> getFluidOutputs() {
		return null;
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		
	}

	@Override
	public void drawAnimations(Minecraft minecraft, int recipeWidth, int recipeHeight) {
		
	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return null;
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		return false;
	}

}
