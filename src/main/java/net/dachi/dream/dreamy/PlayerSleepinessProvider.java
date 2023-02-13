package net.dachi.dream.dreamy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerSleepinessProvider  {

    //public static Capability<PlayerSleepiness> PLAYER_SLEEPINESS = null;
    //implements ICapabilityProvider, INBTSerializable<CompoundNBT>
//
    //private PlayerSleepiness sleepiness = null;
    //private final LazyOptional<PlayerSleepiness> optional = LazyOptional.of(this::createPlayerSleepiness);
//
    //private PlayerSleepiness createPlayerSleepiness() {
//
    //    if(this.sleepiness == null) {
    //        this.sleepiness = new PlayerSleepiness();
    //    }
//
    //    return this.sleepiness;
//
    //}
    //
//
//
    //@Nonnull
    //@Override
    //public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
    //    if(cap == PLAYER_SLEEPINESS) {
    //        return optional.cast();
    //    }
//
    //    return LazyOptional.empty();
    //}
//
    //@Override
    //public CompoundNBT serializeNBT() {
    //    CompoundNBT nbt = new CompoundNBT();
    //    createPlayerSleepiness().saveNBTData(nbt);
    //    return nbt;
    //}
//
    //@Override
    //public void deserializeNBT(CompoundNBT nbt) {
    //    createPlayerSleepiness().loadNBTData(nbt);
    //}


}
