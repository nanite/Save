package com.sunekaer.mods.save.mixin;

import com.sunekaer.mods.save.BlockEntityInterface;
import com.sunekaer.mods.save.Save;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin implements BlockEntityInterface {

    @Shadow public abstract void saveAdditional(CompoundTag tag);
    @Shadow public abstract void saveMetadata(CompoundTag tag);

    @Override
    public CompoundTag save(CompoundTag tag) {
        var clc = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
        Save.LOGGER.warn(clc.getCanonicalName() + " is using BlockEntity.save directly! This is no longer supported as of Forge 39.0.45, please use BlockEntity.saveAdditional instead!");
        this.saveAdditional(tag);
        this.saveMetadata(tag);
        return tag;
    }
}

