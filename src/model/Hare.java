package model;

import java.util.Random;

public class Hare extends Animal {
    public static int EnergyAsFood = 20;
    public static int ReproductionRate = 4;

    public Hare(World world) {
        super(world, 2 * new Random().nextDouble() * Grass.EnergyAsFood);
    }

    public Hare(World world, Hare hare) {
        super(world, hare);
    }

    @Override
    public void feed(Food food) {
        eat(food.get(Grass.class));
    }

    @Override
    public Animal reproduce() {
        return canReproduce(ReproductionRate) ? new Hare(this.world, this) : null;
    }

    private void eat(Grass grass) {
        if (!grass.isGreen()) return;
        grass.eaten();
        energy += Grass.EnergyAsFood;
    }

}
