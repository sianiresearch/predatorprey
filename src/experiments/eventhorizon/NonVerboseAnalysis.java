package experiments.eventhorizon;

import model.Simulation;

import java.util.Random;

public class NonVerboseAnalysis implements Runnable {

    public final int Normal = 200;
    public final int Drought = Normal + 500;
    public final int Outbreak = Drought + 1500;
    private final int eventHorizon;

    public NonVerboseAnalysis(int eventHorizon) {
        this.eventHorizon = eventHorizon;
    }

    @Override
    public void run() {
        int index = 0;
        Simulation simulation = new Simulation();
        index = beforeDrought(index, simulation);
        //A drought starts
        simulation.world().MaxRegrowthTime = 50;
        index = drought(index, simulation);
        //The drought finishes
        simulation.world().MaxRegrowthTime = 17;
        afterDrought(index, simulation);
        System.out.println(eventHorizon + "\t" + simulation.grass() + "\t" + simulation.hares() + "\t" + simulation.foxes());
    }

    private int beforeDrought(int index, Simulation simulation) {
        while (index++ < Normal) simulation.step();
        return index;
    }

    private int drought(int index, Simulation simulation) {
        int horizon = eventHorizon;
        while (index++ < Drought) {
            simulation.step();
            horizon--;
            if (simulation.hares() < 1800 & horizon < 0) growGrass(simulation);
        }
        return index;
    }

    private void afterDrought(int index, Simulation simulation) {
        while (index++ < Outbreak) simulation.step();
    }

    private void growGrass(Simulation simulation) {
        for (int i = 0; i < simulation.world().Size * simulation.world().Size * 3 / 100; i++)
            while (!simulation.growGrass(random(simulation.world().Size), random(simulation.world().Size)));
    }

    private int random(int size) {
        return new Random().nextInt(size);
    }
}
