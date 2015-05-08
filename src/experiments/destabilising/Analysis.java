package experiments.destabilising;

import model.Simulation;

public class Analysis implements Runnable {

    public final int Normal = 200;
    public final int Drought = Normal + 500;
    public final int Outbreak = Drought + 1300;

    @Override
    public void run() {
        System.out.println("Time\tGrass\tHares\tFoxes");
        int index = 0;
        Simulation simulation = new Simulation();
        index = beforeDrought(index, simulation);
        //A drought starts
        simulation.world().MaxRegrowthTime = 50;
        index = drought(index, simulation);
        //The drought finishes
        simulation.world().MaxRegrowthTime = 17;
        afterDrought(index, simulation);
    }

    private int beforeDrought(int index, Simulation simulation) {
        while (index++ < Normal) {
            simulation.step();
            System.out.println(index + "\t" + simulation.grass()/4 + "\t" + simulation.hares() + "\t" + simulation.foxes());
        }
        return index;
    }

    private int drought(int index, Simulation simulation) {
        while (index++ < Drought) {
            simulation.step();
            System.out.println(index    + "\t" + simulation.grass()/4 + "\t" + simulation.hares() + "\t" + simulation.foxes());
        }
        return index;
    }

    private void afterDrought(int index, Simulation simulation) {
        while (index++ < Outbreak) {
            simulation.step();
            System.out.println(index + "\t" + simulation.grass()/4 + "\t" + simulation.hares() + "\t" + simulation.foxes());
        }
    }

}
