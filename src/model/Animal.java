package model;

public abstract class Animal extends Entity {
    protected Point point;
    protected double angle;
    protected double energy;

    protected Animal(double energy) {
        this.energy = energy;
        this.point = Point.random();
    }

    protected Animal(Animal animal) {
        animal.energy = animal.energy / 2.0;
        this.energy = animal.energy;
        this.angle = Math.random() * 360;
        this.point = new Point(animal.point);
        this.point.forward(angle);
        this.point.forward(angle);
    }

    public void die() {
        energy = 0;
    }

    public boolean isDead() {
        return energy <= 0;
    }

    public int x() {
        return (int) point.x;
    }

    public int y() {
        return (int) point.y;
    }

    public boolean isIn(int x, int y) {
        return x == x() && y == y();
    }

    @Override
    public void move() {
        angle += reorient();
        point.forward(angle);
        energy--;
        if (energy < 0)
            die();
    }

    private double reorient() {
        return Math.random() * 50 - Math.random() * 50;
    }

    public abstract void feed(Food food);

    public abstract Animal reproduce();

    private static class Point {
        double x,y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Point(Point point) {
            this.x = point.x;
            this.y = point.y;
        }

        void forward(double angle) {
            this.x = bound(x + Math.cos(angle));
            this.y = bound(y + Math.sin(angle));
        }

        private double bound(double value) {
            return (value + World.Size) % World.Size;
        }

        static Point random() {
            return new Point(Math.random()* World.Size, Math.random()* World.Size);
        }
    }


}
