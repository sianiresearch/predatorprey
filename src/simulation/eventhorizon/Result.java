package simulation.eventhorizon;

public class Result {

    private final String type;
    private final int eventHorizon;
    private final Biomass before;
    private final Biomass after;

    public Result(String type, int eventHorizon, Biomass before, Biomass after) {
        this.type = type;
        this.eventHorizon = eventHorizon;
        this.before = before;
        this.after = after;
    }

    public String type() {
        return type;
    }

    public int eventHorizon() {
        return eventHorizon;
    }

    public Biomass before() {
        return before;
    }

    public Biomass after() {
        return after;
    }

    @Override
    public String toString() {
        return type + '\t' + eventHorizon + "\t" + before + " | " + after;
    }

    public static class Biomass{
        private final int grassBiomass;
        private final int haresBiomass;
        private final int foxesBiomass;

        public Biomass(int grassBiomass, int haresBiomass, int foxesBiomass) {
            this.grassBiomass = grassBiomass;
            this.haresBiomass = haresBiomass;
            this.foxesBiomass = foxesBiomass;
        }

        public int grassBiomass() {
            return grassBiomass;
        }

        public int haresBiomass() {
            return haresBiomass;
        }

        public int foxesBiomass() {
            return foxesBiomass;
        }

        @Override
        public String toString() {
            return grassBiomass + "\t" + haresBiomass + "\t" + foxesBiomass;
        }
    }
}
