package chanceCubes.sounds;

import chanceCubes.CCubesCore;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class CCubesSounds
{
	public static SoundEvent D20_BREAK = registerSound("d20_break", SoundEvent.createVariableRangeEvent(new ResourceLocation(CCubesCore.MODID, "d20_break")));
	public static SoundEvent GIANT_CUBE_SPAWN = registerSound("giant_cube_spawn", SoundEvent.createVariableRangeEvent(new ResourceLocation(CCubesCore.MODID, "giant_cube_spawn")));

	private static <T extends SoundEvent> T registerSound(String id, T event) {
		return Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation(CCubesCore.MODID, id), event);
	}
	
	// public static final Map<String, SoundEvent> customSounds = new HashMap<>();
	
//	private static boolean registered = true;
//
//	public static SoundEvent registerSound(String soundID)
//	{
//		SoundEvent sound;
//		if(!customSounds.containsKey(soundID))
//		{
//			// TODO: I guess we should do something here, but idk what yet
//			if(registered)
//				CCubesCore.logger.log(Level.WARN, "A new sound was added after the sounds were registered and therefore the new sound could not be added!");
//			ResourceLocation res = new ResourceLocation("minecraft", soundID);
//			sound = new SoundEvent(res).setRegistryName(res);
//			//BuiltInRegistries.SOUND_EVENTS.register(sound);
//			customSounds.put(soundID, sound);
//		}
//		else
//		{
//			sound = customSounds.get(soundID);
//		}
//
//		return sound;
//	}
}
