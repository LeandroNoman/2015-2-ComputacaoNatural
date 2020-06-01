package genetictree.functions;

import compnaturaltp1.Point;
import genetictree.FunctionNode;
import genetictree.TreeNode;

public class DivFunction extends FunctionNode {

    public DivFunction(TreeNode leftChildren, TreeNode rightChildren, int index) {
        super(leftChildren, rightChildren, index);
    }

    @Override
    public double evaluate(Point p) {
        double divisor = children[1].evaluate(p);
        
        //Protected division
        if(divisor == 0.0) {
            return 1.0;
        }
        return children[0].evaluate(p) / divisor;
    }
    
    @Override
    public String toString() {
        return "(" + children[0].toString() + " / " + children[1].toString() + ")";
    }
    
    @Override
    public TreeNode copy() {
        return new DivFunction(null, null, index);
    }
    
    @Override
    public int getNodeType() {
        return TreeNode.DIVFUNCTION;
    }
    
}
