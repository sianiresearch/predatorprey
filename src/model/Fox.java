package model;

public class Fox extends Animal {
    public static int ReproductionRate = 3;

    public Fox(World world) {
        super(world, 2 * Math.random() * Hare.EnergyAsFood);
    }

    public Fox(World world, Fox fox) {
        super(world, fox);
    }

    @Override
    public void feed(Food food) {
        eat(food.get(Hare.class));
    }

    @Override
    public Animal reproduce() {
        return canReproduce(ReproductionRate) ? new Fox(this.world, this) : null;
    }

    private void eat(Hare hare) {
        if (hare == null) return;
        hare.die();
        energy += Hare.EnergyAsFood;
    }

}
