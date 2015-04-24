package model;

public class Fox extends Animal {
    private static final int gainFromFood = 20;
    private static final int reproductionRate = 3;

    public Fox() {
        super(2 * Math.random() * gainFromFood + 10);
    }

    public Fox(Fox fox) {
        super(fox);
    }

    @Override
    public void feed(Food food) {
        Hare hare = food.get(Hare.class);
        if (hare == null) return;
        hare.die();
        energy += gainFromFood;
    }

    @Override
    public Animal reproduce() {
        if (Math.random() * 100 < reproductionRate)
            return new Fox(this);
        else return null;
    }

}
