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
        Grass grass = food.get(Grass.class);
        if (!grass.isGreen()) return;
        grass.eaten();
        energy += GainFromFood;
    }

    @Override
    public Animal reproduce() {
        return Math.random() * 100 < ReproductionRate ? new Hare(this) : null;
    }
}
