package frogcraftrewrite.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.tile.TileACWindmillBase;
import frogcraftrewrite.common.tile.TileACWindmillTurbine;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
/**
 * ACWindmill stands for "Academy Windmill", the major generator 
 * in the story settings of animation A Certain Scientific Railgun.
 * */
public class BlockACWindmill extends BlockFrog {
	
	public BlockACWindmill() {
		super(Material.iron, 1);
		setHardness(10.0F);
		setResistance(10.0F);
		setSubNameArray("ACWindMillBase", "ACWindMillTurbine");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister r) {
		this.iconArray[0][0] = r.registerIcon("frogcraftrewrite:ACWindMill_Base_Bottom");
		this.iconArray[0][1] = r.registerIcon("frogcraftrewrite:ACWindMill_Base_Top");
		this.iconArray[0][2] = r.registerIcon("frogcraftrewrite:ACWindMill_Base_Side");
		this.iconArray[0][3] = r.registerIcon("frogcraftrewrite:ACWindMill_Base_Side");
		this.iconArray[0][4] = r.registerIcon("frogcraftrewrite:ACWindMill_Base_Side");
		this.iconArray[0][5] = r.registerIcon("frogcraftrewrite:ACWindMill_Base_Side");

		this.iconArray[1][0] = r.registerIcon("frogcraftrewrite:ACWindMill_Side");
    	this.iconArray[1][1] = r.registerIcon("frogcraftrewrite:ACWindMill_Side");
    	this.iconArray[1][2] = r.registerIcon("frogcraftrewrite:ACWindMill_Back");
    	this.iconArray[1][3] = r.registerIcon("frogcraftrewrite:ACWindMill_Front");    	
    	this.iconArray[1][4] = r.registerIcon("frogcraftrewrite:ACWindMill_Side");
    	this.iconArray[1][5] = r.registerIcon("frogcraftrewrite:ACWindMill_Side");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch(meta){
			case 0:
				return new TileACWindmillBase();
			case 1:
				return new TileACWindmillTurbine();
			default:
				return null;
		}
	}
}
