package experiments.stable;

import model.Simulation;

public class Analysis implements Runnable {

    public final int Time = 2000;

    @Override
    public void run() {
        System.out.println("Time\tGrass\tHares\tFoxes");
        int index = 0;
        Simulation simulation = new Simulation();
        while (index++ < Time) {
            simulation.step();
            System.out.println(index + "\t" + simulation.grass()/4 + "\t" + simulation.hares() + "\t" + simulation.foxes());
        }
    }

}
