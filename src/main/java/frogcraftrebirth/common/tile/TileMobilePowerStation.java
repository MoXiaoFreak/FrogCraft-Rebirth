package frogcraftrebirth.common.tile;

import java.util.UUID;

import frogcraftrebirth.api.mps.IMobilePowerStation;
import frogcraftrebirth.api.mps.MPSUpgradeManager;
import frogcraftrebirth.api.tile.IPersonal;
import frogcraftrebirth.common.lib.tile.TileFrog;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.items.ItemStackHandler;

public class TileMobilePowerStation extends TileFrog implements ITickable, IEnergySource, IMobilePowerStation {

	private static final int 
	UPGRADE_SOLAR = 0, UPGRADE_VOLTAGE = 1, UPGRADE_STORAGE = 2, 
	CHAGRE_IN = 3, CHARGE_OUT = 4; 
	
	public final ItemStackHandler inv = new ItemStackHandler(5);

	public int energy;
	
	protected int energyMax = 60000;
	protected int tier;
	
	private UUID owner;
	
	private boolean isInENet;
	
	@Override
	public void invalidate() {
		if (!worldObj.isRemote && isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			isInENet = false;
		}
		super.invalidate();
	}
	
	@Override
	public void validate() {
		super.validate();
		if (!worldObj.isRemote && !isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			isInENet = true;
		}
	}
	
	@Override
	public void update() {
		if (worldObj.isRemote)
			return;
		//Check storage upgrade, if pass, increase energy capacity
		if (inv.getStackInSlot(UPGRADE_STORAGE) != null) {
			energyMax += MPSUpgradeManager.INSTANCE.getEnergyStoreIncreasementFrom((inv.getStackInSlot(UPGRADE_STORAGE)));
		} else {
			energyMax = 60000;
		}
		//Check transformer upgrade, if pass, increase voltage level
		if (inv.getStackInSlot(UPGRADE_VOLTAGE) != null) {
			tier += MPSUpgradeManager.INSTANCE.getVoltageIncreasementFrom(inv.getStackInSlot(UPGRADE_VOLTAGE));
		} else {
			tier = 1;
		}
		//Check solar upgrade, if pass, generate energy from sunlight
		if (MPSUpgradeManager.INSTANCE.isSolarUpgradeValid(inv.getStackInSlot(UPGRADE_SOLAR)) && worldObj.isDaytime() && worldObj.canBlockSeeSky(getPos())) {
			energy += 1;
		}
		// For each tick, there is 10% probability that overflowed energy disappears
		if (energy > energyMax && worldObj.rand.nextInt(10) == 1)
			energy = energyMax;
		//Extract energy from charge-in slot
		if (inv.getStackInSlot(CHAGRE_IN) != null && inv.getStackInSlot(1).getItem() instanceof IElectricItem) {
			this.energy += ElectricItem.manager.discharge(inv.getStackInSlot(CHAGRE_IN), tier * 32, getSourceTier(), true, false, false);
		}
		//Offer energy to item that is in charge-out slot
		if (inv.getStackInSlot(CHARGE_OUT) != null && inv.getStackInSlot(0).getItem() instanceof IElectricItem) {
			ElectricItem.manager.charge(inv.getStackInSlot(CHARGE_OUT), this.getOfferedEnergy(), getSourceTier(), false, false);
		}
		
		this.markDirty();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		loadDataFrom(tag);
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		saveDataTo(tag);
		return super.writeToNBT(tag);
	}
	
	public void loadDataFrom(NBTTagCompound tag) {
		energy = tag.getInteger("charge");
		energyMax = tag.getInteger("maxCharge");
		inv.deserializeNBT(tag.getCompoundTag("inv"));
	}
	
	public void saveDataTo(NBTTagCompound tag) {
		tag.setInteger("charge", energy);
		tag.setInteger("maxCharge", energyMax);
		tag.setTag("inv", inv.serializeNBT());
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing direction) {
		return direction != EnumFacing.UP;
	}

	@Override
	public double getOfferedEnergy() {
		return Math.min(energy, getSourceTier() * 32);
	}

	@Override
	public void drawEnergy(double amount) {
		//No op
	}

	@Override
	public int getSourceTier() {
		return tier;
	}

	@Override
	public UUID getOwnerUUID() {
		return owner;
	}

	@Override
	public IPersonal setOwner(UUID owner) {
		this.owner = owner;
		return this;
	}

	@Override
	public boolean match(UUID beingChecked) {
		return owner.equals(beingChecked);
	}

	@Override
	public String getWaringInfo(EntityPlayer player) {
		return "ACCESS DENIED";
	}

}
