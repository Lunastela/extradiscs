package net.liziseziest.extradiscs.mixin;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.blaze3d.audio.OggAudioStream;

import net.liziseziest.extradiscs.ExtraDiscs;
import net.liziseziest.extradiscs.MonoWrapper;

import net.minecraft.Util;
import net.minecraft.client.sounds.AudioStream;
import net.minecraft.client.sounds.LoopingAudioStream;
import net.minecraft.client.sounds.SoundBufferLibrary;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceProvider;

@Mixin(SoundBufferLibrary.class)
public class SoundBufferLibraryMixin {
    
    @Final
    @Shadow
    private ResourceProvider resourceManager;

    @Inject(
        method = "getStream(Lnet/minecraft/resources/ResourceLocation;Z)Ljava/util/concurrent/CompletableFuture;", 
        at = @At(value = "RETURN"),
        cancellable = true
    )
    private void returnMonoAudioStream(ResourceLocation resourceLocation, boolean isWrapper, CallbackInfoReturnable<CompletableFuture<AudioStream>> cir) {
        if (ExtraDiscs.OverrideMonoMusic) {
            ExtraDiscs.OverrideMonoMusic = false;
            cir.setReturnValue(CompletableFuture.supplyAsync(() -> {
                try {
                    InputStream inputStream = this.resourceManager.open(resourceLocation);
                    return (AudioStream) (new MonoWrapper(new OggAudioStream(inputStream)));
                } catch (IOException ioException) {
                    throw new CompletionException(ioException);
                }
            }, Util.backgroundExecutor()));
        }
    }
}
