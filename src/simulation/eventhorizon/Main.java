package simulation.eventhorizon;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    FinishedMap map = new FinishedMap();
    private ExecutorService executorService = Executors.newFixedThreadPool(6);
    List<Future<Result>> futures = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        new Main().execute();
    }

    private void execute() throws Exception {
        submitAllAnalysis();
        processResults();
        map.print();
        executorService.shutdown();
    }

    private void submitAllAnalysis() {
        for (int eventHorizon = 0; eventHorizon <= 0; eventHorizon+=10)
            for (int repetition = 0; repetition < 63; repetition++)
                futures.add(executorService.submit(new Analysis(eventHorizon, 3.0, 3.0)));
    }
//  200 200 | 300 0
    private void processResults() throws Exception {
        while (!futures.isEmpty()) {
            List<Future> toRemove = new ArrayList<>();
            futures.stream().filter(Future::isDone).forEach(f -> {
                try {
                    toRemove.add(f);
                } catch (Exception ignored) {
                }
            });
            toRemove.forEach(futures::remove);
            Thread.sleep(5000);
        }
    }

    private Boolean isOk(Result result) {
        return result.after().foxesBiomass() > 0 && result.after().grassBiomass() > 0 && result.after().haresBiomass() > 0;
    }

    class FinishedMap {

        private Map<String, Integer> map = new LinkedHashMap<>();

        public void put(String type, Boolean correct) {
            if (!map.containsKey(type)) map.put(type, 0);
            map.put(type, map.get(type) + (correct ? 1 : 0));
        }


        public void print() {
            System.out.println("\t0.0\t0.5\t1.0\t1.5\t2.0\t2.5\t3.0\t3.5\t4.0\t4.5\t5.0");
            for (double percentage : new double[]{0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0}) {
                System.out.print(percentage);
                map.keySet().stream().filter(k -> k.endsWith(percentage + "")).forEach(k -> System.out.print("\t" + map.get(k)));
                System.out.println("");
            }
        }
    }
}