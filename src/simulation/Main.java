package simulation;

import model.Grass;

import java.io.*;
import java.util.Date;

public class Main {

    private final BufferedWriter writer;

    public Main(BufferedWriter writer) {
        this.writer = writer;
    }

    public static void main(String[] args) throws IOException {
        new Main(createWriter()).execute();
    }

    private void execute() throws IOException {
        Simulation simulation = new Simulation();
        int index = 0;
        write("Index\tGrass\tHares\tFoxes");
        while (index++ <= 1000) {
            write(simulation);
            simulation.step();
        }
        //La hierba crece más rápido
        Grass.MaxRegrowthTime = 5;
        while (index++ <= 5000) {
            write(simulation);
            simulation.step();
        }
        close();

    }

    private void write(Simulation simulation) throws IOException {
        write(simulation.time() + "\t" + simulation.grass() / 4 + "\t" + simulation.hares() + "\t" + simulation.foxes());

    }

    private void write(String line) throws IOException {
        if (writer == null)
            System.out.println(line);
        else
            writer.write(line+ "\n");
    }

    private void close() throws IOException {
        if (writer == null) return;
        writer.close();

    }

    private static BufferedWriter createWriter() throws IOException {
        return new BufferedWriter(new FileWriter(mkdir(filename())));
        //return null;
    }

    private static String mkdir(String filename) {
        new File(filename).getParentFile().mkdirs();
        return filename;
    }

    private static String filename() {
        return "data/" + new Date().getTime() + ".tsv";
    }
}
