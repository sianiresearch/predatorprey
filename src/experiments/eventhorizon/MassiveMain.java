package experiments.eventhorizon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MassiveMain {

    private ExecutorService executorService = Executors.newFixedThreadPool(8);

    public static void main(String[] args) throws Exception {
        new MassiveMain().execute();
    }

    private void execute() throws Exception {
        submitAllAnalysis();
        executorService.shutdown();
    }

    private void submitAllAnalysis() throws Exception {
        for (int repetition = 0; repetition < 2000; repetition++)
            for (int eventHorizon = 0; eventHorizon <= 600; eventHorizon += 10)
                executorService.submit(new NonVerboseAnalysis(eventHorizon));
    }

}
