package model;

public class Fox extends Animal {
    public static int GainFromFood = 20;
    public static int ReproductionRate = 3;

    public Fox() {
        super(2 * Math.random() * GainFromFood);
    }

    protected Fox(Fox fox) {
        super(fox);
    }

    @Override
    public void feed(Food food) {
        eat(food.get(Hare.class));
    }

    @Override
    public Animal reproduce() {
        return canReproduce(ReproductionRate) ? new Fox(this) : null;
    }

    private void eat(Hare hare) {
        if (hare == null) return;
        hare.die();
        energy += GainFromFood;
    }

}
