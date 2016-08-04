package frogcraftrebirth.common.registry;

import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.block.BlockFluidFrog;
import frogcraftrebirth.common.block.BlockNitricAcid;
import frogcraftrebirth.common.lib.FrogFluid;
import net.minecraft.item.EnumRarity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class RegFluid {
	
	public static void init() {
		FrogFluids.ammonia = new FrogFluid("ammonia", "ammonia", 694, 240, true, EnumRarity.EPIC);
		FrogFluids.argon = new FrogFluid("argon", "argon", 1784, 300, true, EnumRarity.RARE);
		FrogFluids.benzene = new FrogFluid("benzene", "benzene", 877, 300, true, EnumRarity.EPIC);
		FrogFluids.bromine = new FrogFluid("bromine", "bromine", 3103, 300, false, EnumRarity.UNCOMMON);
		FrogFluids.carbonOxide = new FrogFluid("carbonOxide", "CO", 1250, 300, true, EnumRarity.UNCOMMON);
		FrogFluids.carbonDioxide = new FrogFluid("carbonDioxide", "CO2", 1980, 300, true, EnumRarity.COMMON);
		FrogFluids.coalTar = new FrogFluid("coalTar", "coal_tar", 2000, 300, false, EnumRarity.RARE).setViscosity(2000);
		FrogFluids.fluorine = new FrogFluid("fluorine", "fluorine", 1696, 300, true, EnumRarity.EPIC);
		FrogFluids.nitricAcid = new FrogFluid("nitricAcid", "HNO3_flow", "HNO3",  1420, 300, false, EnumRarity.RARE);
		FrogFluids.nitrogenOxide = new FrogFluid("nitrogenOxide", "NO", 1340, 300, true, EnumRarity.RARE);
		FrogFluids.oxygen = new FrogFluid("oxygen", "oxygen", 1429, 300, true, EnumRarity.COMMON);
		FrogFluids.sulfurDioxide = new FrogFluid("sulfurDioxide", "SO2", 1640, 300, true, EnumRarity.UNCOMMON);
		FrogFluids.sulfurTrioxide = new FrogFluid("sulfurTrioxide", "SO3", 1800, 300, true, EnumRarity.RARE);
		
		regFluid(FrogFluids.ammonia);
		regFluid(FrogFluids.argon);
		regFluid(FrogFluids.benzene);
		regFluid(FrogFluids.bromine);
		regFluid(FrogFluids.carbonOxide);
		regFluid(FrogFluids.carbonDioxide);
		regFluid(FrogFluids.coalTar, true);
		regFluid(FrogFluids.fluorine);
		regFluid(FrogFluids.nitricAcid, true);
		regFluid(FrogFluids.nitrogenOxide);
		regFluid(FrogFluids.oxygen);
		regFluid(FrogFluids.sulfurDioxide);
		regFluid(FrogFluids.sulfurTrioxide);
		
		FrogBlocks.fluidNitricAcid = new BlockNitricAcid(FrogFluids.nitricAcid);
		FrogBlocks.fluidAmmonia = new BlockFluidFrog(FrogFluids.ammonia, "fluid.ammonia");
		FrogBlocks.fluidArgon = new BlockFluidFrog(FrogFluids.argon, "fluid.argon");
		FrogBlocks.fluidBenzene = new BlockFluidFrog(FrogFluids.benzene, "fluid.benzene");
		FrogBlocks.fluidBromine = new BlockFluidFrog(FrogFluids.bromine, "fluid.bromine");
		FrogBlocks.fluidCarbonDioxide = new BlockFluidFrog(FrogFluids.carbonDioxide, "fluid.carbonDioxide");
		FrogBlocks.fluidCarbonOxide = new BlockFluidFrog(FrogFluids.carbonOxide, "fluid.carbonOxide");
		FrogBlocks.fluidCoalTar = new BlockFluidFrog(FrogFluids.coalTar, "fluid.coalTar");
		FrogBlocks.fluidFluorine = new BlockFluidFrog(FrogFluids.fluorine, "fluid.Fluorine");
		FrogBlocks.fluidNitrogenOxide = new BlockFluidFrog(FrogFluids.nitrogenOxide, "fluid.nitrogenOxide");
		FrogBlocks.fluidOxygen = new BlockFluidFrog(FrogFluids.oxygen, "fluid.Oxygen");
		FrogBlocks.fluidSulfurDioxide = new BlockFluidFrog(FrogFluids.sulfurDioxide, "fluid.sulfurDioxide");
		FrogBlocks.fluidSulfurTrioxide = new BlockFluidFrog(FrogFluids.sulfurTrioxide, "fluid.sulfurTrioxide");
	}
	
	private static void regFluid(Fluid fluid) {
		regFluid(fluid, false);
	}
	
	private static void regFluid(Fluid fluid, boolean regBucket) {
		if (!FluidRegistry.registerFluid(fluid))
			fluid = FluidRegistry.getFluid(fluid.getName());
		if (regBucket)
			FluidRegistry.addBucketForFluid(fluid);
	}

}
