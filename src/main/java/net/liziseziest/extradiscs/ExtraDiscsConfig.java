package net.liziseziest.extradiscs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = ExtraDiscs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExtraDiscsConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue DISC_11_DROP = BUILDER
        .comment("Whether players drop Disc 11 when dying to a Skeleton")
        .define("discElevenDrop", true);

    private static final ForgeConfigSpec.DoubleValue DISC_11_DROP_CHANCE = BUILDER
        .comment("The percentage chance for Disc 11 to drop when being killed by a Skeleton")
        .defineInRange("discElevenDropRate", 0.05, 0, 1);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean discElevenDrop = true;
    public static double discElevenDropRate = 0.05;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        discElevenDrop = DISC_11_DROP.get();
        discElevenDropRate = DISC_11_DROP_CHANCE.get();
    }
}
