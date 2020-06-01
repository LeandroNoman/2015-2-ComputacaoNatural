package genetictree.terminals;

import compnaturaltp1.Point;
import genetictree.TerminalNode;
import genetictree.TreeNode;

public class ConstantTerminal extends TerminalNode {
    
    private double value;

    public ConstantTerminal(double value, int index) {
        super(index);
        this.value = value;
    }

    @Override
    public double evaluate(Point p) {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
    
    @Override
    public TreeNode copy() {
        return new ConstantTerminal(value, index);
    }
    
    @Override
    public int getNodeType() {
        return TreeNode.CONSTANT;
    }
    
    public void changeValue(double value) {
        this.value = value;
    }
    
}
