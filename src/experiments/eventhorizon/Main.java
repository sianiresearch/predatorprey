package experiments.eventhorizon;

public class Main {

    public static void main(String[] args) throws Exception {
        int eventHorizon = 300;
        new Analysis(eventHorizon).run();
    }

}
