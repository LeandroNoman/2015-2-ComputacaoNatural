package genetictree;

public abstract class FunctionNode extends TreeNode {

    public FunctionNode(TreeNode leftChildren, TreeNode rightChildren, int index) {
        super(index, true);
        children = new TreeNode[2];
        children[0] = leftChildren;
        children[1] = rightChildren;
    }
    
    @Override
    public TreeNode getNode(int i) {
        if(this.index == i) {
            return this;
        }
        else {
            TreeNode ret = getLeftChildren().getNode(i);
            if(ret != null) {
                return ret;
            }
            else {
                return getRightChildren().getNode(i);
            }
        }
    }
    
    @Override
    public void updateIndex() {
        IndexCounter ic = new IndexCounter();
        
        this.index = ic.getIndex();
        ic.incrementIndex();
        children[0].updateIndex(ic);
        children[1].updateIndex(ic);
    }
    
    @Override
    public void updateIndex(IndexCounter ic) {        
        this.index = ic.getIndex();
        ic.incrementIndex();
        children[0].updateIndex(ic);
        children[1].updateIndex(ic);
    }
    
    @Override
    public void setChildren(int childrenOrder, TreeNode children) {
        if(childrenOrder >= this.children.length) {
            return;
        }
        this.children[childrenOrder] = children;
    }
    
    @Override
    public int getNodesQuantity() {
        return 1 + children[0].getNodesQuantity() + children[1].getNodesQuantity();
    }
    
    @Override
    public void printIndex() {
        System.out.print(index + " ");
        children[0].printIndex();
        children[1].printIndex();
    }
    
    //Seta os filhos recebendo ambos na ordem correta
    public void setChildren(TreeNode leftChildren, TreeNode rightChildren) {
        children[0] = leftChildren;
        children[1] = rightChildren;
    }
   
    public TreeNode getLeftChildren() {
        return children[0];
    }
    
    public TreeNode getRightChildren() {
        return children[1];
    }
    
}
