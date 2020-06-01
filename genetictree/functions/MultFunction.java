package genetictree.functions;

import compnaturaltp1.Point;
import genetictree.FunctionNode;
import genetictree.TreeNode;

public class MultFunction extends FunctionNode {

    public MultFunction(TreeNode leftChildren, TreeNode rightChildren, int index) {
        super(leftChildren, rightChildren, index);
    }

    @Override
    public double evaluate(Point p) {
        return children[0].evaluate(p) * children[1].evaluate(p);
    }
    
    @Override
    public String toString() {
        return "(" + children[0].toString() + " * " + children[1].toString() + ")";
    }
    
    @Override
    public TreeNode copy() {
        return new MultFunction(null, null, index);
    }
    
    @Override
    public int getNodeType() {
        return TreeNode.MULTFUNCION;
    }
    
}
