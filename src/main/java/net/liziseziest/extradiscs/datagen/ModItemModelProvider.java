package net.liziseziest.extradiscs.datagen;


import net.liziseziest.extradiscs.ExtraDiscs;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExtraDiscs.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (int i = 0; i < ExtraDiscs.itemRegistryList.size(); i++)
            simpleItem(ExtraDiscs.itemRegistryList.get(i));
    }
    
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
            .texture("layer0", new ResourceLocation(ExtraDiscs.MODID, "item/" + item.getId().getPath()));
    }
}
