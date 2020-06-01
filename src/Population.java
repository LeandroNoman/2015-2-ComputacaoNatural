package compnaturaltp1;

import genetictree.ArithmeticTree;
import java.util.ArrayList;

public class Population {
    
    private static final float populationSizeProbability[] = {
        0.02f, 0.05f, 0.25f, 0.20f, 0.15f, 0.15f, 0.10f, 0.08f};
    
    private final int maxIndividualSize;
    private final int populationSize;
    private final int dataDimension;
    private ArrayList<ArithmeticTree> population;
    private final MyRandom rand;

    public Population(int maxIndividualSize, int dataDimension, int populationSize) {
        this.maxIndividualSize = maxIndividualSize;
        this.dataDimension = dataDimension;
        this.populationSize = populationSize;
        rand = new MyRandom();
    }
    
    public void createNew() {
        population = new ArrayList<ArithmeticTree>(populationSize);
        boolean grow = true;
        float popSizeDice, probability;
        int generatedPopSize;
        
        for(int i = 0; i < populationSize; i++) {
            ArithmeticTree at = new ArithmeticTree(dataDimension, maxIndividualSize);
            popSizeDice = rand.nextFloat();
            generatedPopSize = 0;
            probability = 0.0f;
            
            //Loop para verificar qual o tamanho da populacao
            for(generatedPopSize = 0; generatedPopSize < populationSizeProbability.length; generatedPopSize++) {
                probability += populationSizeProbability[generatedPopSize];
                if(popSizeDice <= probability) {
                    break;
                }
            }
            
            if(grow) {
                at.createNewGrow(generatedPopSize);
                grow = false;
            }
            else {
                at.createNewFull(generatedPopSize);
                grow = true;
            }
            
            population.add(at);
        }
    }
    
    public void erease() {
        if(population == null) {
            population = new ArrayList<ArithmeticTree>(populationSize);
        }
        else {
            population.clear();
        }
    }
    
    public void addIndividual(ArithmeticTree at) {
        if(population.size() < populationSize) {
            population.add(at);
        }
    }
    
    public boolean isFull() {
        return population.size() == populationSize;
    }
    
    //Calcula e salva o fitness de cada individuo
    public void fitness(ArrayList<Point> points) {
        //Calcula o fitness de cada individuo
        for(int i = 0; i < populationSize; i++) {
            population.get(i).calculateFitness(points);
        }
    }
    
    public double getWorstFitness() {
        double worstFitness = 0.0;
        
        for(int i = 0; i < populationSize; i++) {
            if(population.get(i).fitness() > worstFitness) {
                worstFitness = population.get(i).fitness();
            }
        }
        
        return worstFitness;
    }
    
    public double getAverageFitness() {
        double averageFitness = 0.0;
        
        for(int i = 0; i < populationSize; i++) {
            averageFitness += population.get(i).fitness();
        }
        
        return averageFitness / populationSize;
    }
    
    public ArithmeticTree getRandomIndividual() {
        return population.get(rand.nextInt(populationSize));
    }
    
    public ArithmeticTree getBestIndividual() {
        double bestFitness = Double.POSITIVE_INFINITY;
        ArithmeticTree bestTree = null;
        
        for(int i = 0; i < populationSize; i++) {
            if(population.get(i).fitness() < bestFitness) {
                bestFitness = population.get(i).fitness();
                bestTree = population.get(i);
            }
        }
        
        return bestTree;
    }
    
}
