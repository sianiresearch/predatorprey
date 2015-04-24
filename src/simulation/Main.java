package simulation;

import model.Grass;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        for (int i = 0; i < 1000; i++) {
            simulation.step();
            System.out.println(simulation.grass()/4 + "\t" + simulation.hares() + "\t" + simulation.foxes());
        }
        System.out.println("------------------");
        //La hierba crece más rápido
        Grass.MaxRegrowthTime = 5;
        for (int i = 0; i < 5000; i++) {
            simulation.step();
            System.out.println(simulation.grass()/4 + "\t" + simulation.hares() + "\t" + simulation.foxes());
        }
    }
}
