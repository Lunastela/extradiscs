package net.liziseziest.extradiscs;

import com.mojang.logging.LogUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import org.slf4j.Logger;

@Mod(ExtraDiscs.MODID)
public class ExtraDiscs {
    public static final String MODID = "extradiscs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<SoundEvent> SOUND_REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);

    public static List<RegistryObject<Item>> itemRegistryList = new ArrayList<>();

    /*
     * Disc Collection
     */
    public static final RegistryObject<Item> MINECRAFT = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "calm1", 0, 254);
    public static final RegistryObject<Item> CLARK = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "calm2", 1, 191);
    public static final RegistryObject<Item> SWEDEN = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "calm3", 2, 215);

    public static final RegistryObject<Item> BIOME_FEST = TypedRecordItem.registerDisc(DiscType.CREATIVE_DISC, "creative1", 0, 377);
    public static final RegistryObject<Item> BLIND_SPOTS = TypedRecordItem.registerDisc(DiscType.CREATIVE_DISC, "creative2", 1, 331);
    public static final RegistryObject<Item> HAUNT_MUSKIE = TypedRecordItem.registerDisc(DiscType.CREATIVE_DISC, "creative3", 2, 360);
    public static final RegistryObject<Item> ARIA_MATH = TypedRecordItem.registerDisc(DiscType.CREATIVE_DISC, "creative4", 3, 309);
    public static final RegistryObject<Item> DREITON = TypedRecordItem.registerDisc(DiscType.CREATIVE_DISC, "creative5", 4, 496);
    public static final RegistryObject<Item> TASWELL = TypedRecordItem.registerDisc(DiscType.CREATIVE_DISC, "creative6", 5, 514);

    public static final RegistryObject<Item> SUBWOOFER_LULLABY = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "hal1", 0, 208);
    public static final RegistryObject<Item> LIVING_MICE = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "hal2", 1, 177);
    public static final RegistryObject<Item> HAGGSTROM = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "hal3", 2, 204);
    public static final RegistryObject<Item> DANNY = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "hal4", 3, 254);

    public static final RegistryObject<Item> KEY = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "nuance1", 0, 65);
    public static final RegistryObject<Item> OXYGENE = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "nuance2", 1, 65);

    public static final RegistryObject<Item> DRY_HANDS = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "piano1", 0, 68);
    public static final RegistryObject<Item> WET_HANDS = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "piano2", 1, 90);
    public static final RegistryObject<Item> MICE_ON_VENUS = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "piano3", 2, 281);

    public static final RegistryObject<Item> AERIE = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "aerie", 0, 296, "swamp");
    public static final RegistryObject<Item> ANCESTRY = TypedRecordItem.registerDisc(DiscType.DEEP_DARK_DISC, "ancestry", 1, 343);
    // public static final RegistryObject<Item> DEEPER = TypedRecordItem.registerDisc(DiscType.DEEP_DARK_DISC, "deeper", 7, 303);
    public static final RegistryObject<Item> A_FAMILIAR_ROOM = TypedRecordItem.registerDisc(DiscType.UNDERGROUND_DISC, "a_familiar_room", 2, 241);
    public static final RegistryObject<Item> AN_ORDINARY_DAY = TypedRecordItem.registerDisc(DiscType.UNDERGROUND_DISC, "an_ordinary_day", 3, 331);

    public static final RegistryObject<Item> BROMELIAD = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "bromeliad", 4, 312);
    public static final RegistryObject<Item> COMFORTING_MELODIES = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "comforting_memories", 5, 275);
    public static final RegistryObject<Item> CRESCENT_DUNES = TypedRecordItem.registerDisc(DiscType.OVERWORLD_DISC, "crescent_dunes", 6, 248);

    public static final RegistryObject<Item> ECHO_IN_THE_WIND = TypedRecordItem.registerDisc(DiscType.UNDERGROUND_DISC, "echo_in_the_wind", 7, 296);
    public static final RegistryObject<Item> FIREBUGS = TypedRecordItem.registerDisc(DiscType.UNDERGROUND_DISC, "firebugs", 8, 312, "swamp");
    public static final RegistryObject<Item> FLOATING_DREAM = TypedRecordItem.registerDisc(DiscType.UNDERGROUND_DISC, "floating_dream", 0, 206);
    public static final RegistryObject<Item> INFINITE_AMETHYST = TypedRecordItem.registerDisc(DiscType.UNDERGROUND_DISC, "infinite_amethyst", 1, 271); // Peak
    public static final RegistryObject<Item> LABYRINTHINE = TypedRecordItem.registerDisc(DiscType.UNDERGROUND_DISC, "labyrinthine", 2, 324, "swamp");
    public static final RegistryObject<Item> LEFT_TO_BLOOM = TypedRecordItem.registerDisc(DiscType.UNDERGROUND_DISC, "left_to_bloom", 3, 342);
    public static final RegistryObject<Item> ONE_MORE_DAY = TypedRecordItem.registerDisc(DiscType.UNDERGROUND_DISC, "one_more_day", 4, 278);
    public static final RegistryObject<Item> STAND_TALL = TypedRecordItem.registerDisc(DiscType.UNDERGROUND_DISC, "stand_tall", 5, 308);
    public static final RegistryObject<Item> WENDING = TypedRecordItem.registerDisc(DiscType.UNDERGROUND_DISC, "wending", 6, 314);

    public static final RegistryObject<Item> AXOLOTL = TypedRecordItem.registerDisc(DiscType.UNDERWATER_DISC, "axolotl", 0, 303);
    public static final RegistryObject<Item> DRAGON_FISH = TypedRecordItem.registerDisc(DiscType.UNDERWATER_DISC, "dragon_fish", 1, 373);
    public static final RegistryObject<Item> SHUNIJI = TypedRecordItem.registerDisc(DiscType.UNDERWATER_DISC, "shuniji", 2, 244);

    public static final RegistryObject<Item> CONCRETE_HALLS = TypedRecordItem.registerDisc(DiscType.NETHER_DISC, "nether1", 0, 253);
    public static final RegistryObject<Item> DEAD_VOXEL = TypedRecordItem.registerDisc(DiscType.NETHER_DISC, "nether2", 1, 295);
    public static final RegistryObject<Item> WARMTH = TypedRecordItem.registerDisc(DiscType.NETHER_DISC, "nether3", 2, 238);
    public static final RegistryObject<Item> BALLAD_OF_THE_CATS = TypedRecordItem.registerDisc(DiscType.NETHER_DISC, "nether4", 3, 274);

    public static final RegistryObject<Item> CHRYSOPOEIA = TypedRecordItem.registerDisc(DiscType.ALT_NETHER_DISC, "chrysopoeia", 4, 243, "crimson_forest");
    public static final RegistryObject<Item> RUBEDO = TypedRecordItem.registerDisc(DiscType.ALT_NETHER_DISC, "rubedo", 5, 312, "nether_wastes");
    public static final RegistryObject<Item> SO_BELOW = TypedRecordItem.registerDisc(DiscType.ALT_NETHER_DISC, "so_below", 6, 319, "soulsand_valley");

    public static final RegistryObject<Item> THE_END = TypedRecordItem.registerDisc(DiscType.END_DISC, "end", 0, 903);
    public static final RegistryObject<Item> ALPHA = TypedRecordItem.registerDisc(DiscType.END_DISC, "credits", 1, 602);

    public static final RegistryObject<Item> MUTATION = TypedRecordItem.registerDisc(DiscType.MENU_DISC, "menu1", 0, 184);
    public static final RegistryObject<Item> MOOG_CITY_2 = TypedRecordItem.registerDisc(DiscType.MENU_DISC, "menu2", 1, 179);
    public static final RegistryObject<Item> BEGINNING_2 = TypedRecordItem.registerDisc(DiscType.MENU_DISC, "menu3", 2, 175);
    public static final RegistryObject<Item> FLOATING_TREES = TypedRecordItem.registerDisc(DiscType.MENU_DISC, "menu4", 3, 244);

    public ExtraDiscs() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEM_REGISTRY.register(modEventBus);
        SOUND_REGISTRY.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ExtraDiscsConfig.SPEC);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            for (int i = 0; i < itemRegistryList.size(); i++)
                event.accept(itemRegistryList.get(i));
        }
    }

    Map<String, DiscType[]> mobDropMap = new HashMap<String, DiscType[]>() {{
            put("entities/zombie", new DiscType[] {DiscType.OVERWORLD_DISC});
            put("entities/iron_golem", new DiscType[] {DiscType.CREATIVE_DISC});
            put("entities/silverfish", new DiscType[] {DiscType.UNDERGROUND_DISC});
            put("entities/guardian", new DiscType[] {DiscType.UNDERWATER_DISC});
            put("entities/ghast", new DiscType[] {DiscType.NETHER_DISC});
            put("entities/piglin_brute", new DiscType[] {DiscType.ALT_NETHER_DISC});
            put("entities/shulker", new DiscType[] {DiscType.END_DISC});
            put("entities/enderman", new DiscType[] {DiscType.MENU_DISC}); 
            put("entities/warden", new DiscType[] {DiscType.DEEP_DARK_DISC});
        }
    };

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        String mobKey = event.getName().getPath();
        if (mobDropMap.containsKey(mobKey)) {
            LootPool.Builder poolExtension = LootPool.lootPool().setRolls(ConstantValue.exactly(1));
            DiscType[] discTypeList = mobDropMap.get(mobKey);
            for (DiscType currentType : discTypeList)
                poolExtension.add(TagEntry.expandTag(currentType.itemTag));
            poolExtension.when(LootItemEntityPropertyCondition.hasProperties(
                    LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(EntityType.SKELETON)
                ));
            event.getTable().addPool(poolExtension.build());
        }

        // 11 Special Rare Drop
        if (ExtraDiscsConfig.discElevenDrop && mobKey.matches("entities/player")) {
            LootPool.Builder poolExtension = LootPool.lootPool().setRolls(ConstantValue.exactly(1));
            poolExtension.add(TagEntry.expandTag(ItemTags.create(new ResourceLocation(ExtraDiscs.MODID, "music_disc_11_drop"))))
                .when(LootItemEntityPropertyCondition.hasProperties(
                    LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(EntityType.SKELETON)
                ))
                .when(LootItemRandomChanceCondition.randomChance((float) ExtraDiscsConfig.discElevenDropRate));
            event.getTable().addPool(poolExtension.build());
        }
    }
}
