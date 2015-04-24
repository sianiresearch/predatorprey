package model;

public class Hare extends Animal {
    public static int GainFromFood = 4;
    public static int ReproductionRate = 4;

    public Hare() {
        super(2 * Math.random() * GainFromFood);
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
        energy += GainFromFood;
    }

}
