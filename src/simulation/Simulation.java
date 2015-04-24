package simulation;

import model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Simulation {
    private Grass[][] grass = new Grass[World.Size][World.Size];
    private List<Animal> animals = new ArrayList<>();

    private static int InitialHares = 146;
    private static int InitialFoxes = 106;

    public Simulation() {
        for (int i = 0; i < World.Size; i++)
            for (int j = 0; j < World.Size; j++) grass[i][j] = new Grass();
        for (int i = 0; i < InitialHares; i++) add(new Hare());
        for (int i = 0; i < InitialFoxes; i++) add(new Fox());
    }

    private void add(Animal animal) {
        if (animal == null) return;
        animals.add(animal);
    }

    public void step() {
        stepGrass();
        stepAnimals();
    }

    private void stepAnimals() {
        removeDeadAnimals();
        moveAnimals();
        reproduceAnimals();
        feedAnimals();
    }

    private void reproduceAnimals() {
        for (Animal animal : animals())
            add(animal.reproduce());
    }

    private Animal[] animals() {
        return animals.toArray(new Animal[animals.size()]);
    }

    private void removeDeadAnimals() {
        Iterator<Animal> iterator = animals.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            if (animal.isDead())
                iterator.remove();
        }
    }

    private void feedAnimals() {
        for (Animal animal : animals) {
            animal.feed(food(animal.x(),animal.y()));
        }
    }

    private Food food(int x, int y) {
        return new Food(grass[x][y], animalsIn(x,y));
    }

    private Animal[] animalsIn(int x, int y) {
        List<Animal> animals = new ArrayList<Animal>();
        for (Animal animal : this.animals) {
            if (animal.isIn(x,y)) animals.add(animal);
        }
        return animals.toArray(new Animal[animals.size()]);
    }

    private void moveAnimals() {
        for (Animal animal : animals)
            animal.move();
    }

    private void stepGrass() {
        for (Grass[] row : grass)
            for (Grass grass : row) grass.move();
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

    private int count(Class class_) {
        int count = 0;
        for (Animal animal : animals) if (animal.getClass().equals(class_)) count++;
        return count;
    }
}
