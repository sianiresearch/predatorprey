package model;

import java.util.Random;

public class Grass  {
    public static int EnergyAsFood = 4;
    public static int MaxRegrowthTime = 17;
    private int regrowthTime;
    private boolean isGreen;

    public Grass() {
        this.regrowthTime = (int) (new Random().nextDouble() * MaxRegrowthTime);
        this.isGreen = new Random().nextDouble() > 0.5;
    }

    public boolean isGreen() {
        return isGreen;
    }

    public void step() {
        if (isGreen || isGrowing()) return;
        isGreen = true;
        regrowthTime = MaxRegrowthTime;
    }

    private boolean isGrowing() {
        return --regrowthTime > 0;
    }

    public void eaten() {
        isGreen = false;
    }
}
