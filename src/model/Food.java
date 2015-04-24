package model;

import java.util.ArrayList;
import java.util.List;

public class Food {

    private List<Object> objects = new ArrayList<>();

    public Food(Grass grass, Animal[] animals) {
        objects.add(grass);
        for (Animal animal : animals) objects.add(animal);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> grassClass) {
        for (Object object : objects) {
            if (object.getClass() == grassClass) return (T) object;
        }
        return null;
    }
}
