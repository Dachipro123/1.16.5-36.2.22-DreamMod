package net.dachi.dream.dreamy;

import net.minecraft.nbt.CompoundNBT;

public class PlayerSleepiness {
    private int sleepiness;
    private final int MIN_SLEEPINESS = 0;
    private final int MAX_SLEEPINESS = 10;

    public int getThirst() {
        return sleepiness;
    }

    public void addSleepiness(int add) {
        this.sleepiness = Math.min(sleepiness + add, MAX_SLEEPINESS);
    }

    public void subSleepiness(int sub) {
        this.sleepiness = Math.max(sleepiness - sub, MIN_SLEEPINESS);
    }

    public void copyFrom(PlayerSleepiness source) {
        this.sleepiness = source.sleepiness;
    }

    public void saveNBTData(CompoundNBT nbt) {
        nbt.putInt("sleepiness", sleepiness);
    }

    public void loadNBTData(CompoundNBT nbt) {
        sleepiness = nbt.getInt("sleepiness");
    }
}
