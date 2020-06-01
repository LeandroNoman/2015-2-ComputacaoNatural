package compnaturaltp1;

public class CompNaturalTP1 {
    
    public static void main(String[] args) {
        int testCounter = 0;
        Logger logger = new Logger();
        TestResults results;
        
        Parameters parameters = new Parameters();
        parameters.setLogging(false);
        parameters.setMaxTreeSize(7);
        parameters.setPopulationSize(500);
        parameters.setTournamentSize(3);
        parameters.setGenerationsNumber(300);
        parameters.setProbabilityCrossOver(0.8f);
        parameters.setProbabilityMutation(0.1f);
        parameters.setTestQuantity(1);
        if(args.length != 0) {
            parameters.setFileName(args[0]);
        }
        else {
            parameters.setFileName("datasets/yacht_hydrodynamics.txt");
        }
        if(parameters.getLogging()) {
            logger.createNewLog("LogTest" + testCounter + ".txt");
            logParameters(logger, parameters);
        }
        results = new TestResults(parameters.getGenerationsNumber());
        
        GeneticProgramming gp = new GeneticProgramming(parameters);
        gp.run(logger, results);
        
        results.normalize(parameters.getTestQuantity());
        
        if(parameters.getLogging()) {
            logger.closeLog();
            logger.createNewLog("Best" + testCounter + ".txt");
            results.logBestFitness(logger);
            logger.closeLog();
            logger.createNewLog("Average" + testCounter + ".txt");
            results.logAverageFitness(logger);
            logger.closeLog();
            logger.createNewLog("Worst" + testCounter + ".txt");
            results.logWorstFitness(logger);
            logger.closeLog();
            logger.createNewLog("Cross" + testCounter + ".txt");
            results.logBestCross(logger);
            logger.closeLog();
            logger.createNewLog("Mutation" + testCounter + ".txt");
            results.logBestMutations(logger);
            logger.closeLog();
        }
    }
    
    private static void logParameters(Logger logger, Parameters parameters) {
        logger.log("Tournament size = " + parameters.getTournamentSize());
        logger.log("Population size = " + parameters.getPopulationSize());
        logger.log("Number of generations = " + parameters.getGenerationsNumber());
        logger.log("CrossOver Probability = " + parameters.getProbabilityCrossOver());
        logger.log("Mutation Probability = " + parameters.getProbabilityMutation());
    }
    
}
