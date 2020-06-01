package genetictree.terminals;

import compnaturaltp1.Point;
import genetictree.TerminalNode;
import genetictree.TreeNode;

public class VariableTerminal extends TerminalNode {
    
    /*
     * Indica o indice da variavel.
     * Em um ambiente de 3 dimensoes, por exemplo,
     * 0 = X, 1 = Y e 2 = Z.
     *
     */
    private int variable;

    public VariableTerminal(int variavel, int index) {
        super(index);
        this.variable = variavel;
    }

    @Override
    public double evaluate(Point p) {
        return p.getValue(variable);
    }

    @Override
    public String toString() {
        return "X" + Integer.toString(variable);
    }
    
    @Override
    public TreeNode copy() {
        return new VariableTerminal(variable, index);
    }
    
    @Override
    public int getNodeType() {
        return variable;
    }
    
    public void changeTerminal(int index) {
        this.variable = index;
    }
    
}