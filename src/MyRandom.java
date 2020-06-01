package compnaturaltp1;

import java.util.Random;

/*
 * Singleton para a classe Random
 */
public class MyRandom {
    
    private static Random rand;

    public MyRandom() {
        if(rand == null) {
            rand = new Random();
        }
    }
    
    public int nextInt() {
        return rand.nextInt();
    }
    
    public int nextInt(int bound) {
        return rand.nextInt(bound);
    }
    
    public double nextDouble() {
        return rand.nextDouble();
    }
    
    public float nextFloat() {
        return rand.nextFloat();
    }
    
    public boolean nextBool() {
        return rand.nextBoolean();
    }
    
}
