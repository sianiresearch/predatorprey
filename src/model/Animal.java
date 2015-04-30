package model;

import java.util.Random;

public abstract class Animal  {
    protected double energy;
    protected Vector vector;

    protected Animal(double energy) {
        this.energy = energy;
        this.vector = Vector.random();
    }

    protected Animal(Animal animal) {
        this.energy = shareEnergy(animal);
        this.vector = new Vector(animal.vector);
        this.vector.forward();
    }

    private double shareEnergy(Animal animal) {
        animal.energy = animal.energy / 2.0;
        return animal.energy;
    }

    public void die() {
        energy = 0;
    }

    public boolean isDead() {
        return energy <= 0;
    }

    public int x() {
        return (int) vector.x;
    }

    public int y() {
        return (int) vector.y;
    }

    public boolean isIn(int x, int y) {
        return x == x() && y == y();
    }

    public void move() {
        vector.forward();
        energy--;
    }

    public abstract void feed(Food food);

    public abstract Animal reproduce();

    protected boolean canReproduce(int reproductionRate) {
        return new Random().nextDouble() * 100 < reproductionRate;
    }


    private static class Vector {
        double angle;
        double x,y;

        Vector(double angle, double x, double y) {
            this.angle = angle;
            this.x = x;
            this.y = y;
        }

        Vector(Vector vector) {
            this.angle = vector.angle + reorient();
            this.x = vector.x;
            this.y = vector.y;
        }

        void forward() {
            this.angle = reorient();
            this.x = bound(x + Math.cos(angle));
            this.y = bound(y + Math.sin(angle));
        }

        private double reorient() {
            return new Random().nextDouble() * 50 - new Random().nextDouble() * 50;
        }

        private double bound(double value) {
            return (value + World.Size) % World.Size;
        }

        static Vector random() {
            return new Vector(new Random().nextDouble() * 360, new Random().nextDouble() * World.Size, new Random().nextDouble() * World.Size);
        }
    }


}
