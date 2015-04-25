package simulation;

import model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Simulation {
    private final Grass[][] grass;
    private final List<Animal> animals = new ArrayList<>();

    public static int InitialHares = 146;
    public static int InitialFoxes = 106;
    private int time = 0;

    public Simulation() {
        grass = new Grass[World.Size][World.Size];
        init();
    }

    public Simulation(Simulation simulation) {
        grass = simulation.grass.clone();
        animals.addAll(simulation.animals);
    }

    private void init() {
        initGrass();
        initAnimals();
    }

    private void initGrass() {
        for (int i = 0; i < World.Size; i++)
            for (int j = 0; j < World.Size; j++) grass[i][j] = new Grass();
    }

    private void initAnimals() {
        for (int i = 0; i < InitialHares; i++) add(new Hare());
        for (int i = 0; i < InitialFoxes; i++) add(new Fox());
    }

    public int time() {
        return time;
    }

    public int grass() {
        int count = 0;
        for (Grass[] row : grass)
            for (Grass grass : row) if (grass.isGreen()) count++;
        return count;

    }

    public int hares() {
        return count(Hare.class);
    }

    public int foxes() {
        return count(Fox.class);
    }

    public void step() {
        stepTime();
        stepGrass();
        stepAnimals();
    }

    public void kill(Class class_) {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getClass() != class_) continue;
            animals.remove(i);
            break;
        }
    }

    private void stepTime() {
        time++;
    }

    private void add(Animal animal) {
        if (animal == null) return;
        animals.add(animal);
    }

    private void stepGrass() {
        for (Grass[] row : grass)
            for (Grass grass : row) grass.step();
    }

    private void stepAnimals() {
        moveAnimals();
        reproduceAnimals();
        feedAnimals();
        removeDeadAnimals();
    }

    private void moveAnimals() {
        for (Animal animal : animals)
            animal.move();
    }

    private void reproduceAnimals() {
        for (Animal animal : animals())
            add(animal.reproduce());
    }

    private void feedAnimals() {
        for (Animal animal : animals)
            animal.feed(foodIn(animal.x(), animal.y()));
    }

    private void removeDeadAnimals() {
        Iterator<Animal> iterator = animals.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            if (animal.isDead()) iterator.remove();
        }
    }

    private Food foodIn(int x, int y) {
        List<Object> objects = new ArrayList<>();
        objects.add(grass[x][y]);
        for (Animal animal : animals)
            if (animal.isIn(x, y)) objects.add(animal);
        return new Food(objects);
    }

    private Animal[] animals() {
        return animals.toArray(new Animal[animals.size()]);
    }

    private int count(Class class_) {
        int count = 0;
        for (Animal animal : animals)
            if (animal.getClass().equals(class_)) count++;
        return count;
    }
}
