package net.liziseziest.extradiscs.datagen;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import net.liziseziest.extradiscs.ExtraDiscs;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ExtraDiscs.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider pProvider) {}
}