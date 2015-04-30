package simulation.minactions;

import model.Fox;
import model.Grass;
import model.Hare;
import simulation.Simulation;

import java.util.concurrent.Callable;

public class Analysis implements Callable<Result> {
    private final double haresPercentage;
    private final double foxesPercentage;

    public Analysis(double haresPercentage, double foxesPercentage) {
        this.haresPercentage = haresPercentage;
        this.foxesPercentage = foxesPercentage;
    }

    @Override
    public Result call() throws Exception {
        Simulation simulation = new Simulation();
        int index = 0;
        while (index++ <= 1000) simulation.step();
        //New kind of grass appears that grows faster
        //What is the minimum actions to keep the stability?
        Grass.MaxRegrowthTime = 5;
        while (index++ <= 3000) {
            simulation.step();
            killExcessPopulation(simulation);
        }
        return new Result(haresPercentage + " - " + foxesPercentage,
                simulation.grass() * Grass.EnergyAsFood,
                simulation.hares() * Hare.EnergyAsFood,
                simulation.foxes() * Fox.EnergyAsFood);
    }

    private void killExcessPopulation(Simulation simulation) {
        int haresToKill = (int) (simulation.hares() * haresPercentage / 100);
        for (int j = 0; j < haresToKill; j++)
                simulation.killHare();
        int foxesToKill = (int) (simulation.foxes() * foxesPercentage / 100);
        for (int j = 0; j < foxesToKill; j++)
                simulation.killFox();
    }

}
