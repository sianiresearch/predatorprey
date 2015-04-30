package simulation.hareskiller;

import model.Fox;
import model.Grass;
import model.Hare;
import simulation.Simulation;

import java.util.concurrent.Callable;

public class Analysis implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        Simulation simulation = new Simulation();
        int index = 0;
        while (index++ <= 1000){
            simulation.step();
            System.out.println(index + "\t" + simulation.grass() + "\t" + simulation.hares() + "\t" + simulation.foxes());
        }
        Result.Biomass before = new Result.Biomass(simulation.grass() * Grass.EnergyAsFood,
                simulation.hares() * Hare.EnergyAsFood,
                simulation.foxes() * Fox.EnergyAsFood);
        //New kind of grass appears that grows faster
        //Is this eventHorizon enough for fixing the system?
        Grass.MaxRegrowthTime = 5;
        while (index++ <= 10000) {
            simulation.step();
            System.out.println(index + "\t" + simulation.grass() + "\t" + simulation.hares() + "\t" + simulation.foxes());
            killExcessPopulation(simulation);
        }
        Result.Biomass after = new Result.Biomass(simulation.grass() * Grass.EnergyAsFood,
                simulation.hares() * Hare.EnergyAsFood,
                simulation.foxes() * Fox.EnergyAsFood);
        return true;
    }

    private void killExcessPopulation(Simulation simulation) {
        int haresToKill = simulation.hares() - 1200;
        for (int j = 0; j < haresToKill; j++)
            simulation.killHare();
    }

}
