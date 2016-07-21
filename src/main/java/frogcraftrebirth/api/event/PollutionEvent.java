package frogcraftrebirth.common.lib.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.Event.HasResult;
import frogcraftrebirth.api.world.IPollutionSource;
import net.minecraft.world.World;
/**
 * PollutionEvent will be fired when a machine produce pollution 
 * and affect the environment nearby. You can use <code>source</code>
 * reference to access the tile entity involved. <p>
 * This event can fire on <code>MinecraftForge.EVENT_BUS</code>.
 */
@Cancelable
@HasResult
public class PollutionEvent extends Event{
	
	public final IPollutionSource source;
	
	public final World world;
	
	public PollutionEvent(World world, IPollutionSource source) {
		this.source = source;
		this.world = world;
	}

}