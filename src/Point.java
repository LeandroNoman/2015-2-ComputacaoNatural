package compnaturaltp1;

public class Point {
    private final int dimention;
    private final double[] values;
    
    public Point(int dimension) {
        this.dimention = dimension;
        values = new double[dimension];
    }
    
    public double getValue(int index) {
        return values[index];
    }
    
    public int getDimension() {
        return dimention;
    }
    
    public void setValue(int index, double value) {
        values[index] = value;
    }
    
}
