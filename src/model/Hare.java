package model;

import java.util.Random;

public class Hare extends Animal {
    public static int EnergyAsFood = 20;
    public static int ReproductionRate = 4;

    public Hare() {
        super(2 * new Random().nextDouble() * Grass.EnergyAsFood);
    }

    protected Hare(Hare hare) {
        super(hare);
    }

    @Override
    public void feed(Food food) {
        eat(food.get(Grass.class));
    }

    @Override
    public Animal reproduce() {
        return canReproduce(ReproductionRate) ? new Hare(this) : null;
    }

    private void eat(Grass grass) {
        if (!grass.isGreen()) return;
        grass.eaten();
        energy += Grass.EnergyAsFood;
    }

}
