package net.liziseziest.extradiscs;

import java.util.function.Supplier;

import net.liziseziest.extradiscs.datagen.ModSoundDefinitionsProvider;
import net.liziseziest.extradiscs.datagen.RecordInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.registries.RegistryObject;

public class TypedRecordItem extends RecordItem {
    private DiscType discType;
    public DiscType getDiscType() {
        return discType;
    }

    public TypedRecordItem(DiscType discType, int comparatorValue, Supplier<SoundEvent> soundSupplier, Properties builder, int lengthInTicks) {
        super((comparatorValue + 1), soundSupplier, builder, lengthInTicks);
        this.discType = discType;
    }

    @Override
    public Component getName(ItemStack pStack) {
        return Component.translatable("item." + ExtraDiscs.MODID + "." + discType.label);
    }

    public static RegistryObject<SoundEvent> registerRecordVariant(String recordName, DiscType recordType, String appender) {
        SoundEvent myEvent = SoundEvent.createFixedRangeEvent(new ResourceLocation(ExtraDiscs.MODID, ("music_disc." + recordName)), 16.0f);
        ModSoundDefinitionsProvider.soundRegistryMap.put(new RecordInfo(recordName, recordType, appender), myEvent);
        RegistryObject<SoundEvent> mySound = ExtraDiscs.SOUND_REGISTRY.register(recordName, () -> myEvent);
        return mySound;
    }
    
    public static RegistryObject<Item> registerDisc(DiscType discType, String discName, int comparatorValue, int lengthInSeconds) {
        Supplier<SoundEvent> soundSupplier = registerRecordVariant(discName, discType, null);
        RegistryObject<Item> registeredDisc = ExtraDiscs.ITEM_REGISTRY.register((discType.label + "_" + discName), 
            () -> new TypedRecordItem(
                discType,
                comparatorValue, 
                soundSupplier, 
                new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE), 
                (int) (lengthInSeconds * 20)
            )
        );
        ExtraDiscs.itemRegistryList.add(registeredDisc);
        return registeredDisc;
    }

    public static RegistryObject<Item> registerDisc(DiscType discType, String discName, int comparatorValue, int lengthInSeconds, String appender) {
        Supplier<SoundEvent> soundSupplier = registerRecordVariant(discName, discType, appender);
        RegistryObject<Item> registeredDisc = ExtraDiscs.ITEM_REGISTRY.register((discType.label + "_" + discName), 
            () -> new TypedRecordItem(
                discType,
                comparatorValue, 
                soundSupplier, 
                new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE), 
                (int) (lengthInSeconds * 20)
            )
        );
        ExtraDiscs.itemRegistryList.add(registeredDisc);
        return registeredDisc;
    }
}
