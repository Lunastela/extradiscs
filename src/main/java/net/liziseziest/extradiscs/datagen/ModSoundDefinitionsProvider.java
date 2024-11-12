package net.liziseziest.extradiscs.datagen;

import java.util.HashMap;

import net.liziseziest.extradiscs.ExtraDiscs;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class ModSoundDefinitionsProvider extends SoundDefinitionsProvider {

    public static HashMap<RecordInfo, SoundEvent> soundRegistryMap = new HashMap<RecordInfo, SoundEvent>();
    public ModSoundDefinitionsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExtraDiscs.MODID, existingFileHelper);
    }

    @Override
    public void registerSounds() {
        soundRegistryMap.forEach((k, v) -> {
            this.add(() -> v, definition().with(
                sound(new ResourceLocation(ExtraDiscs.MODID, "music/" + k.discType().filePath 
                    + (k.appender() == null ? "" : ("/" + k.appender())) + "/" + k.name())).stream()
            ));
        });
    }
}
