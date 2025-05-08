package net.liziseziest.extradiscs.mixin;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.liziseziest.extradiscs.ExtraDiscs;
import net.liziseziest.extradiscs.TypedRecordItem;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.RecordItem;

@Mixin(LevelRenderer.class)
public class LevelRendererMusicMixin {
    @Inject(
		method = "playStreamingMusic(Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/RecordItem;)V",
		remap = false,
		at = @At(value = "HEAD")
	)
	public void playStreamTypedRecord(SoundEvent soundIn, BlockPos pos, RecordItem musicDiscItem, CallbackInfo info) {
		if (musicDiscItem != null && musicDiscItem instanceof TypedRecordItem typedRecord)
            ExtraDiscs.OverrideMonoMusic = true;
	}
}