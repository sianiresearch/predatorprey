package simulation.minactions;

public class Result {

    private final String type;
    private final int grassBiomass;
    private final int haresBiomass;
    private final int foxesBiomass;

    public Result(String type, int grassBiomass, int haresBiomass, int foxesBiomass) {
        this.type = type;
        this.grassBiomass = grassBiomass;
        this.haresBiomass = haresBiomass;
        this.foxesBiomass = foxesBiomass;
    }

    public String getType() {
        return type;
    }

    public int getGrassBiomass() {
        return grassBiomass;
    }

    public int getHaresBiomass() {
        return haresBiomass;
    }

    public int getFoxesBiomass() {
        return foxesBiomass;
    }
}
