package simulation;

import model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Simulation {
    private final Grass[][] grass;
    private final List<Hare> hares = new ArrayList<>();
    private final List<Fox> foxes = new ArrayList<>();

    public static int InitialHares = 2000;
    public static int InitialFoxes = 800;

    public Simulation() {
        grass = new Grass[World.Size][World.Size];
        init();
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

    public int grass() {
        int count = 0;
        for (Grass[] row : grass)
            for (Grass grass : row) if (grass.isGreen()) count++;
        return count;

    }

    public int hares() {
        return hares.size();
    }

    public int foxes() {
        return foxes.size();
    }

    public void step() {
        stepGrass();
        stepAnimals();
    }

    public void killFox() {
        foxes.remove(0);
    }

    public void killHare() {
        hares.remove(0);
    }

    private void add(Animal animal) {
        if (animal == null) return;
        if(animal instanceof Hare)
            hares.add((Hare) animal);
        if(animal instanceof Fox)
            foxes.add((Fox) animal);
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
        hares.forEach(Animal::move);
        foxes.forEach(Animal::move);
    }

    private void reproduceAnimals() {
        for(Hare hare: hares.toArray(new Hare[hares.size()]))
            add(hare.reproduce());
        for(Fox fox: foxes.toArray(new Fox[foxes.size()]))
            add(fox.reproduce());
    }

    private void feedAnimals() {
        hares.forEach(h -> h.feed(grassIn(h.x(), h.y())));
        foxes.forEach(f -> f.feed(haresIn(f.x(), f.y())));
    }

    private void removeDeadAnimals() {
        removeDeadAnimals(hares.iterator());
        removeDeadAnimals(foxes.iterator());
    }

    private void removeDeadAnimals(Iterator<? extends Animal> iterator) {
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            if (animal.isDead()) iterator.remove();
        }
    }

    private Food grassIn(int x, int y) {
        List<Object> objects = new ArrayList<>();
        objects.add(grass[x][y]);
        return new Food(objects);
    }

    private Food haresIn(int x, int y) {
        List<Object> objects = new ArrayList<>();
        hares.stream().filter(h -> h.isIn(x, y)).forEach(objects::add);
        return new Food(objects);
    }

    public void removeGrass(int x, int y) {
        grass[x][y].eaten();
    }
}
