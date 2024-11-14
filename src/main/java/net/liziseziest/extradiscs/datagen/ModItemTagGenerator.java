package net.liziseziest.extradiscs.datagen;

import net.liziseziest.extradiscs.ExtraDiscs;
import net.liziseziest.extradiscs.TypedRecordItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.Items;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
        CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, ExtraDiscs.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider pProvider) {
        for (int i = 0; i < ExtraDiscs.itemRegistryList.size(); i++) {
            RegistryObject<Item> myRecord = ExtraDiscs.itemRegistryList.get(i);
            this.tag(ItemTags.MUSIC_DISCS).add(myRecord.get());
            if (myRecord.get() instanceof TypedRecordItem myTypedRecord)
                this.tag(myTypedRecord.getDiscType().itemTag).add(myRecord.get());
        }
        this.tag(ItemTags.create(new ResourceLocation(ExtraDiscs.MODID, "music_disc_11_drop"))).add(Items.MUSIC_DISC_11);
    }
}