package net.liziseziest.extradiscs;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public enum DiscType {
    OVERWORLD_DISC("overworld_music_disc", "game"),
    CREATIVE_DISC("creative_music_disc", "game/creative"),
    UNDERWATER_DISC("underwater_music_disc", "game/water"),
    UNDERGROUND_DISC("underground_music_disc", "game"),
    DEEP_DARK_DISC("deep_dark_music_disc", "game"),
    NETHER_DISC("nether_music_disc", "game/nether"),
    ALT_NETHER_DISC("alt_nether_music_disc", "game/nether"),
    END_DISC("end_music_disc", "game/end"),
    MENU_DISC("menu_music_disc", "menu");

    public final String label;
    public final String filePath;
    public final TagKey<Item> itemTag;
    private DiscType(String label, String filePath) {
        this.label = label;
        this.filePath = filePath;
        this.itemTag = ItemTags.create(
            new ResourceLocation(ExtraDiscs.MODID, label + "_drop"));
    }
}