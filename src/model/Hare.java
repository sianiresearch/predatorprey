package model;

public class Hare extends Animal {
    private static final int gainFromFood = 4;
    private static final int reproductionRate = 4;

    public Hare() {
        super(2 * Math.random() * gainFromFood);
    }

    protected Hare(Hare hare) {
        super(hare);
    }

    @Override
    public void feed(Food food) {
        Grass grass = food.get(Grass.class);
        if (!grass.isGreen()) return;
        grass.eaten();
        energy += gainFromFood;
    }

    @Override
    public Animal reproduce() {
        return Math.random() * 100 < reproductionRate ? new Hare(this) : null;
    }
}
