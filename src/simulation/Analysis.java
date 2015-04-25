package simulation;

import model.Fox;
import model.Hare;

public class Analysis implements Runnable {
    private final Simulation simulation;
    private final int eventHorizon;

    public Analysis(Simulation simulation, int eventHorizon) {
        this.simulation = new Simulation(simulation);
        this.eventHorizon = eventHorizon;
    }

    @Override
    public void run() {
        int index = 0;
        while (index++ <= 2000) {
            simulation.step();
            if (index < eventHorizon) continue;
            killExcessPopulation(simulation);
        }
        System.out.println(eventHorizon + "\t" + simulation.grass() + "\t" + simulation.hares() + "\t" + simulation.foxes());
    }

    private void killExcessPopulation(Simulation simulation) {
        for (int i = 0; i < simulation.foxes() / 100; i++)
            simulation.kill(Fox.class);
        for (int i = 0; i < simulation.hares() / 100; i++)
            simulation.kill(Hare.class);
    }

}
