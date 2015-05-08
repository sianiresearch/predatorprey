package model;

import java.util.List;

public class Food {

    private final List<Object> objects;

    public Food(List<Object> objects) {
        this.objects = objects;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> foodClass) {
        for (Object object : objects) {
            if (object.getClass() == foodClass) return (T) object;
        }
        return null;
    }
}
