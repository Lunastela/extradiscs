package net.liziseziest.extradiscs;

import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;

import org.spongepowered.asm.mixin.Shadow;

import com.mojang.blaze3d.audio.OggAudioStream;

public class MonoAudioStream extends OggAudioStream {
    public MonoAudioStream(InputStream pInput) throws IOException {
        super(pInput);
    }


}
