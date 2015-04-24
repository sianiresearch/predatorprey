package model;

public class Fox extends Animal {
    public static int GainFromFood = 20;
    public static int ReproductionRate = 3;

    public Fox() {
        super(2 * Math.random() * GainFromFood + 10);
    }

    public Fox(Fox fox) {
        super(fox);
    }

    @Override
    public void feed(Food food) {
        Hare hare = food.get(Hare.class);
        if (hare == null) return;
        hare.die();
        energy += GainFromFood;
    }

    @Override
    public Animal reproduce() {
        if (Math.random() * 100 < ReproductionRate)
            return new Fox(this);
        else return null;
    }

}
