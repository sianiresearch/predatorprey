package model;

public class Grass extends Entity {
    public static int MaxRegrowthTime = 17;
    private int regrowthTime;
    private boolean isGreen;

    public Grass() {
        this.regrowthTime = (int) (Math.random()* MaxRegrowthTime);
        this.isGreen = Math.random() > 0.5;
    }

    public boolean isGreen() {
        return isGreen;
    }

    @Override
    public void move() {
        if (isGreen) return;
        if (--regrowthTime > 0) return;
        isGreen = true;
        regrowthTime = MaxRegrowthTime;
    }

    public void eaten() {
        isGreen = false;
    }
}
