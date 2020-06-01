package compnaturaltp1;

public class Parameters {
    
    private int maxTreeSize;
    private int populationSize;
    private int tournamentSize;
    private int generationsNumber;
    private int testQuantity;
    private float probabilityCrossOver;
    private float probabilityMutation;
    private String fileName;
    private boolean logging;

    public void setMaxTreeSize(int maxTreeSize) {
        this.maxTreeSize = maxTreeSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public void setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    public void setGenerationsNumber(int generationsNumber) {
        this.generationsNumber = generationsNumber;
    }

    public void setTestQuantity(int testQuantity) {
        this.testQuantity = testQuantity;
    }

    public void setProbabilityCrossOver(float probabilityCrossOver) {
        this.probabilityCrossOver = probabilityCrossOver;
    }

    public void setProbabilityMutation(float probabilityMutation) {
        this.probabilityMutation = probabilityMutation;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setLogging(boolean Logging) {
        this.logging = Logging;
    }
    
    public int getMaxTreeSize() {
        return maxTreeSize;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public int getGenerationsNumber() {
        return generationsNumber;
    }

    public int getTestQuantity() {
        return testQuantity;
    }

    public float getProbabilityCrossOver() {
        return probabilityCrossOver;
    }

    public float getProbabilityMutation() {
        return probabilityMutation;
    }

    public String getFileName() {
        return fileName;
    }
    
    public boolean getLogging() {
        return logging;
    }
    
}
