package simulation.minactions;

import model.Animal;
import model.Fox;
import model.Hare;
import simulation.Simulation;

import java.util.Arrays;
import java.util.List;

public class Analysis implements Runnable {
    private final Simulation simulation;
    private final int percentage;
    private final List<Class<? extends Animal>> animals;

    public Analysis(Simulation simulation, double percentage, Class<? extends Animal>... animals) {
        this.simulation = new Simulation(simulation);
        this.percentage = (int) percentage;
        this.animals = Arrays.asList(animals);
    }

    @Override
    public void run() {
        int index = 0;
        while (index++ <= 2000) {
            simulation.step();
            killExcessPopulation();
        }
        String classes = "";
        for (Class<? extends Animal> animal : animals) classes += animal.getName() + " ";
        System.out.println(classes + percentage + "\t" + simulation.grass() + "\t" + simulation.hares() + "\t" + simulation.foxes());
    }

    private void killExcessPopulation() {
        if(animals.contains(Fox.class))
            for (int i = 0; i < simulation.foxes() * percentage / 100; i++)
                simulation.kill(Fox.class);
        if(animals.contains(Hare.class))
            for (int i = 0; i < simulation.hares() * percentage / 100; i++)
                simulation.kill(Hare.class);
    }

}
