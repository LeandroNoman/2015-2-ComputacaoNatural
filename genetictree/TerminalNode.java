package genetictree;

public abstract class TerminalNode extends TreeNode {
    
    protected TerminalNode(int index) {
        super(index, false);
    }
    
    @Override
    public TreeNode getNode(int i) {
        if(this.index == i) {
            return this;
        }
        else {
            return null;
        }
    }
    
    @Override
    public void updateIndex() {
        this.index = 0;
    }
    
    @Override
    public void updateIndex(IndexCounter ic) {
        this.index = ic.getIndex();
        ic.incrementIndex();
    }
    
    @Override
    public void setChildren(int childrenOrder, TreeNode children) {
        return;
    }
    
    @Override
    public int getNodesQuantity() {
        return 1;
    }
    
    @Override
    public void printIndex() {
        System.out.print(index + " ");
    }
    
}
