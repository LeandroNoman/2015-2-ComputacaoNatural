package compnaturaltp1;

import genetictree.ArithmeticTree;
import java.util.ArrayList;

public class GeneticProgramming {

    private final Parameters parameters;
    private final MyRandom rand;
    
    public GeneticProgramming(Parameters parameters) {
        this.parameters = parameters;
        rand = new MyRandom();
    }
    
    public void run(Logger logger, TestResults results) {
        Population population[] = new Population[2];
        ArithmeticTree[] trees;
        ArithmeticTree at1, at2;
        Tournament tournament;
        int dataDimension, popIndex, testNum;
        
        ColumnDataReader cdr = new ColumnDataReader(parameters.getFileName());
        ArrayList<Point> data;
        
        data = cdr.readData();
        //Erro na leitura do arquivo de entrada
        if(data == null) {
            return;
        }
        
        //Recupera a dimensao dos dados
        dataDimension = data.get(0).getDimension();
        
        //Loop para o numero de testes
        for(testNum = 0; testNum < parameters.getTestQuantity(); testNum++) {
            logger.log("**************");
            logger.log("Test " + (testNum + 1));
            //Inicializa a populacao
            population[0] = new Population(parameters.getMaxTreeSize(), dataDimension, parameters.getPopulationSize());
            //Cria a primeira geracao aleatoriamente
            population[0].createNew();
            population[1] = new Population(parameters.getMaxTreeSize(), dataDimension, parameters.getPopulationSize());

            popIndex = 0;

            tournament = new Tournament(parameters.getTournamentSize(), population[0], data);

            //Loop das geracoes
            for(int i = 0; i < parameters.getGenerationsNumber(); i++) {
                int numCrossOvers = 0, bestCrossOvers = 0, numMutation = 0, bestMutation = 0;
                if(parameters.getLogging()) {
                    logger.log("================");
                    logger.log("Generation " + i);
                }
                //Verifica o fitness dos idividuos
                population[popIndex].fitness(data);
                at1 = population[popIndex].getBestIndividual().deepClone();
                if(parameters.getLogging()) {
                    logger.log("Best fitness: " + at1.fitness());
                    results.addBestFitness(at1.fitness(), i);
                    logger.log("Average fitness: " + population[popIndex].getAverageFitness());
                    results.addAverageFitness(population[popIndex].getAverageFitness(), i);
                    logger.log("Worst fitness: " + population[popIndex].getWorstFitness());
                    results.addWorstFitness(population[popIndex].getWorstFitness(), i);
                }
                //Imprime o melhor fitness de cada geracao
                System.out.println("Geracao " + i + ": " + at1.fitness());
                popIndex = (popIndex + 1) % 2;
                population[popIndex].erease();
                //Elitismo
                population[popIndex].addIndividual(at1);

                while(!population[popIndex].isFull()) {
                    //Decide se vai fazer cruzamento ou mutacao
                    float randomFloat = rand.nextFloat();
                    if(randomFloat <= parameters.getProbabilityCrossOver()) {
                        double avgFitness;
                        numCrossOvers += 2;
                        //Seleciona os melhores para fazer cruzamento e mutacao
                        trees = tournament.select(2);
                        at1 = trees[0].deepClone();
                        at2 = trees[1].deepClone();

                        avgFitness = trees[0].fitness();
                        avgFitness += trees[0].fitness();
                        avgFitness = avgFitness / 2;

                        at1.crossOver(at2);

                        at1.calculateFitness(data);
                        at2.calculateFitness(data);

                        if(at1.fitness() < avgFitness) {
                            bestCrossOvers++;
                        }
                        if(at2.fitness() < avgFitness) {
                            bestCrossOvers++;
                        }

                        population[popIndex].addIndividual(at1);
                        population[popIndex].addIndividual(at2);
                    }
                    //Se for para fazer mutacao
                    else if(randomFloat <= parameters.getProbabilityCrossOver() + parameters.getProbabilityMutation()){
                        numMutation++;
                        trees = tournament.select(1);
                        at1 = trees[0].deepClone();
                        at1.mutation();
                        at1.calculateFitness(data);
                        
                        if(at1.fitness() < trees[0].fitness()) {
                            bestMutation++;
                        }
                        
                        population[popIndex].addIndividual(at1);
                    }
                    //Se nao for para fazer nenhum dos dois
                    else {
                        trees = tournament.select(1);
                        at1 = trees[0].deepClone();
                        population[popIndex].addIndividual(at1);
                    }
                }

                if(parameters.getLogging()) {
                    logger.log("Num of ind. gen. by crossovers: " + numCrossOvers);
                    logger.log("Ind. best than parents: " + bestCrossOvers);
                    results.addNumCrossOvers(numCrossOvers, i);
                    results.addBestCrossOvers(bestCrossOvers, i);
                    results.addNumMutations(numMutation, i);
                    results.addBestMutations(bestMutation, i);
                }

                tournament.setPopulation(population[popIndex]);
            }
            System.out.println(population[popIndex].getBestIndividual());
        }
        
    }
    
}
