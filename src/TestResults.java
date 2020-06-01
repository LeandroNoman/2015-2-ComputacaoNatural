package compnaturaltp1;

public class TestResults {
    private double bestFitness[];
    private double averageFitness[];
    private double worstFitness[];
    private int bestCrossOvers[];
    private int numCrossOvers[];
    private int bestMutations[];
    private int numMutations[];
    private int generations;
    
    public TestResults(int numGenerations) {
        generations = numGenerations;
        bestFitness = new double[numGenerations];
        averageFitness = new double[numGenerations];
        worstFitness = new double[numGenerations];
        bestCrossOvers = new int[numGenerations];
        numCrossOvers = new int[numGenerations];
        bestMutations = new int[numGenerations];
        numMutations = new int[numGenerations];
        
        for(int i = 0; i < numGenerations; i++) {
            bestFitness[i] = 0.0;
            averageFitness[i] = 0.0;
            worstFitness[i] = 0.0;
            bestCrossOvers[i] = 0;
            numCrossOvers[i] = 0;
            bestMutations[i] = 0;
            numMutations[i] = 0;
        }
    }
    
    public void normalize(int testNum) {
        for(int i = 0; i < generations; i++) {
            bestFitness[i] = bestFitness[i] / testNum;
            averageFitness[i] = averageFitness[i] / testNum;
            worstFitness[i] = worstFitness[i] / testNum;
        }
    }
    
    public void addBestFitness(double fitness, int gen) {
        bestFitness[gen] += fitness;
    }
    
    public void addWorstFitness(double fitness, int gen) {
        worstFitness[gen] += fitness;
    }
    
    public void addAverageFitness(double fitness, int gen) {
        averageFitness[gen] += fitness;
    }
    
    public void addBestCrossOvers(int num, int gen) {
        bestCrossOvers[gen] += num;
    }
    
    public void addNumCrossOvers(int num, int gen) {
        numCrossOvers[gen] += num;
    }
    
    public void addBestMutations(int num, int gen) {
        bestMutations[gen] += num;
    }
    
    public void addNumMutations(int num, int gen) {
        numMutations[gen] += num;
    }
    
    public void logBestFitness(Logger logger) {
        for(int i = 0; i < generations; i++) {
            logger.log(i + " " + bestFitness[i]);
        }
    }
    
    public void logAverageFitness(Logger logger) {
        for(int i = 0; i < generations; i++) {
            logger.log(i + " " + averageFitness[i]);
        }
    }
    
    public void logWorstFitness(Logger logger) {
        for(int i = 0; i < generations; i++) {
            logger.log(i + " " + worstFitness[i]);
        }
    }
    
    public void logBestCross(Logger logger) {
        for(int i = 0; i < generations; i++) {
            double avg = (double)bestCrossOvers[i] / (double)numCrossOvers[i];
            logger.log(i + " " + avg);
        }
    }
    
    public void logBestMutations(Logger logger) {
        for(int i = 0; i < generations; i++) {
            double avg = (double)bestMutations[i] / (double)numMutations[i];
            logger.log(i + " " + avg);
        }
    }
}
