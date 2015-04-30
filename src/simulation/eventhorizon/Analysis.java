package simulation.eventhorizon;

import model.Fox;
import model.Grass;
import model.Hare;
import model.World;
import simulation.Simulation;

import java.util.Random;
import java.util.concurrent.Callable;

public class Analysis implements Callable<Result> {

    public static final int First = 1000;
    public static final int Second = 10000;
    private final int eventHorizon;
    private int landSize;
    private double haresPercentage;
    private double foxesPercentage;

    public Analysis(int eventHorizon, int landSize) {
        this.eventHorizon = eventHorizon;
        this.landSize = landSize;
    }

    public Analysis(int eventHorizon, double haresPercentage, double foxesPercentage) {
        this.eventHorizon = eventHorizon;
        this.haresPercentage = haresPercentage;
        this.foxesPercentage = foxesPercentage;
    }

    @Override
    public Result call() throws Exception {
        Simulation simulation = new Simulation();
        int index = 0;
        while (index++ <= First){
            simulation.step();
//            System.out.println(index + "\t" + simulation.grass() + "\t" + simulation.hares() + "\t" + simulation.foxes());
        }
        Result.Biomass before = new Result.Biomass(simulation.grass() * Grass.EnergyAsFood,
                simulation.hares() * Hare.EnergyAsFood,
                simulation.foxes() * Fox.EnergyAsFood);
        //New kind of grass appears that grows faster
        //Is this eventHorizon enough for fixing the system?
        Grass.MaxRegrowthTime = 2;
        while (index++ <= Second) {
            simulation.step();
//            if (index < eventHorizon + First) continue;
            killExcessPopulation(simulation);
//            removeGrass(simulation);
        }
        System.out.println(index    + "\t" + simulation.grass() + "\t" + simulation.hares() + "\t" + simulation.foxes());
        Result.Biomass after = new Result.Biomass(simulation.grass() * Grass.EnergyAsFood,
                simulation.hares() * Hare.EnergyAsFood,
                simulation.foxes() * Fox.EnergyAsFood);
        return new Result(haresPercentage + " - " + foxesPercentage, eventHorizon, before, after);
    }

    private void removeGrass(Simulation simulation) {
        int initialX = getInitialPoint(), initialY = getInitialPoint();
        for (int x = initialX; x < landSize; x++)
            for (int y = initialY; y < landSize; y++) simulation.removeGrass(x, y);
    }

    public int getInitialPoint() {
        int random = new Random().nextInt(World.Size);
        while(random + landSize > World.Size)
            random = new Random().nextInt(World.Size);
        return random;
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
