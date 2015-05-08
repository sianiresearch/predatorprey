package model;

import java.util.Random;

public class Grass  {
    public static int EnergyAsFood = 4;

    private final World world;
    private int regrowthTime;
    private boolean isGreen;

    public Grass(World world) {
        this.world = world;
        this.regrowthTime = (int) (new Random().nextDouble() * world.MaxRegrowthTime);
        this.isGreen = new Random().nextDouble() > 0.5;
    }

    public Grass(World world, Grass grass) {
        this.world = world;
        this.regrowthTime = grass.regrowthTime;
        this.isGreen = grass.isGreen;
    }

    public boolean isGreen() {
        return isGreen;
    }

    public void step() {
        if (isGreen || isGrowing()) return;
        isGreen = true;
        regrowthTime = world.MaxRegrowthTime;
    }

    private boolean isGrowing() {
        return --regrowthTime > 0;
    }

    public void eaten() {
        isGreen = false;
    }

    public void grow(){
        isGreen = true;
    }
}
