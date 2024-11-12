package net.liziseziest.extradiscs.datagen;

import javax.annotation.Nullable;

import net.liziseziest.extradiscs.DiscType;

public record RecordInfo(
    String name,
    DiscType discType,
    @Nullable String appender
){}