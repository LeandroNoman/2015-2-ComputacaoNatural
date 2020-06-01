package compnaturaltp1;

import genetictree.ArithmeticTree;
import java.util.ArrayList;

public class Tournament {
    private final int size;
    private Population population;
    private final ArithmeticTree[] randomedTrees;

    public Tournament(int size, Population population, ArrayList<Point> points) {
        this.size = size;
        this.population = population;
        randomedTrees = new ArithmeticTree[size];
    }
    
    public void setPopulation(Population population) {
        this.population = population;
    }
    
    public ArithmeticTree[] select(int qnt) {
        ArithmeticTree[] selected = new ArithmeticTree[qnt];
        
        for(int i = 0; i < qnt; i++) {
            getRandomTrees();
            selected[i] = getBestTree();
        }
        
        return selected;
    }
    
    private void getRandomTrees() {
        for(int i = 0; i < size; i++) {
            randomedTrees[i] = population.getRandomIndividual();
        }
    }
    
    private ArithmeticTree getBestTree() {
        int bestTree = 0;
        double bestFitness = Double.POSITIVE_INFINITY;
        
        for(int i = 0; i < size; i++) {
            if(randomedTrees[i].fitness() < bestFitness) {
                bestFitness = randomedTrees[i].fitness();
                bestTree = i;
            }
        }
        
        return randomedTrees[bestTree];
    }
    
}
